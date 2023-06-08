package util;

import view.RightJPanel;

import javax.swing.*;

import java.awt.*;

import static config.Config.X;
import static config.Config.Y;

public class RightJScrollPane {
    /**
     * 右侧内容
     * 滚动条
     * 界面
     * */
    private static RightJScrollPane instance = null;
    private JScrollPane jsp = null;
    ConXY cxy_right = new ConXY(305,X-305);
    public static RightJScrollPane getInstance(){
        if (instance == null){
            instance = new RightJScrollPane();
        }
        return instance;
    }
    RightJScrollPane(){
        JPanel jp = RightJPanel.getInstance().getRightJPanel();
        jsp = new JScrollPane(jp);
        jsp.setBounds(cxy_right.getX(),180,cxy_right.getW(),Y-210);
        jp.setPreferredSize(new Dimension(X,Y-210));
    }
    public JScrollPane getJsp(){
        if (jsp == null){
            new RightJScrollPane();
        }
        return jsp;
    }
    public ConXY getcxyRight(){
        return cxy_right;
    }
    public void updateCxyRight(int x,int w){
        cxy_right.setX(x);
        cxy_right.setW(w);
    }


}
