package pt.andronikus.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ASMServerConfiguration {
    @JsonProperty
    private String name;

    @JsonProperty
    private String ipAddress;

    @JsonProperty
    private int port;

    @JsonProperty
    private String endpoint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "ASMServer{" +
                "name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}
