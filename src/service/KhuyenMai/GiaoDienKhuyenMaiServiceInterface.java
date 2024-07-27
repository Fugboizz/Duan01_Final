/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.KhuyenMai;

import javax.swing.JTable;
import model.Voucher;

/**
 *
 * @author nguyentrikhoi
 */
public interface GiaoDienKhuyenMaiServiceInterface {
    public void fillToTable(JTable tbl);
    public void themVourcher(Voucher vc);
    public void suaVourcher(Voucher vc);
    
    
}
