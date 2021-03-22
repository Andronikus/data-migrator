package pt.andronikus.entities;

import pt.andronikus.entities.base.BaseEntity;
import pt.andronikus.utils.WhenNullValueThen;

public class Customer extends BaseEntity {
    private String id;
    private String name;
    private String address;
    private String email;
    private String locale;
    private String phone;
    private String status;
    private String taxNumber;
    private Integer migFlag;

    public Customer() {
        super();
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
        this.name = WhenNullValueThen.setStringOrInAbsence(name,"");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = WhenNullValueThen.setStringOrInAbsence(address,"");;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = WhenNullValueThen.setStringOrInAbsence(email,"");
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = WhenNullValueThen.setStringOrInAbsence(locale,"");
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = WhenNullValueThen.setStringOrInAbsence(phone,"");
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
        this.taxNumber = WhenNullValueThen.setStringOrInAbsence(taxNumber, "");
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
                "operatorID=" + operatorId +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", locale='" + locale + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", taxNumber='" + taxNumber + '\'' +
                ", migFlag=" + migFlag +
                ", correlationID='" + correlationId + '\'' +
                ", orderCorrelationID='" + orderCorrelationId + '\'' +
                '}';
    }
}
