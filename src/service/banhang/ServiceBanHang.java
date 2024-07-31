/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.banhang;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.PhanLoai;
import repository.PhanLoai.PhanLoaiRepo;
import repository.hoadon.RepositoryHoaDon;
import repository.hoadonchitiet.RepositoryHoaDonChiTiet;
import java.text.DecimalFormat;

/**
 *
 * @author HUNGpYN
 */
public class ServiceBanHang implements ServiceBanHangInterface {

    private repository.hoadon.RepositoryHoaDon rphd;
    private repository.PhanLoai.PhanLoaiRepo plr;
    private repository.hoadonchitiet.RepositoryHoaDonChiTiet rpct;

    @Override

    public void fillHoaDonCho(JTable tbl) {
        rphd = new RepositoryHoaDon();
        DefaultTableModel modelTable = (DefaultTableModel) tbl.getModel();
        modelTable.setRowCount(0);
        int STT = 1;
        DecimalFormat df = new DecimalFormat("#,##0.00");

        for (HoaDon hd : rphd.getAllHDC()) {
            String tongTienTruoc = hd.getTongTienTRuoc() == null ? "0" : df.format(hd.getTongTienTRuoc());
            modelTable.addRow(new Object[]{STT++, hd.getIDHoaDon(), hd.getNgayTao(), tongTienTruoc,
                hd.isTrangThai() ? "Đã Thanh Toán" : "Chờ Xử Lý"});
        }
    }

    @Override
    public void addComboBox(JComboBox cbo) {
        plr = new PhanLoaiRepo();
        for (PhanLoai pl : plr.getAll()) {
            cbo.addItem(pl.getTenPhanLoai());
        }
    }

    @Override

    public void fillHoaDonChiTietBH(JTable tbl, String idHoaDon) {
        rpct = new RepositoryHoaDonChiTiet();
        DefaultTableModel modelTable = (DefaultTableModel) tbl.getModel();
        modelTable.setRowCount(0);
        int STT = 1;
        double giamGia;
        double tongTien;

        // Tạo đối tượng DecimalFormat để định dạng số tiền
        DecimalFormat df = new DecimalFormat("#,##0.00");

        for (HoaDonChiTiet ct : rpct.getData(idHoaDon)) {
            double giaChiTiet = ct.getIDSanPham().getGiaChiTiet();
            double tyLeGiamGia = ct.getIDSanPham().getIDGiamGia() != null ? ct.getIDSanPham().getIDGiamGia().getTyLeGiamGia() : 0;

            if (tyLeGiamGia > 0) {
                giamGia = giaChiTiet * tyLeGiamGia / 100;
                tongTien = (giaChiTiet - giamGia) * ct.getSoLUongSanPHam();
            } else {
                giamGia = 0;
                tongTien = giaChiTiet * ct.getSoLUongSanPHam();
            }

            // Định dạng các giá trị tiền
            String formattedGiaChiTiet = df.format(giaChiTiet);
            String formattedGiamGia = df.format(giamGia);
            String formattedTongTien = df.format(tongTien);

            modelTable.addRow(new Object[]{
                STT++,
                ct.getIDSanPham().getTenSanPham(),
                ct.getSoLUongSanPHam(),
                formattedGiaChiTiet,
                formattedGiamGia,
                formattedTongTien
            });
        }
    }

}
