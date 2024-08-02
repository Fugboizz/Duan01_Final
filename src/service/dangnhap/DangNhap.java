/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.dangnhap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

/**
 *
 * @author HUNGpYN
 */
public class DangNhap implements DangNhapInteface {


    @Override
    public void showPassword(JPasswordField show, JLabel disable, JLabel enabled) {
        show.setEchoChar((char) 0);
        disable.setVisible(false);
        disable.setEnabled(false);
        enabled.setVisible(true);
        enabled.setEnabled(true);
    }

    @Override
    public void hidePassword(JPasswordField show, JLabel disable, JLabel enabled) {
        show.setEchoChar((char) 8226);
        disable.setVisible(true);
        disable.setEnabled(true);
        enabled.setVisible(false);
        enabled.setEnabled(false);  
    }
}
