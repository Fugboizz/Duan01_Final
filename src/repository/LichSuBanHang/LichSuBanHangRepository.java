/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.LichSuBanHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.GiamGia;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.SanPham;
import model.TaiKhoan;
import model.Voucher;
import until.jdbc;

/**
 *
 * @author nguyentrikhoi
 */
public class LichSuBanHangRepository implements LichSuBanHangInterface{
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<HoaDon> getAll() {
        List<HoaDon> listHD = new ArrayList<>();
        sql = "SELECT * FROM View_HoaDon_ChiTiet";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {                
                HoaDon hd = new HoaDon();
                hd.setIDHoaDon(res.getString("IDHoaDon"));
                hd.setNgayTao(res.getDate("NgayTao"));
                TaiKhoan tk = new TaiKhoan();
                tk.setHoTen(res.getString("TaiKhoan_HoTen"));
                hd.setIdTaiKhoan(tk);
                
                KhachHang kh = new KhachHang();
                kh.setHoTen(res.getString("KhachHang_HoTen"));
                hd.setIdKhachHang(kh);
                
                hd.setTongTienSau(res.getDouble("TongTienSau"));
                
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<HoaDonChiTiet> getData(String IDHoaDon) {
    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    sql = "SELECT * FROM View_HoaDonChiTiet WHERE IDHoaDon = ?";
    try {
        con = jdbc.getConnection();
        pre = con.prepareStatement(sql);
        pre.setString(1, "%"+IDHoaDon+"%");
        res = pre.executeQuery();
        while (res.next()) {                
            HoaDonChiTiet hdCT = new HoaDonChiTiet();
            
            SanPham sp = new SanPham();
            sp.setTenSanPham(res.getString("TenSanPham"));
            sp.setGiaChiTiet(res.getDouble("GiaChiTiet"));
            hdCT.setIDSanPham(sp);
            hdCT.setSoLUongSanPHam(res.getInt("SoLuongSanPham"));
            GiamGia gg = new GiamGia();
            gg.setTyLeGiamGia(res.getFloat("TyLeGiamGia"));
            sp.setIDGiamGia(gg);
            hdCT.setIDSanPham(sp);
            
            listHDCT.add(hdCT);
        }
        return listHDCT;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
