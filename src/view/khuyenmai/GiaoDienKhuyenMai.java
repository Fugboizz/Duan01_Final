/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.khuyenmai;

import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.border.TitledBorder.CENTER;
import model.Voucher;
import view.main.Main;

public class GiaoDienKhuyenMai extends javax.swing.JPanel {
    private service.KhuyenMai.GiaoDienKhuyenMaiService qlKm = new service.KhuyenMai.GiaoDienKhuyenMaiService();
    private repository.KhuyenMai.KhuyenMaiRepository rpKM = new repository.KhuyenMai.KhuyenMaiRepository();
    private Main main;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang

    public GiaoDienKhuyenMai() {
        initComponents();
        setFont();
        qlKm.fillToTable(tbl_Voucher);
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
        
    }
    
    public void lamMoi(){
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
    
    public void clickHienThi(){
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

        jPanel2 = new javax.swing.JPanel();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panel_GiamGia = new view.until.tabbedpane.TabbedPaneCustom();
        panel_Voucher = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_TenVourcher = new view.until.textfield.TextFieldSuggestion();
        lbl_MaVoucher = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_NgayKetThuc = new view.until.textfield.TextFieldSuggestion();
        txt_NgayBatDau = new view.until.textfield.TextFieldSuggestion();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Voucher = new view.until.table.TableDark();
        jLabel6 = new javax.swing.JLabel();
        textFieldSuggestion1 = new view.until.textfield.TextFieldSuggestion();
        btn_TimKiemV = new view.until.button.Button();
        btn_LamMoi = new view.until.button.Button();
        btn_CapNhat = new view.until.button.Button();
        btn_Them = new view.until.button.Button();
        txt_TyLe = new view.until.textfield.TextFieldSuggestion();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdo_HoatDong = new view.until.radiobutton.RadioButtonCustom();
        rdo_DungHoatDong = new view.until.radiobutton.RadioButtonCustom();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        dateChooser1.setForeground(new java.awt.Color(0, 102, 255));
        dateChooser1.setTextRefernce(txt_NgayKetThuc);

        dateChooser2.setTextRefernce(txt_NgayBatDau);

        panel_GiamGia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        panel_Voucher.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel_VoucherLayout = new javax.swing.GroupLayout(panel_Voucher);
        panel_Voucher.setLayout(panel_VoucherLayout);
        panel_VoucherLayout.setHorizontalGroup(
            panel_VoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 995, Short.MAX_VALUE)
        );
        panel_VoucherLayout.setVerticalGroup(
            panel_VoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 769, Short.MAX_VALUE)
        );

        panel_GiamGia.addTab("Giam Giá", panel_Voucher);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

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

        txt_NgayKetThuc.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        txt_NgayBatDau.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

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

        txt_TyLe.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_TyLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TyLeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Tỷ Lệ");

        jLabel9.setText("Trạng Thái");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1)
                .addGap(15, 15, 15))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textFieldSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_TimKiemV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TenVourcher, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_MaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
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
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_MaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_TenVourcher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TyLe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(rdo_HoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdo_DungHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_NgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_NgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textFieldSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiemV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        panel_GiamGia.addTab("Voucher", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_GiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_GiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdo_HoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_HoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_HoatDongActionPerformed

    private void rdo_DungHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_DungHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_DungHoatDongActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        qlKm.themVourcher(redFrom());
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void txt_TyLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TyLeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TyLeActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        lamMoi();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
        qlKm.suaVourcher(redFrom());
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void tbl_VoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_VoucherMouseClicked
        clickHienThi();
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_VoucherMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.button.Button btn_CapNhat;
    private view.until.button.Button btn_LamMoi;
    private view.until.button.Button btn_Them;
    private view.until.button.Button btn_TimKiemV;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_MaVoucher;
    private view.until.tabbedpane.TabbedPaneCustom panel_GiamGia;
    private javax.swing.JPanel panel_Voucher;
    private view.until.radiobutton.RadioButtonCustom rdo_DungHoatDong;
    private view.until.radiobutton.RadioButtonCustom rdo_HoatDong;
    private view.until.table.TableDark tbl_Voucher;
    private view.until.textfield.TextFieldSuggestion textFieldSuggestion1;
    private view.until.textfield.TextFieldSuggestion txt_NgayBatDau;
    private view.until.textfield.TextFieldSuggestion txt_NgayKetThuc;
    private view.until.textfield.TextFieldSuggestion txt_TenVourcher;
    private view.until.textfield.TextFieldSuggestion txt_TyLe;
    // End of variables declaration//GEN-END:variables

    public static class getInstance extends Component {

        public getInstance() {
        }
    }
}
