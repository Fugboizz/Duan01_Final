package view.dangnhap;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import service.dangnhap.DangNhap;

public class DangNhapView extends javax.swing.JFrame {

    public static boolean roleDN;
    public static String nameDN;
    private service.dangnhap.DangNhap dn = new DangNhap();

    public DangNhapView() {
        initComponents();
        setLocationRelativeTo(null);
        txt_TaiKhoan.setBackground(new java.awt.Color(0, 0, 0, 1));
        txt_MatKhau.setBackground(new java.awt.Color(0, 0, 0, 1));
        lbl_enable.setVisible(false);
        txt_MatKhau.setEchoChar((char) 8226);
        init();
    }

private void init() {
        // Đường dẫn tới tệp hình ảnh
        URL imageURL = getClass().getResource("/Icon/nhan.jpg");

        if (imageURL != null) {
            // Tạo ImageIcon từ URL
            ImageIcon originalIcon = new ImageIcon(imageURL);

            // Lấy Image từ ImageIcon
            Image originalImage = originalIcon.getImage();

            // Lấy kích thước của JLabel
            int labelWidth = lbl_BackGround.getWidth();
            int labelHeight = lbl_BackGround.getHeight();

            // Thay đổi kích thước của hình ảnh sao cho phù hợp với kích thước của JLabel
            Image resizedImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            // Đặt biểu tượng đã thay đổi kích thước cho JLabel
            lbl_BackGround.setIcon(resizedIcon);
        } else {
            System.out.println("Hình ảnh không tìm thấy!");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_Logo = new javax.swing.JLabel();
        txtlabe = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_disable = new javax.swing.JLabel();
        txt_TaiKhoan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_MatKhau = new javax.swing.JPasswordField();
        lbl_enable = new javax.swing.JLabel();
        button1 = new view.until.button.Button();
        lbl_BackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(16, 24, 32));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 231, 21));
        jLabel3.setText("ĐĂNG NHẬP");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 140, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 231, 21));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(398, 0, 32, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(254, 231, 21));
        jLabel5.setText("Tài Khoản");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 327, -1));

        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logo.png"))); // NOI18N
        jPanel2.add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 210, 160));

        txtlabe.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        txtlabe.setForeground(new java.awt.Color(254, 231, 21));
        txtlabe.setText("_________________________________________________");
        jPanel2.add(txtlabe, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 250, 40));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(254, 231, 21));
        jLabel7.setText("Mật khẩu");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 310, -1));

        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("Quên mật khẩu");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

        lbl_disable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/disable.png"))); // NOI18N
        lbl_disable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_disable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_disableMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_disable, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, -1, -1));

        txt_TaiKhoan.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txt_TaiKhoan.setForeground(new java.awt.Color(254, 231, 21));
        txt_TaiKhoan.setBorder(null);
        txt_TaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TaiKhoanActionPerformed(evt);
            }
        });
        jPanel2.add(txt_TaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 250, 40));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(254, 231, 21));
        jLabel11.setText("________________________________________________");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 250, 20));

        txt_MatKhau.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt_MatKhau.setForeground(new java.awt.Color(254, 231, 21));
        txt_MatKhau.setBorder(null);
        txt_MatKhau.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_MatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MatKhauActionPerformed(evt);
            }
        });
        jPanel2.add(txt_MatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 250, 40));

        lbl_enable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/show.png"))); // NOI18N
        lbl_enable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_enable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_enableMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_enable, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, -1, -1));

        button1.setText("ĐĂNG NHẬP");
        button1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jPanel2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 270, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 430, 500));

        lbl_BackGround.setText("jLabel4");
        getContentPane().add(lbl_BackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txt_TaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TaiKhoanActionPerformed

    private void txt_MatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MatKhauActionPerformed

    private void lbl_enableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_enableMouseClicked

        dn.hidePassword(txt_MatKhau, lbl_disable, lbl_enable);
    }//GEN-LAST:event_lbl_enableMouseClicked

    private void lbl_disableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_disableMouseClicked
        dn.showPassword(txt_MatKhau, lbl_disable, lbl_enable);
    }//GEN-LAST:event_lbl_disableMouseClicked

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
            java.util.logging.Logger.getLogger(DangNhapView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhapView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhapView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhapView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhapView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.until.button.Button button1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_BackGround;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_disable;
    private javax.swing.JLabel lbl_enable;
    private javax.swing.JPasswordField txt_MatKhau;
    private javax.swing.JTextField txt_TaiKhoan;
    private javax.swing.JLabel txtlabe;
    // End of variables declaration//GEN-END:variables
}
