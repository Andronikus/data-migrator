package pt.andronikus.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.factory.BillingAccountRequestFactory;
import pt.andronikus.client.factory.CustomerRequestFactory;
import pt.andronikus.client.request.BillingAccountRequest;
import pt.andronikus.client.request.CustomerRequest;
import pt.andronikus.client.response.BillingAccountResponse;
import pt.andronikus.client.response.CustomerResponse;
import pt.andronikus.client.service.ASMClient;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.dao.AsmOrderDao;
import pt.andronikus.dao.BillingAccountDao;
import pt.andronikus.dao.CustomerDao;
import pt.andronikus.dao.DaoFactory;
import pt.andronikus.entities.AsmOrder;
import pt.andronikus.entities.BillingAccount;
import pt.andronikus.entities.Customer;
import pt.andronikus.enums.EntityType;
import pt.andronikus.enums.MigrationStatus;
import pt.andronikus.singletons.BillingAccountMigration;
import pt.andronikus.singletons.Migration;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BillingAccountThread implements Runnable{
    private final Logger LOGGER = LoggerFactory.getLogger(BillingAccountThread.class);
    private final String LOG_PREFIX = BillingAccountThread.class.getSimpleName() + " :: ";

    private final ASMClient asmClient;

    public BillingAccountThread() {
        this.asmClient = new ASMClient();
    }

    @Override
    public void run() {
        boolean wasCreatedBillingAccount;
        boolean wasClosedBillingAccount;

        try {

            if(LOGGER.isInfoEnabled()){
                LOGGER.info(LOG_PREFIX + "Lets start migrating some Billing accounts...");
            }

            BillingAccountDao billingAccountDao = DaoFactory.createBillingAccountDao(true);
            AsmOrderDao asmOrderDao = DaoFactory.createAsmOrderDao(true);

            while(BillingAccountMigration.INSTANCE.getStatus().equals(BillingAccountMigration.Status.RUNNING)){
                wasCreatedBillingAccount = createBillingAccount(billingAccountDao, asmOrderDao);
                wasClosedBillingAccount = closeBillingAccount(billingAccountDao, asmOrderDao);

                if (!(wasCreatedBillingAccount || wasClosedBillingAccount)){
                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(LOG_PREFIX + String.format("Thread will sleep %s (ms)", 5000));
                    }
                    Thread.sleep(5000);
                }
            }
        } catch (SQLException sqlException) {
            LOGGER.error(LOG_PREFIX + "Something happened connecting to DB");
            sqlException.printStackTrace();
        } catch (InterruptedException interruptedException){
            LOGGER.error(LOG_PREFIX + "Interrupted Exception happened! Time to leave this thread");
        } catch (Exception e){
            LOGGER.error(LOG_PREFIX + " Exception occurred! ");
            e.printStackTrace();
        }finally {
            if(LOGGER.isInfoEnabled()){
                LOGGER.info(LOG_PREFIX + " Leaving BillingAccount Thread" );
            }
        }
    }

    private boolean createBillingAccount(BillingAccountDao billingAccountDao, AsmOrderDao asmOrderDao){
        final String METHOD_NAME = LOG_PREFIX + " createBillingAccount ";
        boolean didSomething = false;

        // get from DB the last customer in new state
        List<BillingAccount> billingAccounts = billingAccountDao.getBillingAccountToCreate(1);

        if(LOGGER.isInfoEnabled()){
            LOGGER.info(METHOD_NAME + "billing account list size: " + billingAccounts.size());
        }

        if(billingAccounts.size() > 0){
            for (BillingAccount billingAccount: billingAccounts){
                // create the request
                BillingAccountRequest billingAccountRequest = BillingAccountRequestFactory.getBillingAccountCreateRequest(billingAccount);

                // send request
                if(LOGGER.isInfoEnabled()){
                    LOGGER.info(METHOD_NAME + JSONUtils.toJSON(billingAccountRequest));
                }

                Optional<BillingAccountResponse> billingAccountResponse = this.asmClient.billingAccountPost(billingAccountRequest);

                if (billingAccountResponse.isPresent()){

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(METHOD_NAME + "error code " + billingAccountResponse.get().getErrorCode());
                    }

                    if(billingAccountResponse.get().getErrorCode().equals("ASM_0008")){
                        // ASM validate and accept the request for async process
                        // change the customer record to the "WAITING_CREATE" state
                        billingAccountDao.updateBillingAccountMigrationState(billingAccount, MigrationStatus.WAITING_CREATE.name());
                    }

                    AsmOrder asmOrder = new AsmOrder();
                    asmOrder.setOrderExternalId(billingAccountResponse.get().getOrderExternalId());
                    asmOrder.setOrderCorrelationId(billingAccountResponse.get().getOrderCorrelationId());
                    asmOrder.setEntityType(EntityType.BILLING_ACCOUNT);
                    asmOrder.setOperation(OperationType.CREATE);
                    asmOrder.setOrderStatus(billingAccountResponse.get().getOrderStatus());

                    asmOrderDao.addAsmOrder(asmOrder);

                    didSomething = true;
                }
            }
        }
        return  didSomething;
    }

    private boolean closeBillingAccount (BillingAccountDao billingAccountDao, AsmOrderDao asmOrderDao){
        final String METHOD_NAME = LOG_PREFIX + " closeBillingAccount ";

        boolean didSomething = false;
        // get from DB the last customer in new state
        List<BillingAccount> billingAccounts = billingAccountDao.getBillingAccountToClose(1);

        if(LOGGER.isInfoEnabled()){
            LOGGER.info(METHOD_NAME + "billing account to close size: " + billingAccounts.size());
        }

        if(billingAccounts.size() > 0){
            for (BillingAccount billingAccount: billingAccounts){
                // create the request
                BillingAccountRequest billingAccountRequest = BillingAccountRequestFactory.getBillingAccountUpdateRequest(billingAccount);

                Optional<BillingAccountResponse> billingAccountResponse = this.asmClient.billingAccountPost(billingAccountRequest);

                if (billingAccountResponse.isPresent()){

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(METHOD_NAME + "error code " + billingAccountResponse.get().getErrorCode());
                    }

                    if(billingAccountResponse.get().getErrorCode().equals("ASM_0008")){
                        billingAccountDao.updateBillingAccountMigrationState(billingAccount, MigrationStatus.WAITING_CLOSE.name());
                    }

                    AsmOrder asmOrder = new AsmOrder();
                    asmOrder.setOrderExternalId(billingAccountResponse.get().getOrderExternalId());
                    asmOrder.setOrderCorrelationId(billingAccountResponse.get().getOrderCorrelationId());
                    asmOrder.setEntityType(EntityType.CUSTOMER);
                    asmOrder.setOperation(OperationType.UPDATE);
                    asmOrder.setOrderStatus(billingAccountResponse.get().getOrderStatus());

                    asmOrderDao.addAsmOrder(asmOrder);

                    didSomething = true;
                }
            }
        }
        return  didSomething;
    }
}
