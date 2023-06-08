package util;

import cn.zhshipu.log.WinLog;
import com.mongodb.MongoClient;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class DBUtil {
    /**
     * 数据库连接
     *
     * */


    private static Connection con = null;
    WinLog log = new WinLog("DBUtil");

    private static MongoClient mc = null;

    public DBUtil(){

    }

    public DBUtil(int index,    //index: 1 mysql5.2  2 mysql8.0 3 oracle 4 sqlserver 5 mongodb
                  String ip,
                  String port){
        String device;

        switch (index){
            case 1:
                device = "com.mysql.jdbc.Driver";
                try {
                    Class.forName(device);
                } catch (ClassNotFoundException e) {
                    log.error(e.getMessage());
                    JOptionPane.showMessageDialog(null,"驱动加载失败"+e.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
//                    throw new RuntimeException(e);
                }
//                con = goConn(url,user,pass);
                break;
            case 2:
                device = "com.mysql.cj.jdbc.Driver";
                try {
                    Class.forName(device);
                } catch (ClassNotFoundException e) {
                    log.error(e.getMessage());
                    JOptionPane.showMessageDialog(null,"驱动加载失败"+e.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
//                    throw new RuntimeException(e);
                }
//                con = goConn(url,user,pass);
                break;
            case 3:
                device = "oracle.jdbc.driver.OracleDriver";
                try {
                    Class.forName(device);
                } catch (ClassNotFoundException e) {
                    log.error(e.getMessage());
                    JOptionPane.showMessageDialog(null,"驱动加载失败"+e.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
//                    throw new RuntimeException(e);
                }
//                con = goConn(url,user,pass);
                break;
            case 4:
                device = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                try {
                    Class.forName(device);
                } catch (ClassNotFoundException e) {
                    log.error(e.getMessage());
                    JOptionPane.showMessageDialog(null,"驱动加载失败"+e.getMessage(),"提示",JOptionPane.ERROR_MESSAGE);
//                    throw new RuntimeException(e);
                }
//                con = goConn(url,user,pass);
                break;
            case 5:
                mc = new MongoClient(ip, Integer.parseInt(port));
                log.info(String.valueOf(mc));
                break;
            default:
                break;
        }

    }


    public Connection getConn(){
        return con;
    }

    public void Close(){
        Map<String, Connection> dbNames = dbNameMap.getInstance().getDbNames();
        for (Connection con : dbNames.values()){
            if (con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    log.error(e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
