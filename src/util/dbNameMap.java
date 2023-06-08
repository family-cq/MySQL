package util;

import cn.zhshipu.log.WinLog;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class dbNameMap {
    /**
     * 储存连接与连接名
     * String  连接名(不能重复)
     * Connection 连接
     * */
    private static dbNameMap instance =null;
    private Map<String, Connection> dbNames = new HashMap<>();
    WinLog log = new WinLog("dbNameMap");
    public static dbNameMap getInstance(){
        if (instance == null){
            instance = new dbNameMap();
        }
        return instance;
    }
    //获取连接信息
    public Map<String, Connection> getDbNames(){
        return dbNames;
    }
    //修改连接
    public boolean updateNames(String title,Connection conn){
        Set<String> sets = dbNames.keySet();
        for (String str : sets){
            if (title.equals(str)){
                dbNames.put(title,conn);
                return true;
            }
        }
        log.info("没有该连接");
        return false;
    }
    //增加连接
    public boolean addDbNames(String title,Connection conn){
        Set<String> sets = dbNames.keySet();
        for (String str : sets){
            if (title.equals(str)){
                log.info("连接名重复");
                return false;
            }
        }
        dbNames.put(title,conn);
        return true;
    }
    public boolean deleteName(String title){
        Set<String> sets = dbNames.keySet();
        for (String str : sets){
            if (title.equals(str)){
                dbNames.remove(title);
                return true;
            }
        }
        log.info("没有该连接");
        return false;
    }
    //关闭
    public void Close(){
        dbNames = null;
        instance = null;
    }
}
