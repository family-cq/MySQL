package lintener;

import cn.zhshipu.log.WinLog;
import view.conDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static config.Config.*;

public class newConMenuBarListener implements ActionListener {
    /**
     * 新建连接 监听
     * */

    WinLog log = new WinLog("newConMenuBarListener");
    Frame main;

    JTextField jtf_serverName,jtf_type,jtf_db,jtf_yzdb;

    public newConMenuBarListener(Frame main){
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame f = new conDB(main);

        JLabel jl_title = new JLabel("连接名");
        JLabel jl_IP = new JLabel("主机");
        JLabel jl_port = new JLabel("端口");
        JLabel jl_user = new JLabel("用户名");
        JLabel jl_pass = new JLabel("密码");

        JTextField jtf_title = new JTextField();
        JTextField jtf_IP = new JTextField();
        JTextField jtf_port = new JTextField();
        JTextField jtf_user = new JTextField();
        JTextField jtf_pass = new JTextField();

        JButton jb_login = new JButton("确定");

        jl_title.setFont(f_25);
        jl_IP.setFont(f_25);
        jl_port.setFont(f_25);
        jl_user.setFont(f_25);
        jl_pass.setFont(f_25);
        jtf_title.setFont(f_25);
        jtf_IP.setFont(f_25);
        jtf_port.setFont(f_25);
        jtf_user.setFont(f_25);
        jtf_pass.setFont(f_25);
        jb_login.setFont(f_25);

        jl_title.setBounds(50,100,150,50);
        jl_IP.setBounds(50,150,150,50);
        jl_port.setBounds(50,200,150,50);
        jl_user.setBounds(50,250,150,50);
        jl_pass.setBounds(50,300,150,50);
        jtf_title.setBounds(200,100,300,50);
        jtf_IP.setBounds(200,150,300,50);
        jtf_port.setBounds(200,200,300,50);
        jtf_user.setBounds(200,250,300,50);
        jtf_pass.setBounds(200,300,300,50);
        jb_login.setBounds(490,740,100,50);

        jtf_IP.setText("localhost");

        f.add(jl_title);
        f.add(jl_IP);
        f.add(jl_port);
        f.add(jl_user);
        f.add(jl_pass);
        f.add(jtf_title);
        f.add(jtf_IP);
        f.add(jtf_port);
        f.add(jtf_user);
        f.add(jtf_pass);
        f.add(jb_login);

        // 新建框,新建连接信息
        switch (e.getActionCommand()){
            case "mysql5":
                f.setTitle("连接MySQL5.0");
                jtf_port.setText("3306");
                break;
            case "mysql8":
                f.setTitle("连接MySQL8.0");
                jtf_port.setText("3306");
                break;
            case "oracle":
                f.setTitle("连接Oracle");
                jtf_port.setText("1521");

                JLabel jl_serverName = new JLabel("服务名");
                JLabel jl_type = new JLabel("连接类型");
                jtf_serverName = new JTextField();
                jtf_type = new JTextField();

                jl_serverName.setFont(f_25);
                jl_type.setFont(f_25);
                jtf_serverName.setFont(f_25);
                jtf_type.setFont(f_25);

                jl_serverName.setBounds(50,350,150,50);
                jl_type.setBounds(50,400,150,50);
                jtf_serverName.setBounds(200,350,300,50);
                jtf_type.setBounds(200,400,300,50);

                jtf_serverName.setText("orcl");
                jtf_type.setText("Basic");
                jtf_type.setEnabled(false);

                f.add(jl_serverName);
                f.add(jl_type);
                f.add(jtf_serverName);
                f.add(jtf_type);
                break;
            case "sqlserver":
                f.setTitle("连接SQL Server");
                jtf_port.setText("1433");

                JLabel jl_db = new JLabel("初始数据库");
                JLabel jl_yz = new JLabel("验证");
                jtf_db = new JTextField();
                JTextField jtf_yz = new JTextField();

                jl_db.setFont(f_25);
                jl_yz.setFont(f_25);
                jtf_db.setFont(f_25);
                jtf_yz.setFont(f_25);

                jl_db.setBounds(50,350,150,50);
                jl_yz.setBounds(50,400,150,50);
                jtf_db.setBounds(200,350,300,50);
                jtf_yz.setBounds(200,400,300,50);

                jtf_db.setText("master");
                jtf_yz.setText("SQL Server验证");
                jtf_yz.setEnabled(false);

                f.add(jl_db);
                f.add(jl_yz);
                f.add(jtf_db);
                f.add(jtf_yz);
                break;
            case "mongodb":
                f.setTitle("连接mongoDB");
                jtf_port.setText("27017");

                JLabel jl_yzpd = new JLabel("验证");
                JLabel jl_yzdb = new JLabel("验证数据库");
                JLabel jl_con = new JLabel("连接");
                JTextField jtf_yzpd = new JTextField();
                jtf_yzdb = new JTextField();
                JTextField jtf_con = new JTextField();

                jl_yzpd.setFont(f_25);
                jl_yzdb.setFont(f_25);
                jl_con.setFont(f_25);
                jtf_yzpd.setFont(f_25);
                jtf_yzdb.setFont(f_25);
                jtf_con.setFont(f_25);

                jl_yzpd.setBounds(50,350,150,50);
                jl_yzdb.setBounds(50,400,150,50);
                jl_con.setBounds(50,450,150,50);
                jtf_yzpd.setBounds(200,350,300,50);
                jtf_yzdb.setBounds(200,400,300,50);
                jtf_con.setBounds(200,450,300,50);

                jtf_con.setText("Standalone");
                jtf_yzpd.setText("Password");
                jtf_con.setEnabled(false);
                jtf_yzpd.setEnabled(false);

                f.add(jl_yzpd);
                f.add(jl_yzdb);
                f.add(jl_con);
                f.add(jtf_yzpd);
                f.add(jtf_yzdb);
                f.add(jtf_con);

                break;
            default:
                break;
        }

        log.info("选择了 => " + e.getActionCommand());

        jb_login.setActionCommand("login");
        jb_login.addActionListener(new AddConJButton(main,f,e.getActionCommand(),jtf_title,jtf_type,jtf_IP,jtf_port,jtf_user,jtf_pass,jtf_db,jtf_serverName,jtf_yzdb));



        f.setVisible(true);
        main.setEnabled(false);
    }

}
