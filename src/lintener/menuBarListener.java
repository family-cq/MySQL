package lintener;

import cn.zhshipu.log.WinLog;
import view.zhiFuKuang;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuBarListener implements ActionListener {
    /**
     * 支付图片 监听
     * */

    WinLog log = new WinLog("menuBarListener");
    Frame main;
    public menuBarListener(Frame main){
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame f;
        switch (e.getActionCommand()){
            case "weixin":
                log.info("点击了微信支付");
                main.setEnabled(false);
                f = new zhiFuKuang(0,main);
                f.setVisible(true);
                break;
            case "zhifubao":
                log.info("点击了支付宝支付");
                main.setEnabled(false);
                f = new zhiFuKuang(1,main);
                f.setVisible(true);
                break;
            default:
                break;
        }
    }
}
