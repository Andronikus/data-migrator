package pt.andronikus.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.client.factory.CustomerRequestFactory;
import pt.andronikus.client.request.CustomerRequest;
import pt.andronikus.client.response.OrderExecutionResponse;
import pt.andronikus.client.service.ASMClient;
import pt.andronikus.dao.CustomerDao;
import pt.andronikus.dao.DaoFactory;
import pt.andronikus.entities.Customer;
import pt.andronikus.enums.MigrationStatus;
import pt.andronikus.singletons.Migration;

import java.sql.SQLException;
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

        CustomerDao customerDao = null;

        try {
            if(LOGGER.isInfoEnabled()){
                LOGGER.info(LOG_PREFIX + "Lets start migrating some Customers...");
            }

            customerDao = DaoFactory.createCustomerDao(true);

            while(Migration.INSTANCE.getStatus().equals(Migration.Status.RUNNING)){

                    // get from DB the last customer in new state
                    Optional<Customer> customer = customerDao.getCustomerToCreate();

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(LOG_PREFIX + "customer " + customer.isPresent());
                    }

                    if(customer.isPresent()){
                        // create the request
                        CustomerRequest customerRequest = CustomerRequestFactory.getCustomerCreationRequest(customer.get());
                        // send request
                        // OrderExecutionResponse response = this.asmClient.customerCreatePost(customerRequest);

                        customerDao.updateCustomerMigrationState(customer.get().getId(), customer.get().getOrderCorrelationId(), MigrationStatus.WAITING_CREATE.name());
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
