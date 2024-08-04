/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.BaoHanh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.BaoHanh;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.SanPham;

/**
 *
 * @author nguyentrikhoi
 */
public class GiaoDienBaoHanhService implements GiaoDienBaoHanhServicInterface {

    private DefaultTableModel model;
    private repository.BaoHanh.repoBaoHanh rpBH = new repository.BaoHanh.repoBaoHanh();

    @Override
    public void fillToTable(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        int stt = 1;
        for (BaoHanh bh : rpBH.getAll()) {
            String idBaoHanh = bh.getIDBaoHanh();
            KhachHang kh = bh.getKhachHang();
            String tenKhachHang = kh != null ? kh.getHoTen() : "";
            String soDienThoai = kh != null ? kh.getSoDienThoai() : "";
            String diaChi = kh != null ? kh.getDiaChi() : "";
            SanPham sp = bh.getSanPham();
            String tenSanPham = sp != null ? sp.getTenSanPham() : "";
            String ghiChu = bh.getGhiChu();
            Date ngayTao = bh.getNgayYeuCau();
            String formattedDate = sdf.format(ngayTao);

            boolean trangThai = bh.isTrangThai();

            model.addRow(new Object[]{stt++, idBaoHanh, tenKhachHang, soDienThoai, diaChi, tenSanPham, ghiChu, formattedDate, trangThai ? "Đã Nhận" : "Đã Trả"});
        }
    }

}
