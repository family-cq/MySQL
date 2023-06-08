package config;

import java.util.List;
import java.util.Map;

public class SelectName {
    private String title;    //连接名
    private String db;      // 数据库名
    private Map<String,List<String>> tables;//查询名+sql

    public SelectName(String title,String db,Map<String,List<String>> tables){
        this.title = title;
        this.db = db;
        this.tables = tables;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDb() {
        return db;
    }

    public Map<String,List<String>> getTables() {
        return tables;
    }


    public String getTitle() {
        return title;
    }


    public void setTables(Map<String,List<String>> tables) {
        this.tables = tables;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
