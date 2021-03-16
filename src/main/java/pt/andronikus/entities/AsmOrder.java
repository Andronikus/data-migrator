package pt.andronikus.entities;

import pt.andronikus.client.enums.OperationType;
import pt.andronikus.enums.EntityType;

public class AsmOrder {
    private String orderExternalId;
    private String orderCorrelationId;
    private EntityType entityType;
    private OperationType operation;
    private String orderStatus;

    public AsmOrder() {
    }

    public AsmOrder(String orderExternalId, String orderCorrelationId, EntityType entityType, OperationType operation, String orderStatus) {
        this.orderExternalId = orderExternalId;
        this.orderCorrelationId = orderCorrelationId;
        this.entityType = entityType;
        this.operation = operation;
        this.orderStatus = orderStatus;
    }

    public String getOrderExternalId() {
        return orderExternalId;
    }

    public void setOrderExternalId(String orderExternalId) {
        this.orderExternalId = orderExternalId;
    }

    public String getOrderCorrelationId() {
        return orderCorrelationId;
    }

    public void setOrderCorrelationId(String orderCorrelationId) {
        this.orderCorrelationId = orderCorrelationId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "AsmOrder{" +
                "orderExternalId='" + orderExternalId + '\'' +
                ", orderCorrelationId='" + orderCorrelationId + '\'' +
                ", entityType=" + entityType +
                ", operation=" + operation +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
