/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.sanpham;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import model.HinhAnhSanPham;
import model.SanPham;
import repository.HinhAnhSanPham.HinhAnhSanPhamrepo;
import repository.SanPham.repoChiTietSanPham;

/**
 *
 * @author HUNGpYN
 */
public class GiaoDienSanPham extends javax.swing.JPanel {
private List<SanPham> spList = new ArrayList<>();
    private List<HinhAnhSanPham> haList = new ArrayList<>();
    private repository.SanPham.repoChiTietSanPham rpsp = new repoChiTietSanPham();
    private HinhAnhSanPhamrepo hasprp = new HinhAnhSanPhamrepo();
    private model.SanPham mdsp = new SanPham();
    private DefaultTableModel model;
 private Color color2 = Color.decode("#101820");// thanden
 private Color color1 = Color.decode("#FEE715"); //mau vang
    public GiaoDienSanPham() {
        initComponents();
        tbl_SanPham.fixTable(jScrollPane2);
        lbl_DanhSach.setForeground(color1);
        panel_ThemMoi.setBackground(color2);
        btn_TimKiem.setColor1(color2);
        btn_TimKiem.setColor2(color1);
        tbl_SanPham.setRowHeight(100);
        fillToTable();
    }
     void fillToTable() {
        spList = rpsp.getAll();
        haList = hasprp.getAll();

        String[] hienthi = {"Mã Trang Sức", "Tên Trang Sức", "Phân Loại", "Giới Tính", "Giá Bán", "Hình Ảnh", "Trạng Thái"};
        model = new DefaultTableModel(hienthi, 0);

        for (SanPham sp : spList) {
            for (HinhAnhSanPham ha : haList) {
                if (sp.getIDSanPham().equals(ha.getIDSanPham().getIDSanPham())) {
                    ImageIcon imageIcon = null;
                    try {
                        imageIcon = new ImageIcon("/Icon/baohanh.png");
                    } catch (Exception e) {
                        e.printStackTrace();
                        imageIcon = new ImageIcon(); // Placeholder nếu lỗi xảy ra
                    }
                    String tenPhanLoai = sp.getIDPhanLoai() != null ? sp.getIDPhanLoai().getTenPhanLoai() : "N/A";
                    String trangThai = sp.isTrangThai() ? "Đang Hoạt Động" : "Ngừng Kinh Doanh";

                    Object[] rowObject = {
                        sp.getIDSanPham(),
                        sp.getTenSanPham(),
                        tenPhanLoai,
                        sp.isGioiTinh() ? "Nam" : "Nữ",
                        sp.getGiaChiTiet(),
                        imageIcon,
                        trangThai
                    };
                    model.addRow(rowObject);
                }
            }
        }

        tbl_SanPham.setModel(model);
        tbl_SanPham.getColumnModel().getColumn(5).setCellRenderer(tbl_SanPham.getDefaultRenderer(ImageIcon.class)); // Đặt renderer cho cột hình ảnh
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_ThemMoi = new javax.swing.JPanel();
        lbl_DanhSach = new javax.swing.JLabel();
        btn_TaoMoi = new view.until.button.Button();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboBoxSuggestion1 = new view.until.combobox.ComboBoxSuggestion();
        comboBoxSuggestion2 = new view.until.combobox.ComboBoxSuggestion();
        comboBoxSuggestion3 = new view.until.combobox.ComboBoxSuggestion();
        jLabel4 = new javax.swing.JLabel();
        txt_TimKiem = new view.until.textfield.TextFieldSuggestion();
        btn_TimKiem = new view.until.button.Button();
        btn_Excel = new view.until.button.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_SanPham = new view.until.table.TableDark();

        setBackground(new java.awt.Color(255, 255, 255));

        lbl_DanhSach.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_DanhSach.setText("Danh Sách Sản Phẩm");

        btn_TaoMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btn_TaoMoi.setText("Tạo Mới");
        btn_TaoMoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_TaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_ThemMoiLayout = new javax.swing.GroupLayout(panel_ThemMoi);
        panel_ThemMoi.setLayout(panel_ThemMoiLayout);
        panel_ThemMoiLayout.setHorizontalGroup(
            panel_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ThemMoiLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbl_DanhSach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_TaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        panel_ThemMoiLayout.setVerticalGroup(
            panel_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ThemMoiLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panel_ThemMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_DanhSach)
                    .addComponent(btn_TaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Loại Trang Sức");

        jLabel2.setText("Giới Tính");

        jLabel3.setText("Trạng Thái");

        comboBoxSuggestion1.setEditable(false);
        comboBoxSuggestion1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Kinh Doanh", "Ngừng Kinh Doanh" }));
        comboBoxSuggestion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSuggestion1ActionPerformed(evt);
            }
        });

        comboBoxSuggestion2.setEditable(false);
        comboBoxSuggestion2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả" }));
        comboBoxSuggestion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSuggestion2ActionPerformed(evt);
            }
        });

        comboBoxSuggestion3.setEditable(false);
        comboBoxSuggestion3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất Cả", "Nam", "Nữ" }));
        comboBoxSuggestion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSuggestion3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tên Trang Sức");

        txt_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimKiemActionPerformed(evt);
            }
        });

        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btn_Excel.setBorder(null);
        btn_Excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/excel.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxSuggestion3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Excel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxSuggestion3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Excel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Trang Sức", "Tên Trang Sức", "Phân Loại", "Giới Tính", "Giá Bán", "Hình Ảnh", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_SanPham);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_ThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_ThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxSuggestion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSuggestion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxSuggestion1ActionPerformed

    private void comboBoxSuggestion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSuggestion2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxSuggestion2ActionPerformed

    private void comboBoxSuggestion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSuggestion3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxSuggestion3ActionPerformed

    private void btn_TaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_TaoMoiActionPerformed

    private void txt_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.button.Button btn_Excel;
    private view.until.button.Button btn_TaoMoi;
    private view.until.button.Button btn_TimKiem;
    private view.until.combobox.ComboBoxSuggestion comboBoxSuggestion1;
    private view.until.combobox.ComboBoxSuggestion comboBoxSuggestion2;
    private view.until.combobox.ComboBoxSuggestion comboBoxSuggestion3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_DanhSach;
    private javax.swing.JPanel panel_ThemMoi;
    private view.until.table.TableDark tbl_SanPham;
    private view.until.textfield.TextFieldSuggestion txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
