package pt.andronikus.client.enums;

public enum OperationType {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    SUSPEND("SUSPEND"),
    UPDATE_SUSPEND("UPDATE_SUSPEND");

    private String operation;

    OperationType(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
