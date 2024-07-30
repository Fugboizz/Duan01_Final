/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.hoadonchitiet;

import java.util.List;
import model.HoaDonChiTiet;

/**
 *
 * @author HUNGpYN
 */
public interface RepositoryHoaDonChiTietInteface {
    public int create(HoaDonChiTiet hdct);
    public List<HoaDonChiTiet> getData(String idHoaDon);
    public int delete(String text);
}
