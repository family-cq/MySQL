package lintener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newMenuBarListener implements ActionListener {
    /**
     * 新建 监听
     * */

    Frame main;
    public newMenuBarListener(Frame main){
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame f;


        switch (e.getActionCommand()){
            case "biao":

                break;
            case "shitu":

                break;
            case "hanshu":

                break;
            case "yonghu":

                break;
            case "chaxun":

                break;
            default:
                break;
        }

    }
}
