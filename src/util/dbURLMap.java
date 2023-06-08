package util;

import java.util.HashMap;
import java.util.Map;

public class dbURLMap {
    private static dbURLMap instance = null;
    private Map<String,String> urls = new HashMap<>();
    public static dbURLMap getInstance() {
        if (instance == null) {
            instance = new dbURLMap();
        }
        return instance;
    }
    public void addURLMap(String title,String url){
        urls.put(title,url);
    }
    public Map<String,String> getUrls(){
        return urls;
    }
}
