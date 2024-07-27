/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.KhuyenMai;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Voucher;
import until.jdbc;

/**
 *
 * @author nguyentrikhoi
 */
public class KhuyenMaiRepository implements KhuyenMaiInterface{
    private List<Voucher> listVC = new ArrayList<>();
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<Voucher> getAll() {
        sql = "select * from Voucher";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                Voucher vc = new Voucher();
                vc.setIDVoucher(res.getString("IDVoucher"));
                vc.setTenVoucher(res.getString("TenVoucher"));
                vc.setTyLe(res.getFloat("TyLe"));
                vc.setNgayBatDau(res.getDate("NgayBatDau"));
                vc.setNgayKetThuc(res.getDate("NgayKetThuc"));
                vc.setTrangThai(res.getBoolean("TrangThai"));
                listVC.add(vc);
            }
            return listVC;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public int creat(Voucher vc) {
         sql = "INSERT into Voucher(TyLe, TenVoucher ,NgayBatDau, NgayKetThuc, TrangThai) VALUES (?,?,?,?,?)";
         try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            java.sql.Date sqlNgayBatDau = new java.sql.Date(vc.getNgayBatDau().getTime());
            java.sql.Date sqlNgayKetThuc = new java.sql.Date(vc.getNgayKetThuc().getTime());

            pre.setFloat(1, vc.getTyLe());
            pre.setString(2, vc.getTenVoucher());
            pre.setDate(3, sqlNgayBatDau);
            pre.setDate(4, sqlNgayKetThuc);
            pre.setBoolean(5, vc.isTrangThai());
            
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
   public int update(Voucher vc) {
    sql = "UPDATE Voucher Set TyLe = ?, TenVoucher =?, NgayBatDau = ?, NgayKetThuc = ?, TrangThai = ? where IDVoucher = ?";
    Connection con = null;
    PreparedStatement pre = null;
    try {
        con = jdbc.getConnection();
        pre = con.prepareStatement(sql);
        
        java.sql.Date sqlNgayBatDau = new java.sql.Date(vc.getNgayBatDau().getTime());
        java.sql.Date sqlNgayKetThuc = new java.sql.Date(vc.getNgayKetThuc().getTime());

        pre.setFloat(1, vc.getTyLe());
        pre.setString(2, vc.getTenVoucher());
        pre.setDate(3, sqlNgayBatDau);
        pre.setDate(4, sqlNgayKetThuc);
        pre.setBoolean(5, vc.isTrangThai());
        pre.setString(6, vc.getIDVoucher());

        return pre.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    } finally {
        try {
            if (pre != null) pre.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
}
