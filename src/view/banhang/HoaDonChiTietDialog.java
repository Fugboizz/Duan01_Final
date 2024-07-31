package view.banhang;

import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import repository.LichSuBanHang.LichSuBanHangRepository;

/**
 *
 * @author HUNGpYN
 */
public class HoaDonChiTietDialog extends javax.swing.JDialog {

    private Color color2 = Color.decode("#101820");// thanden
    private Color color1 = Color.decode("#FEE715"); //mau vang
    private service.LichSuBanHang.LichSuBanHangService qlLSBanHang = new service.LichSuBanHang.LichSuBanHangService();
    private GiaoDienBanHang gdBanHang = new GiaoDienBanHang();
    private repository.LichSuBanHang.LichSuBanHangRepository rll = new LichSuBanHangRepository();

    public HoaDonChiTietDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setFont();
        System.out.println(rll.getAll().size()+" Model");
        System.out.println(rll.getAll().get(1).getSoLUongSanPHam()+ "Soluong sanpham");
        System.out.println(rll.getAll().get(1).getIDSanPham().getGiaChiTiet()+"Giá");

    }

    public JTable getJTable() {
        return tbl_HDCT;
    }

    void setFont() {
        lbl_Menu.setBackground(color2);
        lbl_thongtin.setForeground(color1);
        setLocationRelativeTo(null);
        setTitle("LuxuryStore");

    }
    String voucher;

    public void setData(HoaDonChiTiet ct) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        // Lấy thông tin hóa đơn
        HoaDon hoaDon = ct.getIDHoaDon();

        // Lấy thông tin khách hàng
        String maHoaDon = hoaDon.getIDHoaDon();
        String khachHang = hoaDon.getIdKhachHang().getHoTen();
        String ngayTao = sdf.format(hoaDon.getNgayTao());

        // Xử lý thông tin giảm giá và thanh toán
        if (hoaDon.getIdVoucher() != null && hoaDon.getIdVoucher().getIDVoucher() != null) {
            voucher = hoaDon.getIdVoucher().getTenVoucher();
        } else {
            voucher = "Không";
        }

        double tongTien = hoaDon.getTongTienTRuoc();
        double thanhToan = hoaDon.getTongTienSau();
        double chietKhau = tongTien - thanhToan;

        // Định dạng số tiền
        String tt = String.format("%.2f", tongTien);
        String tToan = String.format("%.2f", thanhToan);
        String ck = String.format("%.2f", chietKhau);

        // Cập nhật giao diện
        lbl_MaHoaDon.setText(maHoaDon);
        lbl_HoTenKhachHang.setText(khachHang);
        lbl_NgayTaoHoaDon.setText(ngayTao);
        lbl_TenVoucher.setText(voucher);
        lbl_ChietKhau.setText(ck);
        lbl_TongTien.setText(tt);
        lbl_ThanhToan.setText(tToan);
    }

    public void fillDataChiTiet(String text) {
        DefaultTableModel model = (DefaultTableModel) tbl_HDCT.getModel();
        model.setRowCount(0);
        for (HoaDonChiTiet ct : rll.getAll()) {
            if (ct.getIDHoaDon().getIDHoaDon().equalsIgnoreCase(text)) {
                model.addRow(new Object[]{ct.getIDSanPham().getTenSanPham(), 
                    ct.getSoLUongSanPHam(), 
                    ct.getIDSanPham().getGiaChiTiet(),
                    ct.getSoLUongSanPHam() * ct.getIDSanPham().getGiaChiTiet()});
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_Menu = new javax.swing.JPanel();
        lbl_thongtin = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_MaHoaDon = new javax.swing.JLabel();
        lbl_NgayTaoHoaDon = new javax.swing.JLabel();
        lbl_HoTenKhachHang = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_TenVoucher = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_HDCT = new view.until.table.TableDark();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_TongTien = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_ChietKhau = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_ThanhToan = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_thongtin.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbl_thongtin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_thongtin.setText("Hóa Đơn Chi Tiết");

        javax.swing.GroupLayout lbl_MenuLayout = new javax.swing.GroupLayout(lbl_Menu);
        lbl_Menu.setLayout(lbl_MenuLayout);
        lbl_MenuLayout.setHorizontalGroup(
            lbl_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_thongtin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lbl_MenuLayout.setVerticalGroup(
            lbl_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_MenuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_thongtin)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Mã Hóa Đơn:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText(" Khách Hàng:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Ngày Tạo:");

        lbl_MaHoaDon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lbl_NgayTaoHoaDon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lbl_HoTenKhachHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("_________________________________________________________________________________________");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Voucher Sử Dụng:");

        lbl_TenVoucher.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        tbl_HDCT.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbl_HDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên Sản Phẩm", "Số Lượng", "Giá ", "Giảm Giá (%)", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_HDCT.setOpaque(false);
        jScrollPane1.setViewportView(tbl_HDCT);
        if (tbl_HDCT.getColumnModel().getColumnCount() > 0) {
            tbl_HDCT.getColumnModel().getColumn(0).setPreferredWidth(200);
        }

        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("_________________________________________________________________________________________");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Tổng Tiền:");

        lbl_TongTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_TongTien.setForeground(new java.awt.Color(255, 51, 0));
        lbl_TongTien.setText("1000000000000");

        jLabel8.setText("Chiết Khẩu:");

        lbl_ChietKhau.setText("1000000000000");

        jLabel11.setText("Thanh Toán:");

        lbl_ThanhToan.setText("100000000000000000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_MaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_NgayTaoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_HoTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_TenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_TongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(lbl_ChietKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbl_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbl_MaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbl_HoTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbl_NgayTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbl_TenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbl_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbl_ChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(lbl_ThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(HoaDonChiTietDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HoaDonChiTietDialog dialog = new HoaDonChiTietDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel lbl_ChietKhau;
    private javax.swing.JLabel lbl_HoTenKhachHang;
    private javax.swing.JLabel lbl_MaHoaDon;
    private javax.swing.JPanel lbl_Menu;
    private javax.swing.JLabel lbl_NgayTaoHoaDon;
    private javax.swing.JLabel lbl_TenVoucher;
    private javax.swing.JLabel lbl_ThanhToan;
    private javax.swing.JLabel lbl_TongTien;
    private javax.swing.JLabel lbl_thongtin;
    private view.until.table.TableDark tbl_HDCT;
    // End of variables declaration//GEN-END:variables
}
