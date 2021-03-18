package pt.andronikus.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.factory.CustomerRequestFactory;
import pt.andronikus.client.factory.ResourceRequestFactory;
import pt.andronikus.client.request.CustomerRequest;
import pt.andronikus.client.request.ResourceRequest;
import pt.andronikus.client.response.CustomerResponse;
import pt.andronikus.client.response.ResourceResponse;
import pt.andronikus.client.service.ASMClient;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.dao.AsmOrderDao;
import pt.andronikus.dao.CustomerDao;
import pt.andronikus.dao.DaoFactory;
import pt.andronikus.dao.ResourceDao;
import pt.andronikus.entities.AsmOrder;
import pt.andronikus.entities.Customer;
import pt.andronikus.entities.Resource;
import pt.andronikus.enums.AdministrativeStatus;
import pt.andronikus.enums.EntityType;
import pt.andronikus.enums.MigrationStatus;
import pt.andronikus.singletons.Migration;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ResourceThread implements Runnable{
    private final Logger LOGGER = LoggerFactory.getLogger(ResourceThread.class);
    private final String LOG_PREFIX = ResourceThread.class.getSimpleName() + " :: ";

    private final ASMClient asmClient;

    public ResourceThread() {
        this.asmClient = new ASMClient();
    }

    @Override
    public void run() {
        boolean wasResourceCreated;
        boolean wasResourceClosed;
        boolean wasResourceSuspend;

        try {

            if(LOGGER.isInfoEnabled()){
                LOGGER.info(LOG_PREFIX + "Lets start migrating some Resources...");
            }

            ResourceDao resourceDao = DaoFactory.createResourceDao(true);
            AsmOrderDao asmOrderDao = DaoFactory.createAsmOrderDao(true);

            while(Migration.INSTANCE.getStatus().equals(Migration.Status.RUNNING)){
                wasResourceCreated = createResources(resourceDao, asmOrderDao);
                wasResourceSuspend = suspendResources(resourceDao, asmOrderDao);
                wasResourceClosed = closeResources(resourceDao, asmOrderDao);

                if (!(wasResourceCreated || wasResourceClosed || wasResourceSuspend)){
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

    private boolean createResources(ResourceDao resourceDao, AsmOrderDao asmOrderDao){
        final String METHOD_NAME = LOG_PREFIX + " createResources ";
        boolean didSomething = false;

        // get from DB the last customer in new state
        List<Resource> resources = resourceDao.getResourceToCreate(1);

        if(LOGGER.isInfoEnabled()){
            LOGGER.info(METHOD_NAME + "resource list size: " + resources.size());
        }

        if(resources.size() > 0){
            for (Resource resource: resources){
                // create the request
                ResourceRequest resourceRequest = ResourceRequestFactory.getResourceCreateRequest(resource);

                // send request
                if(LOGGER.isInfoEnabled()){
                    LOGGER.info(METHOD_NAME + JSONUtils.toJSON(resourceRequest));
                }

                Optional<ResourceResponse> resourceResponse = this.asmClient.resourcePost(resourceRequest);

                if (resourceResponse.isPresent()){

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(METHOD_NAME + "error code " + resourceResponse.get().getErrorCode());
                    }

                    if(resourceResponse.get().getErrorCode().equals("ASM_0008")){
                        resourceDao.updateResourceMigrationState(resource, MigrationStatus.WAITING_CREATE.name());
                    }

                    AsmOrder asmOrder = new AsmOrder();
                    asmOrder.setOrderExternalId(resourceResponse.get().getOrderExternalId());
                    asmOrder.setOrderCorrelationId(resourceResponse.get().getOrderCorrelationId());
                    asmOrder.setEntityType(EntityType.RESOURCE);
                    asmOrder.setOperation(OperationType.CREATE);
                    asmOrder.setOrderStatus(resourceResponse.get().getOrderStatus());

                    asmOrderDao.addAsmOrder(asmOrder);

                    didSomething = true;
                }
            }
        }
        return  didSomething;
    }

    private boolean suspendResources(ResourceDao resourceDao, AsmOrderDao asmOrderDao){
        final String METHOD_NAME = LOG_PREFIX + " suspendResources ";

        boolean didSomething = false;

        List<Resource> resources = resourceDao.getResourceToSuspend(1);

        if(LOGGER.isInfoEnabled()){
            LOGGER.info(METHOD_NAME + "customer list size: " + resources.size());
        }

        if(resources.size() > 0){
            for (Resource resource: resources){
                // create the request
                ResourceRequest resourceRequest = ResourceRequestFactory.getResourceSuspendRequest(resource);

                Optional<ResourceResponse> resourceResponse = this.asmClient.resourcePost(resourceRequest);

                if (resourceResponse.isPresent()){

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(METHOD_NAME + "error code " + resourceResponse.get().getErrorCode());
                    }

                    if(resourceResponse.get().getErrorCode().equals("ASM_0008")){
                        // ASM validate and accept the request for async process
                        // change the customer record to the "WAITING_CREATE" state
                        resourceDao.updateResourceMigrationState(resource, MigrationStatus.WAITING_SUSPENDED.name());
                    }

                    AsmOrder asmOrder = new AsmOrder();
                    asmOrder.setOrderExternalId(resourceResponse.get().getOrderExternalId());
                    asmOrder.setOrderCorrelationId(resourceResponse.get().getOrderCorrelationId());
                    asmOrder.setEntityType(EntityType.CUSTOMER);
                    asmOrder.setOperation(OperationType.SUSPEND);
                    asmOrder.setOrderStatus(resourceResponse.get().getOrderStatus());

                    asmOrderDao.addAsmOrder(asmOrder);

                    didSomething = true;
                }
            }
        }
        return  didSomething;
    }

    private boolean closeResources(ResourceDao resourceDao, AsmOrderDao asmOrderDao){
        final String METHOD_NAME = LOG_PREFIX + " closeResources ";

        boolean didSomething = false;

        List<Resource> resources = resourceDao.getResourceToClose(1);

        if(LOGGER.isInfoEnabled()){
            LOGGER.info(METHOD_NAME + "customer list size: " + resources.size());
        }

        if(resources.size() > 0){
            for (Resource resource: resources){
                // create the request
                ResourceRequest resourceRequest = null;
                OperationType operationType = OperationType.UPDATE;

                if(resource.getMigStatus().equals(MigrationStatus.CREATED.name())){
                    resourceRequest = ResourceRequestFactory.getResourceUpdateRequest(resource);
                }else if(resource.getMigStatus().equals(MigrationStatus.SUSPENDED.name())){
                    // resource already in suspend state (AdministrativeStatus should be SUSPENDED)
                    resourceRequest = ResourceRequestFactory.getResourceUpdateSuspendRequest(resource);
                    operationType = OperationType.UPDATE_SUSPEND;
                }

                Optional<ResourceResponse> resourceResponse = this.asmClient.resourcePost(resourceRequest);

                if (resourceResponse.isPresent()){

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(METHOD_NAME + "error code " + resourceResponse.get().getErrorCode());
                    }

                    if(resourceResponse.get().getErrorCode().equals("ASM_0008")){
                        // ASM validate and accept the request for async process
                        // change the customer record to the "WAITING_CREATE" state
                        resourceDao.updateResourceMigrationState(resource, MigrationStatus.WAITING_CLOSE.name());
                    }

                    AsmOrder asmOrder = new AsmOrder();
                    asmOrder.setOrderExternalId(resourceResponse.get().getOrderExternalId());
                    asmOrder.setOrderCorrelationId(resourceResponse.get().getOrderCorrelationId());
                    asmOrder.setEntityType(EntityType.CUSTOMER);
                    asmOrder.setOperation(operationType);
                    asmOrder.setOrderStatus(resourceResponse.get().getOrderStatus());

                    asmOrderDao.addAsmOrder(asmOrder);

                    didSomething = true;
                }
            }
        }
        return  didSomething;
    }
}
