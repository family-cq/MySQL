package view;

import cn.zhshipu.log.WinLog;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import config.DBName;
import config.Name;
import fileconfig.ConFile;
import lintener.BiaoNodeListener;
import util.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static config.Config.f_30;

public class LeftJPanel extends JPanel {

    /**
     * 内容栏左侧内容
     * */

    private static LeftJPanel instance = null;

    public static LeftJPanel getInstance(){
        if (instance == null){
            instance = new LeftJPanel();
        }
        return instance;
    }

    public JTree jTree;
    public DefaultMutableTreeNode root;

    WinLog log = new WinLog("LeftJPanel");
    private LeftJPanel(){
        setLayout(new FlowLayout(FlowLayout.LEFT));

        //获取新建连接信息 本地信息
        log.info(ConFile.getInstance().getIniJson()+"");
        JSONArray jsonArray = ConFile.getInstance().getIniJson();

        root = new DefaultMutableTreeNode(new Name("连接"));

        /*
        *   {
    "conname": "数据库1",
    "name": "MYSQL",
    "ip": "0.0.0.0",
    "port": "3306",
    "username": "",
    "password": ""
  },
  {
    "conname": "数据库2",
    "name": "ORACLE",
    "ip": "127.0.0.1",
    "port": "1521",
    "username": "",
    "password": ""
  }
        * */

        for (int i=0;i<jsonArray.size();i++){
            JSONObject json = jsonArray.getJSONObject(i);
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(new Name(json.get("conname")+""));
            root.add(node);

            //加载到内存
            dbNameMap.getInstance().addDbNames(json.get("conname")+"",null);
            dbShuJuKuMap.getInstance().setSjks(json.get("conname")+"");

            String url = "";
            String str = json.get("name")+"";
            log.info(" => "+str);
            switch (str){
                case "mysql5":
                case "mysql8":
                    url = setmysql(json);
                    break;
                case "oracle":
                    dbZiDian.getInstance().addDBName(new DBName(
                            json.get("conname")+"",
                            json.get("name")+"",
                            json.get("ip")+"",
                            json.get("port")+"",
                            json.get("username")+"",
                            json.get("password")+"",
                            json.get("serverName")+"",
                            json.get("type")+"",
                            "",
                            json.get("yzdb")+""));
                    url = "jdbc:oracle:thin:@"+json.get("ip")+":"+json.get("port")+":"+json.get("serverName");
                    break;
                case "sqlserver":
                    dbZiDian.getInstance().addDBName(new DBName(
                            json.get("conname")+"",
                            json.get("name")+"",
                            json.get("ip")+"",
                            json.get("port")+"",
                            json.get("username")+"",
                            json.get("password")+"",
                            "",
                            "",
                            json.get("db")+"",
                            ""));
                    url = "jdbc:sqlserver://"+json.get("ip")+":"+json.get("port")+";";
                    break;
                case "mongodb":
                    dbZiDian.getInstance().addDBName(new DBName(
                            json.get("conname")+"",
                            json.get("name")+"",
                            json.get("ip")+"",
                            json.get("port")+"",
                            json.get("username")+"",
                            json.get("password")+"",
                            "",
                            "",
                            "",
                            json.get("yzdb")+""));
                    break;

            }

            log.info(" url => "+url);
            dbURLMap.getInstance().addURLMap(json.get("conname")+"",url);
        }

        jTree = new JTree(root);
        jTree.setFont(f_30);
        jTree.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jTree.setCellRenderer(new SetIconForJtree());
        add(jTree);

        jTree.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!jTree.isSelectionEmpty() && e.getKeyCode() == 127) {// 查看是否存在被选中的节点
                    //删除节点
                    delete();
                }
            }
        });

        jTree.addMouseListener(new BiaoNodeListener(jTree,jsonArray));
    }

    // mysql 配置 url
    private String setmysql(JSONObject json) {
        dbZiDian.getInstance().addDBName(new DBName(
                json.get("conname")+"",
                json.get("name")+"",
                json.get("ip")+"",
                json.get("port")+"",
                json.get("username")+"",
                json.get("password")+"",
                "",
                "",
                "",
                ""));
        return "jdbc:mysql://"+json.get("ip")+":"+json.get("port")+" ";
    }

    public JPanel getLeftJPanel(){
        return this;
    }

    public void delete(){
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
        log.info("删除node = "+node.toString());
        root.remove(node);

        //更新本地文件
        Object obj = node.toString();
        ConFile.getInstance().outWrite(obj,2);

        //清楚内存
        dbNameMap.getInstance().deleteName(node.toString());
        dbZiDian.getInstance().deleteDBName(node.toString());

        //刷新UI
        updateJtree();
    }

    public void addNode(String name){
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);
        root.insert(node,root.getChildCount());
        updateJtree();
    }

    public void updateJtree(){

        log.info("刷新JTRee");
        jTree.revalidate();
        jTree.repaint();
        jTree.updateUI();

    }


}