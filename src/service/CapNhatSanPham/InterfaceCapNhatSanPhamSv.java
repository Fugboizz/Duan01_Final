/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.CapNhatSanPham;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import view.until.combobox.ComboBoxSuggestion;

/**
 *
 * @author WINDOWS10
 */
public interface InterfaceCapNhatSanPhamSv {

    String addCboKiemDinh(ComboBoxSuggestion cbos);

    String addCboSize(ComboBoxSuggestion cbos);

    String addCboPhanLoai(ComboBoxSuggestion cbos);

    String addCboChatLieu(ComboBoxSuggestion cbos);

    String addCboMauSac(ComboBoxSuggestion cbos);

    String addCboLoaiDa(ComboBoxSuggestion cbos);

    String addCboNhaCungCap(ComboBoxSuggestion cbos);

    boolean checkCbo(ComboBoxSuggestion cbos, String str);

    public void chonAnh(JLabel label);

    String checkCboKiemDinh(ComboBoxSuggestion cbos);

    String checkCboSize(ComboBoxSuggestion cbos);

    String checkCboPhanLoai(ComboBoxSuggestion cbos);

    String checkCboChatLieu(ComboBoxSuggestion cbos);

    String checkCboMauSac(ComboBoxSuggestion cbos);

    String checkCboLoaiDa(ComboBoxSuggestion cbos);

    String checkCboNhaCungCap(ComboBoxSuggestion cbos);

    public void fillToPhanLoaiTbl(JTable tbl);

    public void fillToKiemDinhTbl(JTable tbl);

    public void fillToChatLieuTbl(JTable tbl);

    public void fillToSizeTbl(JTable tbl);

    public void fillToNhaCungCapTbl(JTable tbl);

    public void fillToMauSacTbl(JTable tbl);

    public void fillToDaQuyTbl(JTable tbl);
    
    public void getDataPhanLoai(JTable tbl, JTextField txtID,JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2);
    public void getDataKiemDinh(JTable tbl, JTextField txtID,JTextField txtTen,JTextField txtNgayKiemDinh, JRadioButton rdo1, JRadioButton rdo2);
    public void getDataMauSac(JTable tbl, JTextField txtID,JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2);
    public void getDataNhaCungCap(JTable tbl, JTextField txtID,JTextField txtTen,JTextField txtEmail,JTextField txtSDT,JTextArea txtDiaChi, JRadioButton rdo1, JRadioButton rdo2);
    public void getDataChatLieu(JTable tbl, JTextField txtID,JTextField txtTen,JTextField txtTyLe, JRadioButton rdo1, JRadioButton rdo2);
    public void getDataSize(JTable tbl, JTextField txtID,JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2);
    public void getDataDaQuy(JTable tbl, JTextField txtID,JTextField txtTen, JRadioButton rdo1, JRadioButton rdo2);
}
