package pt.andronikus.configuration;

public class OracleDB {
    private String ipAddress;
    private String port;
    private String sid;
    private String username;
    private String password;
    private Integer partition;

    public OracleDB(){}
    public OracleDB(String ipAddress, String port, String sid) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.sid = sid;
    }

    public OracleDB(String ipAddress, String port, String sid, String username, String password, Integer partition) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.sid = sid;
        this.username = username;
        this.password = password;
        this.partition = partition;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    @Override
    public String toString() {
        return "OracleDB{" +
                "ipAddress='" + ipAddress + '\'' +
                ", port='" + port + '\'' +
                ", sid='" + sid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", partition=" + partition +
                '}';
    }
}
