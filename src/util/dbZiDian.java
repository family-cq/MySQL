package util;

import cn.zhshipu.log.WinLog;
import config.DBName;

import java.util.ArrayList;
import java.util.List;

public class dbZiDian {
    private static dbZiDian instance = null;
    private List<DBName> dbNames = new ArrayList<>();
    WinLog log = new WinLog("dbZiDian");
    public static dbZiDian getInstance(){
        if (instance == null){
            instance = new dbZiDian();
        }
        return instance;
    }

    public List<DBName> getDbNames(){
        return dbNames;
    }
    public void addDBName(DBName dbName){
        dbNames.add(dbName);
    }
    public void deleteDBName(String title){
        for (int i=0;i<dbNames.size();i++){
            if (title.equals(dbNames.get(i).getConname())){
                dbNames.remove(i);
            }
        }
    }
    public void Close(){
        dbNames = null;
        instance = null;
    }
}
