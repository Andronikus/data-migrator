package pt.andronikus.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.factory.CustomerRequestFactory;
import pt.andronikus.client.request.CustomerRequest;
import pt.andronikus.client.response.CustomerResponse;
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

public class CustomerThread implements Runnable{
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerThread.class);
    private final String LOG_PREFIX = CustomerThread.class.getSimpleName() + " :: ";

    private final ASMClient asmClient;

    public CustomerThread() {
        this.asmClient = new ASMClient();
    }

    @Override
    public void run() {
        boolean wasCreatedCustomer;
        boolean wasClosedCustomer;

        try {

            if(LOGGER.isInfoEnabled()){
                LOGGER.info(LOG_PREFIX + "Lets start migrating some Customers...");
            }

            CustomerDao customerDao = DaoFactory.createCustomerDao(true);
            AsmOrderDao asmOrderDao = DaoFactory.createAsmOrderDao(true);

            while(Migration.INSTANCE.getStatus().equals(Migration.Status.RUNNING)){
                wasCreatedCustomer = createCustomers(customerDao, asmOrderDao);
                wasClosedCustomer = closeCustomers(customerDao, asmOrderDao);

                if (!(wasCreatedCustomer || wasClosedCustomer)){
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
                LOGGER.info(LOG_PREFIX + " Leaving CreateCustomer Thread" );
            }
        }
    }

    private boolean createCustomers(CustomerDao customerDao, AsmOrderDao asmOrderDao){
        final String METHOD_NAME = LOG_PREFIX + " createCustomers ";
        boolean didSomething = false;
        // get from DB the last customer in new state
        List<Customer> customers = customerDao.getCustomerToCreate(1);

        if(LOGGER.isInfoEnabled()){
            LOGGER.info(METHOD_NAME + "customer list size: " + customers.size());
        }

        if(customers.size() > 0){
            for (Customer customer: customers){
                // create the request
                CustomerRequest customerRequest = CustomerRequestFactory.getCustomerCreationRequest(customer);

                // send request
                if(LOGGER.isInfoEnabled()){
                    LOGGER.info(METHOD_NAME + JSONUtils.toJSON(customerRequest));
                }

                Optional<CustomerResponse> customerResponse = this.asmClient.customerPost(customerRequest);

                if (customerResponse.isPresent()){

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(METHOD_NAME + "error code " + customerResponse.get().getErrorCode());
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

                    asmOrderDao.addAsmOrder(asmOrder);

                    didSomething = true;
                }
            }
        }
        return  didSomething;
    }

    private boolean closeCustomers(CustomerDao customerDao, AsmOrderDao asmOrderDao){
        final String METHOD_NAME = LOG_PREFIX + " closeCustomers ";

        boolean didSomething = false;
        // get from DB the last customer in new state
        List<Customer> customers = customerDao.getCustomerToClose(1);

        if(LOGGER.isInfoEnabled()){
            LOGGER.info(METHOD_NAME + "customer list size: " + customers.size());
        }

        if(customers.size() > 0){
            for (Customer customer: customers){
                // create the request
                CustomerRequest customerRequest = CustomerRequestFactory.getCustomerUpdateRequest(customer);

                Optional<CustomerResponse> customerResponse = this.asmClient.customerPost(customerRequest);

                if (customerResponse.isPresent()){

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(METHOD_NAME + "error code " + customerResponse.get().getErrorCode());
                    }

                    if(customerResponse.get().getErrorCode().equals("ASM_0008")){
                        // ASM validate and accept the request for async process
                        // change the customer record to the "WAITING_CREATE" state
                        customerDao.updateCustomerMigrationState(customer, MigrationStatus.WAITING_CLOSE.name());
                    }

                    AsmOrder asmOrder = new AsmOrder();
                    asmOrder.setOrderExternalId(customerResponse.get().getOrderExternalId());
                    asmOrder.setOrderCorrelationId(customerResponse.get().getOrderCorrelationId());
                    asmOrder.setEntityType(EntityType.CUSTOMER);
                    asmOrder.setOperation(OperationType.UPDATE);
                    asmOrder.setOrderStatus(customerResponse.get().getOrderStatus());

                    asmOrderDao.addAsmOrder(asmOrder);

                    didSomething = true;
                }
            }
        }
        return  didSomething;
    }
}
