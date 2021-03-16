package pt.andronikus.client.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.andronikus.client.dto.BillingAccountOrderItem;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.BillingAccountRequest;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.configuration.CallbackServerConfiguration;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.configuration.MigrationProcessInfo;
import pt.andronikus.entities.BillingAccount;
import pt.andronikus.singletons.AppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

class BillingAccountRequestFactoryTest {
    private static final boolean alreadySetUp = false;

    private BillingAccount billingAccount;

    @BeforeEach
    void setUp() {
        if(!alreadySetUp){
            this.billingAccount = createBillingAccount();

            CallbackServerConfiguration callbackCfg = new CallbackServerConfiguration();
            callbackCfg.setIpAddress("10.112.45.21");
            callbackCfg.setPort(7777);
            callbackCfg.setEndpoint("fulfillment");

            MigrationProcessInfo mig = new MigrationProcessInfo();
            mig.setOrderSource("MIG-R8-R9");
            mig.setChannel("M2M-MIG");

            InvokatorConfiguration cfg = new InvokatorConfiguration();
            cfg.setCallbackServerConfiguration(callbackCfg);
            cfg.setMigrationProcessInfo(mig);

            AppConfiguration.INSTANCE.setAppCfg(cfg);
        }
    }

    @Test
    void shouldCreateRequestHaveAValidBillingAccountOrderItem(){
        BillingAccountRequest request = BillingAccountRequestFactory.getBillingAccountCreateRequest(this.billingAccount);

        BillingAccountOrderItem orderItem = (BillingAccountOrderItem) request.getOrderItems().get(OrderItemType.ACCOUNT_ORDER_ITEM);
        assertNotNull(orderItem, "Billing Account create request should have a order item accountOrderItem and its null!");

        // validate order item main info
        assertTrue(orderItem.getCorrelationId().length() > 0);
        assertTrue(orderItem.getExternalItemId().length() > 0);
        assertEquals(orderItem.getCustomerId(), this.billingAccount.getCustomerId());
        assertEquals(orderItem.getAccountId(), this.billingAccount.getAccountId());
        assertEquals(orderItem.getAccountName(), this.billingAccount.getAccountName());
        assertEquals(orderItem.getBillingCycleDay(), this.billingAccount.getBillingCycleDay());
        assertEquals(OperationType.CREATE, orderItem.getOperation());

        // validate reason
        assertEquals("OTHER",orderItem.getReason().getCode());
        assertEquals("Migration CREATE Billing Account",orderItem.getReason().getDescription());

        System.out.println(JSONUtils.toJSON(request));
    }

    private BillingAccount createBillingAccount(){
        BillingAccount billingAccount = new BillingAccount();
        billingAccount.setOperatorId(1);
        billingAccount.setAccountId("d40ac04b01db70");
        billingAccount.setAccountName("d40ac04b01db70");
        billingAccount.setCustomerId("f97369847cTR01");
        billingAccount.setBillingCycleDay(1);

        billingAccount.setCorrelationId("MIG_a97369847c3727");
        billingAccount.setOrderCorrelationId("MIG_BILL_a97369847c3727");

        return  billingAccount;
    }
}