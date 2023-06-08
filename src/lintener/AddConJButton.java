package lintener;

import cn.zhshipu.log.WinLog;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.DnsSrv;
import fileconfig.ConFile;
import sun.applet.Main;
import util.DBUtil;
import util.dbNameMap;
import util.dbURLMap;
import view.LeftJPanel;
import view.conDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static config.Config.sqlPath;

public class AddConJButton implements ActionListener {

    Frame main;
    Frame ts;
    String str;
    JTextField jtf_title;
    JTextField jtf_type;
    JTextField jtf_IP;
    JTextField jtf_port;
    JTextField jtf_user;
    JTextField jtf_pass;
    JTextField jtf_db;
    JTextField jtf_serverName;
    JTextField jtf_yzdb;
    WinLog log = new WinLog("AddConJButton");

    public AddConJButton(Frame main,
                         Frame ts,
                         String str,
                         JTextField jtf_title,
                         JTextField jtf_type,
                         JTextField jtf_IP,
                         JTextField jtf_port,
                         JTextField jtf_user,
                         JTextField jtf_pass,
                         JTextField jtf_db,
                         JTextField jtf_serverName,
                         JTextField jtf_yzdb){
        this.main = main;
        this.ts = ts;
        this.str = str;
        this.jtf_title = jtf_title;
        this.jtf_type = jtf_type;
        this.jtf_IP = jtf_IP;
        this.jtf_port = jtf_port;
        this.jtf_user = jtf_user;
        this.jtf_pass = jtf_pass;
        this.jtf_db = jtf_db;
        this.jtf_serverName = jtf_serverName;
        this.jtf_yzdb = jtf_yzdb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "login":

                //确定新建信息
                switch (str){
                    case "mysql5":
                        if (jtf_title.getText().length()>0 ||
                        jtf_IP.getText().length() > 0 ||
                        jtf_port.getText().length() > 0 ||
                        jtf_user.getText().length() > 0 ||
                        jtf_pass.getText().length() > 0){
                            log.info("信息填写完成");

                            if (dbNameMap.getInstance().addDbNames(jtf_title.getText(),
                                    new DBUtil(1,
                                            jtf_IP.getText(),
                                            jtf_port.getText()).getConn())){
                                    success(jtf_title.getText(),
                                            "mysql5",
                                            jtf_IP.getText(),
                                            jtf_port.getText(),
                                            jtf_user.getText(),
                                            jtf_pass.getText(),
                                            "",
                                            "",
                                            "",
                                            "");
                                String url = "jdbc:mysql://"+jtf_IP.getText()+":"+jtf_port.getText()+" ";
                                dbURLMap.getInstance().addURLMap(jtf_title.getText(),url);
                            }
                            close();
                        }
                        break;
                    case "mysql8":
                        if (jtf_title.getText().length()>0 ||
                                jtf_IP.getText().length() > 0 ||
                                jtf_port.getText().length() > 0 ||
                                jtf_user.getText().length() > 0 ||
                                jtf_pass.getText().length() > 0) {
                            if(dbNameMap.getInstance().addDbNames(jtf_title.getText(),
                                    new DBUtil(2,
                                            jtf_IP.getText(),
                                            jtf_port.getText()).getConn())){

                                    success(jtf_title.getText(),
                                            "mysql8",
                                            jtf_IP.getText(),
                                            jtf_port.getText(),
                                            jtf_user.getText(),
                                            jtf_pass.getText(),
                                            "",
                                            "",
                                            "",
                                            "");

                                String url = "jdbc:mysql://"+jtf_IP.getText()+":"+jtf_port.getText()+" ";
                                dbURLMap.getInstance().addURLMap(jtf_title.getText(),url);
                            }
                            close();
                        }
                        break;
                    case "oracle":
                        if (jtf_title.getText().length()>0 ||
                                jtf_IP.getText().length() > 0 ||
                                jtf_port.getText().length() > 0 ||
                                jtf_user.getText().length() > 0 ||
                                jtf_pass.getText().length() > 0 ||
                                jtf_serverName.getText().length() > 0 ) {
                            if (dbNameMap.getInstance().addDbNames(jtf_title.getText(),
                                    new DBUtil(3,
                                            jtf_IP.getText(),
                                            jtf_port.getText()).getConn())){

                                    success(jtf_title.getText(),
                                            "oracle",
                                            jtf_IP.getText(),
                                            jtf_port.getText(),
                                            jtf_user.getText(),
                                            jtf_pass.getText(),
                                            "",
                                            jtf_serverName.getText(),
                                            jtf_yzdb.getText(),
                                            jtf_type.getText());

                                String url = "jdbc:oracle:thin:@"+jtf_IP.getText()+":"+jtf_port.getText()+":"+jtf_serverName.getText();
                                dbURLMap.getInstance().addURLMap(jtf_title.getText(),url);
                            }
                            close();
                        }
                        break;
                    case "sqlserver":
                        if (jtf_title.getText().length()>0 ||
                                jtf_IP.getText().length() > 0 ||
                                jtf_port.getText().length() > 0 ||
                                jtf_user.getText().length() > 0 ||
                                jtf_pass.getText().length() > 0 ||
                                jtf_db.getText().length() > 0) {
                            if (dbNameMap.getInstance().addDbNames(jtf_title.getText(),
                                    new DBUtil(4,
                                            jtf_IP.getText(),
                                            jtf_port.getText()).getConn())){

                                    success(jtf_title.getText(),
                                            "sqlserver",
                                            jtf_IP.getText(),
                                            jtf_port.getText(),
                                            jtf_user.getText(),
                                            jtf_pass.getText(),
                                            jtf_db.getText(),
                                            "",
                                            "",
                                            "");
                                String url = "jdbc:sqlserver://"+jtf_IP.getText()+":"+jtf_port.getText()+";";
                                dbURLMap.getInstance().addURLMap(jtf_title.getText(),url);
                            }
                            close();
                        }
                        break;
                    case "mongodb":
                        if (jtf_title.getText().length()>0 ||
                                jtf_IP.getText().length() > 0 ||
                                jtf_port.getText().length() > 0 ||
                                jtf_user.getText().length() > 0 ||
                                jtf_pass.getText().length() > 0 ||
                                jtf_yzdb.getText().length() > 0) {
                            new DBUtil(5,jtf_IP.getText(), jtf_port.getText());
                                success(jtf_title.getText(),
                                        "mongodb",
                                        jtf_IP.getText(),
                                        jtf_port.getText(),
                                        jtf_user.getText(),
                                        jtf_pass.getText(),
                                        "",
                                        "",
                                        jtf_yzdb.getText(),
                                        "");
                            close();
                        }
                        break;
                    default:
                        close();
                        break;
                }
                break;
        }
    }


    //新建连接 创建成功
    private void success(String title,String name,String ip, String port, String user,String pass,String db,String serverName,String yzdb,String type){
        JOptionPane.showMessageDialog(null,"提示","连接成功",JOptionPane.INFORMATION_MESSAGE);

        //写入本地
        JSONObject json = new JSONObject();
        json.put("conname",title);
        json.put("name",name);
        json.put("ip",ip);
        json.put("port",port);
        json.put("username",user);
        json.put("password",pass);
        json.put("db",db);
        json.put("serverName",serverName);
        json.put("yzdb",yzdb);
        json.put("type",type);
        ConFile.getInstance().outWrite(json,1);

        //更新UI
        ConFile.getInstance().Close();
        LeftJPanel.getInstance().addNode(title);
    }



    private void close(){
        main.setEnabled(true);
        ts.dispose();
    }
}
