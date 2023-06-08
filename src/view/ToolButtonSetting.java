package view;

import cn.zhshipu.log.WinLog;

import javax.swing.*;
import java.awt.*;

public class ToolButtonSetting extends JButton {

    public ToolButtonSetting(ImageIcon bIcon, int x, int y, int w, int h, String tip, Color col){
        bIcon.setImage(bIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        setIcon(bIcon);
        setBounds(x,y,w,h);
        setToolTipText(tip);
        setBackground(col);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

}
