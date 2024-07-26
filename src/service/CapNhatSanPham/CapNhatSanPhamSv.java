package service.CapNhatSanPham;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import repository.DaQuy.DaQuyRepository;
import repository.NhaCungCap.NhaCungCapRepository;
import repository.PhanLoai.PhanLoaiRepo;
import repository.Size.SizeRepository;
import repository.chatlieu.ChatLieuRepository;
import repository.kiemdinh.KiemDinhRepository;
import repository.mausac.MauSacRepository;
import view.until.combobox.ComboBoxSuggestion;
import model.KiemDinh;
import model.ChatLieu;
import model.DaQuy;
import model.MauSac;
import model.PhanLoai;
import model.NhaCungCap;
import model.Size;
import java.sql.Date;

/**
 * Lớp dịch vụ để cập nhật các danh sách vào ComboBox
 *
 * @author WINDOWS10
 */
public class CapNhatSanPhamSv implements InterfaceCapNhatSanPhamSv {

    private KiemDinhRepository rpkd = new KiemDinhRepository();
    private DaQuyRepository rpdq = new DaQuyRepository();
    private SizeRepository rpsz = new SizeRepository();
    private NhaCungCapRepository rpncc = new NhaCungCapRepository();
    private PhanLoaiRepo rppl = new PhanLoaiRepo();
    private MauSacRepository rpms = new MauSacRepository();
    private ChatLieuRepository rpcl = new ChatLieuRepository();

    private List<KiemDinh> lstKiemDinh = new ArrayList<>();
    private List<ChatLieu> lstChatLieus = new ArrayList<>();
    private List<DaQuy> lstDaQuys = new ArrayList<>();
    private List<MauSac> lstMauSacs = new ArrayList<>();
    private List<NhaCungCap> lstNhaCungCaps = new ArrayList<>();
    private List<Size> lstSizes = new ArrayList<>();
    private List<PhanLoai> lstPhanLoais = new ArrayList<>();

//    private Date parseDate(String dateStr) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            return dateStr.isEmpty() ? null : dateFormat.parse(dateStr);
//        } catch (ParseException e) {
//            showMessage("Định dạng ngày không hợp lệ: " + dateStr);
//            return null;
//        }
//    }
    @Override
    public String addCboKiemDinh(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstKiemDinh = rpkd.getAll();
        if (lstKiemDinh == null) {
            lstKiemDinh = new ArrayList<>();
        }
        cbos.removeAllItems();
        for (KiemDinh kiemDinh : lstKiemDinh) {
            if (kiemDinh != null) {
                cbos.addItem(kiemDinh.getDonViKiemDinh());

            }
        }
        return "Danh sách kiểm định đã được thêm vào ComboBox";
    }

    @Override
    public String addCboSize(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstSizes = rpsz.getAll();
        if (lstSizes == null) {
            lstSizes = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
        }
        cbos.removeAllItems();
        for (Size size : lstSizes) {
            if (size != null) {
                cbos.addItem(size.getSoSize());
            }
        }
        return "Danh sách kích thước đã được thêm vào ComboBox";
    }

    @Override
    public String addCboPhanLoai(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstPhanLoais = rppl.getAll();
        if (lstPhanLoais == null) {
            lstPhanLoais = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
        }
        cbos.removeAllItems();
        for (PhanLoai phanLoai : lstPhanLoais) {
            if (phanLoai != null) {
                cbos.addItem(phanLoai.getTenPhanLoai());
            }
        }
        return "Danh sách phân loại đã được thêm vào ComboBox";
    }

    @Override
    public String addCboChatLieu(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstChatLieus = rpcl.getAll();
        if (lstChatLieus == null) {
            lstChatLieus = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
        }
        cbos.removeAllItems();
        for (ChatLieu chatLieu : lstChatLieus) {
            if (chatLieu != null) {
                cbos.addItem(chatLieu.getTenChatLieu());
            }
        }
        return "Danh sách chất liệu đã được thêm vào ComboBox";
    }

    @Override
    public String addCboMauSac(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstMauSacs = rpms.getAll();
        if (lstMauSacs == null) {
            lstMauSacs = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
        }
        cbos.removeAllItems();
        for (MauSac mauSac : lstMauSacs) {
            if (mauSac != null) {
                cbos.addItem(mauSac.getChiTietMauSac());
            }
        }
        return "Danh sách màu sắc đã được thêm vào ComboBox";
    }

    @Override
    public String addCboLoaiDa(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstDaQuys = rpdq.getAll();
        if (lstDaQuys == null) {
            lstDaQuys = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
        }
        cbos.removeAllItems();
        for (DaQuy daQuy : lstDaQuys) {
            if (daQuy != null) {
                cbos.addItem(daQuy.getTenDaQuy());
            }
        }
        return "Danh sách loại đá đã được thêm vào ComboBox";
    }

    @Override
    public String addCboNhaCungCap(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstNhaCungCaps = rpncc.getAll();
        if (lstNhaCungCaps == null) {
            lstNhaCungCaps = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
        }
        cbos.removeAllItems();
        for (NhaCungCap nhaCungCap : lstNhaCungCaps) {
            if (nhaCungCap != null) {
                cbos.addItem(nhaCungCap.getTenNhaCungCap());
            }
        }
        return "Danh sách nhà cung cấp đã được thêm vào ComboBox";
    }

    @Override
    public boolean checkCbo(ComboBoxSuggestion cbos, String str) {
        if (cbos == null || str == null) {
            return false;
        }
        for (int i = 0; i < cbos.getItemCount(); i++) {
            if (cbos.getItemAt(i) != null && cbos.getItemAt(i).toString().equals(str)) {
                cbos.setSelectedItem(str);
                return true;
            }
        }
        return false;
    }

    @Override
    public void chonAnh(JLabel label) {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        filechooser.setDialogTitle("Chọn Ảnh");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        filechooser.setFileFilter(filter);
        int result = filechooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            selectedFile = selectedFile.getAbsoluteFile();
            String link = selectedFile.getAbsolutePath();
            try {
                Image img = ImageIO.read(selectedFile);

                if (img != null) {
                    label.setIcon(new ImageIcon(img.getScaledInstance((label.getWidth() - 2), (label.getHeight() - 2), Image.SCALE_SMOOTH)));
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.putClientProperty("imagepath", link);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String checkCboKiemDinh(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstKiemDinh = rpkd.getAll();
        for (KiemDinh kiemDinh : lstKiemDinh) {
            if (selectedItem.equalsIgnoreCase(kiemDinh.getDonViKiemDinh().trim())) {
                return kiemDinh.getIDKiemDinh();
            }
        }
        return null;
    }

    @Override
    public String checkCboSize(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstSizes = rpsz.getAll();
        for (Size size : lstSizes) {
            if (selectedItem.equalsIgnoreCase(String.valueOf(size.getSoSize()).trim())) {
                return size.getIDSize();
            }
        }

        return null;
    }

    @Override
    public String checkCboPhanLoai(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstPhanLoais = rppl.getAll();
        for (PhanLoai phanLoai : lstPhanLoais) {
            if (selectedItem.equalsIgnoreCase(phanLoai.getTenPhanLoai().trim())) {
                return phanLoai.getIDPhanLoai();
            }
        }
        return null;
    }

    @Override
    public String checkCboChatLieu(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstChatLieus = rpcl.getAll();
        for (ChatLieu chatLieu : lstChatLieus) {
            if (selectedItem.equalsIgnoreCase(chatLieu.getTenChatLieu().trim())) {
                return chatLieu.getIDChatLieu();
            }
        }
        return null;
    }

    @Override
    public String checkCboMauSac(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstMauSacs = rpms.getAll();
        for (MauSac mauSac : lstMauSacs) {
            if (selectedItem.equalsIgnoreCase(mauSac.getChiTietMauSac().trim())) {
                return mauSac.getIDMauSac();
            }
        }
        return null;
    }

    @Override
    public String checkCboLoaiDa(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstDaQuys = rpdq.getAll();
        for (DaQuy daQuy : lstDaQuys) {
            if (selectedItem.equalsIgnoreCase(daQuy.getTenDaQuy().trim())) {
                return daQuy.getIDDaQuy();
            }
        }
        return null;
    }

    @Override
    public String checkCboNhaCungCap(ComboBoxSuggestion cbos) {
        if (cbos == null || cbos.getSelectedItem() == null) {
            return null;
        }
        String selectedItem = cbos.getSelectedItem().toString().trim();
        lstNhaCungCaps = rpncc.getAll();
        for (NhaCungCap nhaCungCap : lstNhaCungCaps) {
            if (selectedItem.equalsIgnoreCase(nhaCungCap.getTenNhaCungCap().trim())) {
                return nhaCungCap.getIDNhaCungCap();
            }
        }
        return null;
    }

    @Override
    public void fillToPhanLoaiTbl(JTable tbl) {
        lstPhanLoais = rppl.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (PhanLoai pl : rppl.getAll()) {
            tableModel.addRow(new Object[]{pl.getIDPhanLoai(), pl.getTenPhanLoai(), pl.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToKiemDinhTbl(JTable tbl) {
        lstKiemDinh = rpkd.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (KiemDinh kd : lstKiemDinh) {
            java.sql.Date sqlDate = kd.getNgayKiemDinh();
            String formattedDate = dateFormat.format(sqlDate);

            tableModel.addRow(new Object[]{
                kd.getIDKiemDinh(),
                kd.getDonViKiemDinh(),
                formattedDate,
                kd.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"
            });
        }
    }

    @Override
    public void fillToChatLieuTbl(JTable tbl) {
        lstChatLieus = rpcl.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (ChatLieu cl : rpcl.getAll()) {
            tableModel.addRow(new Object[]{cl.getIDChatLieu(), cl.getTenChatLieu(), cl.getTyLe(), cl.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToSizeTbl(JTable tbl) {
        lstSizes = rpsz.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (Size sz : rpsz.getAll()) {
            tableModel.addRow(new Object[]{sz.getIDSize(), sz.getSoSize(), sz.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});

        }
    }

    @Override
    public void fillToNhaCungCapTbl(JTable tbl) {
        lstNhaCungCaps = rpncc.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (NhaCungCap ncc : rpncc.getAll()) {
            tableModel.addRow(new Object[]{ncc.getIDNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getEmail(), ncc.getSoDienThoai(), ncc.getDiaChi(), ncc.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToMauSacTbl(JTable tbl) {
        lstMauSacs = rpms.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (MauSac ms : rpms.getAll()) {
            tableModel.addRow(new Object[]{ms.getIDMauSac(), ms.getChiTietMauSac(), ms.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToDaQuyTbl(JTable tbl) {
        lstDaQuys = rpdq.getAll();
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (DaQuy dq : rpdq.getAll()) {
            tableModel.addRow(new Object[]{dq.getIDDaQuy(), dq.getTenDaQuy(), dq.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void getDataPhanLoai(JTable tbl, JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 0).toString());
            txtTen.setText(tbl.getValueAt(i, 1).toString());
            if (tbl.getValueAt(i, 2).toString().equalsIgnoreCase("Hoạt Động")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataKiemDinh(JTable tbl, JTextField txtID, JTextField txtTen, JTextField txtNgayKiemDinh, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 0).toString());
            txtTen.setText(tbl.getValueAt(i, 1).toString());
            txtNgayKiemDinh.setText(tbl.getValueAt(i, 2).toString());
            if (tbl.getValueAt(i, 3).toString().equalsIgnoreCase("Hoạt Động")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataMauSac(JTable tbl, JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 0).toString());
            txtTen.setText(tbl.getValueAt(i, 1).toString());
            if (tbl.getValueAt(i, 2).toString().equalsIgnoreCase("Hoạt Động")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataNhaCungCap(JTable tbl, JTextField txtID, JTextField txtTen, JTextField txtEmail, JTextField txtSDT, JTextArea txtDiaChi, JRadioButton rdo1, JRadioButton rdo2) {
        if (tbl == null || txtID == null || txtTen == null || txtEmail == null || txtSDT == null || txtDiaChi == null || rdo1 == null || rdo2 == null) {
            throw new IllegalArgumentException("One or more arguments are null");
        }

        int i = tbl.getSelectedRow();
        if (i >= 0) {
            Object id = tbl.getValueAt(i, 0);
            Object ten = tbl.getValueAt(i, 1);
            Object email = tbl.getValueAt(i, 2);
            Object sdt = tbl.getValueAt(i, 3);
            Object diaChi = tbl.getValueAt(i, 4);
            Object trangThai = tbl.getValueAt(i, 5);

            txtID.setText(id != null ? id.toString() : "");
            txtTen.setText(ten != null ? ten.toString() : "");
            txtEmail.setText(email != null ? email.toString() : "");
            txtSDT.setText(sdt != null ? sdt.toString() : "");
            txtDiaChi.setText(diaChi != null ? diaChi.toString() : "");

            if (trangThai != null && trangThai.toString().equalsIgnoreCase("Hoạt Động")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataChatLieu(JTable tbl, JTextField txtID, JTextField txtTen, JTextField txtTyLe, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 0).toString());
            txtTen.setText(tbl.getValueAt(i, 1).toString());
            txtTyLe.setText(tbl.getValueAt(i, 2).toString());
            if (tbl.getValueAt(i, 3).toString().equalsIgnoreCase("Hoạt Động")) {
                rdo1.setSelected(true);

            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataSize(JTable tbl, JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 0).toString());
            txtTen.setText(tbl.getValueAt(i, 1).toString());
            if (tbl.getValueAt(i, 2).toString().equalsIgnoreCase("Hoạt Động")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void getDataDaQuy(JTable tbl, JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 0).toString());
            txtTen.setText(tbl.getValueAt(i, 1).toString());
            if (tbl.getValueAt(i, 2).toString().equalsIgnoreCase("Hoạt Động")) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }
        }
    }

    @Override
    public void readFormPhanLoai(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        for (PhanLoai pl : lstPhanLoais) {
            if (txtID.getText().equals(pl.getIDPhanLoai())) {
                pl.setTenPhanLoai(txtTen.getText());
                pl.setTrangThai(rdo1.isSelected());
                if (rppl.updatePhanLoai(pl)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật PhanLoai thành công.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật PhanLoai thất bại.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Không tìm thấy PhanLoai với ID: " + txtID.getText());
    }

    @Override
    public void readFormMauSac(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        for (MauSac ms : lstMauSacs) {
            if (txtID.getText().equals(ms.getIDMauSac())) {
                ms.setChiTietMauSac(txtTen.getText());
                ms.setTrangThai(rdo1.isSelected());
                if (rpms.update(ms) != 0) {
                    JOptionPane.showMessageDialog(null, "Cập nhật MauSac thành công.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật MauSac thất bại.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Không tìm thấy MauSac với ID: " + txtID.getText());
    }

    @Override
    public void readFormKiemDinh(JTextField txtID, JTextField txtTen, JTextField txtNgayKiemDinh, JRadioButton rdo1, JRadioButton rdo2) {
        String id = txtID.getText().trim();
        String ten = txtTen.getText().trim();
        String ngayKiemDinhStr = txtNgayKiemDinh.getText().trim();

        // Kiểm tra nếu ID hoặc tên hoặc ngày kiểm định rỗng
        if (id.isEmpty() || ten.isEmpty() || ngayKiemDinhStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID, tên, và ngày kiểm định không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Chuyển đổi ngày từ chuỗi sang java.sql.Date
        java.sql.Date ngayKiemDinhSql;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Định dạng ngày
            java.util.Date ngayKiemDinhUtil = sdf.parse(ngayKiemDinhStr); // Phân tích chuỗi thành java.util.Date
            ngayKiemDinhSql = new java.sql.Date(ngayKiemDinhUtil.getTime()); // Chuyển đổi thành java.sql.Date
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày kiểm định không hợp lệ. Định dạng ngày phải là dd-MM-yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (KiemDinh kd : lstKiemDinh) {
            if (id.equals(kd.getIDKiemDinh())) {
                kd.setDonViKiemDinh(ten);
                kd.setNgayKiemDinh(ngayKiemDinhSql);
                kd.setTrangThai(rdo1.isSelected());

                // Cập nhật đối tượng KiemDinh
                if (rpkd.updateKiemDinh(kd) != 0) {
                    JOptionPane.showMessageDialog(null, "Cập nhật KiemDinh thành công.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật KiemDinh thất bại.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Không tìm thấy KiemDinh với ID: " + id);
    }

    @Override
    public void readFormChatLieu(JTextField txtID, JTextField txtTen, JTextField txtTyle, JRadioButton rdo1, JRadioButton rdo2) {
        for (ChatLieu cl : lstChatLieus) {
            if (txtID.getText().equals(cl.getIDChatLieu())) {
                cl.setTenChatLieu(txtTen.getText());
                cl.setTyLe(Float.parseFloat(txtTyle.getText()));
                cl.setTrangThai(rdo1.isSelected() ? true : false);

                if (rpcl.update(cl) != 0) {
                    JOptionPane.showMessageDialog(null, "Cập nhật ChatLieu thành công.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật ChatLieu thất bại.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Không tìm thấy ChatLieu với ID: " + txtID.getText());
    }

    @Override
    public void readFormNhaCungCap(JTextField txtID, JTextField txtTen, JTextField txtEmail, JTextField txtSDT, JTextArea txtDiaChi, JRadioButton rdo1, JRadioButton rdo2) {
        for (NhaCungCap ncc : lstNhaCungCaps) {
            if (txtID.getText().equals(ncc.getIDNhaCungCap())) {
                ncc.setTenNhaCungCap(txtTen.getText());
                ncc.setEmail(txtEmail.getText());
                ncc.setDiaChi(txtDiaChi.getText());
                ncc.setSoDienThoai(txtSDT.getText());
                ncc.setTrangThai(rdo1.isSelected());
                if (rpncc.updateNhaCungCap(ncc) != 0) {
                    JOptionPane.showMessageDialog(null, "Cập nhật NhaCungCap thành công.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật NhaCungCap thất bại.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Không tìm thấy NhaCungCap với ID: " + txtID.getText());
    }

    @Override
    public void readFormSize(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        for (Size sz : lstSizes) {
            if (txtID.getText().equals(sz.getIDSize())) {
                sz.setSoSize(Integer.valueOf(txtTen.getText()));
                sz.setTrangThai(rdo1.isSelected());
                if (rpsz.update(sz) != 0) {
                    JOptionPane.showMessageDialog(null, "Cập nhật Size thành công.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật Size thất bại.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Không tìm thấy Size với ID: " + txtID.getText());
    }

    @Override
    public void readFormDaQuy(JTextField txtID, JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        for (DaQuy dq : lstDaQuys) {
            if (txtID.getText().equals(dq.getIDDaQuy())) {
                dq.setTenDaQuy(txtTen.getText());
                dq.setTrangThai(rdo1.isSelected());
                if (rpdq.update(dq) != 0) {
                    JOptionPane.showMessageDialog(null, "Cập nhật DaQuy thành công.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật DaQuy thất bại.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Không tìm thấy DaQuy với ID: " + txtID.getText());
    }

    @Override
    public void clearList(String listName) {
        switch (listName) {
            case "KiemDinh":
                lstKiemDinh.clear();
                break;
            case "ChatLieu":
                lstChatLieus.clear();
                break;
            case "DaQuy":
                lstDaQuys.clear();
                break;
            case "MauSac":
                lstMauSacs.clear();
                break;
            case "NhaCungCap":
                lstNhaCungCaps.clear();
                break;
            case "Size":
                lstSizes.clear();
                break;
            case "PhanLoai":
                lstPhanLoais.clear();
                break;
            default:
                System.out.println("Danh sách không hợp lệ.");
        }
    }

    private void showMessage(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void InsertPhanLoai(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        String tenPhanLoai = txtTen.getText().trim();
        if (tenPhanLoai.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên phân loại không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PhanLoai pl = new PhanLoai();
        pl.setTenPhanLoai(txtTen.getText());
        pl.setTrangThai(rdo1.isSelected() ? true : false);
        boolean success = rppl.addPhanLoai(pl);
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm phân loại thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm phân loại thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void InsertMauSac(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        String tenMauSac = txtTen.getText().trim();
        if (tenMauSac.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên màu sắc không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        MauSac ms = new MauSac();
        ms.setChiTietMauSac(tenMauSac);
        ms.setTrangThai(rdo1.isSelected() ? true : false);
        boolean success = rpms.addMauSac(ms);
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm màu sắc thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm màu sắc thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void InsertKiemDinh(JTextField txtTen, JTextField txtNgayKiemDinh, JRadioButton rdo1, JRadioButton rdo2) {
        String tenKiemDinh = txtTen.getText().trim();
        String ngayKiemDinhStr = txtNgayKiemDinh.getText().trim();

        if (tenKiemDinh.isEmpty() || ngayKiemDinhStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên kiểm định và ngày kiểm định không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        java.sql.Date ngayKiemDinhSql;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Định dạng ngày
            java.util.Date ngayKiemDinhUtil = sdf.parse(ngayKiemDinhStr); // Phân tích chuỗi thành java.util.Date
            ngayKiemDinhSql = new java.sql.Date(ngayKiemDinhUtil.getTime()); // Chuyển đổi thành java.sql.Date
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Ngày kiểm định không hợp lệ. Định dạng ngày phải là dd-MM-yyyy.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        KiemDinh kd = new KiemDinh();
        kd.setDonViKiemDinh(tenKiemDinh);
        kd.setNgayKiemDinh(ngayKiemDinhSql);
        kd.setTrangThai(rdo1.isSelected());

        boolean success = rpkd.addKiemDinh(kd);
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm kiểm định thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm kiểm định thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void InsertChatLieu(JTextField txtTen, JTextField txtTyle, JRadioButton rdo1, JRadioButton rdo2) {
        String tenChatLieu = txtTen.getText().trim();
        String tyle = txtTyle.getText().trim();
        if (tenChatLieu.isEmpty() || tyle.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên chất liệu và tỷ lệ không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ChatLieu cl = new ChatLieu();
        cl.setTenChatLieu(tenChatLieu);
        cl.setTyLe(Float.parseFloat(tyle));
        cl.setTrangThai(rdo1.isSelected() ? true : false);
        boolean success = rpcl.creat(cl) > 0;
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm chất liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm chất liệu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void InsertNhaCungCap(JTextField txtTen, JTextField txtEmail, JTextField txtSDT, JTextArea txtDiaChi, JRadioButton rdo1, JRadioButton rdo2) {
        String soDienThoai = txtSDT.getText().trim();
        if (soDienThoai.length() > 10) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không được vượt quá 10 ký tự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        NhaCungCap nc = new NhaCungCap();
        nc.setTenNhaCungCap(txtTen.getText());
        nc.setSoDienThoai(soDienThoai);
        nc.setDiaChi(txtDiaChi.getText());
        nc.setEmail(txtEmail.getText());
        nc.setTrangThai(rdo1.isSelected());
        boolean success = rpncc.addNhaCungCap(nc);
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void InsertSize(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        int tenSize;
        try {
            tenSize = Integer.parseInt(txtTen.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tên kích cỡ phải là một số nguyên hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (tenSize <= 0) {
            JOptionPane.showMessageDialog(null, "Tên kích cỡ không được để trống và phải lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Size sz = new Size();
        sz.setSoSize(tenSize);
        sz.setTrangThai(rdo1.isSelected());
        boolean success = rpsz.creat(sz) > 0;
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm kích cỡ thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm kích cỡ thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void InsertDaQuy(JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2) {
        String tenDaQuy = txtTen.getText().trim();
        if (tenDaQuy.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên đá quý không được để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DaQuy dq = new DaQuy();
        dq.setTenDaQuy(tenDaQuy);
        dq.setTrangThai(rdo1.isSelected() ? true : false);
        boolean success = rpdq.creat(dq) > 0;
        if (success) {
            JOptionPane.showMessageDialog(null, "Thêm đá quý thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm đá quý thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

}
