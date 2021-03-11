package pt.andronikus.client.dto;

import pt.andronikus.client.enums.OperationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class OrderItem {
    protected final String correlationId;
    protected String externalItemId;
    protected Map<String,List<EntryObject>> otherInfo;
    protected Reason reason;
    protected OperationType operation;

    public OrderItem() {
        this.correlationId = UUID.randomUUID().toString();
        this.otherInfo = new HashMap<>();
    }

    public OrderItem(OperationType operation) {
        this();
        this.operation = operation;
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

    public Map<String, List<EntryObject>> getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(Map<String, List<EntryObject>> otherInfo) {
        this.otherInfo = otherInfo;
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

    public void addOtherInfoEntry(String key, List<EntryObject> value){
        this.otherInfo.putIfAbsent(key,value);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "correlationId='" + correlationId + '\'' +
                ", externalItemId='" + externalItemId + '\'' +
                ", otherInfo=" + otherInfo +
                ", reason=" + reason +
                ", operation=" + operation +
                '}';
    }
}
