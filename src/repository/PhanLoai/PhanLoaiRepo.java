/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.PhanLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.PhanLoai;
import until.jdbc;

/**
 *
 * @author WINDOWS10
 */
public class PhanLoaiRepo implements InterfaceRepoPhanLoai {
    
    private List<PhanLoai> listpl = new ArrayList<>();
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;
    @Override
    public List<PhanLoai> getAll() {
        sql = "select * from PhanLoai";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {                
               PhanLoai pl = new PhanLoai(res.getString("IDPhanLoai"), res.getString("TenPhanLoai"), res.getBoolean("TrangThai"), res.getDate("NgayTao"), res.getDate("NgaySua"));
               listpl.add(pl);
            }
            return listpl;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
}
