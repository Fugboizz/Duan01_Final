/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.KhuyenMai;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.GiamGia;
import model.GiaoDien.GiaoDienKhuyenMaiModel;
import model.GiaoDien.ThemGGModel;
import model.SanPham;
import model.Voucher;
import view.until.combobox.ComboBoxSuggestion;

/**
 *
 * @author nguyentrikhoi
 */
public class GiaoDienKhuyenMaiService implements GiaoDienKhuyenMaiServiceInterface {

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
            model.addRow(new Object[]{vc.getIDVoucher(), vc.getTenVoucher(), vc.getTyLe(), ngayBatDauFormatted, ngayKetThucFormatted, vc.isTrangThai() ? "Hoạt Động" : "Dừng Hoạt Động"});
        }
    }

    @Override
    public void themVourcher(Voucher vc) {
        if (vc != null) {
            rpKM.creat(vc);
        }
    }

    @Override
    public void suaVourcher(Voucher vc) {
        if (vc != null) {
            rpKM.update(vc);
        }
    }

    @Override
    public void fillToTableByID(JTable tbl, String str) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        rpKM.getAllByID(str).clear();
        for (Voucher vc : rpKM.getAllByID(str)) {
            String ngayBatDauFormatted = sdf.format(vc.getNgayBatDau());
            String ngayKetThucFormatted = sdf.format(vc.getNgayKetThuc());
            model.addRow(new Object[]{vc.getIDVoucher(), vc.getTenVoucher(), vc.getTyLe(), ngayBatDauFormatted, ngayKetThucFormatted, vc.isTrangThai() ? "Hoạt Động" : "Dừng Hoạt Động"});
        }
    }

    @Override
    public void fillTableGiamGia(JTable tbl) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        int i = 0;
        for (GiamGia gg : rpKM.getAllGiamGia()) {
            String ngayBatDauFormatted = sdf.format(gg.getNgayBatDau());
            String ngayKetThucFormatted = sdf.format(gg.getNgayKetThuc());
            model.addRow(new Object[]{i++, gg.getIDGIamGia(), gg.getTenMaGiamGia(), gg.getTyLeGiamGia(), ngayBatDauFormatted, ngayKetThucFormatted, gg.isTrangThai() ? "Hoạt Động" : "Dừng Hoạt Động"});
        }
    }

    @Override
    public void SearchGiamGiaGG(JTable tbl, GiaoDienKhuyenMaiModel gdkmm) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        int i = 0;
        for (GiamGia gg : rpKM.SearchGiamGiaGG(gdkmm)) {
            String ngayBatDauFormatted = sdf.format(gg.getNgayBatDau());
            String ngayKetThucFormatted = sdf.format(gg.getNgayKetThuc());
            model.addRow(new Object[]{i++, gg.getIDGIamGia(), gg.getTenMaGiamGia(), gg.getTyLeGiamGia(), ngayBatDauFormatted, ngayKetThucFormatted, gg.isTrangThai() ? "Hoạt Động" : "Dừng Hoạt Động"});
        }
    }

    @Override
    public void SearchGiamGiaSp(JTable tbl, GiaoDienKhuyenMaiModel gdkmm) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        int i = 0;
        for (SanPham sp : rpKM.SearchGiamGiaSP(gdkmm)) {
            String ngayBatDauFormatted = sdf.format(sp.getIDGiamGia().getNgayBatDau());
            String ngayKetThucFormatted = sdf.format(sp.getIDGiamGia().getNgayKetThuc());
            model.addRow(new Object[]{false, i++, sp.getIDGiamGia().getIDGIamGia(), sp.getIDSanPham(), sp.getIDPhanLoai().getTenPhanLoai(), ngayBatDauFormatted, ngayKetThucFormatted, sp.getIDGiamGia().getTyLeGiamGia()});
        }
    }

    @Override
    public String fillCbo(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        cbos.removeAllItems();
        cbos.addItem("Tất Cả");
        for (GiamGia gg : rpKM.getAllGiamGia()) {
            if (gg != null) {
                cbos.addItem(gg.getIDGIamGia());

            }
        }
        return "Danh sách kiểm định đã được thêm vào ComboBox";
    }

    @Override
    public void fillTableGiamGiaSP(JTable tbl) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        int i = 0;
        for (SanPham sp : rpKM.fillTableGiamGiaSP()) {
            String ngayBatDauFormatted = sdf.format(sp.getIDGiamGia().getNgayBatDau());
            String ngayKetThucFormatted = sdf.format(sp.getIDGiamGia().getNgayKetThuc());
            model.addRow(new Object[]{false, i++, sp.getIDGiamGia().getIDGIamGia(), sp.getIDSanPham(), sp.getIDPhanLoai().getTenPhanLoai(), ngayBatDauFormatted, ngayKetThucFormatted, sp.getIDGiamGia().getTyLeGiamGia()});
        }
    }

    @Override
    public void fillWhenClick(JTable tbl, JTextField TenGG, JTextField TyleField, JTextField Ngaybd, JTextField NgayKt, JRadioButton btn1, JRadioButton btn2, JLabel MaGG) {
        int index = tbl.getSelectedRow();
        if (index == -1) {
            return;
        }
        String idGiamGia = tbl.getValueAt(index, 1).toString();
        List<GiamGia> giamGiaList = rpKM.getAllByIDGiamGia(idGiamGia);
        if (giamGiaList.isEmpty()) {
            return;
        }
        GiamGia gg = giamGiaList.get(0);
        TenGG.setText(gg.getTenMaGiamGia());
        String ngayBatDauFormatted = sdf.format(gg.getNgayBatDau());
        String ngayKetThucFormatted = sdf.format(gg.getNgayKetThuc());
        MaGG.setText(gg.getIDGIamGia());
        TyleField.setText(String.valueOf(gg.getTyLeGiamGia()));
        Ngaybd.setText(ngayBatDauFormatted);
        NgayKt.setText(ngayKetThucFormatted);
        if (gg.isTrangThai()) {
            btn1.setSelected(true);
        } else {
            btn2.setSelected(true);
        }
    }

    @Override
    public void addNewGG(JTextField TenGG, JTextField TyleField, JTextField Ngaybd, JTextField NgayKt, JRadioButton btn1, JRadioButton btn2, JLabel MaGG) {
        String tenGiamGia = TenGG.getText().trim();
        String tyleStr = TyleField.getText().trim();
        String ngayBatDauStr = Ngaybd.getText().trim();
        String ngayKetThucStr = NgayKt.getText().trim();

        if (tenGiamGia.isEmpty() || tyleStr.isEmpty() || ngayBatDauStr.isEmpty() || ngayKetThucStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên giảm giá, tỷ lệ và ngày bắt đầu, ngày kết thúc không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        float tyleGiamGia;
        java.sql.Date ngayBatDauSql = null;
        java.sql.Date ngayKetThucSql = null;

        try {
            tyleGiamGia = Float.parseFloat(tyleStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDateStart = dateFormat.parse(ngayBatDauStr);
            java.util.Date utilDateEnd = dateFormat.parse(ngayKetThucStr);

            ngayBatDauSql = new java.sql.Date(utilDateStart.getTime());
            ngayKetThucSql = new java.sql.Date(utilDateEnd.getTime());
            if (ngayKetThucSql.before(ngayBatDauSql)) {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn ngày bắt đầu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tỷ lệ giảm giá phải là số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu và ngày kết thúc không hợp lệ. Định dạng ngày phải là dd-MM-yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GiamGia gg = new GiamGia();
        gg.setTenMaGiamGia(tenGiamGia);
        gg.setTyLeGiamGia(tyleGiamGia);
        gg.setNgayBatDau(ngayBatDauSql);
        gg.setNgayKetThuc(ngayKetThucSql);
        gg.setTrangThai(btn1.isSelected());

        boolean success = rpKM.addGiamGia(gg);
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm giảm giá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm giảm giá thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void updateGG(JTextField TenGG, JTextField TyleField, JTextField Ngaybd, JTextField NgayKt, JRadioButton btn1, JRadioButton btn2, JLabel MaGG) {
        String tenGiamGia = TenGG.getText().trim();
        String tyleStr = TyleField.getText().trim();
        String ngayBatDauStr = Ngaybd.getText().trim();
        String ngayKetThucStr = NgayKt.getText().trim();
        String idGiamGia = MaGG.getText().trim();
        if (tenGiamGia.isEmpty() || tyleStr.isEmpty() || ngayBatDauStr.isEmpty() || ngayKetThucStr.isEmpty() || idGiamGia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên giảm giá, tỷ lệ và ngày bắt đầu, ngày kết thúc, ID giảm giá không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        float tyleGiamGia;
        java.sql.Date ngayBatDauSql = null;
        java.sql.Date ngayKetThucSql = null;

        try {
            tyleGiamGia = Float.parseFloat(tyleStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDateStart = dateFormat.parse(ngayBatDauStr);
            java.util.Date utilDateEnd = dateFormat.parse(ngayKetThucStr);
            ngayBatDauSql = new java.sql.Date(utilDateStart.getTime());
            ngayKetThucSql = new java.sql.Date(utilDateEnd.getTime());

            if (ngayKetThucSql.before(ngayBatDauSql)) {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn ngày bắt đầu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tỷ lệ giảm giá phải là số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu và ngày kết thúc không hợp lệ. Định dạng ngày phải là dd-MM-yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GiamGia gg = new GiamGia();
        gg.setTenMaGiamGia(tenGiamGia);
        gg.setTyLeGiamGia(tyleGiamGia);
        gg.setNgayBatDau(ngayBatDauSql);
        gg.setNgayKetThuc(ngayKetThucSql);
        gg.setTrangThai(btn1.isSelected());
        System.out.println(tenGiamGia);
        System.out.println(tyleGiamGia);
        System.out.println(ngayBatDauSql);
        System.out.println(ngayKetThucSql);
        System.out.println(idGiamGia);
        boolean success = rpKM.updateGiamGia(gg, idGiamGia);
        if (success) {
            JOptionPane.showMessageDialog(null, "Cập nhật giảm giá thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật giảm giá thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void SearchTSp(JTable tbl, ThemGGModel tmd) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        int i = 0;
        for (SanPham sp : rpKM.SearchTSP(tmd)) {
            model.addRow(new Object[]{false, i++, sp.getIDSanPham(), sp.getTenSanPham(), sp.getIDPhanLoai().getTenPhanLoai()});
        }
    }

    @Override
    public String fillCbo2(ComboBoxSuggestion cbos, GiaoDienKhuyenMaiModel gdkmm) {

        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        cbos.removeAllItems();
        cbos.addItem("Tất Cả");
        for (SanPham sp : rpKM.SearchGiamGiaSP(gdkmm)) {
            if (sp != null) {
                cbos.addItem(sp.getIDPhanLoai().getTenPhanLoai());
            }
        }
        return "Danh sách kiểm định đã được thêm vào ComboBox";
    }

    @Override
    public List<String> checkSelectedRows(JTable table) {
        int rowCount = table.getRowCount();
        List<String> lstList = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            Boolean isSelected = (Boolean) table.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                String idSanPham = (String) table.getValueAt(i, 2);
                lstList.add(idSanPham);
                System.out.println(idSanPham + "checkRow");
            } 
            
        }
        return lstList;
    }

    @Override
    public List<String> checkSelectedRowsTB1(JTable table) {
        int rowCount = table.getRowCount();
        List<String> lstList = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            Boolean isSelected = (Boolean) table.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                String idSanPham = (String) table.getValueAt(i, 3);
                lstList.add(idSanPham);
                System.out.println(idSanPham + "checkRow1");
            } 
        }
        return lstList;
    }
}
