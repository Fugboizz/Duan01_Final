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
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.SanPham;
import until.jdbc;

/**
 *
 * @author nguyentrikhoi
 */
public class repoBaoHanh implements InterfaceRepoBaoHanh {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<BaoHanh> getAll() {
        List<BaoHanh> listBH = new ArrayList<>();
        sql = "SELECT * FROM View_BaoHanh_ChiTiet";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
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
        int rowsAffected = 0;
        String sql = "INSERT INTO BaoHanh (IDBaoHanh, IDKhachHang, IDHoaDonChiTiet, NgayYeuCau, GhiChu, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);

            pre.setString(1, bh.getIDBaoHanh());

            KhachHang kh = bh.getKhachHang();
            pre.setString(2, kh.getIDKhachHang());

            HoaDonChiTiet hdCT = new HoaDonChiTiet();
            pre.setString(3, hdCT.getIDHoaDonChiTiet());

            pre.setDate(4, new java.sql.Date(bh.getNgayYeuCau().getTime()));
            pre.setString(5, bh.getGhiChu());
            pre.setBoolean(6, bh.isTrangThai());

            rowsAffected = pre.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (pre != null) {
                    pre.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return rowsAffected;
    }

    @Override
    public int capNhat(BaoHanh bh) {
        int rowsAffected = 0;
        String sql = "UPDATE BaoHanh SET GhiChu = ?, TrangThai = ? WHERE IDBaoHanh = ?";

        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);

            pre.setString(1, bh.getGhiChu());
            pre.setBoolean(2, bh.isTrangThai());
            pre.setString(3, bh.getIDBaoHanh());

 
            rowsAffected = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (pre != null) {
                    pre.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return rowsAffected;
    }

    @Override
    public List<HoaDonChiTiet> getSanPhamBaoHanh(String soDienThoaiBaoHanh) {
        List<HoaDonChiTiet> listSP = new ArrayList<>();
        String sql = "SELECT IDSanPham, TenSanPham, ThoiHanBaoHanh FROM View_DanhSachSanPham_BaoHanh WHERE KhachHangSoDienThoai = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, soDienThoaiBaoHanh);
            try (ResultSet res = pre.executeQuery()) {
                while (res.next()) {
                    HoaDonChiTiet hdCT = new HoaDonChiTiet();
                    HoaDon hd = new HoaDon();
                    SanPham sp = new SanPham();

                    sp.setIDSanPham(res.getString("IDSanPham"));
                    sp.setTenSanPham(res.getString("TenSanPham"));
                    hdCT.setIDSanPham(sp);
                    hdCT.setThoiHanBaoHanh(res.getDate("ThoiHanBaoHanh"));
                    listSP.add(hdCT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listSP;
    }
}
