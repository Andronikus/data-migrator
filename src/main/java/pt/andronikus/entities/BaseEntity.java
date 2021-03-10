package pt.andronikus.entities;

public abstract class BaseEntity {
    protected Integer operatorId;
    protected String correlationId;
    protected String orderCorrelationId;
    protected String migStatus;

    public BaseEntity() {

    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getOrderCorrelationId() {
        return orderCorrelationId;
    }

    public void setOrderCorrelationId(String orderCorrelationId) {
        this.orderCorrelationId = orderCorrelationId;
    }

    public String getMigStatus() {
        return migStatus;
    }

    public void setMigStatus(String migStatus) {
        this.migStatus = migStatus;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "operatorId=" + operatorId +
                ", correlationId='" + correlationId + '\'' +
                ", orderCorrelationId='" + orderCorrelationId + '\'' +
                ", migStatus='" + migStatus + '\'' +
                '}';
    }
}
