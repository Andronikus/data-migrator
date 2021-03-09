package pt.andronikus.client.dto;

import pt.andronikus.client.enums.OperationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderItem {
    private final String correlationId;
    private String externalItemId;
    private String customerId;
    private String customerName;
    private Map<String,List<EntryObject>> otherInfo;
    private Reason reason;
    private OperationType operation;

    public OrderItem() {
        this.correlationId = UUID.randomUUID().toString();
        this.otherInfo = new HashMap<>();
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public String getExternalItemId() {
        return externalItemId;
    }

    public void setExternalItemId(String externalItemId) {
        this.externalItemId = externalItemId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<String, List<EntryObject>> getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(Map<String, List<EntryObject>> otherInfo) {
        this.otherInfo = otherInfo;
    }

    public void addOtherInfoEntry(String entryName, List<EntryObject> entry){
        this.otherInfo.putIfAbsent(entryName,entry);
    }


    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "externalItemId='" + externalItemId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", otherInfo=" + otherInfo.toString() +
                ", reason=" + reason.toString() +
                '}';
    }
}
