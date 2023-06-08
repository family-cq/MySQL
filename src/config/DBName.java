package config;

public class DBName {
    /**
     * 新建连接信息
     * */

        /*
        *   {
    "conname": "数据库1",
    "type": "MYSQL",
    "ip": "0.0.0.0",
    "port": "3306",
    "username": "",
    "password": ""
  },
  {
    "conname": "数据库2",
    "type": "ORACLE",
    "ip": "127.0.0.1",
    "port": "1521",
    "username": "",
    "password": ""
  }
        * */
    private String conname;
    private String name;
    private String ip;
    private String port;
    private String username;
    private String password,serverName,type,db,yzdb;

    public DBName(){}

    public DBName(String conname,   //连接名
                  String name,      //数据库类型     mysql oracle
                  String ip,        //ip
                  String port,      //端口
                  String username,  //账号
                  String password, //密码
                  String serverName, //服务名
                  String type,      //连接类型
                  String db,        //初始数据库
                  String yzdb){     //验证数据库
        this.conname = conname;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.serverName = serverName;
        this.type = type;
        this.db = db;
        this.yzdb = yzdb;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getDb() {
        return db;
    }

    public String getServerName() {
        return serverName;
    }

    public String getYzdb() {
        return yzdb;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setYzdb(String yzdb) {
        this.yzdb = yzdb;
    }

    public String getConname() {
        return conname;
    }

    public String getIp() {
        return ip;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setConname(String conname) {
        this.conname = conname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DBName{" +
                "conname='" + conname + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
