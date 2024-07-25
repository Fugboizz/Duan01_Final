package service.KhachHang;


import javax.swing.JTable;
import model.KhachHang;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author nguyentrikhoi
 */
public interface GiaoDienKhachHangServiceInterface {
    public void fillToTable(JTable tbl);
    public void themKhachHang(KhachHang ThemMoiKH);
}
