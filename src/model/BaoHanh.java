/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author WINDOWS10
 */
public class BaoHanh {
    private String IDBaoHanh ;
    private KhachHang IDKhachHang;
    private HoaDonChiTiet IDHoaDonChiTiet;
    private Date NgayYeuCau ;
    private String GhiChu ;
    private Date NgayTao; 
    private Date NgaySua ;
    private boolean TrangThai ;

    public BaoHanh() {
    }

    public BaoHanh(String IDBaoHanh, KhachHang IDKhachHang, HoaDonChiTiet IDHoaDonChiTiet, Date NgayYeuCau, String GhiChu, Date NgayTao, Date NgaySua, boolean TrangThai) {
        this.IDBaoHanh = IDBaoHanh;
        this.IDKhachHang = IDKhachHang;
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
        this.NgayYeuCau = NgayYeuCau;
        this.GhiChu = GhiChu;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public String getIDBaoHanh() {
        return IDBaoHanh;
    }

    public void setIDBaoHanh(String IDBaoHanh) {
        this.IDBaoHanh = IDBaoHanh;
    }

    public KhachHang getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(KhachHang IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public HoaDonChiTiet getIDHoaDonChiTiet() {
        return IDHoaDonChiTiet;
    }

    public void setIDHoaDonChiTiet(HoaDonChiTiet IDHoaDonChiTiet) {
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
    }

    public Date getNgayYeuCau() {
        return NgayYeuCau;
    }

    public void setNgayYeuCau(Date NgayYeuCau) {
        this.NgayYeuCau = NgayYeuCau;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgaySua() {
        return NgaySua;
    }

    public void setNgaySua(Date NgaySua) {
        this.NgaySua = NgaySua;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
