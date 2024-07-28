/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.LichSuBanHang;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.SanPham;
import view.banhang.GiaoDienBanHang;
import view.main.Main;

/**
 *
 * @author nguyentrikhoi
 */
public class LichSuBanHangService implements LichSuBanHangServiceInterface {

    private DefaultTableModel model;
    private GiaoDienBanHang gdBanHang;
    private repository.LichSuBanHang.LichSuBanHangRepository rpLSBanHang = new repository.LichSuBanHang.LichSuBanHangRepository();
    private Main main;
    private view.banhang.HoaDonChiTiet hdct;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void fillToTable(JTable tbl) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        int stt = 0;
        for (HoaDon hd : rpLSBanHang.getAll()) {
            String ngayTaoHoaDon = sdf.format(hd.getNgayTao());
            stt++;
            model.addRow(new Object[]{stt, hd.getIDHoaDon(), ngayTaoHoaDon, hd.getIdTaiKhoan().getHoTen(), hd.getIdKhachHang().getHoTen(), hd.getTongTienSau()});
        }
    }

    @Override
    public void doubleClick(JTable tbl) {
    tbl.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                int row = tbl.getSelectedRow();
                if (row >= 0) {
                    
                    for (HoaDon hd : rpLSBanHang.getAll()) {
                        if (hd.getIDHoaDon().equalsIgnoreCase(tbl.getValueAt(row, 1).toString())) {
                            hdct  = new view.banhang.HoaDonChiTiet(main, true);
                            hdct.setData(hd);
                            hdct.setVisible(true);
                            break;
                        }
                    }
                }
            }
        }
    });
}

    @Override
    public void fillToTableHDCT(JTable tbl, String IDHoaDon) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);

        List<HoaDonChiTiet> hdctList = rpLSBanHang.getData(IDHoaDon);
        for (HoaDonChiTiet hdCT : hdctList) {
            String ngayTaoHoaDon = sdf.format(hdCT.getNgayTao());
            double giaChiTiet = hdCT.getIDSanPham().getGiaChiTiet();
            double tyLeGiamGia = 0.0f;
            double tongTien = 0;
            if (hdCT.getIDSanPham().getIDGiamGia() != null) {
                tyLeGiamGia = hdCT.getIDSanPham().getIDGiamGia().getTyLeGiamGia();
            }
            if (hdCT.getIDSanPham().getIDGiamGia() != null) {
                tongTien = (hdCT.getSoLUongSanPHam() * giaChiTiet) - ((hdCT.getSoLUongSanPHam() * giaChiTiet) * tyLeGiamGia / 100);
            }

            model.addRow(new Object[]{
                hdCT.getIDSanPham().getTenSanPham(),
                hdCT.getSoLUongSanPHam(),
                giaChiTiet,
                tyLeGiamGia,
                tongTien
            });
        }
    }

}
