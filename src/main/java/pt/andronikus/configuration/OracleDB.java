package pt.andronikus.configuration;

public class OracleDB {
    private String ipAddress;
    private String port;
    private String sid;

    public OracleDB(){}
    public OracleDB(String ipAddress, String port, String sid) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.sid = sid;
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

    @Override
    public String toString() {
        return "OracleDB{" +
                "ipAddress='" + ipAddress + '\'' +
                ", port='" + port + '\'' +
                ", sid='" + sid + '\'' +
                '}';
    }
}
