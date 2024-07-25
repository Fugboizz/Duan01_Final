/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.taikhoan;

import java.util.List;
import model.TaiKhoan;

/**
 *
 * @author HUNGpYN
 */
public interface RepositoryTaiKhoanInterface {
                public List<TaiKhoan> getAll();
    public int creat(TaiKhoan tk);
    public int update(TaiKhoan tk);
    public TaiKhoan getDataClicked(String tk);
}
