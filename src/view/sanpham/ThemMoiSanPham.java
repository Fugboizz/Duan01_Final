/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.sanpham;

import java.awt.Color;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import model.SanPham;
import repository.SanPham.repoChiTietSanPham;
import service.CapNhatSanPham.CapNhatSanPhamSv;
import service.taikhoan.ServiceTaiKhoan;
import view.main.Main;
import view.until.combobox.ComboBoxSuggestion;

public class ThemMoiSanPham extends javax.swing.JDialog {

    private CapNhatSanPhamSv capNhatSanPhamSv = new CapNhatSanPhamSv();
    private repoChiTietSanPham rpChiTietSanPham = new repoChiTietSanPham();
//    lấy phương thức hình ảnh
    private service.taikhoan.ServiceTaiKhoan stk;
    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang

    public ThemMoiSanPham(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lbl_ThemMoi.setForeground(color1);
        pnl_ThemMoi.setBackground(color2);
        setLocationRelativeTo(null);
        btn_ThemAnh.setColor1(Color.LIGHT_GRAY);
        updateComboBoxes();
        fillAllTable();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDark2 = new view.until.table.TableDark();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        tabbedPaneCustom1 = new view.until.tabbedpane.TabbedPaneCustom();
        panel_ThemSanPham = new javax.swing.JPanel();
        pnl_ThemMoi = new javax.swing.JPanel();
        lbl_ThemMoi = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_TenTrangSuc = new view.until.textfield.TextFieldSuggestion();
        jLabel3 = new javax.swing.JLabel();
        txt_GiaBan = new view.until.textfield.TextFieldSuggestion();
        jLabel4 = new javax.swing.JLabel();
        rdoNu = new view.until.radiobutton.RadioButtonCustom();
        rdoNam = new view.until.radiobutton.RadioButtonCustom();
        jLabel5 = new javax.swing.JLabel();
        txt_TrongLuong = new view.until.textfield.TextFieldSuggestion();
        jLabel6 = new javax.swing.JLabel();
        txt_TonKho = new view.until.textfield.TextFieldSuggestion();
        jLabel7 = new javax.swing.JLabel();
        txt_SoLuongDa = new view.until.textfield.TextFieldSuggestion();
        jLabel8 = new javax.swing.JLabel();
        txt_KichThuocDa1 = new view.until.textfield.TextFieldSuggestion();
        jLabel9 = new javax.swing.JLabel();
        cbos_Size = new view.until.combobox.ComboBoxSuggestion();
        jLabel10 = new javax.swing.JLabel();
        cbo_PhanLoai = new view.until.combobox.ComboBoxSuggestion();
        jLabel11 = new javax.swing.JLabel();
        cbo_KiemDinh = new view.until.combobox.ComboBoxSuggestion();
        jLabel12 = new javax.swing.JLabel();
        cbo_MauSac = new view.until.combobox.ComboBoxSuggestion();
        jLabel13 = new javax.swing.JLabel();
        cbo_ChatLieu = new view.until.combobox.ComboBoxSuggestion();
        jLabel14 = new javax.swing.JLabel();
        cbo_LoaiDa1 = new view.until.combobox.ComboBoxSuggestion();
        jLabel15 = new javax.swing.JLabel();
        cbo_NhaCungCap = new view.until.combobox.ComboBoxSuggestion();
        jLabel16 = new javax.swing.JLabel();
        rdoNgungKinhDoanh = new view.until.radiobutton.RadioButtonCustom();
        rdoKinhDoanh = new view.until.radiobutton.RadioButtonCustom();
        btn_ThemAnh = new view.until.button.Button();
        btnHuy = new view.until.button.Button();
        btnLuu = new view.until.button.Button();
        lbl_HinhAnh = new javax.swing.JLabel();
        Panel_ThemThuocTinh = new javax.swing.JPanel();
        materialTabbed1 = new view.until.tabbedpane.MaterialTabbed();
        PanelPhanLoai = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_MaPhanLoai = new view.until.textfield.TextFieldSuggestion();
        jLabel17 = new javax.swing.JLabel();
        txt_TenPhanLoai = new view.until.textfield.TextFieldSuggestion();
        jLabel18 = new javax.swing.JLabel();
        rdo_HoatDongPL = new view.until.radiobutton.RadioButtonCustom();
        rdo_NgungHoatDongPL = new view.until.radiobutton.RadioButtonCustom();
        button4 = new view.until.button.Button();
        button5 = new view.until.button.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_PhanLoai = new view.until.table.TableDark();
        PanelChatLieu = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txt_MaChatLieu = new view.until.textfield.TextFieldSuggestion();
        jLabel21 = new javax.swing.JLabel();
        txt_TyLe = new view.until.textfield.TextFieldSuggestion();
        jLabel22 = new javax.swing.JLabel();
        txt_TenChatLieu = new view.until.textfield.TextFieldSuggestion();
        jLabel23 = new javax.swing.JLabel();
        rdo_HoatDong_CL = new view.until.radiobutton.RadioButtonCustom();
        rdo_NHoatDong_CL = new view.until.radiobutton.RadioButtonCustom();
        btn_CapNhatCL = new view.until.button.Button();
        btn_ThemCL = new view.until.button.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ChatLieu = new view.until.table.TableDark();
        PanelSize = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txt_SoSize = new view.until.textfield.TextFieldSuggestion();
        txt_MaSize = new view.until.textfield.TextFieldSuggestion();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        rdo_HSZ = new view.until.radiobutton.RadioButtonCustom();
        rdo_NSZ = new view.until.radiobutton.RadioButtonCustom();
        btn_CapNhatS = new view.until.button.Button();
        btn_ThemS = new view.until.button.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_Size = new view.until.table.TableDark();
        PanelNhaCungCap = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txt_SoDienThoai = new view.until.textfield.TextFieldSuggestion();
        jLabel28 = new javax.swing.JLabel();
        txt_MaNhaCungCap = new view.until.textfield.TextFieldSuggestion();
        jLabel29 = new javax.swing.JLabel();
        txt_Email = new view.until.textfield.TextFieldSuggestion();
        jLabel30 = new javax.swing.JLabel();
        bnt_ThemNhaCungCap = new view.until.button.Button();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_NhaCungCAp = new view.until.table.TableDark();
        btn_CapNhatNhaCungCap = new view.until.button.Button();
        jLabel31 = new javax.swing.JLabel();
        txt_TenNhaCungCap = new view.until.textfield.TextFieldSuggestion();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtA_DiaChi = new view.until.textarea.TextAreaSuggestion();
        jLabel32 = new javax.swing.JLabel();
        rdo_HNCC = new view.until.radiobutton.RadioButtonCustom();
        rdo_NNCC = new view.until.radiobutton.RadioButtonCustom();
        PanelMauSac = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txt_MaMau = new view.until.textfield.TextFieldSuggestion();
        jLabel34 = new javax.swing.JLabel();
        txt_TenMau = new view.until.textfield.TextFieldSuggestion();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_MauSac = new view.until.table.TableDark();
        btn_CapNhatNhaCungCap1 = new view.until.button.Button();
        bnt_ThemNhaCungCap1 = new view.until.button.Button();
        jLabel38 = new javax.swing.JLabel();
        rdo_HMS = new view.until.radiobutton.RadioButtonCustom();
        rdo_NMS = new view.until.radiobutton.RadioButtonCustom();
        PanelDaQuy = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_MaDQ = new view.until.textfield.TextFieldSuggestion();
        txt_TenDQ = new view.until.textfield.TextFieldSuggestion();
        jLabel41 = new javax.swing.JLabel();
        rdo_HDQ = new view.until.radiobutton.RadioButtonCustom();
        rdo_NDQ = new view.until.radiobutton.RadioButtonCustom();
        btn_CapNhatNhaCungCap2 = new view.until.button.Button();
        bnt_ThemNhaCungCap2 = new view.until.button.Button();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_DaQuy = new view.until.table.TableDark();
        PanelKiemDinh = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        txt_MaKD = new view.until.textfield.TextFieldSuggestion();
        jLabel43 = new javax.swing.JLabel();
        txt_DonViKD = new view.until.textfield.TextFieldSuggestion();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl_KiemDinh = new view.until.table.TableDark();
        btn_CapNhatNhaCungCap3 = new view.until.button.Button();
        bnt_ThemNhaCungCap3 = new view.until.button.Button();
        rdo_NKD = new view.until.radiobutton.RadioButtonCustom();
        rdo_HKD = new view.until.radiobutton.RadioButtonCustom();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txt_NgayKD = new view.until.textfield.TextFieldSuggestion();

        tableDark2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tableDark2);

        dateChooser1.setForeground(new java.awt.Color(102, 102, 102));
        dateChooser1.setTextRefernce(txt_NgayKD);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        tabbedPaneCustom1.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        panel_ThemSanPham.setBackground(new java.awt.Color(255, 255, 255));

        lbl_ThemMoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_ThemMoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ThemMoi.setText("Thêm Mới Trang Sức");

        javax.swing.GroupLayout pnl_ThemMoiLayout = new javax.swing.GroupLayout(pnl_ThemMoi);
        pnl_ThemMoi.setLayout(pnl_ThemMoiLayout);
        pnl_ThemMoiLayout.setHorizontalGroup(
            pnl_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_ThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_ThemMoiLayout.setVerticalGroup(
            pnl_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThemMoiLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_ThemMoi)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel2.setText("Tên Trang Sức");

        jLabel3.setText("Giá Bán");

        jLabel4.setText("Giới Tính");

        buttonGroup1.add(rdoNu);
        rdoNu.setSelected(true);
        rdoNu.setText("Nữ");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        jLabel5.setText("Tồn Kho");

        jLabel6.setText("Số Lượng Đá Quý");

        jLabel7.setText("Kích Thước Đá Quý");

        jLabel8.setText("Trọng Lượng");

        jLabel9.setText("Size");

        cbos_Size.setEditable(false);

        jLabel10.setText("Phân Loại");

        cbo_PhanLoai.setEditable(false);

        jLabel11.setText("Màu Sắc");

        cbo_KiemDinh.setEditable(false);

        jLabel12.setText("Chất Liệu");

        cbo_MauSac.setEditable(false);

        jLabel13.setText("Loại Đá");

        cbo_ChatLieu.setEditable(false);

        jLabel14.setText("Kiểm Định");

        cbo_LoaiDa1.setEditable(false);

        jLabel15.setText("Nhà Cung Cấp");

        cbo_NhaCungCap.setEditable(false);

        jLabel16.setText("Trạng Thái");

        buttonGroup2.add(rdoNgungKinhDoanh);
        rdoNgungKinhDoanh.setText("Ngừng Kinh Doanh");

        buttonGroup2.add(rdoKinhDoanh);
        rdoKinhDoanh.setSelected(true);
        rdoKinhDoanh.setText("Kinh Doanh");
        rdoKinhDoanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKinhDoanhActionPerformed(evt);
            }
        });

        btn_ThemAnh.setText("Thêm Ảnh");
        btn_ThemAnh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_ThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemAnhActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnLuu.setText("Lưu");
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        lbl_HinhAnh.setText("jLabel20");

        javax.swing.GroupLayout panel_ThemSanPhamLayout = new javax.swing.GroupLayout(panel_ThemSanPham);
        panel_ThemSanPham.setLayout(panel_ThemSanPhamLayout);
        panel_ThemSanPhamLayout.setHorizontalGroup(
            panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btn_ThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbo_KiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbo_LoaiDa1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(188, 188, 188)
                                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(57, 57, 57)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4))))
                        .addContainerGap(76, Short.MAX_VALUE))
                    .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                .addComponent(cbo_NhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_TonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_GiaBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txt_KichThuocDa1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbos_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbo_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel16)
                                            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                                .addComponent(rdoKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdoNgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ThemSanPhamLayout.createSequentialGroup()
                                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                                .addGap(66, 66, 66)
                                                .addComponent(txt_SoLuongDa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(cbo_ChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbo_PhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txt_TrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(30, 30, 30))))
                            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                                .addComponent(txt_TenTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(pnl_ThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_ThemSanPhamLayout.setVerticalGroup(
            panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                .addComponent(pnl_ThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TenTrangSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_TonKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoLuongDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ThemSanPhamLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_KichThuocDa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbos_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_PhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_ChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ThemSanPhamLayout.createSequentialGroup()
                        .addComponent(cbo_LoaiDa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel14))
                    .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoNgungKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoKinhDoanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_KiemDinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_NhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_ThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 56, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Trang Sức", panel_ThemSanPham);

        Panel_ThemThuocTinh.setBackground(new java.awt.Color(255, 255, 255));

        materialTabbed1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        PanelPhanLoai.setBackground(new java.awt.Color(255, 255, 255));
        PanelPhanLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelPhanLoaiMouseClicked(evt);
            }
        });

        jLabel1.setText("Mã Phân Loại");

        txt_MaPhanLoai.setEditable(false);

        jLabel17.setText("Tên Phân Loại");

        txt_TenPhanLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenPhanLoaiActionPerformed(evt);
            }
        });

        jLabel18.setText("Trạng Thái");

        buttonGroup3.add(rdo_HoatDongPL);
        rdo_HoatDongPL.setText("Kinh Doanh");

        buttonGroup3.add(rdo_NgungHoatDongPL);
        rdo_NgungHoatDongPL.setText("Ngừng Kinh Doanh");
        rdo_NgungHoatDongPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_NgungHoatDongPLActionPerformed(evt);
            }
        });

        button4.setText("Cập Nhật");
        button4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        button5.setText("Thêm");
        button5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        tbl_PhanLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Phân Loại", "Tên Phân Loại", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_PhanLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_PhanLoaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_PhanLoai);

        javax.swing.GroupLayout PanelPhanLoaiLayout = new javax.swing.GroupLayout(PanelPhanLoai);
        PanelPhanLoai.setLayout(PanelPhanLoaiLayout);
        PanelPhanLoaiLayout.setHorizontalGroup(
            PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(jLabel1)
                    .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                                .addComponent(rdo_HoatDongPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdo_NgungHoatDongPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(83, 83, 83)
                                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        PanelPhanLoaiLayout.setVerticalGroup(
            PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPhanLoaiLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HoatDongPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_NgungHoatDongPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(PanelPhanLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        materialTabbed1.addTab("Phân Loại", PanelPhanLoai);

        PanelChatLieu.setBackground(new java.awt.Color(255, 255, 255));
        PanelChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelChatLieuMouseClicked(evt);
            }
        });

        jLabel19.setText("Mã Chất Liệu");

        jLabel21.setText("Tên Chất Liệu");

        jLabel22.setText("Tỷ Lệ");

        jLabel23.setText("Trạng Thái");

        buttonGroup4.add(rdo_HoatDong_CL);
        rdo_HoatDong_CL.setText("Kinh Doanh");

        buttonGroup4.add(rdo_NHoatDong_CL);
        rdo_NHoatDong_CL.setText("Ngừng Kinh doanh");

        btn_CapNhatCL.setText("Cập Nhật");
        btn_CapNhatCL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_CapNhatCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatCLActionPerformed(evt);
            }
        });

        btn_ThemCL.setText("Thêm");
        btn_ThemCL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        tbl_ChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Chất Liệu", "Tên Chất Liệu", "Tỷ Lệ ", "Trạng Thái"
            }
        ));
        tbl_ChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChatLieuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_ChatLieu);

        javax.swing.GroupLayout PanelChatLieuLayout = new javax.swing.GroupLayout(PanelChatLieu);
        PanelChatLieu.setLayout(PanelChatLieuLayout);
        PanelChatLieuLayout.setHorizontalGroup(
            PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChatLieuLayout.createSequentialGroup()
                .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChatLieuLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21)
                            .addComponent(jLabel19)
                            .addComponent(jLabel23)
                            .addGroup(PanelChatLieuLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_CapNhatCL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_MaChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                            .addComponent(txt_TenChatLieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_TyLe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(PanelChatLieuLayout.createSequentialGroup()
                                            .addComponent(rdo_HoatDong_CL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(41, 41, 41)
                                            .addComponent(rdo_NHoatDong_CL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(PanelChatLieuLayout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(btn_ThemCL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        PanelChatLieuLayout.setVerticalGroup(
            PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChatLieuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HoatDong_CL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_NHoatDong_CL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(PanelChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_CapNhatCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Chất Liệu", PanelChatLieu);

        PanelSize.setBackground(new java.awt.Color(255, 255, 255));
        PanelSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelSizeMouseClicked(evt);
            }
        });

        jLabel24.setText("Số Size");

        txt_MaSize.setEditable(false);

        jLabel25.setText("Mã Size");

        jLabel26.setText("Trạng Thái");

        buttonGroup5.add(rdo_HSZ);
        rdo_HSZ.setText("Kinh Doanh");

        buttonGroup5.add(rdo_NSZ);
        rdo_NSZ.setText("Ngừng Kinh doanh");

        btn_CapNhatS.setText("Cập Nhật");
        btn_CapNhatS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_CapNhatS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatSActionPerformed(evt);
            }
        });

        btn_ThemS.setText("Thêm");
        btn_ThemS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_ThemS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemSActionPerformed(evt);
            }
        });

        tbl_Size.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Size", "Số Size", "Trạng Thái"
            }
        ));
        tbl_Size.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SizeMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_Size);

        javax.swing.GroupLayout PanelSizeLayout = new javax.swing.GroupLayout(PanelSize);
        PanelSize.setLayout(PanelSizeLayout);
        PanelSizeLayout.setHorizontalGroup(
            PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSizeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelSizeLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(btn_CapNhatS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelSizeLayout.createSequentialGroup()
                            .addComponent(rdo_HSZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(rdo_NSZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel24)
                        .addComponent(jLabel25)
                        .addComponent(jLabel26)
                        .addGroup(PanelSizeLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_MaSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_SoSize, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(99, 99, 99)
                .addComponent(btn_ThemS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        PanelSizeLayout.setVerticalGroup(
            PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSizeLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_SoSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HSZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_NSZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(PanelSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThemS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhatS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        materialTabbed1.addTab("Size", PanelSize);

        PanelNhaCungCap.setBackground(new java.awt.Color(255, 255, 255));
        PanelNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelNhaCungCapMouseClicked(evt);
            }
        });

        jLabel27.setText("Mã Nhà Cung Cấp");

        jLabel28.setText("Số Điện Thoại");

        txt_MaNhaCungCap.setEditable(false);

        jLabel29.setText("Email");

        txt_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EmailActionPerformed(evt);
            }
        });

        jLabel30.setText("Địa Chỉ");

        bnt_ThemNhaCungCap.setText("Thêm");
        bnt_ThemNhaCungCap.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        tbl_NhaCungCAp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Nhà Cung Cấp", "Tên Nhà Cung Cấp", "Email", "Số Điện Thoại", "Địa Chỉ", "Trạng Thái"
            }
        ));
        tbl_NhaCungCAp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhaCungCApMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_NhaCungCAp);

        btn_CapNhatNhaCungCap.setText("Cập Nhật");
        btn_CapNhatNhaCungCap.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel31.setText("Tên Nhà Cung Cấp");

        txtA_DiaChi.setColumns(20);
        txtA_DiaChi.setRows(5);
        jScrollPane5.setViewportView(txtA_DiaChi);

        jLabel32.setText("Trạng Thái");

        buttonGroup6.add(rdo_HNCC);
        rdo_HNCC.setText("Hoạt Động");

        buttonGroup6.add(rdo_NNCC);
        rdo_NNCC.setText("Ngừng Hoạt Động");

        javax.swing.GroupLayout PanelNhaCungCapLayout = new javax.swing.GroupLayout(PanelNhaCungCap);
        PanelNhaCungCap.setLayout(PanelNhaCungCapLayout);
        PanelNhaCungCapLayout.setHorizontalGroup(
            PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelNhaCungCapLayout.createSequentialGroup()
                        .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txt_TenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel30)
                                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(bnt_ThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)))
                        .addGap(150, 150, 150))
                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                        .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_MaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                        .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_CapNhatNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel32)
                                        .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(rdo_HNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(35, 35, 35)
                                            .addComponent(rdo_NNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PanelNhaCungCapLayout.setVerticalGroup(
            PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_MaNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_NNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                        .addGap(18, 107, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelNhaCungCapLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(PanelNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bnt_ThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_CapNhatNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        materialTabbed1.addTab("Nhà Cung Cấp", PanelNhaCungCap);

        PanelMauSac.setBackground(new java.awt.Color(255, 255, 255));
        PanelMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelMauSacMouseClicked(evt);
            }
        });

        jLabel33.setText("Mã Màu");

        txt_MaMau.setEditable(false);

        jLabel34.setText("Tên Màu");

        tbl_MauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Màu", "Tên Màu", "Trạng Thái"
            }
        ));
        tbl_MauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_MauSacMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_MauSac);

        btn_CapNhatNhaCungCap1.setText("Cập Nhật");
        btn_CapNhatNhaCungCap1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        bnt_ThemNhaCungCap1.setText("Thêm");
        bnt_ThemNhaCungCap1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel38.setText("Trạng Thái");

        buttonGroup7.add(rdo_HMS);
        rdo_HMS.setText("Hoạt Động");

        buttonGroup7.add(rdo_NMS);
        rdo_NMS.setText("Ngừng Hoạt Động");

        javax.swing.GroupLayout PanelMauSacLayout = new javax.swing.GroupLayout(PanelMauSac);
        PanelMauSac.setLayout(PanelMauSacLayout);
        PanelMauSacLayout.setHorizontalGroup(
            PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(PanelMauSacLayout.createSequentialGroup()
                .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMauSacLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel33)
                            .addComponent(jLabel38)
                            .addGroup(PanelMauSacLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_MaMau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_TenMau, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                                    .addGroup(PanelMauSacLayout.createSequentialGroup()
                                        .addComponent(rdo_HMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(rdo_NMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(PanelMauSacLayout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMauSacLayout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(bnt_ThemNhaCungCap1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelMauSacLayout.createSequentialGroup()
                                .addComponent(btn_CapNhatNhaCungCap1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(180, 180, 180)))))
                .addContainerGap(243, Short.MAX_VALUE))
        );
        PanelMauSacLayout.setVerticalGroup(
            PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMauSacLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_NMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(PanelMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnt_ThemNhaCungCap1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhatNhaCungCap1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        materialTabbed1.addTab("Màu Sắc", PanelMauSac);

        PanelDaQuy.setBackground(new java.awt.Color(255, 255, 255));
        PanelDaQuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelDaQuyMouseClicked(evt);
            }
        });

        jLabel39.setText("Mã Đá Quý");

        jLabel40.setText("Tên Đá Quý");

        jLabel41.setText("Trạng Thái");

        buttonGroup8.add(rdo_HDQ);
        rdo_HDQ.setText("Hoạt Động");

        buttonGroup8.add(rdo_NDQ);
        rdo_NDQ.setText("Ngừng Hoạt Động");

        btn_CapNhatNhaCungCap2.setText("Cập Nhật");
        btn_CapNhatNhaCungCap2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_CapNhatNhaCungCap2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatNhaCungCap2ActionPerformed(evt);
            }
        });

        bnt_ThemNhaCungCap2.setText("Thêm");
        bnt_ThemNhaCungCap2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        tbl_DaQuy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Đá Quý", "Tên Đá Quý", "Trạng Thái"
            }
        ));
        tbl_DaQuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DaQuyMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_DaQuy);

        javax.swing.GroupLayout PanelDaQuyLayout = new javax.swing.GroupLayout(PanelDaQuy);
        PanelDaQuy.setLayout(PanelDaQuyLayout);
        PanelDaQuyLayout.setHorizontalGroup(
            PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDaQuyLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel40)
                    .addComponent(jLabel39)
                    .addGroup(PanelDaQuyLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_MaDQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_TenDQ, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                            .addGroup(PanelDaQuyLayout.createSequentialGroup()
                                .addComponent(rdo_HDQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdo_NDQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PanelDaQuyLayout.createSequentialGroup()
                                        .addComponent(btn_CapNhatNhaCungCap2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(76, 76, 76)
                                        .addComponent(bnt_ThemNhaCungCap2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelDaQuyLayout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelDaQuyLayout.setVerticalGroup(
            PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDaQuyLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaDQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenDQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HDQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdo_NDQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(PanelDaQuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_CapNhatNhaCungCap2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnt_ThemNhaCungCap2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        materialTabbed1.addTab("Đá Quý", PanelDaQuy);

        PanelKiemDinh.setBackground(new java.awt.Color(255, 255, 255));
        PanelKiemDinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelKiemDinhMouseClicked(evt);
            }
        });

        jLabel42.setText("Mã Kiểm Định");

        jLabel43.setText("Đơn Vị");

        tbl_KiemDinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Kiểm Định", "Đơn Vị", "Ngày Kiểm Định", "Trạng Thái"
            }
        ));
        tbl_KiemDinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KiemDinhMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tbl_KiemDinh);

        btn_CapNhatNhaCungCap3.setText("Cập Nhật");
        btn_CapNhatNhaCungCap3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_CapNhatNhaCungCap3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatNhaCungCap3ActionPerformed(evt);
            }
        });

        bnt_ThemNhaCungCap3.setText("Thêm");
        bnt_ThemNhaCungCap3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        buttonGroup9.add(rdo_NKD);
        rdo_NKD.setText("Ngừng Hoạt Động");

        buttonGroup9.add(rdo_HKD);
        rdo_HKD.setSelected(true);
        rdo_HKD.setText("Hoạt Động");

        jLabel44.setText("Trạng Thái");

        jLabel45.setText("Ngày Kiểm Định ");

        javax.swing.GroupLayout PanelKiemDinhLayout = new javax.swing.GroupLayout(PanelKiemDinh);
        PanelKiemDinh.setLayout(PanelKiemDinhLayout);
        PanelKiemDinhLayout.setHorizontalGroup(
            PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
            .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel42)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_NgayKD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                    .addComponent(txt_DonViKD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_MaKD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(btn_CapNhatNhaCungCap3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(bnt_ThemNhaCungCap3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(rdo_HKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_NKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        PanelKiemDinhLayout.setVerticalGroup(
            PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MaKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_DonViKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_NgayKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel44)
                .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                        .addGap(24, 82, Short.MAX_VALUE)
                        .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_CapNhatNhaCungCap3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bnt_ThemNhaCungCap3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelKiemDinhLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(PanelKiemDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdo_NKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdo_HKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        materialTabbed1.addTab("Kiểm Định", PanelKiemDinh);

        javax.swing.GroupLayout Panel_ThemThuocTinhLayout = new javax.swing.GroupLayout(Panel_ThemThuocTinh);
        Panel_ThemThuocTinh.setLayout(Panel_ThemThuocTinhLayout);
        Panel_ThemThuocTinhLayout.setHorizontalGroup(
            Panel_ThemThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Panel_ThemThuocTinhLayout.setVerticalGroup(
            Panel_ThemThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ThemThuocTinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Thuộc Tính", Panel_ThemThuocTinh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoKinhDoanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKinhDoanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoKinhDoanhActionPerformed

    private void rdo_NgungHoatDongPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_NgungHoatDongPLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_NgungHoatDongPLActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button4ActionPerformed

    private void txt_TenPhanLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TenPhanLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TenPhanLoaiActionPerformed

    private void btn_CapNhatCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatCLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_CapNhatCLActionPerformed

    private void btn_CapNhatSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_CapNhatSActionPerformed

    private void btn_ThemSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemSActionPerformed

    private void txt_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmailActionPerformed

    private void btn_CapNhatNhaCungCap2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatNhaCungCap2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_CapNhatNhaCungCap2ActionPerformed

    private void btn_CapNhatNhaCungCap3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatNhaCungCap3ActionPerformed

    }//GEN-LAST:event_btn_CapNhatNhaCungCap3ActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        String link = (String) lbl_HinhAnh.getClientProperty("imagepath");
        rpChiTietSanPham.add(capNhatSanPhamSv.checkCboPhanLoai(cbo_PhanLoai),
                capNhatSanPhamSv.checkCboKiemDinh(cbo_KiemDinh),
                capNhatSanPhamSv.checkCboMauSac(cbo_MauSac),
                capNhatSanPhamSv.checkCboSize(cbos_Size), txt_TenTrangSuc.getText(),
                rdoNam.isSelected() ? true : false, capNhatSanPhamSv.checkCboChatLieu(cbo_ChatLieu),
                Integer.parseInt(txt_TonKho.getText()), Float.parseFloat(txt_GiaBan.getText()), "GG0001",
                capNhatSanPhamSv.checkCboNhaCungCap(cbo_NhaCungCap), Integer.parseInt(txt_SoLuongDa.getText()),
                Float.parseFloat(txt_KichThuocDa1.getText()), Float.parseFloat(txt_TrongLuong.getText()), link,
                capNhatSanPhamSv.checkCboLoaiDa(cbo_LoaiDa1), rdoKinhDoanh.isSelected() ? true : false);


    }//GEN-LAST:event_btnLuuActionPerformed
    public void fillAllTable() {
        capNhatSanPhamSv.fillToChatLieuTbl(tbl_ChatLieu);
        capNhatSanPhamSv.fillToPhanLoaiTbl(tbl_PhanLoai);
        capNhatSanPhamSv.fillToSizeTbl(tbl_Size);
        capNhatSanPhamSv.fillToDaQuyTbl(tbl_DaQuy);
        capNhatSanPhamSv.fillToMauSacTbl(tbl_MauSac);
        capNhatSanPhamSv.fillToNhaCungCapTbl(tbl_NhaCungCAp);
        capNhatSanPhamSv.fillToKiemDinhTbl(tbl_KiemDinh);

    }

    public void getDataOfTable() {
        capNhatSanPhamSv.getDataPhanLoai(tbl_PhanLoai, txt_MaPhanLoai, txt_TenPhanLoai, rdo_HoatDongPL, rdo_NgungHoatDongPL);

        capNhatSanPhamSv.getDataChatLieu(tbl_ChatLieu, txt_MaChatLieu, txt_TenChatLieu, txt_TyLe, rdo_HoatDong_CL, rdo_NHoatDong_CL);

        capNhatSanPhamSv.getDataDaQuy(tbl_DaQuy, txt_MaDQ, txt_TenDQ, rdo_HDQ, rdo_NDQ);

        capNhatSanPhamSv.getDataKiemDinh(tbl_KiemDinh, txt_MaKD, txt_DonViKD, txt_NgayKD, rdo_HKD, rdo_NKD);

        capNhatSanPhamSv.getDataMauSac(tbl_MauSac, txt_MaMau, txt_TenMau, rdo_HMS, rdo_NMS);

        capNhatSanPhamSv.getDataNhaCungCap(tbl_NhaCungCAp, txt_MaNhaCungCap, txt_TenNhaCungCap, txt_Email, txt_SoDienThoai, txtA_DiaChi, rdo_HNCC, rdo_NNCC);

        capNhatSanPhamSv.getDataSize(tbl_Size, txt_MaSize, txt_SoSize, rdo_HSZ, rdo_HSZ);

    }
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        Window window = SwingUtilities.getWindowAncestor(this);
        window.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Main.closeCurrentInstance();
        Main newMain = new Main(1);
        newMain.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void tbl_PhanLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PhanLoaiMouseClicked
        capNhatSanPhamSv.getDataPhanLoai(tbl_PhanLoai, txt_MaPhanLoai, txt_TenPhanLoai, rdo_HoatDongPL, rdo_NgungHoatDongPL);
    }//GEN-LAST:event_tbl_PhanLoaiMouseClicked

    private void PanelPhanLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelPhanLoaiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PanelPhanLoaiMouseClicked

    private void PanelChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelChatLieuMouseClicked
        capNhatSanPhamSv.getDataChatLieu(tbl_ChatLieu, txt_MaChatLieu, txt_TenChatLieu, txt_TyLe, rdo_HoatDong_CL, rdo_NHoatDong_CL);
    }//GEN-LAST:event_PanelChatLieuMouseClicked

    private void PanelSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelSizeMouseClicked
        capNhatSanPhamSv.getDataSize(tbl_Size, txt_MaSize, txt_SoSize, rdo_HSZ, rdo_HSZ);
    }//GEN-LAST:event_PanelSizeMouseClicked

    private void PanelNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelNhaCungCapMouseClicked
        capNhatSanPhamSv.getDataNhaCungCap(tbl_NhaCungCAp, txt_MaNhaCungCap, txt_TenNhaCungCap, txt_Email, txt_SoDienThoai, txtA_DiaChi, rdo_HNCC, rdo_NNCC);
    }//GEN-LAST:event_PanelNhaCungCapMouseClicked

    private void PanelMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMauSacMouseClicked
        capNhatSanPhamSv.getDataMauSac(tbl_MauSac, txt_MaMau, txt_TenMau, rdo_HMS, rdo_NMS);
    }//GEN-LAST:event_PanelMauSacMouseClicked

    private void PanelKiemDinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelKiemDinhMouseClicked
        capNhatSanPhamSv.getDataKiemDinh(tbl_KiemDinh, txt_MaKD, txt_DonViKD, txt_NgayKD, rdo_HKD, rdo_NKD);
    }//GEN-LAST:event_PanelKiemDinhMouseClicked

    private void PanelDaQuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelDaQuyMouseClicked
        capNhatSanPhamSv.getDataDaQuy(tbl_DaQuy, txt_MaDQ, txt_TenDQ, rdo_HDQ, rdo_NDQ);
    }//GEN-LAST:event_PanelDaQuyMouseClicked

    private void tbl_ChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChatLieuMouseClicked
        capNhatSanPhamSv.getDataChatLieu(tbl_ChatLieu, txt_MaChatLieu, txt_TenChatLieu, txt_TyLe, rdo_HoatDong_CL, rdo_NHoatDong_CL);
    }//GEN-LAST:event_tbl_ChatLieuMouseClicked

    private void tbl_SizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SizeMouseClicked
        capNhatSanPhamSv.getDataSize(tbl_Size, txt_MaSize, txt_SoSize, rdo_HSZ, rdo_HSZ);
    }//GEN-LAST:event_tbl_SizeMouseClicked

    private void tbl_NhaCungCApMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhaCungCApMouseClicked
        capNhatSanPhamSv.getDataNhaCungCap(tbl_NhaCungCAp, txt_MaNhaCungCap, txt_TenNhaCungCap, txt_Email, txt_SoDienThoai, txtA_DiaChi, rdo_HNCC, rdo_NNCC);
    }//GEN-LAST:event_tbl_NhaCungCApMouseClicked

    private void tbl_MauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_MauSacMouseClicked
        capNhatSanPhamSv.getDataMauSac(tbl_MauSac, txt_MaMau, txt_TenMau, rdo_HMS, rdo_NMS);
    }//GEN-LAST:event_tbl_MauSacMouseClicked

    private void tbl_DaQuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DaQuyMouseClicked
        capNhatSanPhamSv.getDataDaQuy(tbl_DaQuy, txt_MaDQ, txt_TenDQ, rdo_HDQ, rdo_NDQ);
    }//GEN-LAST:event_tbl_DaQuyMouseClicked

    private void tbl_KiemDinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KiemDinhMouseClicked
        capNhatSanPhamSv.getDataKiemDinh(tbl_KiemDinh, txt_MaKD, txt_DonViKD, txt_NgayKD, rdo_HKD, rdo_NKD);
    }//GEN-LAST:event_tbl_KiemDinhMouseClicked

    private void btn_ThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemAnhActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(ThemMoiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemMoiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemMoiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemMoiSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemMoiSanPham dialog = new ThemMoiSanPham(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel PanelChatLieu;
    private javax.swing.JPanel PanelDaQuy;
    private javax.swing.JPanel PanelKiemDinh;
    private javax.swing.JPanel PanelMauSac;
    private javax.swing.JPanel PanelNhaCungCap;
    private javax.swing.JPanel PanelPhanLoai;
    private javax.swing.JPanel PanelSize;
    private javax.swing.JPanel Panel_ThemThuocTinh;
    private view.until.button.Button bnt_ThemNhaCungCap;
    private view.until.button.Button bnt_ThemNhaCungCap1;
    private view.until.button.Button bnt_ThemNhaCungCap2;
    private view.until.button.Button bnt_ThemNhaCungCap3;
    private view.until.button.Button btnHuy;
    private view.until.button.Button btnLuu;
    private view.until.button.Button btn_CapNhatCL;
    private view.until.button.Button btn_CapNhatNhaCungCap;
    private view.until.button.Button btn_CapNhatNhaCungCap1;
    private view.until.button.Button btn_CapNhatNhaCungCap2;
    private view.until.button.Button btn_CapNhatNhaCungCap3;
    private view.until.button.Button btn_CapNhatS;
    private view.until.button.Button btn_ThemAnh;
    private view.until.button.Button btn_ThemCL;
    private view.until.button.Button btn_ThemS;
    private view.until.button.Button button4;
    private view.until.button.Button button5;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private view.until.combobox.ComboBoxSuggestion cbo_ChatLieu;
    private view.until.combobox.ComboBoxSuggestion cbo_KiemDinh;
    private view.until.combobox.ComboBoxSuggestion cbo_LoaiDa1;
    private view.until.combobox.ComboBoxSuggestion cbo_MauSac;
    private view.until.combobox.ComboBoxSuggestion cbo_NhaCungCap;
    private view.until.combobox.ComboBoxSuggestion cbo_PhanLoai;
    private view.until.combobox.ComboBoxSuggestion cbos_Size;
    private com.raven.datechooser.DateChooser dateChooser1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lbl_HinhAnh;
    private javax.swing.JLabel lbl_ThemMoi;
    private view.until.tabbedpane.MaterialTabbed materialTabbed1;
    private javax.swing.JPanel panel_ThemSanPham;
    private javax.swing.JPanel pnl_ThemMoi;
    private view.until.radiobutton.RadioButtonCustom rdoKinhDoanh;
    private view.until.radiobutton.RadioButtonCustom rdoNam;
    private view.until.radiobutton.RadioButtonCustom rdoNgungKinhDoanh;
    private view.until.radiobutton.RadioButtonCustom rdoNu;
    private view.until.radiobutton.RadioButtonCustom rdo_HDQ;
    private view.until.radiobutton.RadioButtonCustom rdo_HKD;
    private view.until.radiobutton.RadioButtonCustom rdo_HMS;
    private view.until.radiobutton.RadioButtonCustom rdo_HNCC;
    private view.until.radiobutton.RadioButtonCustom rdo_HSZ;
    private view.until.radiobutton.RadioButtonCustom rdo_HoatDongPL;
    private view.until.radiobutton.RadioButtonCustom rdo_HoatDong_CL;
    private view.until.radiobutton.RadioButtonCustom rdo_NDQ;
    private view.until.radiobutton.RadioButtonCustom rdo_NHoatDong_CL;
    private view.until.radiobutton.RadioButtonCustom rdo_NKD;
    private view.until.radiobutton.RadioButtonCustom rdo_NMS;
    private view.until.radiobutton.RadioButtonCustom rdo_NNCC;
    private view.until.radiobutton.RadioButtonCustom rdo_NSZ;
    private view.until.radiobutton.RadioButtonCustom rdo_NgungHoatDongPL;
    private view.until.tabbedpane.TabbedPaneCustom tabbedPaneCustom1;
    private view.until.table.TableDark tableDark2;
    private view.until.table.TableDark tbl_ChatLieu;
    private view.until.table.TableDark tbl_DaQuy;
    private view.until.table.TableDark tbl_KiemDinh;
    private view.until.table.TableDark tbl_MauSac;
    private view.until.table.TableDark tbl_NhaCungCAp;
    private view.until.table.TableDark tbl_PhanLoai;
    private view.until.table.TableDark tbl_Size;
    private view.until.textarea.TextAreaSuggestion txtA_DiaChi;
    private view.until.textfield.TextFieldSuggestion txt_DonViKD;
    private view.until.textfield.TextFieldSuggestion txt_Email;
    private view.until.textfield.TextFieldSuggestion txt_GiaBan;
    private view.until.textfield.TextFieldSuggestion txt_KichThuocDa1;
    private view.until.textfield.TextFieldSuggestion txt_MaChatLieu;
    private view.until.textfield.TextFieldSuggestion txt_MaDQ;
    private view.until.textfield.TextFieldSuggestion txt_MaKD;
    private view.until.textfield.TextFieldSuggestion txt_MaMau;
    private view.until.textfield.TextFieldSuggestion txt_MaNhaCungCap;
    private view.until.textfield.TextFieldSuggestion txt_MaPhanLoai;
    private view.until.textfield.TextFieldSuggestion txt_MaSize;
    private view.until.textfield.TextFieldSuggestion txt_NgayKD;
    private view.until.textfield.TextFieldSuggestion txt_SoDienThoai;
    private view.until.textfield.TextFieldSuggestion txt_SoLuongDa;
    private view.until.textfield.TextFieldSuggestion txt_SoSize;
    private view.until.textfield.TextFieldSuggestion txt_TenChatLieu;
    private view.until.textfield.TextFieldSuggestion txt_TenDQ;
    private view.until.textfield.TextFieldSuggestion txt_TenMau;
    private view.until.textfield.TextFieldSuggestion txt_TenNhaCungCap;
    private view.until.textfield.TextFieldSuggestion txt_TenPhanLoai;
    private view.until.textfield.TextFieldSuggestion txt_TenTrangSuc;
    private view.until.textfield.TextFieldSuggestion txt_TonKho;
    private view.until.textfield.TextFieldSuggestion txt_TrongLuong;
    private view.until.textfield.TextFieldSuggestion txt_TyLe;
    // End of variables declaration//GEN-END:variables
}
