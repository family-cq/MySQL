package fileconfig;

import cn.zhshipu.log.WinLog;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static config.Config.iniPath;

public class ConIni {
    private static ConIni instance = null;
    WinLog log = new WinLog("ConIni");
    private JSONObject iniJson = new JSONObject();
    public static ConIni getInstance(){
        if (instance == null){
            instance = new ConIni();
        }
        return instance;
    }

    ConIni(){
        File ini = new File(iniPath);
        if (ini.exists()){
            try {
                iniJson = (JSONObject) JSONObject.parse(new String(Files.readAllBytes(Paths.get(ini.getPath()))));
            } catch (IOException e) {
                log.error(e.getMessage());
//                throw new RuntimeException(e);
            }
        }else {
            try {
                ini.createNewFile();
                FileWriter writer = new FileWriter(ini);
                BufferedWriter out = new BufferedWriter(writer);
                out.write("{}");
                out.flush();
                out.close();
            } catch (IOException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    public void Close(){
        iniJson = new JSONObject();
        instance = null;
    }

    //写入本地配置文件
    private void writeJSON(){
        File file = new File(iniPath);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(writer);


            out.flush();
            out.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public JSONObject getIniJson(){
        return iniJson;
    }
}
