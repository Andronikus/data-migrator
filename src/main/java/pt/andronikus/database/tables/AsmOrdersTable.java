package pt.andronikus.database.tables;

public class AsmOrdersTable {
    public final static String ASM_ORDERS = "ASM_ORDERS";

    // columns
    public final static String ORDER_EXTERNAL_ID = "ORDER_EXTERNAL_ID";
    public final static String ORDER_CORRELATION_ID = "ORDER_CORRELATION_ID";
    public final static String ENTITY_TYPE = "ENTITY_TYPE";
    public final static String OPERATION = "OPERATION";
    public final static String ORDER_STATUS = "ORDER_STATUS";
    public final static String CREATED_AT = "CREATED_AT";

    // dml
    public final static String INSERT_ASM_ODER = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES ( ?,?,?,?,?)",
            ASM_ORDERS, ORDER_EXTERNAL_ID,ORDER_CORRELATION_ID, ENTITY_TYPE, OPERATION, ORDER_STATUS);
}
