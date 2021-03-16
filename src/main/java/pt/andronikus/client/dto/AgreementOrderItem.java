package pt.andronikus.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pt.andronikus.client.enums.OperationType;

public class AgreementOrderItem extends OrderItem{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accountId;
    private String agreementId;
    private String parentAgreementId;
    private String catalogSpec;
    private String offerSpec;
    private String offerParams;

    public AgreementOrderItem(){
        super();
    }

    public AgreementOrderItem(OperationType operationType){
        super(operationType);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getCatalogSpec() {
        return catalogSpec;
    }

    public void setCatalogSpec(String catalogSpec) {
        this.catalogSpec = catalogSpec;
    }

    public String getOfferSpec() {
        return offerSpec;
    }

    public void setOfferSpec(String offerSpec) {
        this.offerSpec = offerSpec;
    }

    public String getOfferParams() {
        return offerParams;
    }

    public String getParentAgreementId() {
        return parentAgreementId;
    }

    public void setParentAgreementId(String parentAgreementId) {
        this.parentAgreementId = parentAgreementId;
    }

    public void setOfferParams(FulfillmentParams fulfillmentParams) {
        this.offerParams = "{\"parameters\":" + fulfillmentParams.toJson() + "}";
    }

    @Override
    public String toString() {
        return "AgreementOrderItem{" +
                "accountId='" + accountId + '\'' +
                ", agreementId='" + agreementId + '\'' +
                ", parentAgreementId='" + parentAgreementId + '\'' +
                ", catalogSpec='" + catalogSpec + '\'' +
                ", offerSpec='" + offerSpec + '\'' +
                ", offerParams='" + offerParams + '\'' +
                ", correlationId='" + correlationId + '\'' +
                ", externalItemId='" + externalItemId + '\'' +
                ", otherInfo=" + otherInfo +
                ", reason=" + reason +
                ", operation=" + operation +
                '}';
    }
}
