/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.until.swing.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import model.SanPham;

public class Item extends javax.swing.JPanel {

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
    
    public Item() {
        initComponents();
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR) {
        });
    }
//    set thong tin của sản phẩm
    private SanPham sp;
    public void setData(SanPham sp) {
        this.sp = sp;
        txt_MaSP.setText(sp.getTenSanPham());
        txt_GiaBan.setText(String.valueOf(sp.getGiaChiTiet()));
        if (sp.getIDGiamGia() != null) {
            double giaGiam = sp.getGiaChiTiet() - (sp.getGiaChiTiet() * sp.getIDGiamGia().getTyLeGiamGia()/100);
            txt_GiaGiam.setText(String.valueOf(giaGiam));
        } else {
            txt_GiaGiam.setText("");
        }
        txt_SoLuong.setText(sp.getSoLuongTonKho()==0?"Hết hàng":String.valueOf(sp.getSoLuongTonKho()));
        if (sp.getHinhAnhSanPham() == null) {
            anh.setImage(new ImageIcon(getClass().getResource("/Icon/noimage.png")));
        } else {
            ImageIcon imageIcon = new ImageIcon(sp.getHinhAnhSanPham());
            Image image = imageIcon.getImage(); // Chuyển đổi về đối tượng Image
            Image scaledImage = image.getScaledInstance(anh.getWidth() - 2, anh.getHeight() - 2, Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh
            imageIcon = new ImageIcon(scaledImage);
            anh.setImage(imageIcon);
        }

    }
    @Override
    public void paint(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(242,242,242));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        if (selected) {
            g2.setColor(new Color(94,156,255));
            g2.drawRoundRect(0,0, getWidth()-1,getHeight()-1,20,20);
        }
        g2.dispose();
        super.paint(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_MaSP = new javax.swing.JLabel();
        anh = new view.until.swing.PictureBox();
        jLabel1 = new javax.swing.JLabel();
        lbl_GiaGiam = new javax.swing.JLabel();
        txt_GiaBan = new javax.swing.JLabel();
        txt_GiaGiam = new javax.swing.JLabel();
        txt_SoLuong = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        txt_MaSP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_MaSP.setText("SP001");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Giá:");

        lbl_GiaGiam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_GiaGiam.setForeground(new java.awt.Color(0, 102, 255));
        lbl_GiaGiam.setText("Giá Giảm:");

        txt_GiaBan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_GiaBan.setText("300.000.000");

        txt_GiaGiam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_GiaGiam.setText("250.0000");

        txt_SoLuong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_SoLuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_SoLuong.setText("10");

        jLabel3.setText("Số Lượng:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_MaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(anh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_GiaGiam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(76, 76, 76)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_GiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_GiaGiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28)
                        .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(anh, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_GiaBan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_GiaGiam)
                    .addComponent(txt_GiaGiam))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.swing.PictureBox anh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbl_GiaGiam;
    private javax.swing.JLabel txt_GiaBan;
    private javax.swing.JLabel txt_GiaGiam;
    private javax.swing.JLabel txt_MaSP;
    private javax.swing.JLabel txt_SoLuong;
    // End of variables declaration//GEN-END:variables
}
