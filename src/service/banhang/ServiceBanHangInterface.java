/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.banhang;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author HUNGpYN
 */
public interface ServiceBanHangInterface{
    public void fillHoaDonCho(JTable tbl);
    public void addComboBox(JComboBox cbo);
    public void fillHoaDonChiTietBH(JTable tbl, String idHoaDon);
}
