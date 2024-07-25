/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.KhachHang;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import repository.KhachHang.repoKhachHang;

/**
 *
 * @author nguyentrikhoi
 */
public class GiaoDienKhachHangService implements GiaoDienKhachHangServiceInterface {

    private DefaultTableModel model;
    private repository.KhachHang.repoKhachHang rpKH = new repoKhachHang();
  

    @Override
    public void fillToTable(JTable tbl) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        for (KhachHang kh : rpKH.getAll()) {
            model.addRow(new Object[]{kh.getIDKhachHang(), kh.getHoTen(), kh.getSoDienThoai(), kh.getEmail(), kh.getDiaChi(), kh.getTichDiem(), kh.isTrangThai()});
        }

    }

    @Override
    public void themKhachHang(KhachHang kh) {
        if (kh != null) {
            rpKH.creat(kh);
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại. Kiểm tra lại");
        }
    }

}
