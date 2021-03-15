package pt.andronikus.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallbackServerConfiguration {
    @JsonProperty
    private String ipAddress;

    @JsonProperty
    private int port;

    @JsonProperty
    private String endpoint;

    public CallbackServerConfiguration() {
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
        return "CallbackServerConfiguration{" +
                "ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}
