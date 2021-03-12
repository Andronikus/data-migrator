package pt.andronikus.enums;

import java.util.Objects;

public enum AdministrativeStatus {
    ACTIVE("ACTIVE"),
    SUSPENDED("SUSPENDED");

    private final String adminStatus;

    AdministrativeStatus(String status) {
        this.adminStatus = status;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public AdministrativeStatus fromString(String orderItemType) {
        for(AdministrativeStatus type : AdministrativeStatus.values()){
            if(Objects.equals(type.name(), orderItemType.toUpperCase())){
                return type;
            }
        }
        return null;
    }
}
