/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.KhuyenMai;

import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Voucher;

/**
 *
 * @author nguyentrikhoi
 */
public class GiaoDienKhuyenMaiService implements GiaoDienKhuyenMaiServiceInterface{
    private DefaultTableModel model;
    private repository.KhuyenMai.KhuyenMaiRepository rpKM = new repository.KhuyenMai.KhuyenMaiRepository();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void fillToTable(JTable tbl) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        for (Voucher vc : rpKM.getAll()) {
            String ngayBatDauFormatted = sdf.format(vc.getNgayBatDau());
            String ngayKetThucFormatted = sdf.format(vc.getNgayKetThuc());
            model.addRow(new Object[]{vc.getIDVoucher(), vc.getTenVoucher(), vc.getTyLe(), ngayBatDauFormatted, ngayKetThucFormatted,  vc.isTrangThai()?"Hoạt Động":"Dừng Hoạt Động"});
        }
    }

    @Override
    public void themVourcher(Voucher vc) {
        if (vc != null) {
            rpKM.creat(vc);
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại. Kiểm tra lại");
        }
    }

    @Override
    public void suaVourcher(Voucher vc) {
        if (vc != null) {
            rpKM.update(vc);
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại. Kiểm tra lại");
        }
    }
    
    
}
