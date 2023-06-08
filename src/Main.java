import cn.zhshipu.log.WinLog;
import fileconfig.ConIni;
import util.*;
import view.LeftJPanel;
import view.MyMenuBar;
import view.MyTool;
import view.RightJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static config.Config.*;
public class Main extends Frame {
    public static WinLog log = new WinLog("Main");
    private JPanel jp_daoHang,jp_daoHangUp,jp_daoHangDown,jp_nr_center,jp_down;
   private ConXY cxy_center = new ConXY(300,5);
    Main(){
        setTitle("CQ数据库连接中心");
        setLayout(null);
        setBounds(0,0,X,Y);
        setIconImage(logo.getImage());
        setResizable(false);
        setLocationRelativeTo(null);

        //导航栏
        jp_daoHang = new JPanel();
        jp_daoHang.setLayout(null);
        jp_daoHang.setBounds(0,30,X,150);
        add(jp_daoHang);

        jp_daoHangUp = new JPanel();
        jp_daoHangUp.setLayout(null);
        jp_daoHangUp.setBounds(0,0,X,40);
        jp_daoHangUp.setBackground(new Color(192,192,192));
        jp_daoHang.add(jp_daoHangUp);

        JMenuBar jm_bar = new MyMenuBar(this);
        jm_bar.setBounds(10,0,X-20,40);
        jp_daoHangUp.add(jm_bar);

        jp_daoHangDown = new MyTool();
        jp_daoHangDown.setBounds(0,40,X,110);
        jp_daoHang.add(jp_daoHangDown);

        //内容栏
        //左侧
        add(LeftJScrollPane.getInstance().getJsp());

        //中间
        jp_nr_center = new JPanel();
        jp_nr_center.setLayout(null);
        jp_nr_center.setBounds(cxy_center.getX(),180,cxy_center.getW(),Y-210);
        jp_nr_center.setBackground(Color.white);
        jp_nr_center.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        add(jp_nr_center);

        //右侧
        add(RightJScrollPane.getInstance().getJsp());

        jp_nr_center.addMouseListener(new MoveCenter(LeftJScrollPane.getInstance().getJsp(),jp_nr_center,RightJScrollPane.getInstance().getJsp(), LeftJScrollPane.getInstance().getcxyLeft(), cxy_center,RightJScrollPane.getInstance().getcxyRight()));


        //底部导航栏
        jp_down = new JPanel();
        jp_down.setLayout(null);
        jp_down.setBounds(0,Y-30,X,30);
        jp_down.setBackground(Color.cyan);
        add(jp_down);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new DBUtil().Close();
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {

        log.info(ConIni.getInstance().getIniJson()+"");

        new Main();
    }

}