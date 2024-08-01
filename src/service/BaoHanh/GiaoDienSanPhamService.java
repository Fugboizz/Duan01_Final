/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.BaoHanh;

//import com.sun.org.apache.xalan.internal.XalanConstants;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.BaoHanh;
import model.KhachHang;
import model.SanPham;

/**
 *
 * @author nguyentrikhoi
 */
public class GiaoDienSanPhamService implements GiaoDienBaoHanhServicInterface {

    private DefaultTableModel model;
    private repository.BaoHanh.repoBaoHanh rpBH = new repository.BaoHanh.repoBaoHanh();

    @Override
    public void fillToTable(JTable tbl) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);  // Xóa dữ liệu hiện tại trong bảng

        int stt = 1;
        for (BaoHanh bh : rpBH.getAll()) {
            // Lấy thông tin từ đối tượng BaoHanh
            String idBaoHanh = bh.getIDBaoHanh();
            KhachHang kh = bh.getKhachHang();
            String tenKhachHang = kh != null ? kh.getHoTen() : "";
            String soDienThoai = kh != null ? kh.getSoDienThoai() : "";
            String diaChi = kh != null ? kh.getDiaChi() : "";
            SanPham sp = bh.getSanPham();
            String tenSanPham = sp != null ? sp.getTenSanPham() : "";
            String ghiChu = bh.getGhiChu();
            Date ngayTao = bh.getNgayYeuCau();
            boolean trangThai = bh.isTrangThai();

            model.addRow(new Object[]{stt++, idBaoHanh, tenKhachHang, soDienThoai, diaChi, tenSanPham, ghiChu, ngayTao, trangThai ? "Đã Nhận" : "Đã Trả"});
        }
    }

}
