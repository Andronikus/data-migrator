package pt.andronikus.database.tables;

public class CustomerTable {
    public final static String CUSTOMER = "CDM_CUSTOMER";

    // columns
    public final static String ADDRESS = "ADDRESS";
    public final static String CORRELATION_ID = "CORRELATION_ID";
    public final static String CUSTOMER_ID = "CUSTOMER_ID";
    public final static String CUSTOMER_NAME = "CUSTOMER_NAME";
    public final static String CUSTOMER_STATUS = "CUSTOMER_STATUS";
    public final static String EMAIL = "EMAIL";
    public final static String LOCALE = "LOCALE";
    public final static String MIG_STATUS = "MIG_STATUS";
    public final static String OPERATOR_ID = "OPERATOR_ID";
    public final static String ORDER_CORRELATION_ID = "ORDER_CORRELATION_ID";
    public final static String PF = "PF";
    public final static String PHONE = "PHONE";
    public final static String TAX_NUMBER = "TAX_NUMBER";


    // queries
    public final static String GET_CUSTOMERS = "SELECT * FROM " + CUSTOMER;
    public final static String GET_CUSTOMER = "SELECT * FROM " + CUSTOMER + " WHERE ID=?";
}
