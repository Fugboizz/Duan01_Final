/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.sanpham;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
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
            txt_TyLe.setText(String.valueOf(sp.getSoLuongTonKho()));
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
            ImageIcon imageIcon = new ImageIcon(sp.getHinhAnhSanPham());
            Image image = imageIcon.getImage(); // Chuyển đổi về đối tượng Image
            Image scaledImage = image.getScaledInstance(lbl_HinhAnh.getWidth() - 2, lbl_HinhAnh.getHeight() - 2, Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh
            imageIcon = new ImageIcon(scaledImage);
            lbl_HinhAnh.setIcon(imageIcon);
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
            soLuongTonKho = Integer.parseInt(txt_TyLe.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn kho không hợp lệ!");
            return;
        }
        String link = (String) lbl_HinhAnh.getClientProperty("imagepath");
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
                link, // Hình ảnh nếu không có giá trị
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
        txt_TenTrangSuc = new view.until.textfield.TextFieldSuggestion();
        jLabel2 = new javax.swing.JLabel();
        txt_GiaBan = new view.until.textfield.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_TyLe = new view.until.textfield.TextFieldSuggestion();
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
        cbo_LoaiDa1 = new view.until.combobox.ComboBoxSuggestion();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbo_MauSac = new view.until.combobox.ComboBoxSuggestion();
        btn_ThemAnh = new view.until.button.Button();
        jLabel16 = new javax.swing.JLabel();
        cbo_NhaCungCap = new view.until.combobox.ComboBoxSuggestion();
        jLabel15 = new javax.swing.JLabel();
        btn_Huy = new view.until.button.Button();
        btn_CapNhat = new view.until.button.Button();
        cbo_KiemDinh = new view.until.combobox.ComboBoxSuggestion();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_NgayKiemDinh = new view.until.textfield.TextFieldSuggestion();
        lbl_HinhAnh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        rdoKinhDoanh = new javax.swing.JRadioButton();
        rdoNgungKinhDoanh = new javax.swing.JRadioButton();
        txt_TonKho = new view.until.textfield.TextFieldSuggestion();
        txt_MaTrangSuc = new view.until.textfield.TextFieldSuggestion();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(780, 850));

        lbl_CapNhat.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
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
                .addContainerGap()
                .addComponent(lbl_CapNhat)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel1.setText("Mã Trang Sức");

        txt_TenTrangSuc.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel2.setText("Tên Trang Sức");

        txt_GiaBan.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setText("Giá Bán");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel4.setText("Giới Tính");

        txt_TyLe.setEditable(false);
        txt_TyLe.setFocusable(false);
        txt_TyLe.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel5.setText("Tồn Kho");

        txt_SoLuongDa.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel6.setText("Số Lượng Đá Quý");

        txt_KichThuocDa1.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        txt_TrongLuong.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel8.setText("Trọng Lượng");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel7.setText("Kích Thước Đá Quý");

        cbos_Size.setEditable(false);
        cbos_Size.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        cbo_PhanLoai.setEditable(false);
        cbo_PhanLoai.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel10.setText("Phân Loại");

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel9.setText("Size");

        cbo_ChatLieu.setEditable(false);
        cbo_ChatLieu.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        cbo_ChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_ChatLieuActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel12.setText("Chất Liệu");

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel11.setText("Tỉ Lệ chất Liệu");

        cbo_LoaiDa1.setEditable(false);
        cbo_LoaiDa1.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel13.setText("Loại Đá");

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel14.setText("Màu Sắc");

        cbo_MauSac.setEditable(false);
        cbo_MauSac.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        btn_ThemAnh.setText("Thêm Ảnh");
        btn_ThemAnh.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btn_ThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemAnhActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel16.setText("Trạng Thái");

        cbo_NhaCungCap.setEditable(false);

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel15.setText("Nhà Cung Cấp");

        btn_Huy.setText("Hủy");
        btn_Huy.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });

        btn_CapNhat.setText("Cập Nhật");
        btn_CapNhat.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        cbo_KiemDinh.setEditable(false);
        cbo_KiemDinh.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        cbo_KiemDinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_KiemDinhActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel17.setText("Kiểm Định");

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel18.setText("Ngày Kiểm Định");

        txt_NgayKiemDinh.setEditable(false);
        txt_NgayKiemDinh.setFocusable(false);
        txt_NgayKiemDinh.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txt_NgayKiemDinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayKiemDinhActionPerformed(evt);
            }
        });

        lbl_HinhAnh.setText("jLabel19");

        rdoNam.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        rdoNu.setText("Nữ");

        buttonGroup2.add(rdoKinhDoanh);
        rdoKinhDoanh.setText("Kinh Doanh");

        buttonGroup2.add(rdoNgungKinhDoanh);
        rdoNgungKinhDoanh.setText("Ngừng Kinh Doanh");

        txt_TonKho.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        txt_MaTrangSuc.setEditable(false);
        txt_MaTrangSuc.setFocusable(false);
        txt_MaTrangSuc.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_CapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoKinhDoanh)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoNgungKinhDoanh))
                                    .addComponent(txt_MaTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_TonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_KichThuocDa1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbos_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbo_ChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbo_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_NgayKiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(40, 40, 40)
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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbo_NhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbo_KiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(rdoNam)
                                            .addGap(63, 63, 63)
                                            .addComponent(rdoNu))
                                        .addComponent(txt_TrongLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_SoLuongDa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_TyLe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbo_LoaiDa1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                        .addComponent(cbo_PhanLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_TenTrangSuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242))
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
                            .addComponent(txt_TenTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoNam)
                                    .addComponent(rdoNu)))
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_TrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_TonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_SoLuongDa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_KichThuocDa1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_ThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbo_PhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cbos_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cbo_ChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbo_LoaiDa1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cbo_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_NhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoKinhDoanh)
                        .addComponent(rdoNgungKinhDoanh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_NgayKiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_KiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void cbo_ChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_ChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_ChatLieuActionPerformed

    private void txt_NgayKiemDinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgayKiemDinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayKiemDinhActionPerformed

    private void cbo_KiemDinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_KiemDinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_KiemDinhActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("view.until.sampletable.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
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
    private javax.swing.JRadioButton rdoKinhDoanh;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNgungKinhDoanh;
    private javax.swing.JRadioButton rdoNu;
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
