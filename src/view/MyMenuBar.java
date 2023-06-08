package view;

import lintener.menuBarListener;
import lintener.newConMenuBarListener;
import lintener.newMenuBarListener;

import javax.swing.*;
import java.awt.*;

import static config.Config.*;

public class MyMenuBar extends JMenuBar {

    /**
     * 自定义导航栏内容
     * view
     * 监听
     * */

    Frame main;

    public MyMenuBar(Frame main){
        this.main = main;
        add(FileBar());
        add(MoneyBar());
        setVisible(true);
    }

    private JMenu FileBar(){
        JMenu jm_file = new JMenu("文件");
        jm_file.setFont(f_25);

        JMenu jm_newCon = new JMenu("新建连接");
        jm_newCon.setFont(f_25);
        jm_file.add(jm_newCon);
        JMenuItem item1 = new JMenuItem("MySQL5.1");
        item1.setFont(f_20);
        jm_newCon.add(item1);
        JMenuItem item2 = new JMenuItem("MySQL8.0");
        item2.setFont(f_20);
        jm_newCon.add(item2);
        JMenuItem item3 = new JMenuItem("Oracle");
        item3.setFont(f_20);
        jm_newCon.add(item3);
        JMenuItem item4 = new JMenuItem("SQLServer");
        item4.setFont(f_20);
        jm_newCon.add(item4);
        JMenuItem item5 = new JMenuItem("mongoDB");
        item5.setFont(f_20);
        jm_newCon.add(item5);

        item1.setActionCommand("mysql5");
        item2.setActionCommand("mysql8");
        item3.setActionCommand("oracle");
        item4.setActionCommand("sqlserver");
        item5.setActionCommand("mongodb");

        item1.addActionListener(new newConMenuBarListener(main));
        item2.addActionListener(new newConMenuBarListener(main));
        item3.addActionListener(new newConMenuBarListener(main));
        item4.addActionListener(new newConMenuBarListener(main));
        item5.addActionListener(new newConMenuBarListener(main));

        JMenu jm_new = new JMenu("新建");
        jm_new.setFont(f_25);
        jm_file.add(jm_new);
        JMenuItem item6 = new JMenuItem("表");
        item6.setFont(f_20);
        jm_new.add(item6);
        JMenuItem item7 = new JMenuItem("视图");
        item7.setFont(f_20);
        jm_new.add(item7);
        JMenuItem item8 = new JMenuItem("函数");
        item8.setFont(f_20);
        jm_new.add(item8);
        JMenuItem item9 = new JMenuItem("用户");
        item9.setFont(f_20);
        jm_new.add(item9);
        JMenuItem item10 = new JMenuItem("查询");
        item10.setFont(f_20);
        jm_new.add(item10);

        item6.setActionCommand("biao");
        item7.setActionCommand("shitu");
        item8.setActionCommand("hanshu");
        item9.setActionCommand("yonghu");
        item10.setActionCommand("chaxun");

        item6.addActionListener(new newMenuBarListener(main));
        item7.addActionListener(new newMenuBarListener(main));
        item8.addActionListener(new newMenuBarListener(main));
        item9.addActionListener(new newMenuBarListener(main));
        item10.addActionListener(new newMenuBarListener(main));

        return jm_file;
    }

    private JMenu MoneyBar(){
        JMenu jm_money = new JMenu("捐助");
        jm_money.setFont(f_25);
        JMenuItem item1 = new JMenuItem("微信");
        item1.setFont(f_25);
        jm_money.add(item1);

        item1.setActionCommand("weixin");
        item1.addActionListener(new menuBarListener(main));

        JMenuItem item2 = new JMenuItem("支付宝");
        item2.setFont(f_25);
        jm_money.add(item2);

        item2.setActionCommand("zhifubao");
        item2.addActionListener(new menuBarListener(main));

        return jm_money;
    }
}
