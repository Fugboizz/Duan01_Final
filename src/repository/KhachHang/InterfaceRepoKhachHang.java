/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.KhachHang;

import java.util.List;
import model.KhachHang;

/**
 *
 * @author nguyentrikhoi
 */
public interface InterfaceRepoKhachHang {
    public List<KhachHang> getAll();

    public int creat(KhachHang kh);

    public int update(KhachHang kh);

}
