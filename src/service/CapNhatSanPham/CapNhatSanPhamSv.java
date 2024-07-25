package service.CapNhatSanPham;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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

    private List<KiemDinh> lstKiemDinh;
    private List<ChatLieu> lstChatLieus;
    private List<DaQuy> lstDaQuys;
    private List<MauSac> lstMauSacs;
    private List<NhaCungCap> lstNhaCungCaps;
    private List<Size> lstSizes;
    private List<PhanLoai> lstPhanLoais;

    public CapNhatSanPhamSv() {
        lstKiemDinh = new ArrayList<>();
        lstChatLieus = new ArrayList<>();
        lstDaQuys = new ArrayList<>();
        lstMauSacs = new ArrayList<>();
        lstNhaCungCaps = new ArrayList<>();
        lstSizes = new ArrayList<>();
        lstPhanLoais = new ArrayList<>();
    }

    @Override
    public String addCboKiemDinh(ComboBoxSuggestion cbos) {
        if (cbos == null) {
            return "ComboBox không được khởi tạo.";
        }
        lstKiemDinh = rpkd.getAll();
        if (lstKiemDinh == null) {
            lstKiemDinh = new ArrayList<>(); // Khởi tạo danh sách nếu nó là null
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
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (PhanLoai pl : rppl.getAll()) {
            tableModel.addRow(new Object[]{pl.getIDPhanLoai(), pl.getTenPhanLoai(), pl.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToKiemDinhTbl(JTable tbl) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (KiemDinh kd : rpkd.getAll()) {
            tableModel.addRow(new Object[]{kd.getIDKiemDinh(), kd.getDonViKiemDinh(), kd.getNgayKiemDinh(), kd.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToChatLieuTbl(JTable tbl) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (ChatLieu cl : rpcl.getAll()) {
            tableModel.addRow(new Object[]{cl.getIDChatLieu(), cl.getTenChatLieu(), cl.getTyLe(), cl.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToSizeTbl(JTable tbl) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (Size sz : rpsz.getAll()) {
            tableModel.addRow(new Object[]{sz.getIDSize(), sz.getSoSize(), sz.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToNhaCungCapTbl(JTable tbl) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (NhaCungCap ncc : rpncc.getAll()) {
            tableModel.addRow(new Object[]{ncc.getIDNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getEmail(), ncc.getSoDienThoai(), ncc.getDiaChi(), ncc.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToMauSacTbl(JTable tbl) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (MauSac ms : rpms.getAll()) {
            tableModel.addRow(new Object[]{ms.getIDMauSac(), ms.getChiTietMauSac(), ms.isTrangThai() ? "Hoạt Động" : "Ngừng Hoạt Động"});
        }
    }

    @Override
    public void fillToDaQuyTbl(JTable tbl) {
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
        int i = tbl.getSelectedRow();
        if (i >= 0) {
            txtID.setText(tbl.getValueAt(i, 0).toString());
            txtTen.setText(tbl.getValueAt(i, 1).toString());
            txtEmail.setText(tbl.getValueAt(i, 2).toString());
            txtSDT.setText(tbl.getValueAt(i, 3).toString());
            txtDiaChi.setText(tbl.getValueAt(i, 4).toString());
            if (tbl.getValueAt(i, 5).toString().equalsIgnoreCase("Hoạt Động")) {
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

}
