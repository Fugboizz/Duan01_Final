package repository.taikhoan;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TaiKhoan;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class RepositoryTaiKhoan implements RepositoryTaiKhoanInterface {

    private Connection conn = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql;

    @Override
    public List<TaiKhoan> getAll() {
        List<TaiKhoan> lisTk = new ArrayList<>();
        sql = "Select * from TaiKhoan";
        try {
            conn = jdbc.getConnection();
            pre = conn.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setIDTaiKhoan(res.getString(1));
                tk.setTaiKhoan(res.getString(2));
                tk.setMatKhau(res.getString(3));
                tk.setHoTen(res.getString(4));
                tk.setDiaChi(res.getString(5));
                tk.setSoDienThoai(res.getString(6));
                tk.setEmail(res.getString(7));
                tk.setNgaySinh(res.getDate(8));
                tk.setHinhAnh(res.getString(9));
                tk.setChucVu(res.getBoolean(10));
                tk.setGioiTinh(res.getBoolean(11));
                tk.setTrangThai(res.getBoolean(14));
                lisTk.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return lisTk;
    }

    @Override
    public int creat(TaiKhoan tk) {
        sql = "EXEC ThemTaiKhoan \n"
                + "    @TaiKhoan = ?,\n"
                + "    @MatKhau = ?,\n"
                + "    @HoTen = ?,\n"
                + "    @DiaChi = ?,\n"
                + "    @SoDienThoai = ?,\n"
                + "    @Email = ?,\n"
                + "    @NgaySinh = ?,\n"
                + "    @HinhAnh = ?,\n"
                + "    @ChucVu = ?,\n"
                + "    @GioiTinh = ?;";
        try {
            conn = jdbc.getConnection();
            CallableStatement call = conn.prepareCall(sql);
            call.setString(1, tk.getTaiKhoan());
            call.setString(2, tk.getMatKhau());
            call.setString(3, tk.getHoTen());
            call.setString(4, tk.getDiaChi());
            call.setString(5, tk.getSoDienThoai());
            call.setString(6, tk.getEmail());
            call.setDate(7, tk.getNgaySinh());
            call.setString(8, tk.getHinhAnh());
            call.setBoolean(9, tk.isChucVu());
            call.setBoolean(10, tk.isGioiTinh());
            return call.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(TaiKhoan tk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TaiKhoan getDataClicked(String id) {
        sql = "select * from TaiKhoan where IDTaiKhoan = ?";
        TaiKhoan tk = new TaiKhoan();
        try {
            conn = jdbc.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            res = pre.executeQuery();
            if (res.next()) {
                tk.setIDTaiKhoan(res.getString(1));
                tk.setTaiKhoan(res.getString(2));
                tk.setMatKhau(res.getString(3));
                tk.setHoTen(res.getString(4));
                tk.setDiaChi(res.getString(5));
                tk.setSoDienThoai(res.getString(6));
                tk.setEmail(res.getString(7));
                tk.setNgaySinh(res.getDate(8));
                tk.setHinhAnh(res.getString(9));
                tk.setChucVu(res.getBoolean(10));
                tk.setGioiTinh(res.getBoolean(11));
                tk.setTrangThai(res.getBoolean(14));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return tk;
    }

}
