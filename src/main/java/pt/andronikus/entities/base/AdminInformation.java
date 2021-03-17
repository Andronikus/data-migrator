package pt.andronikus.entities.base;

import pt.andronikus.utils.WhenNullValueThen;

public class AdminInformation {
    private String adminName;
    private String adminLogin;
    private String adminMobile;
    private String adminEmail;

    public AdminInformation(){
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = WhenNullValueThen.setStringOrInAbsence(adminName, "");
    }

    public String getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin = WhenNullValueThen.setStringOrInAbsence(adminLogin, "");
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        this.adminMobile = WhenNullValueThen.setStringOrInAbsence(adminMobile, "");
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = WhenNullValueThen.setStringOrInAbsence(adminEmail, "") ;
    }

    @Override
    public String toString() {
        return "AdminInformation{" +
                "adminName='" + adminName + '\'' +
                ", adminLogin='" + adminLogin + '\'' +
                ", adminMobile='" + adminMobile + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                '}';
    }
}
