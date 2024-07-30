/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.banhang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.HoaDon;
import model.KhachHang;
import model.SanPham;
import model.Voucher;
import repository.KhachHang.repoKhachHang;
import repository.KhuyenMai.KhuyenMaiRepository;
import repository.SanPham.repoChiTietSanPham;
import repository.hoadon.RepositoryHoaDon;
import repository.hoadonchitiet.RepositoryHoaDonChiTiet;
import service.LichSuBanHang.LichSuBanHangService;
import service.banhang.ServiceBanHang;
import view.main.Main;
import view.until.swing.event.EventItem;
import view.until.swing.form.MainForm;
import model.HoaDonChiTiet;

/**
 *
 * @author HUNGpYN
 */
public class GiaoDienBanHang extends javax.swing.JPanel {

    private Main main;
    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang
    private repoChiTietSanPham rpct = new repoChiTietSanPham();
    private LichSuBanHangService qlLSBanHang = new LichSuBanHangService();
    private String IDHoaDon;
    private MainForm mainForm;
    private LichSuBanHangService lhsv;
    //repo tạo hóa đơn:
    private repository.hoadon.RepositoryHoaDon rpbh = new RepositoryHoaDon();
    // service bán hàng
    private service.banhang.ServiceBanHang sbh = new ServiceBanHang();
    //repo hóa đơn chi tiết
    private repository.hoadonchitiet.RepositoryHoaDonChiTiet rhdct = new RepositoryHoaDonChiTiet();
    //repo khách hàng
    private repository.KhachHang.repoKhachHang rpkh = new repoKhachHang();
    //repo voucher
    private repository.KhuyenMai.KhuyenMaiRepository kmrp = new KhuyenMaiRepository();
    // Khai báo biển để getter
    private String IDHoaDonBanHang;
    private String idSanPham;
    private int soLuong;

    // khai bao getter
    public String getIdSanPham() {
        return idSanPham;
    }

    public String getIdHoaDon() {
        if (tbl_HoaDonCho.getRowCount() == 1) {
            IDHoaDonBanHang = tbl_HoaDonCho.getValueAt(0, 1).toString();
            return IDHoaDonBanHang;
        }
        int index = tbl_HoaDonCho.getSelectedRow();
        if (index >= 0 && index < tbl_HoaDonCho.getRowCount()) {
            IDHoaDonBanHang = tbl_HoaDonCho.getValueAt(index, 1).toString();

        }
        return IDHoaDonBanHang;
    }

    public GiaoDienBanHang() {
        initComponents();
        setFont();
        init();
        System.out.println(rpct.getAll().size());
        qlLSBanHang.fillToTable(tbl_DanhSachHoaDon);
        qlLSBanHang.doubleClick(tbl_DanhSachHoaDon);

    }

    private void init() {
        mainForm = new MainForm();
        SanPhamPanel.setLayout(new BorderLayout());
        SanPhamPanel.add(mainForm);
        testData();
    }

    private void testData() {
        mainForm.setEvent(new EventItem() {
            @Override
            public void itemClick(Component com, SanPham sp) {
                idSanPham = sp.getIDSanPham();
                double giaGiam = sp.getGiaChiTiet() - (sp.getGiaChiTiet() * sp.getIDGiamGia().getTyLeGiamGia());
                String input = JOptionPane.showInputDialog("Nhập số lượng");
                try {
                    soLuong = Integer.parseInt(input);
                    if (readForm() != null) {
                        rhdct.create(readForm());
                        sbh.fillHoaDonChiTietBH(tbl_GioHang, getIdHoaDon());
                        sbh.fillHoaDonCho(tbl_HoaDonCho);
                        System.out.println("HdCT" + getIdHoaDon());
                        txt_TongTien.setText(String.valueOf(gettongTien()));
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Số lượng phải là số");
                }
                mainForm.setSelected(com);
            }
        });
        for (SanPham sp : rpct.getAll()) {
            if (sp.isTrangThai()) {
                mainForm.addItem(new SanPham(sp.getIDSanPham(), sp.getSoLuongTonKho(), sp.getGiaChiTiet(), sp.getIDGiamGia(), sp.getHinhAnhSanPham()));
            }
        }
    }

    void setFont() {
        btn_TimKiemHoaDon.setColor1(color2);
        btn_TimKiemHoaDon.setColor2(color1);
        panel_ThongTin.setBackground(color2);
        lbl_ThongTin.setForeground(color1);
    }

    // Đọc Dữ liệu hóa Đơn Chi Tiết
    HoaDonChiTiet readForm() {
        SanPham sp = new SanPham();
        sp.setIDSanPham(getIdSanPham());
        HoaDon hd = new HoaDon();
        hd.setIDHoaDon(getIdHoaDon());
        return new model.HoaDonChiTiet(sp, hd, soLuong);
    }

    // Lấy IdHoaDonChiTiet
    private String idHoaDonChiTiet;

    private String getidHoaDonChiTiet() {
        int i = tbl_GioHang.getSelectedRow();
        if (i >= 0) {
            String tenSP = tbl_GioHang.getValueAt(i, 1).toString();
            for (HoaDonChiTiet ct : rhdct.getData(getIdHoaDon())) {

                if (ct.getIDSanPham().getTenSanPham().equalsIgnoreCase(tenSP)) {
                    idHoaDonChiTiet = ct.getIDHoaDonChiTiet();
                }
            }
        }
        return idHoaDonChiTiet;
    }
    // lấy data tổng tiền
    private double tongTien;

    private double gettongTien() {
        for (HoaDon hd : rpbh.getAllHDC()) {
            if (hd.getIDHoaDon().equals(getIdHoaDon())) {
                tongTien = hd.getTongTienTRuoc();
            }

        }
        return tongTien;
    }

    void setDataThanhToan() {
        txt_MaHoaDon.setText(getIdHoaDon());
        txt_TongTien.setText(String.valueOf(gettongTien()));

        // Thay đổi thông tin khách hàng khi số điện thoại thay đổi
        txt_DienThoai.getDocument().addDocumentListener(new DocumentListener() {
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
                    String inputPhone = txt_DienThoai.getText();
                    List<KhachHang> customers = rpkh.getAll();
                    boolean found = false;

                    for (KhachHang kh : customers) {
                        if (inputPhone.equals(kh.getSoDienThoai())) {
                            txt_KhachHang.setText(kh.getHoTen());
                            txt_TichDiem.setText(String.valueOf(kh.getTichDiem()));
                            found = true;
                            break; // Đã tìm thấy khách hàng, không cần kiểm tra thêm
                        }
                    }

                    if (!found) {
                        // Nếu không tìm thấy khách hàng, xóa thông tin
                        txt_KhachHang.setText("");
                        txt_TichDiem.setText("");
                    }

                    updateThanhTien();
                });
            }
        });

        // Thay đổi thông tin voucher khi mã voucher thay đổi
        txt_Voucher1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateVoucherInfo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateVoucherInfo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Không xử lý thay đổi này vì không có sự thay đổi định dạng văn bản
            }

            private void updateVoucherInfo() {
                SwingUtilities.invokeLater(() -> {
                    String inputVoucher = txt_Voucher1.getText();
                    List<Voucher> vouchers = kmrp.getAll();
                    boolean found = false;

                    for (Voucher vc : vouchers) {
                        if (inputVoucher.equals(vc.getIDVoucher())) {
                            if (vc.isTrangThai()) {
                                lbl_TrangThaiVoucher.setText("Có thể sử dụng");
                                lbl_TrangThaiVoucher.setForeground(Color.GREEN);
                                double chietKhau = gettongTien() * vc.getTyLe() / 100;
                                txt_ChietKhau.setText(String.valueOf(chietKhau));
                            } else {
                                lbl_TrangThaiVoucher.setText("Hết hạn sử dụng");
                                lbl_TrangThaiVoucher.setForeground(Color.RED);
                                txt_ChietKhau.setText("0");
                            }

                            found = true;
                            break; // Đã tìm thấy voucher, không cần kiểm tra thêm
                        }
                    }

                    if (!found) {
                        // Nếu không tìm thấy voucher, xóa thông tin
                        lbl_TrangThaiVoucher.setText("Voucher không tồn tại");
                        lbl_TrangThaiVoucher.setForeground(Color.RED);
                        txt_ChietKhau.setText("0");
                    }

                    updateThanhTien();
                });
            }
        });

        // Thay đổi thành tiền khi trạng thái của checkbox thay đổi
        check_SuDung.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateThanhTien();
            }
        });

        // Cập nhật thành tiền lần đầu khi dữ liệu được thiết lập
        updateThanhTien();
    }

    void updateThanhTien() {
        double tongTien = gettongTien();
        double chietKhau = 0;
        double tichDiem = 0;

        // Cập nhật giá trị chiết khấu
        if (!txt_ChietKhau.getText().isEmpty()) {
            try {
                chietKhau = Double.parseDouble(txt_ChietKhau.getText());
            } catch (NumberFormatException e) {
                chietKhau = 0;
            }
        }

        // Cập nhật giá trị tích điểm nếu checkbox được chọn
        if (check_SuDung.isSelected() && !txt_TichDiem.getText().isEmpty()) {
            try {
                tichDiem = Double.parseDouble(txt_TichDiem.getText());
            } catch (NumberFormatException e) {
                tichDiem = 0;
            }
        }

        // Tính toán giá trị thành tiền
        double thanhTien = tongTien - chietKhau - tichDiem;
        txt_ThanhTien.setText(String.valueOf(thanhTien));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPaneCustom1 = new view.until.tabbedpane.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        comboBoxSuggestion2 = new view.until.combobox.ComboBoxSuggestion();
        jLabel6 = new javax.swing.JLabel();
        txt_TenTrangSuc = new view.until.textfield.TextFieldSuggestion();
        button1 = new view.until.button.Button();
        jLabel7 = new javax.swing.JLabel();
        SanPhamPanel = new view.until.swing.Background();
        jLabel1 = new javax.swing.JLabel();
        btn_TaoHoaDon = new view.until.button.Button();
        background2 = new view.until.swing.Background();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_GioHang = new view.until.table.TableDark();
        jLabel8 = new javax.swing.JLabel();
        btn_XoaCT = new view.until.button.Button();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btn_TimKH = new view.until.button.Button();
        jLabel10 = new javax.swing.JLabel();
        txt_KhachHang = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_TichDiem = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_TongTien = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_DienThoai = new view.until.textfield.TextFieldSuggestion();
        check_SuDung = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        txt_ThanhTien = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_ChietKhau = new javax.swing.JLabel();
        txt_Voucher1 = new view.until.textfield.TextFieldSuggestion();
        btn_ThanhToan = new view.until.button.Button();
        button2 = new view.until.button.Button();
        jLabel21 = new javax.swing.JLabel();
        txt_MaHoaDon = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbl_TrangThaiVoucher = new javax.swing.JLabel();
        background3 = new view.until.swing.Background();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_HoaDonCho = new view.until.table.TableDark();
        jPanel1 = new javax.swing.JPanel();
        panel_ThongTin = new javax.swing.JPanel();
        lbl_ThongTin = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboBoxSuggestion1 = new view.until.combobox.ComboBoxSuggestion();
        txt_BatDau = new view.until.textfield.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_KetThuc = new view.until.textfield.TextFieldSuggestion();
        btn_TimKiemHoaDon = new view.until.button.Button();
        btn_Excel = new view.until.button.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachHoaDon = new view.until.table.TableDark();

        tabbedPaneCustom1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Phân Loại");

        jLabel6.setText("Tên Trang Sức");

        button1.setText("Tìm Kiếm");
        button1.setColor1(new java.awt.Color(16, 24, 32));
        button1.setColor2(new java.awt.Color(254, 231, 21));
        button1.setDefaultCapable(false);
        button1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_TenTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Hóa Đơn Chờ");

        javax.swing.GroupLayout SanPhamPanelLayout = new javax.swing.GroupLayout(SanPhamPanel);
        SanPhamPanel.setLayout(SanPhamPanelLayout);
        SanPhamPanelLayout.setHorizontalGroup(
            SanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        SanPhamPanelLayout.setVerticalGroup(
            SanPhamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Trang Sức");

        btn_TaoHoaDon.setText("Tạo Hóa Đơn");
        btn_TaoHoaDon.setColor1(new java.awt.Color(16, 24, 32));
        btn_TaoHoaDon.setColor2(new java.awt.Color(254, 231, 21));
        btn_TaoHoaDon.setDefaultCapable(false);
        btn_TaoHoaDon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_TaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoHoaDonActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Hóa Đơn Chi Tiết");

        tbl_GioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên Trang Sức", "Số Lượng", "Giá Tiền", "Giá Giảm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbl_GioHang);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Thanh Toán");

        btn_XoaCT.setText("Xóa");
        btn_XoaCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaCTActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Số Điện Thoại");

        btn_TimKH.setText("button3");

        jLabel10.setText(" Khách Hàng:");

        txt_KhachHang.setText("Vũ Văn Hùng");

        jLabel12.setText("Tích Điểm:");

        txt_TichDiem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_TichDiem.setForeground(new java.awt.Color(255, 0, 0));
        txt_TichDiem.setText("10000");

        jLabel15.setText("Tổng Tiền:");

        txt_TongTien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_TongTien.setText("10000000");

        jLabel17.setText("Voucher:");

        check_SuDung.setText("Sử Dụng");

        jLabel18.setText("Chiết Khấu:");

        txt_ThanhTien.setText("jLabel19");

        jLabel20.setText("Thành Tiền:");

        txt_ChietKhau.setText("jLabel19");

        btn_ThanhToan.setText("Thanh Toán");
        btn_ThanhToan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThanhToanActionPerformed(evt);
            }
        });

        button2.setText("Hủy");
        button2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jLabel21.setText("Mã Hóa Đơn:");

        txt_MaHoaDon.setText("jLabel22");

        jLabel23.setForeground(new java.awt.Color(153, 153, 153));
        jLabel23.setText("_________________________________________________________________________");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbl_TrangThaiVoucher.setText("jLabel11");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_KhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(66, 66, 66))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_TichDiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(check_SuDung)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_DienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(41, 41, 41)
                                .addComponent(btn_TimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel15))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel17))))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel18))
                                .addGap(213, 213, 213))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_ThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_ChietKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_TrangThaiVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_Voucher1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_TongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_MaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(63, 63, 63))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txt_DienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_TimKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_KhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TichDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(check_SuDung))
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_MaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Voucher1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_TrangThaiVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_XoaCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_XoaCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbl_HoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Tạo", "Trạng Thái"
            }
        ));
        tbl_HoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_HoaDonCho);

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SanPhamPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_TaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_TaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SanPhamPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(background2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedPaneCustom1.addTab("Bán Hàng", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_ThongTin.setBackground(new java.awt.Color(255, 255, 255));
        lbl_ThongTin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_ThongTin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ThongTin.setText("Thông Tin Hóa Đơn");

        javax.swing.GroupLayout panel_ThongTinLayout = new javax.swing.GroupLayout(panel_ThongTin);
        panel_ThongTin.setLayout(panel_ThongTinLayout);
        panel_ThongTinLayout.setHorizontalGroup(
            panel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_ThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_ThongTinLayout.setVerticalGroup(
            panel_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        jLabel2.setText("Tên Nhân Viên");

        comboBoxSuggestion1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả" }));

        jLabel3.setText("Từ Ngày");

        jLabel4.setText("Đến Ngày");

        btn_TimKiemHoaDon.setText("Tìm Kiếm");
        btn_TimKiemHoaDon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btn_Excel.setBorder(null);
        btn_Excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/excel.png"))); // NOI18N

        tbl_DanhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Thời Gian", "Tên Nhân Viên", "Tên Khách Hàng", "Số Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_DanhSachHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DanhSachHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DanhSachHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txt_BatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_KetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(btn_TimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Excel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 89, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)
            .addComponent(panel_ThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panel_ThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_BatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_KetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Excel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Lịch Sử Bán Hàng", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoHoaDonActionPerformed
        rpbh = new RepositoryHoaDon();
        rpbh.create();
        JOptionPane.showMessageDialog(null, "Tạo hóa đơn thành công");
        sbh.fillHoaDonCho(tbl_HoaDonCho);    }//GEN-LAST:event_btn_TaoHoaDonActionPerformed

    private void tbl_DanhSachHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DanhSachHoaDonMouseClicked
        view.banhang.HoaDonChiTiet hdct = new view.banhang.HoaDonChiTiet(main, true);
        int index = tbl_DanhSachHoaDon.getSelectedRow();
        if (index >= 0) {
            IDHoaDon = tbl_DanhSachHoaDon.getValueAt(index, 1).toString();
            lhsv = new LichSuBanHangService();
            lhsv.fillToTableHDCT(hdct.getJTable(), IDHoaDon);
            hdct.setVisible(true);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_DanhSachHoaDonMouseClicked

    private void tbl_HoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HoaDonChoMouseClicked
        sbh.fillHoaDonChiTietBH(tbl_GioHang, getIdHoaDon());
        setDataThanhToan();
    }//GEN-LAST:event_tbl_HoaDonChoMouseClicked

    private void btn_XoaCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaCTActionPerformed
        rhdct.delete(getidHoaDonChiTiet());
        sbh.fillHoaDonCho(tbl_HoaDonCho);
        sbh.fillHoaDonChiTietBH(tbl_GioHang, getIdHoaDon());
        JOptionPane.showMessageDialog(null, "Xóa Thành Công");    }//GEN-LAST:event_btn_XoaCTActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        rpbh.delete(getIdHoaDon());
        sbh.fillHoaDonCho(tbl_HoaDonCho);
        sbh.fillHoaDonChiTietBH(tbl_GioHang, getIdHoaDon());
        JOptionPane.showMessageDialog(null, "Hủy Hóa Đơn Thành Công");    }//GEN-LAST:event_button2ActionPerformed
    String IDKhachHang;

    private String getIKhacHang() {
        for (KhachHang kh : rpkh.getAll()) {
            if (kh.getSoDienThoai().equals(txt_DienThoai.getText())) {
                IDKhachHang = kh.getIDKhachHang();
            }
        }
        return IDKhachHang;
    }
    String IDVoucer;

    private String getIDVoucher() {
        for (Voucher vc : kmrp.getAll()) {
            if (vc.getIDVoucher().equals(txt_Voucher1.getText()) && vc.isTrangThai()) {
                return vc.getIDVoucher();
            }
        }
        return null;
    }
    private void btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhToanActionPerformed
        rpbh.update(getIKhacHang(), getIDVoucher(), Double.parseDouble(txt_TongTien.getText()), Double.parseDouble(txt_ThanhTien.getText()), txt_MaHoaDon.getText());
        JOptionPane.showMessageDialog(null, "The thanh cong");
        sbh.fillHoaDonCho(tbl_HoaDonCho);
        sbh.fillHoaDonChiTietBH(tbl_GioHang, getIdHoaDon());    }//GEN-LAST:event_btn_ThanhToanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.swing.Background SanPhamPanel;
    private view.until.swing.Background background2;
    private view.until.swing.Background background3;
    private view.until.button.Button btn_Excel;
    private view.until.button.Button btn_TaoHoaDon;
    private view.until.button.Button btn_ThanhToan;
    private view.until.button.Button btn_TimKH;
    private view.until.button.Button btn_TimKiemHoaDon;
    private view.until.button.Button btn_XoaCT;
    private view.until.button.Button button1;
    private view.until.button.Button button2;
    private javax.swing.JCheckBox check_SuDung;
    private view.until.combobox.ComboBoxSuggestion comboBoxSuggestion1;
    private view.until.combobox.ComboBoxSuggestion comboBoxSuggestion2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_ThongTin;
    private javax.swing.JLabel lbl_TrangThaiVoucher;
    private javax.swing.JPanel panel_ThongTin;
    private view.until.tabbedpane.TabbedPaneCustom tabbedPaneCustom1;
    private view.until.table.TableDark tbl_DanhSachHoaDon;
    private view.until.table.TableDark tbl_GioHang;
    private view.until.table.TableDark tbl_HoaDonCho;
    private view.until.textfield.TextFieldSuggestion txt_BatDau;
    private javax.swing.JLabel txt_ChietKhau;
    private view.until.textfield.TextFieldSuggestion txt_DienThoai;
    private view.until.textfield.TextFieldSuggestion txt_KetThuc;
    private javax.swing.JLabel txt_KhachHang;
    private javax.swing.JLabel txt_MaHoaDon;
    private view.until.textfield.TextFieldSuggestion txt_TenTrangSuc;
    private javax.swing.JLabel txt_ThanhTien;
    private javax.swing.JLabel txt_TichDiem;
    private javax.swing.JLabel txt_TongTien;
    private view.until.textfield.TextFieldSuggestion txt_Voucher1;
    // End of variables declaration//GEN-END:variables
}
