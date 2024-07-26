/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.sanpham;

import java.awt.Color;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.SanPham;
import repository.SanPham.repoChiTietSanPham;
import service.CapNhatSanPham.CapNhatSanPhamSv;
import service.taikhoan.ServiceTaiKhoan;
import view.main.Main;
import view.until.combobox.ComboBoxSuggestion;

/**
 *
 * @author HUNGpYN
 */
public class CapNhatSanPham extends javax.swing.JDialog {

    private List<SanPham> lstSp = new ArrayList<>();
    private CapNhatSanPhamSv capNhatSanPhamSv = new CapNhatSanPhamSv();
    private repoChiTietSanPham rpChiTietSanPham = new repoChiTietSanPham();
    private service.taikhoan.ServiceTaiKhoan stk;
    private Color color2 = Color.decode("#101820");
    private Color color1 = Color.decode("#FEE715");
    private String selectedID;
    private GiaoDienSanPham gdsp = new GiaoDienSanPham();
    private boolean check;
    private Main main;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public CapNhatSanPham(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setFont();
        updateComboBoxes();
    }

    public void setSelectedID(String id) {
        this.selectedID = id;
        fillToTable();
    }

    void fillToTable() {
        if (selectedID == null || selectedID.isEmpty()) {
            System.out.println("selectedID is not set.");
            return;
        }

        lstSp = rpChiTietSanPham.getByID(selectedID);
        for (SanPham sp : lstSp) {
            txt_GiaBan.setText(String.valueOf(sp.getGiaChiTiet()));
            txt_KichThuocDa1.setText(String.valueOf(sp.getKichThuocDa()));
            txt_MaTrangSuc.setText(sp.getIDSanPham());
            txt_NgayKiemDinh.setText(String.valueOf(sp.getIDKIemDinh().getNgayKiemDinh()));
            txt_SoLuongDa.setText(String.valueOf(sp.getSoLuongDaQuy()));
            txt_TenTrangSuc.setText(sp.getTenSanPham());
            txt_TonKho.setText(String.valueOf(sp.getSoLuongTonKho()));
            txt_TyLe.setText(String.valueOf(sp.getIDChatLieu().getTyLe()));
            txt_TrongLuong.setText(String.valueOf(sp.getTrongLuong()));
            if (sp.isGioiTinh()) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            if (sp.isTrangThai()) {
                rdoKinhDoanh.setSelected(true);
            } else {
                rdoNgungKinhDoanh.setSelected(true);
            }

            for (int i = 0; i < cbos_Size.getItemCount(); i++) {
                if (cbos_Size.getItemAt(i) != null && cbos_Size.getItemAt(i).toString().equals(String.valueOf(sp.getIDSize().getSoSize()))) {
                    cbos_Size.setSelectedIndex(i);
                }
            }
            updateComboBox(cbo_ChatLieu, sp.getIDChatLieu().getTenChatLieu());
            updateComboBox(cbo_KiemDinh, sp.getIDKIemDinh().getDonViKiemDinh());
            updateComboBox(cbo_LoaiDa1, sp.getIDDaQuy().getTenDaQuy());
            updateComboBox(cbo_MauSac, sp.getIDMauSac().getChiTietMauSac());
            updateComboBox(cbo_NhaCungCap, sp.getIDNhaCungCap().getTenNhaCungCap());
            updateComboBox(cbo_PhanLoai, sp.getIDPhanLoai().getTenPhanLoai());

        }
    }

    void UpdateData() {
        // Lấy giá trị tồn kho từ trường nhập
        int soLuongTonKho;
        try {
            soLuongTonKho = Integer.parseInt(txt_TonKho.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn kho không hợp lệ!");
            return;
        }

        // Gọi phương thức update với tất cả các tham số, bao gồm soLuongTonKho
        int check = rpChiTietSanPham.update(
                txt_MaTrangSuc.getText(),
                capNhatSanPhamSv.checkCboPhanLoai(cbo_PhanLoai),
                capNhatSanPhamSv.checkCboKiemDinh(cbo_KiemDinh),
                capNhatSanPhamSv.checkCboMauSac(cbo_MauSac),
                capNhatSanPhamSv.checkCboSize(cbos_Size),
                txt_TenTrangSuc.getText(),
                rdoNam.isSelected(),
                capNhatSanPhamSv.checkCboChatLieu(cbo_ChatLieu),
                soLuongTonKho, // Sử dụng giá trị tồn kho đã lấy
                Float.parseFloat(txt_GiaBan.getText()), // Có thể cần thay đổi tùy vào cách xử lý
                "", // idGiamGia nếu không có giá trị
                capNhatSanPhamSv.checkCboNhaCungCap(cbo_NhaCungCap),
                Integer.parseInt(txt_SoLuongDa.getText()), // Có thể cần thay đổi tùy vào cách xử lý
                Float.parseFloat(txt_KichThuocDa1.getText()),
                Float.parseFloat(txt_TrongLuong.getText()),
                "", // Hình ảnh nếu không có giá trị
                capNhatSanPhamSv.checkCboLoaiDa(cbo_LoaiDa1),
                rdoKinhDoanh.isSelected()
        );

        // Kiểm tra kết quả và thông báo
        if (check != 0) {
            JOptionPane.showMessageDialog(this, "Thành Công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất Bại");
        }
    }

    public void updateComboBox(ComboBoxSuggestion cbos, String valueToSelect) {
        boolean isSelected = capNhatSanPhamSv.checkCbo(cbos, valueToSelect);
    }

    public void updateComboBoxes() {
        if (capNhatSanPhamSv == null) {
            System.err.println("capNhatSanPhamSv is null.");
            return;
        }

        if (cbo_KiemDinh != null) {
            capNhatSanPhamSv.addCboKiemDinh(cbo_KiemDinh);
        } else {
            System.err.println("cboKiemDinh is null.");
        }

        if (cbos_Size != null) {
            capNhatSanPhamSv.addCboSize(cbos_Size);
        } else {
            System.err.println("cboSize is null.");
        }

        if (cbo_PhanLoai != null) {
            capNhatSanPhamSv.addCboPhanLoai(cbo_PhanLoai);
        } else {
            System.err.println("cboPhanLoai is null.");
        }

        if (cbo_ChatLieu != null) {
            capNhatSanPhamSv.addCboChatLieu(cbo_ChatLieu);
        } else {
            System.err.println("cboChatLieu is null.");
        }

        if (cbo_MauSac != null) {
            capNhatSanPhamSv.addCboMauSac(cbo_MauSac);
        } else {
            System.err.println("cboMauSac is null.");
        }

        if (cbo_LoaiDa1 != null) {
            capNhatSanPhamSv.addCboLoaiDa(cbo_LoaiDa1);
        } else {
            System.err.println("cboLoaiDa is null.");
        }

        if (cbo_NhaCungCap != null) {
            capNhatSanPhamSv.addCboNhaCungCap(cbo_NhaCungCap);
        } else {
            System.err.println("cboNhaCungCap is null.");
        }
    }

//Font Màu
    void setFont() {
        setLocationRelativeTo(null);
        lbl_CapNhat.setForeground(color1);
        pnl_CapNhat.setBackground(color2);
        btn_ThemAnh.setColor1(Color.LIGHT_GRAY);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        pnl_CapNhat = new javax.swing.JPanel();
        lbl_CapNhat = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_MaTrangSuc = new view.until.textfield.TextFieldSuggestion();
        txt_TenTrangSuc = new view.until.textfield.TextFieldSuggestion();
        jLabel2 = new javax.swing.JLabel();
        txt_GiaBan = new view.until.textfield.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        rdoNam = new view.until.radiobutton.RadioButtonCustom();
        jLabel4 = new javax.swing.JLabel();
        rdoNu = new view.until.radiobutton.RadioButtonCustom();
        txt_TonKho = new view.until.textfield.TextFieldSuggestion();
        jLabel5 = new javax.swing.JLabel();
        txt_SoLuongDa = new view.until.textfield.TextFieldSuggestion();
        jLabel6 = new javax.swing.JLabel();
        txt_KichThuocDa1 = new view.until.textfield.TextFieldSuggestion();
        txt_TrongLuong = new view.until.textfield.TextFieldSuggestion();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbos_Size = new view.until.combobox.ComboBoxSuggestion();
        cbo_PhanLoai = new view.until.combobox.ComboBoxSuggestion();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbo_ChatLieu = new view.until.combobox.ComboBoxSuggestion();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_TyLe = new view.until.textfield.TextFieldSuggestion();
        cbo_LoaiDa1 = new view.until.combobox.ComboBoxSuggestion();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbo_MauSac = new view.until.combobox.ComboBoxSuggestion();
        btn_ThemAnh = new view.until.button.Button();
        rdoKinhDoanh = new view.until.radiobutton.RadioButtonCustom();
        jLabel16 = new javax.swing.JLabel();
        rdoNgungKinhDoanh = new view.until.radiobutton.RadioButtonCustom();
        cbo_NhaCungCap = new view.until.combobox.ComboBoxSuggestion();
        jLabel15 = new javax.swing.JLabel();
        btn_Huy = new view.until.button.Button();
        btn_CapNhat = new view.until.button.Button();
        cbo_KiemDinh = new view.until.combobox.ComboBoxSuggestion();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_NgayKiemDinh = new view.until.textfield.TextFieldSuggestion();
        lbl_HinhAnh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(780, 850));

        lbl_CapNhat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_CapNhat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_CapNhat.setText("Cập Nhật Trang Sức");

        javax.swing.GroupLayout pnl_CapNhatLayout = new javax.swing.GroupLayout(pnl_CapNhat);
        pnl_CapNhat.setLayout(pnl_CapNhatLayout);
        pnl_CapNhatLayout.setHorizontalGroup(
            pnl_CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_CapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_CapNhatLayout.setVerticalGroup(
            pnl_CapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_CapNhatLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_CapNhat)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel1.setText("Mã Trang Sức");

        txt_MaTrangSuc.setEditable(false);

        jLabel2.setText("Tên Trang Sức");

        jLabel3.setText("Giá Bán");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        jLabel4.setText("Giới Tính");

        buttonGroup1.add(rdoNu);
        rdoNu.setSelected(true);
        rdoNu.setText("Nữ");

        jLabel5.setText("Tồn Kho");

        jLabel6.setText("Số Lượng Đá Quý");

        jLabel8.setText("Trọng Lượng");

        jLabel7.setText("Kích Thước Đá Quý");

        cbos_Size.setEditable(false);

        cbo_PhanLoai.setEditable(false);

        jLabel10.setText("Phân Loại");

        jLabel9.setText("Size");

        cbo_ChatLieu.setEditable(false);

        jLabel12.setText("Chất Liệu");

        jLabel11.setText("Tỉ Lệ chất Liệu");

        txt_TyLe.setEditable(false);

        cbo_LoaiDa1.setEditable(false);

        jLabel13.setText("Loại Đá");

        jLabel14.setText("Màu Sắc");

        cbo_MauSac.setEditable(false);

        btn_ThemAnh.setText("Thêm Ảnh");
        btn_ThemAnh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_ThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemAnhActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoKinhDoanh);
        rdoKinhDoanh.setSelected(true);
        rdoKinhDoanh.setText("Kinh Doanh");
        rdoKinhDoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKinhDoanhActionPerformed(evt);
            }
        });

        jLabel16.setText("Trạng Thái");

        buttonGroup2.add(rdoNgungKinhDoanh);
        rdoNgungKinhDoanh.setText("Ngừng Kinh Doanh");

        cbo_NhaCungCap.setEditable(false);

        jLabel15.setText("Nhà Cung Cấp");

        btn_Huy.setText("Hủy");
        btn_Huy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });

        btn_CapNhat.setText("Cập Nhật");
        btn_CapNhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        cbo_KiemDinh.setEditable(false);

        jLabel17.setText("Kiểm Định");

        jLabel18.setText("Ngày Kiểm Định");

        txt_NgayKiemDinh.setEditable(false);

        lbl_HinhAnh.setText("jLabel19");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_CapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(btn_ThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_MaTrangSuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                        .addComponent(txt_GiaBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(137, 137, 137))
                                        .addComponent(txt_TonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(76, 76, 76)
                                .addComponent(txt_TrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdoKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(cbos_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txt_KichThuocDa1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel12)))
                                                .addComponent(cbo_ChatLieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbo_MauSac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txt_NgayKiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17)
                                    .addComponent(txt_TenTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(57, 57, 57)
                                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txt_SoLuongDa, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbo_PhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbo_LoaiDa1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbo_NhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbo_KiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnl_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_MaTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TenTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_TonKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_SoLuongDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_KichThuocDa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btn_ThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbos_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_PhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_ChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_LoaiDa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoNgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbo_NhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_KiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgayKiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoKinhDoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKinhDoanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoKinhDoanhActionPerformed

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
        Window window = SwingUtilities.getWindowAncestor(this);
        window.dispose();
    }//GEN-LAST:event_btn_HuyActionPerformed

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
        UpdateData();
        GiaoDienSanPham gdnv = GiaoDienSanPham.getInstance();
        if (gdnv != null) {
            gdnv.update();
        }
        this.dispose();
    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
//        Main.closeCurrentInstance();
//        Main newMain = new Main(1);
//        newMain.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void btn_ThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemAnhActionPerformed
        stk = new ServiceTaiKhoan();
        stk.chonAnh(lbl_HinhAnh);
    }//GEN-LAST:event_btn_ThemAnhActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CapNhatSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CapNhatSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CapNhatSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CapNhatSanPham.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CapNhatSanPham dialog = new CapNhatSanPham(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.button.Button btn_CapNhat;
    private view.until.button.Button btn_Huy;
    private view.until.button.Button btn_ThemAnh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private view.until.combobox.ComboBoxSuggestion cbo_ChatLieu;
    private view.until.combobox.ComboBoxSuggestion cbo_KiemDinh;
    private view.until.combobox.ComboBoxSuggestion cbo_LoaiDa1;
    private view.until.combobox.ComboBoxSuggestion cbo_MauSac;
    private view.until.combobox.ComboBoxSuggestion cbo_NhaCungCap;
    private view.until.combobox.ComboBoxSuggestion cbo_PhanLoai;
    private view.until.combobox.ComboBoxSuggestion cbos_Size;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_CapNhat;
    private javax.swing.JLabel lbl_HinhAnh;
    private javax.swing.JPanel pnl_CapNhat;
    private view.until.radiobutton.RadioButtonCustom rdoKinhDoanh;
    private view.until.radiobutton.RadioButtonCustom rdoNam;
    private view.until.radiobutton.RadioButtonCustom rdoNgungKinhDoanh;
    private view.until.radiobutton.RadioButtonCustom rdoNu;
    private view.until.textfield.TextFieldSuggestion txt_GiaBan;
    private view.until.textfield.TextFieldSuggestion txt_KichThuocDa1;
    private view.until.textfield.TextFieldSuggestion txt_MaTrangSuc;
    private view.until.textfield.TextFieldSuggestion txt_NgayKiemDinh;
    private view.until.textfield.TextFieldSuggestion txt_SoLuongDa;
    private view.until.textfield.TextFieldSuggestion txt_TenTrangSuc;
    private view.until.textfield.TextFieldSuggestion txt_TonKho;
    private view.until.textfield.TextFieldSuggestion txt_TrongLuong;
    private view.until.textfield.TextFieldSuggestion txt_TyLe;
    // End of variables declaration//GEN-END:variables
}
