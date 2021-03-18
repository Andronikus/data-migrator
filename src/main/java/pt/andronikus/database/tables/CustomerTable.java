package pt.andronikus.database.tables;

import pt.andronikus.constants.Global;
import pt.andronikus.enums.MigrationStatus;
import pt.andronikus.singletons.AppConfiguration;

public class CustomerTable {
    public final static String CUSTOMER = "CDM_CUSTOMER";
    public final static String VW_CUSTOMER_TO_CREATE = "VW_CUSTOMER_TO_CREATE";

    // columns
    public final static String OPERATOR_ID = "OPERATOR_ID";
    public final static String CUSTOMER_ID = "CUSTOMER_ID";
    public final static String CUSTOMER_NAME = "CUSTOMER_NAME";
    public final static String PHONE = "PHONE";
    public final static String EMAIL = "EMAIL";
    public final static String ADDRESS = "ADDRESS";
    public final static String LOCALE = "LOCALE";
    public final static String TAX_NUMBER = "TAX_NUMBER";
    public final static String CUSTOMER_STATUS = "CUSTOMER_STATUS";

    public final static String PF = "PF";
    public final static String CORRELATION_ID = "CORRELATION_ID";
    public final static String ORDER_CORRELATION_ID = "ORDER_CORRELATION_ID";
    public final static String MIG_STATUS = "MIG_STATUS";
    public final static String CREATED_AT = "CREATED_AT";
    public final static String UPDATED_AT = "UPDATED_AT";
}
