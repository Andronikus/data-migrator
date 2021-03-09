package pt.andronikus.entities;

public class Customer {
    private String id;
    private String name;
    private String address;
    private String email;
    private String locale;
    private Integer operatorID;
    private String phone;
    private String status;
    private String taxNumber;
    private Integer migFlag;

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Integer getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Integer getMigFlag() {
        return migFlag;
    }

    public void setMigFlag(Integer migFlag) {
        this.migFlag = migFlag;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", locale='" + locale + '\'' +
                ", operatorID=" + operatorID +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", taxNumber='" + taxNumber + '\'' +
                ", migFlag=" + migFlag +
                '}';
    }
}
