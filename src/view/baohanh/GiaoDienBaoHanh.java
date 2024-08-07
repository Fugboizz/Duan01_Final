/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.baohanh;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.BaoHanh;
import model.HoaDonChiTiet;
import model.KhachHang;
import view.form.JTableHeader;
import view.khuyenmai.TableKhuyenMai;

/**
 *
 * @author HUNGpYN
 */
public class GiaoDienBaoHanh extends javax.swing.JPanel {

    private service.BaoHanh.GiaoDienBaoHanhService qlBaoHanh = new service.BaoHanh.GiaoDienBaoHanhService();
    private repository.BaoHanh.repoBaoHanh rpBH = new repository.BaoHanh.repoBaoHanh();
    private repository.KhachHang.repoKhachHang rpkh = new repository.KhachHang.repoKhachHang();
    private view.main.Main main;
    private String IDSanPhamDaChon;
    private String IdHDCT;
    private String idSeri;
    static {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("view.until.sampletable.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
    }

    public GiaoDienBaoHanh() {
        initComponents();
        init();
        qlBaoHanh.fillToTable(tbl_DanhSachBH);
        timData();
    }

    private void init() {
        panel_BaoHanh.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");

        TableKhuyenMai tkm = new TableKhuyenMai();
        tkm.init(tbl_DanhSachBH, scrollBH);
        tbl_DanhSachBH.getTableHeader().setDefaultRenderer(new JTableHeader(tbl_DanhSachBH));
        tbl_DanhSachBH.getTableHeader().setReorderingAllowed(false);

    }

    public void clickHienThi() {
        int index = tbl_DanhSachBH.getSelectedRow();
        if (index != -1) {
            String maBaoHanh = tbl_DanhSachBH.getValueAt(index, 1).toString();
            String tenKhachHang = tbl_DanhSachBH.getValueAt(index, 2).toString();
            String soDienThoai = tbl_DanhSachBH.getValueAt(index, 3).toString();
            String diaChi = tbl_DanhSachBH.getValueAt(index, 4).toString();
            String tenSanPham = tbl_DanhSachBH.getValueAt(index, 5).toString();
            String ghiChu = tbl_DanhSachBH.getValueAt(index, 6).toString();
            String ngayTao = tbl_DanhSachBH.getValueAt(index, 7).toString();
            String trangThaiStr = tbl_DanhSachBH.getValueAt(index, 8).toString();

            if (trangThaiStr.equalsIgnoreCase("Đã Nhận")) {
                rdo_DaNhan.setSelected(true);
                rdo_DaTra.setSelected(false);
            } else if (trangThaiStr.equalsIgnoreCase("Đã Trả")) {
                rdo_DaNhan.setSelected(false);
                rdo_DaTra.setSelected(true);
            }

            lbl_maBaoHanh.setText(maBaoHanh);
            txt_TenKhachHang.setText(tenKhachHang);
            txt_SoDienThoai.setText(soDienThoai);
            txt_DiaChi.setText(diaChi);
            txt_TenSanPham.setText(tenSanPham);
            txt_GhiChu.setText(ghiChu);

            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = inputFormat.parse(ngayTao);
                
            } catch (ParseException e) {
                e.printStackTrace();
                
            }
        } else {
            System.out.println("Không có hàng nào được chọn trong bảng Bảo Hành.");
        }
    }

    public void lamMoi() {
        buttonGroup1.clearSelection();
        lbl_maBaoHanh.setText("");
        txt_TenKhachHang.setText("");
        txt_SoDienThoai.setText("");
        txt_DiaChi.setText("");
        txt_TenSanPham.setText("");
        txt_GhiChu.setText("");

    }

    private List<BaoHanh> getListSP() {
        return rpBH.getSanPhamBaoHanh(txt_SoDienThoai.getText());
    }

    void timData() {
        txt_SoDienThoai.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCustomerInfo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCustomerInfo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Không xử lý thay đổi này vì không có sự thay đổi định dạng văn bản
            }

            private void updateCustomerInfo() {
                SwingUtilities.invokeLater(() -> {
                    String inputPhone = txt_SoDienThoai.getText();
                    List<KhachHang> customers = rpkh.getAll();
                    boolean found = false;

                    for (KhachHang kh : customers) {
                        if (inputPhone.equals(kh.getSoDienThoai())) {
                            txt_TenKhachHang.setText(kh.getHoTen());
                            txt_DiaChi.setText(kh.getDiaChi());
                            found = true;
                            lbl_KhachHangKhongTonTai.setText("");
                            break; // Đã tìm thấy khách hàng, không cần kiểm tra thêm
                        }
                    }

                    if (!found) {
                        // Nếu không tìm thấy khách hàng, xóa thông tin
                        lbl_KhachHangKhongTonTai.setText("Số điện thoại không tồn tại");
                    }
                });
            }
        });
    }
     public void datNgayTao(String NgayTao) {
        // Giả sử có JTextField hoặc JLabel để hiển thị tên sản phẩm
        txt_TenSanPham.setText(NgayTao);
    }
    public void datTenSanPham(String tenSanPham) {
        // Giả sử có JTextField hoặc JLabel để hiển thị tên sản phẩm
        txt_TenSanPham.setText(tenSanPham);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dateChooserYeuCau = new com.raven.datechooser.DateChooser();
        dateChooserHenTra = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panel_BaoHanh = new view.until.swing.Background();
        txt_TimSoDienThoai = new view.until.textfield.TextFieldSuggestion();
        jLabel2 = new javax.swing.JLabel();
        txt_TuNgay = new view.until.textfield.TextFieldSuggestion();
        textFieldSuggestion3 = new view.until.textfield.TextFieldSuggestion();
        button1 = new view.until.button.Button();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        scrollBH = new javax.swing.JScrollPane();
        tbl_DanhSachBH = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        button6 = new view.until.button.Button();
        background1 = new view.until.swing.Background();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_TenKhachHang = new view.until.textfield.TextFieldSuggestion();
        txt_SoDienThoai = new view.until.textfield.TextFieldSuggestion();
        txt_TenSanPham = new view.until.textfield.TextFieldSuggestion();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_DiaChi = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_GhiChu = new javax.swing.JTextArea();
        rdo_DaNhan = new javax.swing.JRadioButton();
        rdo_DaTra = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        btn_LamMoi = new view.until.button.Button();
        btn_Them = new view.until.button.Button();
        btn_CapNhat = new view.until.button.Button();
        btn_ChonSanPham = new view.until.button.Button();
        jLabel13 = new javax.swing.JLabel();
        lbl_maBaoHanh = new javax.swing.JLabel();
        lbl_KhachHangKhongTonTai = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_NgayYeuCau = new view.until.textfield.TextFieldSuggestion();

        dateChooserYeuCau.setTextRefernce(txt_NgayYeuCau);

        jPanel1.setBackground(new java.awt.Color(16, 24, 32));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 231, 21));
        jLabel1.setText("Danh Sách Bảo Hành");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18))
        );

        jLabel2.setText("Số Điện Thoại");

        button1.setText("Tìm Kiếm");

        jLabel3.setText("Từ Ngày");

        jLabel4.setText("Đến Ngày");

        scrollBH.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_DanhSachBH.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbl_DanhSachBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Bảo Hành", "Tên Khách Hàng", "Số Điện Thoại", "Địa Chỉ", "Sản Phẩm Bảo Hành", "Ghi Chú", "Thời Gian Tạo", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_DanhSachBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DanhSachBHMouseClicked(evt);
            }
        });
        scrollBH.setViewportView(tbl_DanhSachBH);
        if (tbl_DanhSachBH.getColumnModel().getColumnCount() > 0) {
            tbl_DanhSachBH.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        button6.setText("Thêm");
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_BaoHanhLayout = new javax.swing.GroupLayout(panel_BaoHanh);
        panel_BaoHanh.setLayout(panel_BaoHanhLayout);
        panel_BaoHanhLayout.setHorizontalGroup(
            panel_BaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_BaoHanhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_BaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollBH)
                    .addGroup(panel_BaoHanhLayout.createSequentialGroup()
                        .addGroup(panel_BaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TimSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(50, 50, 50)
                        .addGroup(panel_BaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt_TuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(panel_BaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(panel_BaoHanhLayout.createSequentialGroup()
                                .addComponent(textFieldSuggestion3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_BaoHanhLayout.setVerticalGroup(
            panel_BaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_BaoHanhLayout.createSequentialGroup()
                .addGroup(panel_BaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_BaoHanhLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6))
                    .addGroup(panel_BaoHanhLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_BaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_BaoHanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_TimSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldSuggestion3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBH, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel5.setText("Tên Khách Hàng:");

        jLabel7.setText("Số Điện Thoại:");

        jLabel8.setText("Địa Chỉ:");

        jLabel9.setText("Ghi Chú:");

        jLabel10.setText("Mã Sản Phẩm:");

        txt_DiaChi.setColumns(20);
        txt_DiaChi.setRows(5);
        jScrollPane1.setViewportView(txt_DiaChi);

        txt_GhiChu.setColumns(20);
        txt_GhiChu.setRows(5);
        jScrollPane2.setViewportView(txt_GhiChu);

        buttonGroup1.add(rdo_DaNhan);
        rdo_DaNhan.setText("Đã Nhận");

        buttonGroup1.add(rdo_DaTra);
        rdo_DaTra.setText("Đã Trả");

        jLabel11.setText("Trạng Thái:");

        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_CapNhat.setText("Cập Nhật");
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        btn_ChonSanPham.setText("...");
        btn_ChonSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChonSanPhamActionPerformed(evt);
            }
        });

        jLabel13.setText("Mã Bảo Hành");

        lbl_maBaoHanh.setText("---");

        lbl_KhachHangKhongTonTai.setForeground(new java.awt.Color(255, 51, 51));

        jLabel14.setText("Ngày Yêu Cầu");

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_maBaoHanh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_TenKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                                        .addComponent(lbl_KhachHangKhongTonTai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(background1Layout.createSequentialGroup()
                                        .addComponent(rdo_DaNhan)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdo_DaTra))
                                    .addComponent(txt_NgayYeuCau, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(background1Layout.createSequentialGroup()
                                        .addComponent(txt_TenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_ChonSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_TenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ChonSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(rdo_DaNhan)
                                    .addComponent(rdo_DaTra)))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(162, 162, 162))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lbl_maBaoHanh))
                        .addGap(15, 15, 15)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(lbl_KhachHangKhongTonTai)
                                .addGap(30, 30, 30)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_NgayYeuCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)))))
                .addGap(18, 18, 18)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_BaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_BaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
      
       qlBaoHanh.addBaoHanh(txt_TenKhachHang, idSeri, IDSanPhamDaChon, IdHDCT, txt_GhiChu, txt_NgayYeuCau, rdo_DaTra);
       
       qlBaoHanh.fillToTable(tbl_DanhSachBH);
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
       qlBaoHanh.CapNhatBaoHanh(lbl_maBaoHanh, txt_GhiChu, rdo_DaTra);
       qlBaoHanh.fillToTable(tbl_DanhSachBH);
    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button6ActionPerformed

    private void btn_ChonSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChonSanPhamActionPerformed
        
        DanhSachSPBaoHanh dsBH = new DanhSachSPBaoHanh(main, true);
        dsBH.fillToTableSPBH(getListSP());
        dsBH.setVisible(true);
        IDSanPhamDaChon = dsBH.SanPhamDCBaoHanh();
        IdHDCT = dsBH.HDCTBaoHanh();
        idSeri = dsBH.SeriBaoHanh();
        datTenSanPham(IDSanPhamDaChon); 
    }//GEN-LAST:event_btn_ChonSanPhamActionPerformed

    private void tbl_DanhSachBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DanhSachBHMouseClicked
        clickHienThi();
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_DanhSachBHMouseClicked

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        lamMoi();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.swing.Background background1;
    private view.until.button.Button btn_CapNhat;
    private view.until.button.Button btn_ChonSanPham;
    private view.until.button.Button btn_LamMoi;
    private view.until.button.Button btn_Them;
    private view.until.button.Button button1;
    private view.until.button.Button button6;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.raven.datechooser.DateChooser dateChooserHenTra;
    private com.raven.datechooser.DateChooser dateChooserYeuCau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_KhachHangKhongTonTai;
    private javax.swing.JLabel lbl_maBaoHanh;
    private view.until.swing.Background panel_BaoHanh;
    private javax.swing.JRadioButton rdo_DaNhan;
    private javax.swing.JRadioButton rdo_DaTra;
    private javax.swing.JScrollPane scrollBH;
    private javax.swing.JTable tbl_DanhSachBH;
    private view.until.textfield.TextFieldSuggestion textFieldSuggestion3;
    private javax.swing.JTextArea txt_DiaChi;
    private javax.swing.JTextArea txt_GhiChu;
    private view.until.textfield.TextFieldSuggestion txt_NgayYeuCau;
    private view.until.textfield.TextFieldSuggestion txt_SoDienThoai;
    private view.until.textfield.TextFieldSuggestion txt_TenKhachHang;
    private view.until.textfield.TextFieldSuggestion txt_TenSanPham;
    private view.until.textfield.TextFieldSuggestion txt_TimSoDienThoai;
    private view.until.textfield.TextFieldSuggestion txt_TuNgay;
    // End of variables declaration//GEN-END:variables
}
