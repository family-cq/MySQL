package fileconfig;

import cn.zhshipu.log.WinLog;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static config.Config.*;

public class ConFile {
    private static ConFile instance = null;
    private static WinLog log = new WinLog("ConFile");
    private static JSONArray iniJson = new JSONArray();

    /**
     * 连接名       conname
     * 数据库类型    type
     * 数据库ip      IP
     * 数据库端口    port
     * 账号         username
     * 密码         password
     * */

    public static ConFile getInstance() {
        if (instance == null){
            instance = new ConFile();
        }
        return instance;
    }

    public void Close(){
        iniJson = new JSONArray();
        instance = null;
    }

    ConFile(){
        //处理ini配置文件
        File ini = new File(sqlPath);
        if (ini.exists()){
            try {
                iniJson = (JSONArray)JSONObject.parse(new String(Files.readAllBytes(Paths.get(ini.getPath()))));
                log.info("sqlJson = "+iniJson.toJSONString());
            } catch (IOException e) {
                log.error(e.getMessage());
                JOptionPane.showMessageDialog(null,"获取文件格式错误","提示",JOptionPane.ERROR_MESSAGE);
//                throw new RuntimeException(e);
            }
        }else {
            try {
                ini.createNewFile();
                FileWriter writer = new FileWriter(ini);
                BufferedWriter out = new BufferedWriter(writer);
                out.write("[]");
                out.flush();
                out.close();
            } catch (IOException e) {
                log.error(e.getMessage());
                JOptionPane.showMessageDialog(null,"文件创建错误","提示",JOptionPane.ERROR_MESSAGE);
//                throw new RuntimeException(e);
            }
        }
    }

    //连接信息写入本地
    public void outWrite(Object obj,int index){ // 1 add 2 delete
//        JSONArray jsonArray = ConFile.getInstance().getIniJson();
        JSONArray jsonArray = iniJson;
        switch (index){
            case 1:
                jsonArray.add(obj);
                break;
            case 2:
                for (int i=0;i<jsonArray.size();i++){
                    JSONObject json = jsonArray.getJSONObject(i);
                    if (obj.equals(json.get("conname"))){
                        jsonArray.remove(i);
                        break;
                    }
                }
                break;
            default:
                break;
        }

        File file = new File(sqlPath);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("文件sql.json创建失败"+e.getMessage());
                JOptionPane.showMessageDialog(null,"文件创建错误","提示",JOptionPane.ERROR_MESSAGE);
//                throw new RuntimeException(e);
            }
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(jsonArray.toJSONString());
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("文件连接失败"+e.getMessage());
            JOptionPane.showMessageDialog(null,"文件储存失败","提示",JOptionPane.ERROR_MESSAGE);
//            throw new RuntimeException(e);
        }
    }

    public JSONArray getIniJson(){
        return iniJson;
    }
}
