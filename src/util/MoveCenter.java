package util;

import cn.zhshipu.log.WinLog;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static config.Config.Y;

public class MoveCenter extends MouseAdapter {

    int x=-1;
    JScrollPane jp_left,jp_right;
    JPanel jp_center;
    ConXY con_left,con_center,con_right;

    WinLog log = new WinLog("MoveCenter");

    public MoveCenter(JScrollPane jp_left,
                      JPanel jp_center,
                      JScrollPane jp_right,
                      ConXY con_left,
                      ConXY con_center,
                      ConXY con_right){
        this.jp_left = jp_left;
        this.jp_center = jp_center;
        this.jp_right = jp_right;
        this.con_left = con_left;
        this.con_center = con_center;
        this.con_right = con_right;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (x!=-1){
            x=-1;
        }
        x = e.getX();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mx = e.getX() - x;
        log.info("mx = "+mx);

        if (con_center.getX()+mx >= Y-1)return;

        if (con_center.getX()+mx <300){
            mx = 300 - con_center.getX();
        }

        con_left.setW(con_left.getW()+mx);
        con_center.setX(con_center.getX()+mx);
        con_right.setX(con_right.getX()+mx);
        con_right.setW(con_right.getW()-mx);

        try{
            jp_left.setBounds(con_left.getX(),180,con_left.getW(),Y-210);
            jp_center.setBounds(con_center.getX(),180,con_center.getW(),Y-210);
            jp_right.setBounds(con_right.getX(),180,con_right.getW(),Y-210);

            jp_left.updateUI();
            jp_right.updateUI();
        }catch (NullPointerException ex){
            log.error(ex.getMessage());
        }
    }
}
