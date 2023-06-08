package lintener;

import cn.zhshipu.log.WinLog;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fileconfig.ConFile;
import util.LeftJScrollPane;
import util.dbNameMap;
import util.dbShuJuKuMap;
import util.dbURLMap;
import view.LeftJPanel;
import config.Name;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.*;


public class BiaoNodeListener extends MouseAdapter {

    long firstTime = 0,secondTime = 0,thirdTime = 0;
    DefaultMutableTreeNode sNode,tNode,fNode;
    WinLog log = new WinLog("BiaoNodeListener");
    JTree jTree;
    JSONArray jsonArray;
    public BiaoNodeListener(JTree jTree,JSONArray jsonArray){
        this.jTree = jTree;
        this.jsonArray = jsonArray;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
        if (null != node)
        if (node.getLevel() == 1){  // 库
            if (System.currentTimeMillis() - firstTime <= 2000 && sNode != null && sNode == node){
                firstTime = 0;
//                log.info("jsonArray = "+jsonArray.toJSONString());
                if (gogogo(node)){
                    //执行语句查询数据库
                    String sql = "show databases ";
                    try {
                        PreparedStatement stsm = dbNameMap.getInstance().getDbNames().get(node.toString()).prepareStatement(sql);
                        ResultSet rs = stsm.executeQuery();
                        while (rs.next()){
//                                log.info("rs = "+rs.getObject("Database").toString());
                            //库名加载到内存
                            dbShuJuKuMap.getInstance().addSjks(node.toString(),rs.getObject("Database").toString());
                        }
                        rs.close();
                        stsm.close();
                    } catch (SQLException ex) {
                        log.error("执行SQL语句错误\r"+ex.getMessage());
                        JOptionPane.showMessageDialog(null,"sql语句错误","提示",JOptionPane.ERROR_MESSAGE);
//                            throw new RuntimeException(ex);
                    }
                    closeCon(node.toString());

                    //更新UI
                    updateDB(jTree,node);
                }
            }else {
                firstTime = System.currentTimeMillis();
                sNode = node;
                log.info("选中-> " +node);
            }
        }else if (node.getLevel() == 3){    //表/视图
            if (System.currentTimeMillis() - secondTime <= 2000){
                secondTime = 0;
                TreeNode[] path = node.getPath();
                String title = path[1].toString();  //连接名
                String db = path[2].toString();     //数据库名

                JSONArray jsonArray = ConFile.getInstance().getIniJson();

                switch (path[3].toString()){
                    case "视图":
                    case "表":
                        biaoShiTu(title,db,path,jsonArray);
                        break;
                    case "函数":
                        String url = getUrl(title,db);
                        if (goBiaoCon(title,url,path,3)){
                            String sql = "SHOW PROCEDURE STATUS; ";
                            tryConGetTables(title,db,sql,2);


                            //临时关闭con释放资源
                            closeCon(title);
                            //更新UI
                            updateTableDB(path);
                        }
                        break;
                    case "查询":

                        break;
                }

            }else {
                secondTime = System.currentTimeMillis();
                tNode = node;
                log.info("选中-> " +node);
            }
        }else if (node.getLevel() == 4){     //查询表内容数据
            if (System.currentTimeMillis() - thirdTime <= 2000){
                thirdTime = 0;
                TreeNode[] path = node.getPath();
                String title = path[1].toString();
                String db = path[2].toString();
                String type = path[3].toString();
                String table = path[4].toString();



            }else {
                thirdTime = System.currentTimeMillis();
                fNode = node;
                log.info("选中 -> "+node);
            }
        }

        LeftJScrollPane.getInstance().updateJspXY(jTree.getWidth()+10,jTree.getHeight()+10);
    }

    //临时关闭con 释放不必要资源
    private void closeCon(String title) {
        //关闭con 方便查询表时,需要切换con
        Connection con = dbNameMap.getInstance().getDbNames().get(title);
        if (con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                log.info("连接关闭失败"+ex.getMessage());
                JOptionPane.showMessageDialog(null,"连接关闭失败"+ex.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
//                            throw new RuntimeException(ex);
            }
        }
        dbNameMap.getInstance().updateNames(title,null);
    }

    //查询表/视图 并更新
    private void biaoShiTu(String title,String db,TreeNode[] path,JSONArray jsonArray) {
        String url = getUrl(title,db);
        int index = 1;
        switch (path[3].toString()){
            case "表":
                break;
            case "视图":
                index = 2;
                break;
        }
        //连接数据库
//                log.info("biao url = "+url);
        if(goBiaoCon(title,url,path,index)){
//                    String sql = "use "+db+"; ";
            String sql1 = "show full tables; ";
//                    log.info("sql = "+sql);
            tryConGetTables(title,db,sql1,1);

            //临时关闭con释放资源
            closeCon(title);
            //更新UI
            updateTableDB(path);
        }
    }

    private void tryConGetTables(String title,String db,String sql1,int index) {    //1 表/视图 2函数/储存过程
        try {
            Statement st = dbNameMap.getInstance().getDbNames().get(title).createStatement();
            ResultSet rs = st.executeQuery(sql1);
            String table = "Tables_in_"+db;
            String type = "Table_type";
            String mDb = "Db";
            String name = "Name";
//                        log.info("table = "+table);
            while (rs.next()){
//                            log.info("rs.toString() = "+rs.toString());
//                            log.info("rs = "+rs.getObject(name));
                switch (index){
                    case 1:
                        if("BASE TABLE".equals(rs.getObject(type))){
                            dbShuJuKuMap.getInstance().addSjksDBs(title,db,"表",rs.getObject(table)+"");
                        }else if ("VIEW".equals(rs.getObject(type))){
                            dbShuJuKuMap.getInstance().addSjksDBs(title,db,"视图",rs.getObject(table)+"");
                        }
                        break;
                    case 2:
                        if(db.equals(rs.getObject(mDb))){
                            log.info("rs.getObject(name) = "+rs.getObject(name));
                            dbShuJuKuMap.getInstance().addSjksDBs(title,db,"函数",rs.getObject(name)+"");
                        }
                        break;
                }

            }
//                        log.info("list = "+list.toString());
            rs.close();
            st.close();
        } catch (SQLException ex) {
            log.error("查询错误 "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"查询错误 "+ex.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
//                        throw new RuntimeException(ex);
        }
    }

    private String getUrl(String title,String db) {

        String url = dbURLMap.getInstance().getUrls().get(title);
        for (int w = 0;w<jsonArray.size();w++){
            JSONObject json = jsonArray.getJSONObject(w);
            if (title.equals(json.get("conname"))){
                String name = json.get("name")+"";
                switch (name){
                    case "mysql5":
                    case "mysql8":
                        url = url+"/"+db;
                        break;
                    case "oracle":

                        break;
                    case "sqlserver":
                        url += "database="+db;
                        break;
                    case "mongodb":

                        break;
                    default:
                        break;
                }
                break;
            }
        }
        return url;
    }

    private boolean goBiaoCon(String title,String url,TreeNode[] path,int index){
        JSONArray jsonArray1 = ConFile.getInstance().getIniJson();
        for (int e=0;e<jsonArray1.size();e++){
            JSONObject json = jsonArray1.getJSONObject(e);
            if (title.equals(json.get("conname"))){
                log.info("连接数据库");
                return isGoCon(json,title,url,path,index);
            }
        }
        return false;
    }

    private boolean gogogo(DefaultMutableTreeNode node) {
        JSONArray jsonArray = ConFile.getInstance().getIniJson();
        //进行连接
        for (int m=0;m<jsonArray.size();m++){
            JSONObject json = jsonArray.getJSONObject(m);
//            log.info("json = "+json.toJSONString());
            if (node.toString().equals(json.get("conname")+"")){
                log.info("进行连接");
//                log.info("urls = "+dbURLMap.getInstance().getUrls().toString());
                return isCon(json,node);
            }
        }
        return false;
    }

    private boolean isGoCon(JSONObject json,String title,String url,TreeNode[] path,int index){
        log.info("url = "+url);
        Connection con = goConn(dbNameMap.getInstance().getDbNames().get(title),
                url,
                json.get("username")+"",
                json.get("password")+"");
        if (con != null){
            log.info("成功");
            dbNameMap.getInstance().updateNames(title,con);

            return true;
        }
        return false;
    }

    private boolean isCon(JSONObject json,DefaultMutableTreeNode node){
        log.info("url = "+ dbURLMap.getInstance().getUrls().get(node.toString()));
        Connection con = goConn(dbNameMap.getInstance().getDbNames().get(node.toString()),
                dbURLMap.getInstance().getUrls().get(node.toString()),
                json.get("username")+"",
                json.get("password")+"");
        if (con != null){
            log.info("连接到数据库");
            dbNameMap.getInstance().updateNames(node.toString(),con);
            return true;
        }else {
            return false;
        }
    }


    private Connection goConn(Connection con,String url, String user, String pass){
        try {
            con = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            log.error(e.getMessage());
            JOptionPane.showMessageDialog(null,"连接数据库失败"+e.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
            return null;
//            throw new RuntimeException(e);
        }
        return con;
    }

    //获取表更新UI
    private void updateTableDB(TreeNode[] path){    // 0 连接 1 连接名 2 数据库名 3 表 4 表名
        List<Map<String,List<Map<String,List<Map<String,List<String>>>>>>> titles = dbShuJuKuMap.getInstance().getSjks();
//        log.info("titls = "+titles.toString());
        for (int j=0;j<titles.size();j++){  // title : dbName : table : list
            Map<String,List<Map<String,List<Map<String,List<String>>>>>> titles_map = titles.get(j);
            List<Map<String,List<Map<String,List<String>>>>> titles_list = titles_map.get(path[1].toString());
            if (titles_list == null) titles_list = new ArrayList<>();
            for (int i=0;i<titles_list.size();i++){     // dbName : table : list
                Map<String,List<Map<String,List<String>>>> dbName_map = titles_list.get(i);
                Set<String> set_dbName = dbName_map.keySet();
                for (String str_dbName : set_dbName){
                    if (path[2].toString().equals(str_dbName)){
                        List<Map<String,List<String>>> dbName_list = dbName_map.get(path[2].toString());
                        for (int k=0;k<dbName_list.size();k++){
                            Map<String,List<String>> table_map = dbName_list.get(k);
                            List<String> table_list = table_map.get(path[3].toString());
//                            log.info("table_list = "+table_list);
                            if (table_list != null && path[3].getChildCount() < table_list.size()){
                                DefaultMutableTreeNode node3 = (DefaultMutableTreeNode) path[3];
                                for (int y=0;y<table_list.size();y++){
                                    DefaultMutableTreeNode node4 = new DefaultMutableTreeNode(new Name(table_list.get(y)));
                                    node3.add(node4);
                                }
                            }
                        }
                    }
                }
            }
        }
        jTree.expandPath(new TreePath(path[3]));
        LeftJPanel.getInstance().updateJtree();
    }

    //获取库更新UI
    private void updateDB(JTree jTree, DefaultMutableTreeNode node) {
        List<Map<String,List<Map<String,List<Map<String,List<String>>>>>>> titles = dbShuJuKuMap.getInstance().getSjks();
//        log.info("titls = "+titles.toString());
        for (int j=0;j<titles.size();j++){  // 2
            Map<String,List<Map<String,List<Map<String,List<String>>>>>> titles_map = titles.get(j);
            Set<String> set_titles = titles_map.keySet();
            for (String str_titles : set_titles){
                if (node.toString().equals(str_titles)){
                    List<Map<String,List<Map<String,List<String>>>>> titles_list = titles_map.get(node.toString());
                    for (int i=0;i<titles_list.size();i++){ // 8
                        Map<String,List<Map<String,List<String>>>> dbName_map = titles_list.get(i);
                        Set<String> dbName_set = dbName_map.keySet();
                        for (String str_set : dbName_set){
                            if (node.getChildCount() < titles_list.size()) { //限制UI重复
                                DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(new Name(str_set));
                                node.add(node2);
                                List<Map<String, List<String>>> dbName_list = dbName_map.get(str_set);
                                for (int p = 0; p < dbName_list.size(); p++) {
                                    Map<String, List<String>> table_map = dbName_list.get(p);
                                    Set<String> table_set = table_map.keySet();
                                    for (String str_table : table_set) {
                                        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode(new Name(str_table));
                                        node2.add(node3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        jTree.expandPath(new TreePath(node.getPath()));
        LeftJPanel.getInstance().updateJtree();
    }

}
