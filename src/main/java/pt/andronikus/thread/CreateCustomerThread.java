package pt.andronikus.thread;

import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.factory.CustomerRequestFactory;
import pt.andronikus.client.request.CustomerRequest;
import pt.andronikus.client.response.CustomerResponse;
import pt.andronikus.client.response.OrderExecutionResponse;
import pt.andronikus.client.service.ASMClient;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.dao.AsmOrderDao;
import pt.andronikus.dao.CustomerDao;
import pt.andronikus.dao.DaoFactory;
import pt.andronikus.entities.AsmOrder;
import pt.andronikus.entities.Customer;
import pt.andronikus.enums.EntityType;
import pt.andronikus.enums.MigrationStatus;
import pt.andronikus.singletons.Migration;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CreateCustomerThread implements Runnable{
    private final Logger LOGGER = LoggerFactory.getLogger(CreateCustomerThread.class);
    private final String LOG_PREFIX = CreateCustomerThread.class.getSimpleName() + " :: ";

    private ASMClient asmClient;

    public CreateCustomerThread() {
        this.asmClient = new ASMClient();
    }

    @Override
    public void run() {
        try {
            if(LOGGER.isInfoEnabled()){
                LOGGER.info(LOG_PREFIX + "Lets start migrating some Customers...");
            }

            CustomerDao customerDao = DaoFactory.createCustomerDao(true);
            AsmOrderDao asmOrderDao = DaoFactory.createAsmOrderDao(true);

            while(Migration.INSTANCE.getStatus().equals(Migration.Status.RUNNING)){

                    // get from DB the last customer in new state
                    List<Customer> customers = customerDao.getCustomerToCreate(1);

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(LOG_PREFIX + "customer list size: " + customers.size());
                    }

                    if(customers.size() > 0){
                        for (Customer customer: customers){
                            // create the request
                            CustomerRequest customerRequest = CustomerRequestFactory.getCustomerCreationRequest(customer);

                            // send request
                            if(LOGGER.isInfoEnabled()){
                                LOGGER.info(LOG_PREFIX + JSONUtils.toJSON(customerRequest));
                            }
                            Optional<CustomerResponse> customerResponse = this.asmClient.customerPost(customerRequest);

                            if (customerResponse.isPresent()){

                                if(LOGGER.isInfoEnabled()){
                                    LOGGER.info(LOG_PREFIX + "error code " + customerResponse.get().getErrorCode());
                                }

                                if(customerResponse.get().getErrorCode().equals("ASM_0008")){
                                    // ASM validate and accept the request for async process
                                    // change the customer record to the "WAITING_CREATE" state
                                    customerDao.updateCustomerMigrationState(customer, MigrationStatus.WAITING_CREATE.name());
                                }

                                AsmOrder asmOrder = new AsmOrder();
                                asmOrder.setOrderExternalId(customerResponse.get().getOrderExternalId());
                                asmOrder.setOrderCorrelationId(customerResponse.get().getOrderCorrelationId());
                                asmOrder.setEntityType(EntityType.CUSTOMER);
                                asmOrder.setOperation(OperationType.CREATE);
                                asmOrder.setOrderStatus(customerResponse.get().getOrderStatus());

                                boolean asmOrderInserted = asmOrderDao.addAsmOrder(asmOrder);
                            }

                        }
                    }else {
                        // nothing to do... wait a little more
                        if(LOGGER.isInfoEnabled()){
                            LOGGER.info(LOG_PREFIX + " No new customers do migrate... wait 10s..." );
                        }
                        Thread.sleep(10000);
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
                LOGGER.info(LOG_PREFIX + " Leaving CreateCustomer Thread" );
            }
        }
    }
}
