/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;
import until.jdbc;

/**
 *
 * @author nguyentrikhoi
 */
public class repoKhachHang implements InterfaceRepoKhachHang{
    private List<KhachHang> listKH = new ArrayList<>();
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;


    @Override
    public List<KhachHang> getAll() {
      sql = "select * from KhachHang where TrangThai = 1";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            
            while (res.next()) {
               KhachHang kh = new KhachHang();
               kh.setIDKhachHang(res.getString("IDKhachHang"));
               kh.setHoTen(res.getString("HoTen"));
               kh.setSoDienThoai(res.getString("SoDienThoai"));
               kh.setDiaChi(res.getString("DiaChi"));
               kh.setEmail(res.getString("Email"));
               kh.setTichDiem(res.getInt("TichDiem"));
               kh.setTrangThai(res.getBoolean("TrangThai"));
               listKH.add(kh);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int creat(KhachHang kh) {
        sql = "INSERT into KhachHang(HoTen,SoDienThoai,DiaChi,Email) VALUES(?,?,?,?)";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, kh.getHoTen());
            pre.setString(2, kh.getSoDienThoai());
            pre.setString(3, kh.getDiaChi());
            pre.setString(4, kh.getEmail());
            
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(KhachHang kh) {
        sql = "UPDATE KhachHang SET HoTen = ?, SoDienThoai = ?, DiaChi = ?, Email = ?, TrangThai = ? WHERE IDKhachHang = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, kh.getHoTen());
            pre.setString(2, kh.getSoDienThoai());
            pre.setString(3, kh.getDiaChi());
            pre.setString(4, kh.getEmail());
            pre.setBoolean(4, kh.isTrangThai());
            pre.setString(4, kh.getIDKhachHang());
            
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    

   
     
}
