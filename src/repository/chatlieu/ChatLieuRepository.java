/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.chatlieu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.MauSac;
import until.jdbc;

/**
 *
 * @author HUNGpYN
 */
public class ChatLieuRepository implements ChatLieuInterface {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    @Override
    public List<ChatLieu> getAll() {
    List<ChatLieu> list = new ArrayList<>();
    sql = "SELECT c.IDChatLieu, c.TenChatLieu, c.TyLe, c.IDMauSac, m.ChiTietMauSac " +
          "FROM ChatLieu c " +
          "JOIN MauSac m ON c.IDMauSac = m.IDMauSac " +
          "WHERE c.TrangThai = 1";
    try {
        con = jdbc.getConnection();
        pre = con.prepareStatement(sql);
        res = pre.executeQuery();
        while (res.next()) {
            ChatLieu cl = new ChatLieu();
            cl.setIDChatLieu(res.getString("IDChatLieu"));
            cl.setTenChatLieu(res.getString("TenChatLieu"));
            cl.setTyLe(res.getFloat("TyLe"));
            
            MauSac ms = new MauSac();
            ms.setIDMauSac(res.getString("IDMauSac"));
            ms.setChiTietMauSac(res.getString("ChiTietMauSac"));         
            list.add(cl);
        }
        return list;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

    @Override
    public int creat(ChatLieu cl) {
        sql = "INSERT INTO ChatLieu(TenChatLieu, TyLe,IDMauSac, TrangThai) VALUES(?,?,?,1)";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, cl.getTenChatLieu());
            pre.setFloat(2, cl.getTyLe());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(ChatLieu cl) {
        sql = "UPDATE ChatLieu SET TenChatLieu = ?, TyLe = ?, IDMauSac = ? WHERE IDChatLieu = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, cl.getTenChatLieu());
            pre.setFloat(2, cl.getTyLe());
            pre.setString(4, cl.getIDChatLieu());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(ChatLieu cl) {
        sql = "UPDATE ChatLieu SET TrangThai = 0 WHERE IDChatLieu = ?";
        try {
            con = jdbc.getConnection();
            pre = con.prepareStatement(sql);
            pre.setString(1, cl.getIDChatLieu());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
