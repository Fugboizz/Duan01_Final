/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.taikhoan;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.TaiKhoan;
import repository.taikhoan.RepositoryTaiKhoan;
import view.main.Main;
import view.nhanvien.CapNhatNhanVien;

public class ServiceTaiKhoan implements ServiceTaiKhoanInterface {

    private RepositoryTaiKhoan rptk = new RepositoryTaiKhoan();
    private Main main;

    @Override
    public void fillToTable(JTable tbl) {
        DefaultTableModel tableModel = (DefaultTableModel) tbl.getModel();
        tableModel.setRowCount(0);
        for (TaiKhoan tk : rptk.getAll()) {
            tableModel.addRow(new Object[]{tk.getIDTaiKhoan(), tk.getHoTen(), tk.getTaiKhoan(), tk.getMatKhau(), tk.isGioiTinh() ? "Nam" : "Nữ",
                tk.getSoDienThoai(), tk.isChucVu() ? "Quản Lí" : "Nhân Viên", tk.isTrangThai() ? "Làm Việc" : "Nghỉ Việc"});
        }
    }

    @Override
    public void doubleCicled(JTable tbl) {
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tbl.getSelectedRow();
                    if (row >= 0) {
                        for (TaiKhoan tk : rptk.getAll()) {
                            if (tk.getIDTaiKhoan().equalsIgnoreCase(tbl.getValueAt(row, 0).toString())) {
                                CapNhatNhanVien cnnv = new CapNhatNhanVien(main, true);
                                cnnv.setData(tk);
                                cnnv.setVisible(true);
                                break;
                            }

                        }

                    }
                }
            }

        });
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
}
