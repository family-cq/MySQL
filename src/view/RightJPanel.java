package view;

import cn.zhshipu.log.WinLog;

import javax.swing.*;

public class RightJPanel extends JPanel {

    private static RightJPanel instance = null;
    WinLog log = new WinLog("RightJPanel");

    public static RightJPanel getInstance(){
        if (instance == null){
            instance = new RightJPanel();
        }
        return instance;
    }

    private RightJPanel(){

    }

    public RightJPanel getRightJPanel(){
        return this;
    }
}
