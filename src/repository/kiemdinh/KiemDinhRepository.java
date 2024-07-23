/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.kiemdinh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.KiemDinh;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class KiemDinhRepository implements KiemDinhInterface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<KiemDinh> getAll() {
        List kd = new ArrayList();
        sql = "select * from KiemDinh";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {                
                KiemDinh kiemDinh = new KiemDinh();
                kiemDinh.setIDKiemDinh(res.getString(1));
                kiemDinh.setDonViKiemDinh(res.getString(2));
                kiemDinh.setNgayKiemDinh(res.getDate(3));
                kiemDinh.setTrangThai(res.getBoolean(6));
                kd.add(kiemDinh);
            }
            return kd;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int creat() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
