/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.HinhAnhSanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HinhAnhSanPham;
import model.SanPham;
import until.jdbc;

/**
 *
 * @author WINDOWS10
 */
public class HinhAnhSanPhamrepo implements InterfaceHinhAnhSanPham {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<HinhAnhSanPham> getAll() {
        List hasp = new ArrayList();
        sql = "select * from HinhAnhSanPham";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                SanPham sp = new SanPham();
                sp.setIDSanPham(res.getString("IDSanPham"));
                HinhAnhSanPham ha;
                ha = new HinhAnhSanPham(res.getString("IDHinhAnhSanPham"), sp, res.getString("HinhAnhURL"), res.getDate("NgayTao"),
                res.getDate("NgaySua"), res.getBoolean("TrangThai"));
                hasp.add(ha);
            }
            return hasp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
