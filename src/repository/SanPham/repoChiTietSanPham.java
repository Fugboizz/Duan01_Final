/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.DaQuy;
import model.GiamGia;
import model.KiemDinh;
import model.MauSac;
import model.NhaCungCap;
import model.PhanLoai;
import model.SanPham;
import model.Size;
import until.jdbc;

/**
 *
 * @author WINDOWS10
 */
public class repoChiTietSanPham implements InterfaceRepoChiTietSanPham {

    private List<SanPham> listctsp = new ArrayList<>();
    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<SanPham> getAll() {
        sql = "select * from SanPham";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                KiemDinh kd = new KiemDinh();
                kd.setIDKiemDinh(res.getString(2));
                PhanLoai pl = new PhanLoai();
                pl.setIDPhanLoai(res.getString("IDPhanLoai"));
                MauSac ms = new MauSac();
                ms.setIDMauSac(res.getString("IDMauSac"));
                Size sz = new Size();
                sz.setIDSize(res.getString(4));
                ChatLieu cl = new ChatLieu();
                cl.setIDChatLieu(res.getString(6));
                NhaCungCap ncc = new NhaCungCap();
                ncc.setIDNhaCungCap(res.getString("IDNhaCungCap"));
                GiamGia gg = new GiamGia();
                gg.setIDGIamGia(res.getString("IDGiamGia"));
                DaQuy dq = new DaQuy();
                dq.setIDDaQuy(res.getString("IDDaQuy"));
                SanPham sp = new SanPham(res.getString("IDSanPham"), pl, kd, ms, sz, res.getString("TenSanPham"), res.getBoolean("GioiTinh"), cl,
                 res.getInt("SoLuongTonKho"), res.getDouble("GiaChiTiet"), gg, ncc, res.getInt("SoLuongDaQuy"), res.getFloat("KichThuocDa"), res.getFloat("TrongLuong"), dq, res.getDate("NgayTao"), res.getDate("NgaySua"), res.getBoolean("TrangThai"));
                listctsp.add(sp);
            }
            return listctsp;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

//    @Override
//    public List<SanPham> getAllWithConditional(model.GiaoDien.SanPham sp) {
//        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM v_ChiTiet WHERE 1=1 ");
//        List<Object> params = new ArrayList<>();
//
//        int defaultIntValue = -1;
//
//        if (sp.getGiamGia() != defaultIntValue) {
//            if (sp.getGiamGia() == 1) {
//                sqlBuilder.append(" AND IDGiamGia IN (SELECT IDGiamGia FROM GiamGia WHERE TrangThai = ?)");
//                params.add(true);
//            } else if (sp.getGiamGia() == 0) {
//                sqlBuilder.append(" AND IDGiamGia IN (SELECT IDGiamGia FROM GiamGia WHERE TrangThai = ?)");
//                params.add(false);
//            }
//        }
//
//        if (sp.getTrangThai() != defaultIntValue) {
//            if (sp.getTrangThai() == 1) {
//                sqlBuilder.append(" AND SoLuongTonKho > 0");
//            } else if (sp.getTrangThai() == 0) {
//                sqlBuilder.append(" AND SoLuongTonKho = 0");
//            }
//        }
//
//        if (sp.getGioiTinh() != defaultIntValue) {
//            if (sp.getGioiTinh() == 1) {
//                sqlBuilder.append(" AND GioiTinh = ?");
//                params.add("Nam");
//            } else if (sp.getGioiTinh() == 0) {
//                sqlBuilder.append(" AND GioiTinh = ?");
//                params.add("Nữ");
//            }
//        }
//
//        if (sp.getSanPham() != null && !sp.getSanPham().isEmpty()) {
//            sqlBuilder.append(" AND IDSanPham IN (SELECT IDSanPham FROM SanPham WHERE TenSanPham LIKE ?)");
//            params.add("%" + sp.getSanPham() + "%");
//        }
//        String sql = sqlBuilder.toString();
//        List<SanPham> listctsp = new ArrayList<>();
//
//        try (Connection con = jdbc.getConnection(); PreparedStatement pre = con.prepareStatement(sql)) {
//
//            for (int i = 0; i < params.size(); i++) {
//                pre.setObject(i + 1, params.get(i));
//            }
//
//            try (ResultSet res = pre.executeQuery()) {
//                while (res.next()) {
//                    KiemDinh kd = new KiemDinh();
//                    kd.setIDKiemDinh(res.getString(2));
//                    SanPham sanPham = new SanPham();
//                    sanPham.setIDSanPham(res.getString(3));
//                    GioiTinh gt = new GioiTinh();
//                    gt.setIDGioiTinh(res.getString("IDGioiTinh"));
//                    gt.setGioiTinh(res.getString("GioiTinh"));
//                    sanPham.setIDGioiTinh(gt);
//                    sanPham.setTenSanPham(res.getString("TenSanPham"));
//                    Size sz = new Size();
//                    sz.setIDSize(res.getString(4));
//                    ChatLieu cl = new ChatLieu();
//                    cl.setIDChatLieu(res.getString(6));
//                    NguonGoc ng = new NguonGoc();
//                    ng.setIDNguonGoc(res.getString("IDNguonGoc"));
//                    GiamGia gg = new GiamGia();
//                    gg.setIDGIamGia(res.getString("IDGiamGia"));
//                    DaQuy dq = new DaQuy();
//                    dq.setIDDaQuyString(res.getString("IDDaQuy"));
//                    ChiTietSanPham ctsp = new ChiTietSanPham(res.getString(1), kd, sanPham, sz, res.getString("Ten"), cl, res.getInt("SoLuongTonKho"),
//                            res.getInt("SoLuongDaQuy"), res.getDouble("GiaMoi"), res.getDouble("GiaCu"),
//                            gg, res.getString("HinhAnh"), ng, res.getBoolean("TrangThai"), res.getFloat("TrongLuong"), dq);
//                    listctsp.add(ctsp);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//
//        return listctsp;
//    }
//
//    @Override
//    public int add(ChiTietSanPham ctsp) {
//        sql = "INSERT INTO [dbo].[ChiTietSanPham]([IDKiemDinh],[IDSanPham],[IDSize],[Ten],[IDChatLieu],[SoLuongTonKho],[SoLuongDaQuy],[GiaMoi],[GiaCu],[IDGiamGia],[HinhAnh]\n"
//                + "           ,[IDNguonGoc],[TrangThai],[TrongLuong],[IDDaQuy])\n"
//                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//
//        try {
//            con = jdbc.getConnection();
//            pre = con.prepareStatement(sql);
////            KiemDinh kd = new KiemDinh();
////            SanPham sp = new SanPham();
////            Size size = new Size();
////            ChatLieu ct = new ChatLieu();
////            GiamGia gg = new GiamGia();
////            NguonGoc ng = new NguonGoc();
////            DaQuy dq = new DaQuy();
//
//            pre.setString(1, ctsp.getIDKIemDinh().getIDKiemDinh());
//            pre.setString(2, ctsp.getIDSanPham().getIDSanPham());
//            pre.setString(3, ctsp.getIDSize().getIDSize());
//            pre.setString(4, ctsp.getTen());
//            pre.setString(5, ctsp.getIDChatLieu().getIDChatLieu());
//            pre.setInt(6, ctsp.getSoLuongTonKho());
//            pre.setInt(7, ctsp.getSoLuongDaQuy());
//            pre.setDouble(8, ctsp.getGiaMoi());
//            pre.setDouble(9, ctsp.getGiaCu());
//            pre.setString(10, ctsp.getIDGiamGia().getIDGIamGia());
//            pre.setString(11, ctsp.getHinhAnh());
//            pre.setString(12, ctsp.getIDNguonGoc().getIDNguonGoc());
//            pre.setBoolean(13, ctsp.isTrangThai());
//            pre.setFloat(14, ctsp.getTrongLuong());
//            pre.setString(15, ctsp.getIDDaQuy().getIDDaQuyString());
//
//            return pre.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//
//    }
//    // xử lý kiểm tra ở database xem mã trang sức có không
//    @Override
//    public ChiTietSanPham getSanPhamSua(String string) {
//        sql = "select * from v_SuaSanPham where IDChiTietSanPham = ?";
//        
//        try {
//            con = jdbc.getConnection();
//            pre = con.prepareStatement(sql);
//            pre.setString(1, string);
//            res = pre.executeQuery();
//            if(res.next()){
//                ChiTietSanPham ctsp = new ChiTietSanPham();
//                SanPham sp = new SanPham();
//                sp.setTenSanPham(res.getString(1));
//                ctsp.setIDSanPham(sp);
//                ctsp.setTen(res.getString(2));
//                ctsp.setIDChiTietSanPham(res.getString(3));
//                ctsp.setGiaCu(res.getDouble(4));
//                Size s = new Size();
//                s.setSoSize(res.getInt(5));
//                ctsp.setIDSize(s);
//                ChatLieu cl = new ChatLieu();
//                cl.setTenChatLieu(res.getString(6));
//                ctsp.setIDChatLieu(cl);
//                ctsp.setTrongLuong(res.getFloat(7));
//                DaQuy dq = new DaQuy();
//                dq.setTenDaQuy(res.getString(8));
//                dq.setKichThuoc(res.getFloat(9));
//                ctsp.setIDDaQuy(dq);
//                NguonGoc ng = new NguonGoc();
//                ng.setCongTy(res.getString(10));
//                ctsp.setIDNguonGoc(ng);
//                KiemDinh kd = new KiemDinh();
//                kd.setDonViKiemDinh(res.getString(11));
//                ctsp.setIDKIemDinh(kd);
//                ctsp.setHinhAnh(res.getString(12));
//                return ctsp;
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//   //Xử lý tìm kiếm để hiển thị chi tiết sản phẩm
//
//    @Override
//    public ChiTietSanPham getChiTietSanPham(String string) {
//        sql = "select * from v_ChiTietSanPham where IDChiTietSanPham = ?";
//        
//        try {
//            con = jdbc.getConnection();
//            pre = con.prepareStatement(sql);
//            pre.setString(1, string);
//            res = pre.executeQuery();
//            if(res.next()){
//                ChiTietSanPham ctsp = new ChiTietSanPham();
//                SanPham sp = new SanPham();
//                sp.setTenSanPham(res.getString(1));
//                GioiTinh gt = new GioiTinh();
//                gt.setGioiTinh(res.getString(3));
//                sp.setIDGioiTinh(gt);
//                ctsp.setIDSanPham(sp);
//                ctsp.setTen(res.getString(2));
//                ctsp.setIDChiTietSanPham(res.getString(4));
//                ctsp.setGiaCu(res.getDouble(5));
//                ctsp.setGiaMoi(res.getDouble(6));
//                Size s = new Size();
//                s.setSoSize(res.getInt(7));
//                ctsp.setIDSize(s);
//                ChatLieu cl = new ChatLieu();
//                cl.setTenChatLieu(res.getString(8));
//                cl.setTyLe(res.getFloat(9));
//                MauSac ms = new MauSac();
//                ms.setChiTietMauSac(res.getString(10));
//                cl.setIDMauSac(ms);
//                ctsp.setIDChatLieu(cl);
//                ctsp.setTrongLuong(res.getFloat(11));
//                 DaQuy dq = new DaQuy();
//                dq.setTenDaQuy(res.getString(12));
//                dq.setKichThuoc(res.getFloat(13));
//                ctsp.setIDDaQuy(dq);
//                NguonGoc ng = new NguonGoc();
//                ng.setCongTy(res.getString(14));
//                ctsp.setIDNguonGoc(ng);
//                KiemDinh kd = new KiemDinh();
//                kd.setDonViKiemDinh(res.getString(15));
//                ctsp.setIDKIemDinh(kd);
//                ctsp.setHinhAnh(res.getString(16));
//                
//                
//                return ctsp;
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    //Xóa(chuyển trạng thái chi tiết sản phảm về 0)
//    @Override
//    public int delete(String string) {
//        sql = "UPDATE ChiTietSanPham SET TrangThai = 0   WHERE IdChiTietSanPham = ?";
//        try {
//            con = jdbc.getConnection();
//            pre = con.prepareStatement(sql);
//            pre.setString(1,string);
//
//            return pre.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    } 
//
//    @Override
//    public int update(ChiTietSanPham ctsp, String string) {
//        sql = "UPDATE [dbo].[ChiTietSanPham]\n" +
//"   SET [IDKiemDinh] = ?\n" +
//"      ,[IDSanPham] = ?\n" +
//"      ,[IDSize] = ?\n" +
//"      ,[Ten] = ?\n" +
//"      ,[IDChatLieu] = ?\n" +
//"      ,[SoLuongTonKho] = ?\n" +
//"      ,[SoLuongDaQuy] = ?\n" +
//"      ,[GiaMoi] =?\n" +
//"      ,[GiaCu] = ?\n" +
//"      ,[IDGiamGia] =?\n" +
//"      ,[HinhAnh] = ?\n" +
//"      ,[IDNguonGoc] = ?\n" +
//"      ,[TrangThai] = ?\n" +
//"      ,[TrongLuong] = ?\n" +
//"      ,[IDDaQuy] = ?\n" +
//"\n" +
//" WHERE IDChiTietSanPham = ?";
//
//        try {
//            con = jdbc.getConnection();
//            pre = con.prepareStatement(sql);
//            pre.setString(16, string);
////            KiemDinh kd = new KiemDinh();
////            SanPham sp = new SanPham();
////            Size size = new Size();
////            ChatLieu ct = new ChatLieu();
////            GiamGia gg = new GiamGia();
////            NguonGoc ng = new NguonGoc();
////            DaQuy dq = new DaQuy();
//
//            pre.setString(1, ctsp.getIDKIemDinh().getIDKiemDinh());
//            pre.setString(2, ctsp.getIDSanPham().getIDSanPham());
//            pre.setString(3, ctsp.getIDSize().getIDSize());
//            pre.setString(4, ctsp.getTen());
//            pre.setString(5, ctsp.getIDChatLieu().getIDChatLieu());
//            pre.setInt(6, ctsp.getSoLuongTonKho());
//            pre.setInt(7, ctsp.getSoLuongDaQuy());
//            pre.setDouble(8, ctsp.getGiaMoi());
//            pre.setDouble(9, ctsp.getGiaCu());
//            pre.setString(10, ctsp.getIDGiamGia().getIDGIamGia());
//            pre.setString(11, ctsp.getHinhAnh());
//            pre.setString(12, ctsp.getIDNguonGoc().getIDNguonGoc());
//            pre.setBoolean(13, ctsp.isTrangThai());
//            pre.setFloat(14, ctsp.getTrongLuong());
//            pre.setString(15, ctsp.getIDDaQuy().getIDDaQuyString());
//
//            return pre.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }

    @Override
    public List<SanPham> getAllWithConditional(SanPham sp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int add(SanPham ct) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPham getSanPhamSua(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPham getChiTietSanPham(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(SanPham ct, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
