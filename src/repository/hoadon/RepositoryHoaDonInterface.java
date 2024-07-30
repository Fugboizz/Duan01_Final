/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.hoadon;

import java.util.List;
import model.HoaDon;

/**
 *
 * @author HUNGpYN
 */
public interface RepositoryHoaDonInterface {
      public List<HoaDon> getAllHDC();
      public int create();
       public int update(String idkhach, String IDvoucher ,double tongTienTruoc,double tongTienSau,String idhoadon);
      public int delete(String text);
}
