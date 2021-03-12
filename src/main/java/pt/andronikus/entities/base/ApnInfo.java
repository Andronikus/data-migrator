package pt.andronikus.entities.base;

import java.util.ArrayList;
import java.util.List;

public class ApnInfo {
    private String apnId;
    private String type;
    private final List<IpAddressInfo> ipAddressList;

    public ApnInfo() {
        this.ipAddressList = new ArrayList<>();
    }

    public ApnInfo(String apnId, String type) {
        this();
        this.apnId = apnId;
        this.type = type;
    }

    public String getApnId() {
        return apnId;
    }

    public void setApnId(String apnId) {
        this.apnId = apnId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<IpAddressInfo> getIpAddressList() {
        return ipAddressList;
    }

    public void setIpAddress(IpAddressInfo ipAddress) {
        this.ipAddressList.add(ipAddress);
    }

    static class IpAddressInfo {
        private String type;
        private boolean fixed;
        private String fixedIpRanges;

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
    }
}
