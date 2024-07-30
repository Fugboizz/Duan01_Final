/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.LichSuBanHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.GiamGia;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.KhachHang;
import model.SanPham;
import model.TaiKhoan;
import model.Voucher;
import until.jdbc;

/**
 *
 * @author nguyentrikhoi
 */
public class LichSuBanHangRepository implements LichSuBanHangInterface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<HoaDonChiTiet> getAll() {
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        sql = "SELECT * FROM v_showhoadon_hoadonchitiet where TrangThai = 1";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                KhachHang kh = new KhachHang();
                HoaDonChiTiet ct = new HoaDonChiTiet();
                SanPham sp = new SanPham();
                GiamGia gg = new GiamGia();
                HoaDon hd = new HoaDon();
                TaiKhoan tk = new TaiKhoan();
                Voucher v = new Voucher();
                hd.setIDHoaDon(res.getString("IDHoaDon"));
                kh.setIDKhachHang(res.getString("IDKhachHang"));
                kh.setHoTen(res.getString("KhachHangHoTen"));
                tk.setIDTaiKhoan(res.getString("IDTaiKhoan"));
                tk.setHoTen(res.getString("TaiKhoanHoTen"));
                String idVoucher = res.getString("IDVoucher");
                if (idVoucher != null) {
                    v.setIDVoucher(idVoucher);
                    v.setTyLe(res.getFloat("TyLe"));
                } else {
                    v.setIDVoucher(null);
                    v.setTyLe(0); // Nếu không có giảm giá, tỷ lệ giảm giá là 0
                }
                v.setTenVoucher(res.getString("TenVoucher"));
                hd.setIdTaiKhoan(tk);
                hd.setIdKhachHang(kh);
                hd.setIdVoucher(v);
                hd.setNgayTao(res.getDate("NgayTao"));
                hd.setTongTienTRuoc(res.getDouble("TongTienTruoc"));
                hd.setTongTienSau(res.getDouble("TongTienSau"));
                hd.setTrangThai(res.getBoolean("TrangThai"));
                String idGiamGia = res.getString("IDGiamGia");
                if (idGiamGia != null) {
                    gg.setIDGIamGia(idGiamGia);
                    gg.setTyLeGiamGia(res.getFloat("TyLeGiamGia"));
                } else {
                    gg.setIDGIamGia(null);
                    gg.setTyLeGiamGia(0); // Nếu không có giảm giá, tỷ lệ giảm giá là 0
                }
                sp.setIDGiamGia(gg);
                sp.setIDSanPham(res.getString("IDSanPham"));
                sp.setTenSanPham(res.getString("TenSanPham"));
                sp.setGiaChiTiet(res.getDouble("GiaChiTiet"));
                ct.setIDSanPham(sp);
                ct.setIDHoaDon(hd);
                ct.setSoLUongSanPHam(res.getInt("SoLuongSanPham"));
                listHDCT.add(ct);

            }
            return listHDCT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
