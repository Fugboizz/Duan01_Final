/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.hoadon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class RepositoryHoaDon implements RepositoryHoaDonInterface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    // Lấy Dữ liệu hóa đơn chờ
    public List<HoaDon> getAllHDC() {
        List<HoaDon> listHDC = new ArrayList<>();
        sql = "select * from v_HoaDonCho where TrangThai = 0";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();

            while (res.next()) {
                HoaDon hd = new HoaDon();
                hd.setIDHoaDon(res.getString("IDHoaDon"));
                hd.setNgayTao(res.getDate("NgayTao"));

                Double tongGia = res.getDouble("TongGia");
                if (res.wasNull()) {
                    tongGia = null; // Set to null if TongGia is null
                }

                Double tongGiaGiam = res.getDouble("TongGiaGiam");
                if (res.wasNull()) {
                    tongGiaGiam = null; // Set to null if TongGiaGiam is null
                }

                // Use TongGiaGiam if it's not null, otherwise use TongGia
                if (tongGiaGiam != null) {
                    hd.setTongTienTRuoc(tongGiaGiam);
                } else {
                    hd.setTongTienTRuoc(tongGia != null ? tongGia : 0.0); // Default to 0.0 if both are null
                }

                hd.setTrangThai(res.getBoolean("TrangThai"));
                listHDC.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng các tài nguyên
            try {
                if (res != null) {
                    res.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listHDC;
    }

    @Override
    public int create() {
        sql = "insert into HoaDon(TrangThai) values(?)";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setBoolean(1, false);
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            // Đảm bảo đóng các tài nguyên
            try {
                if (res != null) {
                    res.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int delete(String text) {
        String sql = "delete HoaDonChiTiet where IDHoaDon = ? Delete HoaDon where IDHoaDon = ?";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, text);
            pre.setString(2, text);
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (pre != null) {
                    pre.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int update(String idkhach, String IDvoucher ,double tongTienTruoc,double tongTienSau,String idhoadon) {
        String sql = "update HoaDon set \n"
                + "IDKhachHang = ? \n"
                + ",IDTaiKhoan = ? \n"
                + ",IDVoucher = ? \n"
                + ",TongTienTruoc = ? \n"
                + ",TongTienSau = ? \n"
                + ",TrangThai = ? \n"
                + ",TrangThaiTichDiem = ?\n"
                + "where IDHoaDon = ?";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, idkhach);
            pre.setString(2, "TK0001");
            pre.setString(3,IDvoucher);
            pre.setDouble(4, tongTienTruoc);
            pre.setDouble(5, tongTienSau);
            pre.setBoolean(6, true);
            pre.setBoolean(7, true);
            pre.setString(8, idhoadon);
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (pre != null) {
                    pre.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
