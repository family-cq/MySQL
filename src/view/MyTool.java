package view;

import cn.zhshipu.log.WinLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static config.Config.*;

public class MyTool  extends JPanel {

    /**
     *  工具栏
     * */

    WinLog log = new WinLog("MyTool");

    public MyTool(){
        setLayout(null);
        setBackground(new Color(255,192,192));

        add(NewCon());
        add(NewSelect());
        add(Outside());
        add(jbView());
        add(jbFunction());
        add(jbUser());
    }

    //用户
    private JButton jbUser(){
        JButton jb = new ToolButtonSetting(yh_png,555,5,100,100,"用户",this.getBackground());
        jb.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });
        return jb;
    }

    //函数
    private JButton jbFunction(){
        JButton jb = new ToolButtonSetting(hanshu_png,445,5,100,100,"函数",this.getBackground());
        jb.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });
        return jb;
    }

    //视图
    private JButton jbView(){
        JButton jb = new ToolButtonSetting(shitu_png,335,5,100,100,"视图",this.getBackground());
        jb.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });
        return jb;
    }

    //表
    private JButton Outside(){
        JButton jb = new ToolButtonSetting(biao_png,225,5,100,100,"表",this.getBackground());
        jb.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });
        return jb;
    }

    //新建
    private JButton NewCon(){
        JButton jb = new ToolButtonSetting(con1_png,5,5,100,100,"连接",this.getBackground());
        jb.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });
        return jb;
    }

    //新建查询
    private JButton NewSelect(){
        JButton jb = new ToolButtonSetting(sel_png,115,5,100,100,"新建查询",this.getBackground());
        jb.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });
        return jb;
    }
}
