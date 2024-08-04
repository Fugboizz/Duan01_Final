/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.BaoHanh;

import java.util.List;
import model.BaoHanh;
import model.HoaDonChiTiet;

/**
 *
 * @author nguyentrikhoi
 */
public interface InterfaceRepoBaoHanh {
    public List<BaoHanh> getAll();
    
    public List<HoaDonChiTiet> getSanPhamBaoHanh(String SoDienThoaiBaoHanh);
    
    public int them(BaoHanh bh);

    public int capNhat(BaoHanh bh);
    
}
