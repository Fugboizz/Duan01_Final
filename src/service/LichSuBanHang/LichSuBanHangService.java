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
    private view.banhang.HoaDonChiTietDialog hdct;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void fillToTable(JTable tbl) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        int stt = 0;
        for (HoaDonChiTiet ct : rpLSBanHang.getAll()) {
            String ngayTaoHoaDon = sdf.format(ct.getIDHoaDon().getNgayTao());
            String maHoaDon = ct.getIDHoaDon().getIDHoaDon();
            String tenNhanVien = ct.getIDHoaDon().getIdTaiKhoan().getHoTen();
            String tenKhachHang = ct.getIDHoaDon().getIdKhachHang().getHoTen();
            double tongTien = ct.getIDHoaDon().getTongTienSau();
            stt++;
            model.addRow(new Object[]{stt,maHoaDon,ngayTaoHoaDon,tenNhanVien,tenKhachHang,tongTien,ct.getIDHoaDon().isTrangThai()?"Đã Thanh Toán":""});
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

                        for (HoaDonChiTiet hd : rpLSBanHang.getAll()) {
                            if (hd.getIDHoaDon().getIDHoaDon().equalsIgnoreCase(tbl.getValueAt(row, 1).toString())) {
                                hdct = new view.banhang.HoaDonChiTietDialog(main, true);
                                hdct.setData(hd);
                                hdct.fillDataChiTiet(tbl.getValueAt(row, 1).toString());
                                hdct.setVisible(true);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

//    @Override
//    public void fillToTableHDCT(JTable tbl, String IDHoaDon) {
//        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
//        model.setRowCount(0);
//        for (HoaDonChiTiet hdCT : rpLSBanHang.getData(IDHoaDon)) {
//            System.out.println(hdCT.getSoLUongSanPHam());
//            double giaChiTiet = hdCT.getIDSanPham().getGiaChiTiet();
//            double tyLeGiamGia = 0.0;
//            if (hdCT.getIDSanPham().getIDGiamGia() != null) {
//                tyLeGiamGia = hdCT.getIDSanPham().getIDGiamGia().getTyLeGiamGia();
//            }
//            double tongTien = (hdCT.getSoLUongSanPHam() * giaChiTiet) - ((hdCT.getSoLUongSanPHam() * giaChiTiet) * tyLeGiamGia / 100);
//            model.addRow(new Object[]{
//                hdCT.getIDSanPham().getTenSanPham(),
//                hdCT.getSoLUongSanPHam(),
//                giaChiTiet,
//                tyLeGiamGia,
//                tongTien
//            });
//        }
//    }

}
