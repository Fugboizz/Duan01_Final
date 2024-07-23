/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.mausac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MauSac;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class MauSacRepository implements MauSacInterface{
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;
    @Override
    public List<MauSac> getAll() {
            List ms = new ArrayList();
        sql = "select * from MauSac where TrangThai = 1";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while(res.next()){
                MauSac  mauSac= new MauSac();
                mauSac.setIDMauSac(res.getString(1));
                mauSac.setChiTietMauSac(res.getString(2));
                ms.add(mauSac);
            }
            return ms;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int creat(MauSac ms) {
        sql = "EXEC ADDMauSac ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, ms.getChiTietMauSac());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(MauSac ms) {
        sql = "UPDATE MauSac SET ChiTietMauSac = ?   WHERE IDMauSac = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(2, ms.getIDMauSac());
            pre.setString(1, ms.getChiTietMauSac());

            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(MauSac ms) {
       sql = "UPDATE MauSac SET TrangThai = 0   WHERE IDMauSac = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, ms.getIDMauSac());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public MauSac getMauSacByID(MauSac idMauSac) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
