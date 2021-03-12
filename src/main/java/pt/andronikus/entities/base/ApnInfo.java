package pt.andronikus.entities.base;

import java.util.ArrayList;
import java.util.List;

public class ApnInfo {
    private String id;
    private String type;
    private String ipType;
    private IpAddressInfo ipAddressList1;
    private IpAddressInfo ipAddressList2;

    public ApnInfo() {
    }

    public ApnInfo(String apnId, String type, String ipType) {
        this();
        this.id = apnId;
        this.type = type;
        this.ipType = ipType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IpAddressInfo getIpAddressList1() {
        return ipAddressList1;
    }

    public void setIpAddressList1(IpAddressInfo ipAddressList1) {
        this.ipAddressList1 = ipAddressList1;
    }

    public IpAddressInfo getIpAddressList2() {
        return ipAddressList2;
    }

    public void setIpAddressList2(IpAddressInfo ipAddressList2) {
        this.ipAddressList2 = ipAddressList2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpType() {
        return ipType;
    }

    public void setIpType(String ipType) {
        this.ipType = ipType;
    }

    @Override
    public String toString() {
        return "ApnInfo{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", ipType='" + ipType + '\'' +
                ", ipAddressList1=" + ipAddressList1 +
                ", ipAddressList2=" + ipAddressList2 +
                '}';
    }

    public static class IpAddressInfo {
        private String type;
        private boolean fixed;
        private String fixedIpRanges;

        public IpAddressInfo() {
        }

        public IpAddressInfo(String type, boolean fixed, String fixedIpRanges) {
            this.type = type;
            this.fixed = fixed;
            this.fixedIpRanges = fixedIpRanges;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isFixed() {
            return fixed;
        }

        public void setFixed(boolean fixed) {
            this.fixed = fixed;
        }

        public String getFixedIpRanges() {
            return fixedIpRanges;
        }

        public void setFixedIpRanges(String fixedIpRanges) {
            this.fixedIpRanges = fixedIpRanges;
        }

        @Override
        public String toString() {
            return "IpAddressInfo{" +
                    "type='" + type + '\'' +
                    ", fixed=" + fixed +
                    ", fixedIpRanges='" + fixedIpRanges + '\'' +
                    '}';
        }
    }

}
