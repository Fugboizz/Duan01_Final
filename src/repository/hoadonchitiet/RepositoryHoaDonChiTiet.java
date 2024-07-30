/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.hoadonchitiet;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.GiamGia;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.SanPham;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class RepositoryHoaDonChiTiet implements RepositoryHoaDonChiTietInteface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;
    private CallableStatement call;

    @Override
    public int create(HoaDonChiTiet hdct) {
        String sql = "EXEC sp_ThemHoaDonChiTiet @IDSanPham = ?, @IDHoaDon = ?, @SoLuongSanPham = ?, @TrangThai = ?";

        try {
            con = jdbc.getConnection();
            call = con.prepareCall(sql);

            // Cung cấp các giá trị cho các tham số của stored procedure
            call.setString(1, hdct.getIDSanPham().getIDSanPham());
            call.setString(2, hdct.getIDHoaDon().getIDHoaDon());
            call.setInt(3, hdct.getSoLUongSanPHam());
            call.setInt(4, 0); // TrangThai

            // Thực thi stored procedure và trả về số hàng bị ảnh hưởng
            return call.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (call != null) {
                    call.close();
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
    public List<HoaDonChiTiet> getData(String idHoaDon) {
        List<HoaDonChiTiet> listData = new ArrayList<>();
        sql = " select * from v_HoaDonChiTietBanHang where IDHoaDon = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, idHoaDon);
            res = pre.executeQuery();
            while (res.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                SanPham sp = new SanPham();
                sp.setIDSanPham(res.getString("IDSanPham"));
                sp.setGiaChiTiet(res.getDouble("GiaChiTiet"));
                sp.setTenSanPham(res.getString("TenSanPham"));
                GiamGia gg = new GiamGia();
                gg.setIDGIamGia(res.getString("IDGiamGia"));
                String idGiamGia = res.getString("IDGiamGia");
                if (res.wasNull()) {
                    idGiamGia = null;
                }
                gg.setIDGIamGia(idGiamGia);

                Float tyLeGiamGia = res.getFloat("TyLeGiamGia");
                if (res.wasNull()) {
                    tyLeGiamGia = 0.0f;
                }
                gg.setTyLeGiamGia(tyLeGiamGia);
                sp.setIDGiamGia(gg);

                hdct.setIDHoaDonChiTiet(res.getString("IDHoaDonChiTiet"));
                hdct.setIDSanPham(sp);
                HoaDon hd = new HoaDon();
                hd.setIDHoaDon("IDHoaDon");
                hdct.setIDHoaDon(hd);
                hdct.setSoLUongSanPHam(res.getInt("SoLuongSanPham"));
                listData.add(hdct);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    @Override
    public int delete(String text) {
                String sql = "Delete HoaDonChiTiet where IDHoaDonChiTiet = ?";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, text);
            return pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (call != null) {
                    call.close();
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
