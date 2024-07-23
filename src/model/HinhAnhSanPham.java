/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.net.URL;
import java.util.Date;

/**
 *
 * @author WINDOWS10
 */
public class HinhAnhSanPham {
    private String IDHinhAnhSanPham ;
    private SanPham IDSanPham ;
    private String HinhAnhURL ;
    private Date NgayTao ;
    private Date NgaySua ;
    private boolean TrangThai;

    public HinhAnhSanPham() {
    }

    public HinhAnhSanPham(String IDHinhAnhSanPham, SanPham IDSanPham, String HinhAnhURL, Date NgayTao, Date NgaySua, boolean TrangThai) {
        this.IDHinhAnhSanPham = IDHinhAnhSanPham;
        this.IDSanPham = IDSanPham;
        this.HinhAnhURL = HinhAnhURL;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
        this.TrangThai = TrangThai;
    }

    public String getIDHinhAnhSanPham() {
        return IDHinhAnhSanPham;
    }

    public void setIDHinhAnhSanPham(String IDHinhAnhSanPham) {
        this.IDHinhAnhSanPham = IDHinhAnhSanPham;
    }

    public SanPham getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(SanPham IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public String getHinhAnhURL() {
        return HinhAnhURL;
    }

    public void setHinhAnhURL(String HinhAnhURL) {
        this.HinhAnhURL = HinhAnhURL;
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
