/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.BaoHanh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.BaoHanh;
import model.KhachHang;
import model.SanPham;
import until.jdbc;

/**
 *
 * @author nguyentrikhoi
 */
public class repoBaoHanh implements InterfaceRepoBaoHanh{
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<BaoHanh> getAll() {
    List<BaoHanh> listBH = new ArrayList<>();
    String sql = "SELECT * FROM View_BaoHanh_ChiTiet";
    
    try (Connection con = jdbc.getConnection();
         PreparedStatement pre = con.prepareStatement(sql);
         ResultSet res = pre.executeQuery()) {
        
        while (res.next()) {
            BaoHanh bh = new BaoHanh();
            bh.setIDBaoHanh(res.getString("IDBaoHanh"));
            bh.setGhiChu(res.getString("GhiChu"));
            bh.setNgayYeuCau(res.getDate("NgayYeuCau"));
            bh.setTrangThai(res.getBoolean("TrangThai"));
            
            KhachHang kh = new KhachHang();
            kh.setHoTen(res.getString("TenKhachHang"));
            kh.setSoDienThoai(res.getString("SoDienThoai"));
            kh.setDiaChi(res.getString("DiaChi"));
            
            SanPham sp = new SanPham();
            sp.setTenSanPham(res.getString("TenSanPham"));
            
            bh.setKhachHang(kh);
            bh.setSanPham(sp);
            
            listBH.add(bh);
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    return listBH;
}

    @Override
    public int them(BaoHanh bh) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int capNhat(BaoHanh bh) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
