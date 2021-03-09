package pt.andronikus.database.tables;

public class CustomerTable {
    public final static String CUSTOMER = "MIG_CUSTOMER_INFO";

    // columns
    public final static String CUSTOMER_ID = "CUSTOMER_ID";
    public final static String CUSTOMER_NAME = "CUSTOMER_NAME";
    public final static String ADDRESS = "ADDRESS";
    public final static String EMAIL = "EMAIL";
    public final static String LOCALE = "LOCALE";
    public final static String OPERATOR_ID = "OPERATOR_ID";
    public final static String PHONE = "PHONE";
    public final static String STATUS = "STATUS";
    public final static String TAX_NUMBER = "TAX_NUMBER";

    // queries
    public final static String GET_CUSTOMERS = "SELECT * FROM " + CUSTOMER;
    public final static String GET_CUSTOMER = "SELECT * FROM " + CUSTOMER + " WHERE ID=?";

}
