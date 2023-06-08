package util;

import cn.zhshipu.log.WinLog;

import java.util.*;

public class dbShuJuKuMap {
    private static dbShuJuKuMap instance = null;
    WinLog log = new WinLog("dbShuJuKuMap");
    private List<Map<String,List<Map<String,List<Map<String,List<String>>>>>>> sjks = new ArrayList<>();

    public static dbShuJuKuMap getInstance(){
        if (instance == null){
            instance = new dbShuJuKuMap();
        }
        return instance;
    }
    public List<Map<String,List<Map<String,List<Map<String,List<String>>>>>>> getSjks(){
        return sjks;
    }

    //增加数据库名
    public void addSjks(String title,String dbName){
//        log.info("sjks = "+sjks.toString());

        for (int i=0;i<sjks.size();i++){
            Map<String, List<Map<String,List<Map<String,List<String>>>>>> title_maps = sjks.get(i);
            Set<String> set_titles = title_maps.keySet();
            for (String str_titles : set_titles){
//                log.info("str_titles = "+str_titles);
                if (title.equals(str_titles)){
                    List<Map<String,List<Map<String,List<String>>>>> db_list = title_maps.get(title);
                    for (int u=0;u<db_list.size();u++){
                        Map<String,List<Map<String,List<String>>>> db_map = db_list.get(u);
                        Set<String> setss =db_map.keySet();
                        for (String strr : setss){
//                            log.info("strr = "+strr);
                            if (dbName.equals(strr)){
                                return;
                            }
                        }
                    }
                }
            }
        }

        Map<String,List<String>> dbs = new HashMap<>();
        dbs.put("表",new ArrayList<>());
        dbs.put("函数",new ArrayList<>());
        dbs.put("视图",new ArrayList<>());
        dbs.put("查询",new ArrayList<>());
        List<Map<String,List<String>>> table_list = new ArrayList<>();
        table_list.add(dbs);
        Map<String ,List<Map<String,List<String>>>> table_map = new HashMap<>();
        table_map.put(dbName,table_list);

//        log.info("table_map = "+table_map);

        //判断是否有数据 有增加 没有 新建
        for (int j=0;j<sjks.size();j++){
//            List<Map<String,List<Map<String,List<Map<String,List<String>>>>>>>
            Map<String,List<Map<String,List<Map<String,List<String>>>>>> title_map = sjks.get(j);
//            log.info("title_map = "+title_map);
            Set<String> set_title = title_map.keySet();
            for (String str_title : set_title){
                if (title.equals(str_title)){
                    List<Map<String ,List<Map<String,List<String>>>>>db_list = title_map.get(str_title);

                    db_list.add(table_map);
//                    log.info("db_list = "+db_list);
//                Map<String, List<Map<String ,List<Map<String,List<String>>>>>> db_map = new HashMap<>();
                    title_map.put(title,db_list);
//                sjks.add(title_map);
                }
            }
        }
    }


    //初始化连接名
    public void setSjks(String title){
        for (int m =0; m < sjks.size();m++){
            Map<String,List<Map<String,List<Map<String,List<String>>>>>> title_map = sjks.get(m);
            Set<String> set = title_map.keySet();
            for (String str : set){
                if (title.equals(str))
                    return;
            }
        }
        Map<String,List<Map<String,List<Map<String,List<String>>>>>> map = new HashMap<>();
        map.put(title,new ArrayList<>());
        sjks.add(map);
    }

    //增加表/视图名
    public void addSjksDBs(String title,String dbName,String type,String db){
        for (int i=0;i<sjks.size();i++){
            List<Map<String,List<Map<String,List<String>>>>> title_list = sjks.get(i).get(title);
            if (title_list == null)title_list = new ArrayList<>();
            for (int j=0;j<title_list.size();j++){
                Map<String,List<Map<String,List<String>>>> dbName_map = title_list.get(j);
                Set<String> set_dbName = dbName_map.keySet();
                for (String str_dbName : set_dbName){
                    if (dbName.equals(str_dbName)){
//                        log.info("dbName = "+dbName);
//                        log.info("dbName_map = "+dbName_map);
                        List<Map<String,List<String>>> dbName_list = dbName_map.get(dbName);
                        for (int k=0;k<dbName_list.size();k++){
                            Map<String,List<String>> table_map = dbName_list.get(k);
                            List<String> list = table_map.get(type);
                            if (list.size() > 0){
                                for (String str : list){
                                    if (db.equals(str)){
//                                        log.info(type+"名重复");
                                        return;
                                    }
                                }
                            }
//                            log.info("新增db = "+db);
                            list.add(db);
                        }
                    }
                }
            }
        }
    }

    //修改库名
//    public void updateSjks(String title,String dbName,String oldName,List<String> dbs){
//        for (int i=0;i<sjks.size();i++){
//            Map<String,List<Map<String,List<String>>>> titls_map = sjks.get(i);
//            Set<String> sets = titls_map.keySet();
//            boolean flag = false;
//            for (String str : sets){
//                if (title.equals(str)){
//                    List<Map<String,List<String>>> db_list = titls_map.get(str);
//                    for (int j=0;j<db_list.size();j++){
//                        Map<String,List<String>> db_map = db_list.get(j);
//                        Set<String> setss = db_map.keySet();
//                        for (String strr : setss){
//                            if (oldName.equals(strr)){
//                                Map<String,List<String>> dbNameMap = new HashMap<>();
//                                dbNameMap.put(dbName,dbs);
//                                db_list.remove(j);
//                                db_list.add(dbNameMap);
//                                flag = true;
//                                break;
//                            }
//                            if (flag){
//                                break;
//                            }
//                        }
//                        if (flag){
//                            break;
//                        }
//                    }
//                    if (flag){
//                        break;
//                    }
//                }
//            }
//        }
////        log.info("找不到连接名");
//    }
}
