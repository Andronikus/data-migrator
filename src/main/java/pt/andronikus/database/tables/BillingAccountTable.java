package pt.andronikus.database.tables;

public class BillingAccountTable {
    public final static String CDM_BILLING_ACCOUNT = "CDM_BILLING_ACCOUNT";

    // columns
    public final static String OPERATOR_ID = "OPERATOR_ID";
    public final static String ACCOUNT_ID = "ACCOUNT_ID";
    public final static String PF = "PF";
    public final static String ACCOUNT_NAME = "ACCOUNT_NAME";
    public final static String CUSTOMER_ID = "CUSTOMER_ID";
    public final static String BILLING_CYCLE_DAY = "BILLING_CYCLE_DAY";
    public final static String CORRELATION_ID = "CORRELATION_ID";
    public final static String ORDER_CORRELATION_ID = "ORDER_CORRELATION_ID";
    public final static String MIG_STATUS = "MIG_STATUS";
    public final static String CREATED_AT = "CREATED_AT";
    public final static String UPDATED_AT = "UPDATED_AT";

    // queries
    public final static String GET_BILLING_ACCOUNT = String.format("SELECT * FROM %s where %s=?", CDM_BILLING_ACCOUNT, PF);
}