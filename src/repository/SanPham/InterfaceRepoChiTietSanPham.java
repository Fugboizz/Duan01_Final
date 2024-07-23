/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.SanPham;

import java.util.List;
import model.SanPham;

/**
 *
 * @author WINDOWS10
 */
public interface InterfaceRepoChiTietSanPham {
    public List<SanPham> getAll();
    public List<SanPham> getAllWithConditional (SanPham sp);
    public int add(SanPham ct);
    public SanPham getSanPhamSua(String string);
    public SanPham getChiTietSanPham(String string);
    public int delete(String string);
     public int update(SanPham ct, String string);
}
