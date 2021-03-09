package pt.andronikus.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Server {
    @JsonProperty
    private String ipAddress;
    @JsonProperty
    private int port;

    public Server(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getIPAddress() {
        return ipAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.ipAddress = IPAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "AsmServer{" +
                "IPAddress='" + ipAddress + '\'' +
                ", port=" + port +
                '}';
    }
}
