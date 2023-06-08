package util;

import cn.zhshipu.log.WinLog;
import view.LeftJPanel;

import javax.swing.*;

import java.awt.*;

import static config.Config.*;

public class LeftJScrollPane {
    /**
     * 左侧内容
     * 目录
     * 导航
     * */
    private static LeftJScrollPane instance = null;
    WinLog log = new WinLog("LeftJScrollPane");
    private ConXY cxy_left = new ConXY(0,300),
            jsp_xy = new ConXY(0,0);
    private JScrollPane jsp = null;
    private JPanel jp = null;
    public static LeftJScrollPane getInstance(){
        if (instance == null){
            instance = new LeftJScrollPane();
        }
        return instance;
    }
    LeftJScrollPane(){
        jp = LeftJPanel.getInstance().getLeftJPanel();
        jsp = new JScrollPane(jp);
        jsp.setBounds(cxy_left.getX(),180,cxy_left.getW(),Y-210);
        jp.setPreferredSize(new Dimension(jsp_xy.getX(), jsp_xy.getW()));
    }
    public JScrollPane getJsp(){
        if (null == jsp){
            new LeftJScrollPane();
        }
        return jsp;
    }
    public ConXY getcxyLeft(){
        return cxy_left;
    }
    public ConXY getJspXY(){
        return jsp_xy;
    }
    public void updateJspXY(int w,int h){
        jsp_xy.setX(h);
        jsp_xy.setW(w);
        jp.setPreferredSize(new Dimension(jsp_xy.getW(),jsp_xy.getX()));
    }
    public void updatecxyRight(int x,int w){
        cxy_left.setX(x);
        cxy_left.setW(w);
        jsp.setBounds(cxy_left.getX(),180,cxy_left.getW(),Y-210);
    }

}
