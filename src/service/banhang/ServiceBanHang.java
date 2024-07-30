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
        for (HoaDon hd : rphd.getAllHDC()) {
            modelTable.addRow(new Object[]{STT++, hd.getIDHoaDon(), hd.getNgayTao(),
                hd.getTongTienTRuoc() == null ? "0" : hd.getTongTienTRuoc(),
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
        for (HoaDonChiTiet ct : rpct.getData(idHoaDon)) {
            System.out.println("Gia Chi Tiết: "
                    +ct.getIDSanPham().getGiaChiTiet());
            if (ct.getIDSanPham().getIDGiamGia().getIDGIamGia() != null) {
                System.out.println(ct.getIDSanPham().getIDGiamGia().getIDGIamGia());
                giamGia = ct.getIDSanPham().getGiaChiTiet() * ct.getIDSanPham().getIDGiamGia().getTyLeGiamGia() / 100;
                tongTien = ct.getIDSanPham().getGiaChiTiet() * ct.getSoLUongSanPHam() - giamGia * ct.getSoLUongSanPHam();

            } else {
                giamGia = 0;
                tongTien = ct.getIDSanPham().getGiaChiTiet() * ct.getSoLUongSanPHam();
            }

            System.out.println(tongTien);
            modelTable.addRow(new Object[]{STT++,
                ct.getIDSanPham().getTenSanPham(),
                ct.getSoLUongSanPHam(),
                ct.getIDSanPham().getGiaChiTiet(), giamGia, tongTien});
        }
    }
}
