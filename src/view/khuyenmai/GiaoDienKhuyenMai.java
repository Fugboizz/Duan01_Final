/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.khuyenmai;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import model.Voucher;
import raven.modal.ModalDialog;
import raven.modal.Toast;
import raven.modal.component.SimpleModalBorder;
import view.main.Main;
import view.until.sampletable.CheckBoxTableHeaderRenderer;
import view.until.sampletable.TableHeaderAlignment;

public class GiaoDienKhuyenMai extends javax.swing.JPanel {

    private service.KhuyenMai.GiaoDienKhuyenMaiService qlKm = new service.KhuyenMai.GiaoDienKhuyenMaiService();
    private repository.KhuyenMai.KhuyenMaiRepository rpKM = new repository.KhuyenMai.KhuyenMaiRepository();
    private Main main;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang

    static {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("view.until.sampletable.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
    }

    public GiaoDienKhuyenMai() {
        initComponents();
        setFont();
        qlKm.fillToTable(tbl_Voucher);
        init();
    }

    private void init() {
        panel_SPGiamGia.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        pnl_ThemGiamGia.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        TableKhuyenMai tkm = new TableKhuyenMai();
        tkm.init(tbl_SPGiamGia, scrollSPGG);
        tkm.init(tbl_GiamGia, scroll_GiamGia);
        tbl_SPGiamGia.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(tbl_SPGiamGia, 0));
        tbl_SPGiamGia.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(tbl_SPGiamGia));
        tbl_GiamGia.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(tbl_SPGiamGia));

    }

    void setFont() {
        txt_NgayBatDau.setText("dd-MM-yyyy");
        txt_NgayKetThuc.setText("dd-MM-yyyy");
        btn_TimKiemV.setColor1(color2);
        btn_Them.setColor1(color2);
        btn_CapNhat.setColor1(color2);
        btn_LamMoi.setColor1(color2);
        btn_Them.setForeground(color1);
        btn_LamMoi.setForeground(color1);
        btn_CapNhat.setForeground(color1);

        //Giảm Giá
        btn_TimKiemSP.setColor1(color2);
        btn_ThemSPGiamGia.setColor1(color2);
        btn_XoaSPGiamGia.setColor1(color2);
        btn_ThemSPGiamGia.setForeground(color1);
        btn_XoaSPGiamGia.setForeground(color1);

    }

    public void lamMoi() {
        lbl_MaVoucher.setText("--/~/--");
        txt_TenVourcher.setText("");
        txt_TyLe.setText("");
        txt_NgayBatDau.setText("dd-MM-yyyy");
        txt_NgayKetThuc.setText("dd-MM-yyyy");
        buttonGroup1.clearSelection();

    }

    public Voucher redFrom() {
        boolean trangThai = rdo_HoatDong.isSelected();
        if (!rdo_HoatDong.isSelected()) {
            trangThai = false;

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String stringBatDau = txt_NgayBatDau.getText();
        String stringKetThuc = txt_NgayKetThuc.getText();

        Date dateBatDau = null;
        Date dateKetThuc = null;
        try {
            dateBatDau = dateFormat.parse(stringBatDau);
            dateKetThuc = dateFormat.parse(stringKetThuc);
        } catch (ParseException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ, có thể thông báo lỗi cho người dùng
            JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ. Vui lòng nhập lại.");
            return null;
        }

        float tyLe = 0;
        try {
            tyLe = Float.parseFloat(txt_TyLe.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ, có thể thông báo lỗi cho người dùng
            JOptionPane.showMessageDialog(null, "Tỷ lệ không hợp lệ. Vui lòng nhập lại.");
            return null;
        }

        return new Voucher(lbl_MaVoucher.getText(), txt_TenVourcher.getText(), tyLe, dateBatDau, dateKetThuc, trangThai);
    }

    public void clickHienThi() {
        int index = tbl_Voucher.getSelectedRow();
        Voucher vc = rpKM.getAll().get(index);
        String ngayBatDauFormatted = sdf.format(vc.getNgayBatDau());
        String ngayKetThucFormatted = sdf.format(vc.getNgayKetThuc());
        lbl_MaVoucher.setText(vc.getIDVoucher());
        txt_TenVourcher.setText(vc.getTenVoucher());
        txt_TyLe.setText(String.valueOf(vc.getTyLe()));
        txt_NgayBatDau.setText(String.valueOf(ngayBatDauFormatted));
        txt_NgayKetThuc.setText(String.valueOf(ngayKetThucFormatted));
        if (vc.isTrangThai()) {
            rdo_HoatDong.setSelected(true);
            rdo_DungHoatDong.setSelected(false);
        } else {
            rdo_HoatDong.setSelected(false);
            rdo_DungHoatDong.setSelected(true);
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        tabbedPaneCustom1 = new view.until.tabbedpane.TabbedPaneCustom();
        jPanel1 = new javax.swing.JPanel();
        txt_NgayBatDau = new view.until.textfield.TextFieldSuggestion();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Voucher = new view.until.table.TableDark();
        rdo_HoatDong = new view.until.radiobutton.RadioButtonCustom();
        rdo_DungHoatDong = new view.until.radiobutton.RadioButtonCustom();
        jLabel6 = new javax.swing.JLabel();
        textFieldSuggestion1 = new view.until.textfield.TextFieldSuggestion();
        btn_TimKiemV = new view.until.button.Button();
        btn_LamMoi = new view.until.button.Button();
        btn_CapNhat = new view.until.button.Button();
        btn_Them = new view.until.button.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_TenVourcher = new view.until.textfield.TextFieldSuggestion();
        lbl_MaVoucher = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_TyLe = new view.until.textfield.TextFieldSuggestion();
        txt_NgayKetThuc = new view.until.textfield.TextFieldSuggestion();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panel_SPGiamGia = new javax.swing.JPanel();
        scrollSPGG = new javax.swing.JScrollPane();
        tbl_SPGiamGia = new javax.swing.JTable();
        comboBoxSuggestion1 = new view.until.combobox.ComboBoxSuggestion();
        jLabel13 = new javax.swing.JLabel();
        btn_TimKiemSP = new view.until.button.Button();
        jLabel12 = new javax.swing.JLabel();
        lbl_tilte = new javax.swing.JLabel();
        txt_MaGiamGiaSP = new view.until.textfield.TextFieldSuggestion();
        btn_ThemSPGiamGia = new view.until.button.Button();
        btn_XoaSPGiamGia = new view.until.button.Button();
        pnl_ThemGiamGia = new javax.swing.JPanel();
        btn_Them1 = new view.until.button.Button();
        btn_CapNhat1 = new view.until.button.Button();
        btn_LamMoi1 = new view.until.button.Button();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_NgayKetThucG = new view.until.textfield.TextFieldSuggestion();
        txt_NgayBatDauG = new view.until.textfield.TextFieldSuggestion();
        jLabel14 = new javax.swing.JLabel();
        txt_TenGiamGia = new view.until.textfield.TextFieldSuggestion();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_TenGiamGia2 = new view.until.textfield.TextFieldSuggestion();
        lbl_MaGiamGia = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        scroll_GiamGia = new javax.swing.JScrollPane();
        tbl_GiamGia = new javax.swing.JTable();
        textFieldSuggestion2 = new view.until.textfield.TextFieldSuggestion();
        comboBoxSuggestion2 = new view.until.combobox.ComboBoxSuggestion();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btn_TimKiemSP1 = new view.until.button.Button();

        tabbedPaneCustom1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txt_NgayBatDau.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel9.setText("Trạng Thái");

        tbl_Voucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Voucher", "Tên Voucher", "Tỷ Lệ(%)", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Voucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_VoucherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Voucher);

        buttonGroup1.add(rdo_HoatDong);
        rdo_HoatDong.setText("Hoạt Động");
        rdo_HoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_HoatDongActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_DungHoatDong);
        rdo_DungHoatDong.setSelected(true);
        rdo_DungHoatDong.setText("Dừng Hoạt Động");
        rdo_DungHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_DungHoatDongActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 0, 0));
        jLabel6.setText("Danh Sách Voucher");

        btn_TimKiemV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N

        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_CapNhat.setText("Cập Nhật");
        btn_CapNhat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        btn_Them.setText("Thêm");
        btn_Them.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Mã Voucher:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Tên Voucher:");

        txt_TenVourcher.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        lbl_MaVoucher.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lbl_MaVoucher.setText("--/~/--");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Ngày Bắt Đầu:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Ngày Kết Thúc:");

        txt_TyLe.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_TyLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TyLeActionPerformed(evt);
            }
        });

        txt_NgayKetThuc.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Tỷ Lệ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TenVourcher, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_MaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel4)
                        .addGap(67, 67, 67)
                        .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_HoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(rdo_DungHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(199, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textFieldSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiemV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(15, 15, 15))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_MaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_TenVourcher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(rdo_HoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdo_DungHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textFieldSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiemV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneCustom1.addTab("Voucher", jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        scrollSPGG.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tbl_SPGiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SELECT", "#", "Mã Giảm Giá", "Mã Sản Phẩm", "Tên Sản Phẩm", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Tỷ Lệ Giảm Giá (%)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SPGiamGia.getTableHeader().setReorderingAllowed(false);
        scrollSPGG.setViewportView(tbl_SPGiamGia);
        if (tbl_SPGiamGia.getColumnModel().getColumnCount() > 0) {
            tbl_SPGiamGia.getColumnModel().getColumn(0).setMaxWidth(50);
            tbl_SPGiamGia.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel13.setText("Loại Trang Sức");

        btn_TimKiemSP.setBorder(null);
        btn_TimKiemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btn_TimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemSPActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 0));
        jLabel12.setText("Trang Sức Giảm Giá");

        lbl_tilte.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbl_tilte.setText("Mã Giảm Giá");

        btn_ThemSPGiamGia.setText("Thêm");
        btn_ThemSPGiamGia.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N

        btn_XoaSPGiamGia.setText("Xóa");
        btn_XoaSPGiamGia.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N

        javax.swing.GroupLayout panel_SPGiamGiaLayout = new javax.swing.GroupLayout(panel_SPGiamGia);
        panel_SPGiamGia.setLayout(panel_SPGiamGiaLayout);
        panel_SPGiamGiaLayout.setHorizontalGroup(
            panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_SPGiamGiaLayout.createSequentialGroup()
                .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollSPGG))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_SPGiamGiaLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_MaGiamGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(59, 59, 59)
                        .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_tilte)
                            .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                                .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_TimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_ThemSPGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btn_XoaSPGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        panel_SPGiamGiaLayout.setVerticalGroup(
            panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lbl_tilte))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_MaGiamGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_SPGiamGiaLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panel_SPGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ThemSPGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_XoaSPGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_SPGiamGiaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_TimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(10, 10, 10)
                .addComponent(scrollSPGG, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl_ThemGiamGia.setBackground(new java.awt.Color(255, 255, 255));

        btn_Them1.setText("Thêm");
        btn_Them1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_Them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Them1ActionPerformed(evt);
            }
        });

        btn_CapNhat1.setText("Cập Nhật");
        btn_CapNhat1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_CapNhat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhat1ActionPerformed(evt);
            }
        });

        btn_LamMoi1.setText("Làm Mới");
        btn_LamMoi1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btn_LamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoi1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setText("Kết Thúc");

        buttonGroup2.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Hoạt Động");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel11.setText("Trạng Thái:");

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel15.setText("Ngày Kết Thúc:");

        txt_NgayKetThucG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayKetThucGActionPerformed(evt);
            }
        });

        txt_NgayBatDauG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NgayBatDauGActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel14.setText("Ngày Bắt Đầu:");

        txt_TenGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenGiamGiaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel10.setText("Tỷ Lệ:");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel8.setText("Tên Giảm Giá:");

        txt_TenGiamGia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenGiamGia2ActionPerformed(evt);
            }
        });

        lbl_MaGiamGia.setText("Mã Giảm Giá");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel3.setText("Mã Giảm Giá:");

        tbl_GiamGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Mã Giảm Giá", "Tên Giảm Giá", "Tỷ Lệ (%)", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scroll_GiamGia.setViewportView(tbl_GiamGia);

        textFieldSuggestion2.setText("textFieldSuggestion2");

        comboBoxSuggestion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSuggestion2ActionPerformed(evt);
            }
        });

        jLabel16.setText("Mã Giảm Giá:");

        jLabel17.setText("Trạng Thái:");

        btn_TimKiemSP1.setBorder(null);
        btn_TimKiemSP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/search.png"))); // NOI18N
        btn_TimKiemSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemSP1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_ThemGiamGiaLayout = new javax.swing.GroupLayout(pnl_ThemGiamGia);
        pnl_ThemGiamGia.setLayout(pnl_ThemGiamGiaLayout);
        pnl_ThemGiamGiaLayout.setHorizontalGroup(
            pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel15))
                            .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel14)
                                .addComponent(jLabel10)
                                .addComponent(jLabel8))
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(40, 40, 40)
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2))
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addComponent(btn_LamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(btn_CapNhat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(btn_Them1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_TenGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_MaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TenGiamGia2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_NgayBatDauG, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_NgayKetThucG, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(textFieldSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_TimKiemSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17)))
                    .addComponent(scroll_GiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnl_ThemGiamGiaLayout.setVerticalGroup(
            pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_ThemGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_MaGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_TenGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(12, 12, 12)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_TenGiamGia2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(12, 12, 12)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txt_NgayBatDauG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_NgayKetThucG, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(12, 12, 12)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addGap(18, 18, 18)
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Them1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_CapNhat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_LamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7))
                    .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                        .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(10, 10, 10)
                                .addComponent(textFieldSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_ThemGiamGiaLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(9, 9, 9)
                                .addGroup(pnl_ThemGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_TimKiemSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scroll_GiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_SPGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_ThemGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_ThemGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_SPGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPaneCustom1.addTab("Giảm Giá", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemSPActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btn_TimKiemSPActionPerformed

    private void btn_Them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Them1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Them1ActionPerformed

    private void btn_CapNhat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhat1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_CapNhat1ActionPerformed

    private void btn_LamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LamMoi1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void txt_NgayKetThucGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgayKetThucGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayKetThucGActionPerformed

    private void txt_NgayBatDauGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NgayBatDauGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NgayBatDauGActionPerformed

    private void txt_TenGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TenGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TenGiamGiaActionPerformed

    private void txt_TenGiamGia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TenGiamGia2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TenGiamGia2ActionPerformed

    private void comboBoxSuggestion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSuggestion2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxSuggestion2ActionPerformed

    private void btn_TimKiemSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemSP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_TimKiemSP1ActionPerformed

    private void tbl_VoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_VoucherMouseClicked
        clickHienThi();
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_VoucherMouseClicked

    private void rdo_HoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_HoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_HoatDongActionPerformed

    private void rdo_DungHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_DungHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_DungHoatDongActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        lamMoi();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
        qlKm.suaVourcher(redFrom());
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        qlKm.themVourcher(redFrom());
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void txt_TyLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TyLeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TyLeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.button.Button btn_CapNhat;
    private view.until.button.Button btn_CapNhat1;
    private view.until.button.Button btn_LamMoi;
    private view.until.button.Button btn_LamMoi1;
    private view.until.button.Button btn_Them;
    private view.until.button.Button btn_Them1;
    private view.until.button.Button btn_ThemSPGiamGia;
    private view.until.button.Button btn_TimKiemSP;
    private view.until.button.Button btn_TimKiemSP1;
    private view.until.button.Button btn_TimKiemV;
    private view.until.button.Button btn_XoaSPGiamGia;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private view.until.combobox.ComboBoxSuggestion comboBoxSuggestion1;
    private view.until.combobox.ComboBoxSuggestion comboBoxSuggestion2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_MaGiamGia;
    private javax.swing.JLabel lbl_MaVoucher;
    private javax.swing.JLabel lbl_tilte;
    private javax.swing.JPanel panel_SPGiamGia;
    private javax.swing.JPanel pnl_ThemGiamGia;
    private view.until.radiobutton.RadioButtonCustom rdo_DungHoatDong;
    private view.until.radiobutton.RadioButtonCustom rdo_HoatDong;
    private javax.swing.JScrollPane scrollSPGG;
    private javax.swing.JScrollPane scroll_GiamGia;
    private view.until.tabbedpane.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JTable tbl_GiamGia;
    private javax.swing.JTable tbl_SPGiamGia;
    private view.until.table.TableDark tbl_Voucher;
    private view.until.textfield.TextFieldSuggestion textFieldSuggestion1;
    private view.until.textfield.TextFieldSuggestion textFieldSuggestion2;
    private view.until.textfield.TextFieldSuggestion txt_MaGiamGiaSP;
    private view.until.textfield.TextFieldSuggestion txt_NgayBatDau;
    private view.until.textfield.TextFieldSuggestion txt_NgayBatDauG;
    private view.until.textfield.TextFieldSuggestion txt_NgayKetThuc;
    private view.until.textfield.TextFieldSuggestion txt_NgayKetThucG;
    private view.until.textfield.TextFieldSuggestion txt_TenGiamGia;
    private view.until.textfield.TextFieldSuggestion txt_TenGiamGia2;
    private view.until.textfield.TextFieldSuggestion txt_TenVourcher;
    private view.until.textfield.TextFieldSuggestion txt_TyLe;
    // End of variables declaration//GEN-END:variables
}
