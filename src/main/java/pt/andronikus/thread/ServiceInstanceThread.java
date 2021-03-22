package pt.andronikus.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.factory.ServiceInstanceRequestFactory;
import pt.andronikus.client.request.ServiceInstanceRequest;
import pt.andronikus.client.response.ServiceInstanceResponse;
import pt.andronikus.client.service.ASMClient;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.dao.AsmOrderDao;
import pt.andronikus.dao.DaoFactory;
import pt.andronikus.dao.ServiceInstanceDao;
import pt.andronikus.entities.AsmOrder;
import pt.andronikus.entities.ServiceInstance;
import pt.andronikus.enums.AdministrativeStatus;
import pt.andronikus.enums.EntityType;
import pt.andronikus.enums.MigrationStatus;
import pt.andronikus.singletons.Migration;
import pt.andronikus.singletons.ServiceInstanceMigration;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ServiceInstanceThread implements Runnable{
    private final Logger LOGGER = LoggerFactory.getLogger(ServiceInstanceThread.class);
    private final String LOG_PREFIX = ServiceInstanceThread.class.getSimpleName() + " :: ";

    private final ASMClient asmClient;

    public ServiceInstanceThread() {
        this.asmClient = new ASMClient();
    }

    @Override
    public void run() {
        boolean wasServiceInstanceCreated;
        boolean wasServiceInstanceSuspendedOrClosed;

        try {

            if(LOGGER.isInfoEnabled()){
                LOGGER.info(LOG_PREFIX + "Lets start migrating some Service Instances...");
            }

            ServiceInstanceDao serviceInstanceDao = DaoFactory.createServiceInstanceDao(true);
            AsmOrderDao asmOrderDao = DaoFactory.createAsmOrderDao(true);

            while(ServiceInstanceMigration.INSTANCE.getStatus().equals(ServiceInstanceMigration.Status.RUNNING)){
                wasServiceInstanceCreated = createServiceInstances(serviceInstanceDao, asmOrderDao);
                wasServiceInstanceSuspendedOrClosed = suspendOrCloseServiceInstances(serviceInstanceDao, asmOrderDao);

                if (!(wasServiceInstanceCreated || wasServiceInstanceSuspendedOrClosed)){
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
                LOGGER.info(LOG_PREFIX + " Leaving ServiceInstance Thread" );
            }
        }
    }

    private boolean createServiceInstances(ServiceInstanceDao serviceInstanceDao, AsmOrderDao asmOrderDao){
        final String METHOD_NAME = LOG_PREFIX + " createServiceInstance ";
        boolean didSomething = false;

        // get from DB the last customer in new state
        List<ServiceInstance> serviceInstances = serviceInstanceDao.getServiceInstanceToCreate(1);

        if(LOGGER.isInfoEnabled()){
            LOGGER.info(METHOD_NAME + "serviceInstances list size: " + serviceInstances.size());
        }

        if(serviceInstances.size() > 0){
            for (ServiceInstance serviceInstance: serviceInstances){
                // create the request
                ServiceInstanceRequest request = ServiceInstanceRequestFactory.getServiceInstanceCreateRequest(serviceInstance);

                // send request
                if(LOGGER.isInfoEnabled()){
                    LOGGER.info(METHOD_NAME + JSONUtils.toJSON(request));
                }

                Optional<ServiceInstanceResponse> response = this.asmClient.serviceInstancePost(request);

                if (response.isPresent()){

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(METHOD_NAME + "error code " + response.get().getErrorCode());
                    }

                    if(response.get().getErrorCode().equals("ASM_0008")){
                        serviceInstanceDao.updateServiceInstanceMigrationState(serviceInstance, MigrationStatus.WAITING_CREATE.name());
                    }

                    AsmOrder asmOrder = new AsmOrder();
                    asmOrder.setOrderExternalId(response.get().getOrderExternalId());
                    asmOrder.setOrderCorrelationId(response.get().getOrderCorrelationId());
                    asmOrder.setEntityType(EntityType.SERVICE_INSTANCE);
                    asmOrder.setOperation(OperationType.CREATE);
                    asmOrder.setOrderStatus(response.get().getOrderStatus());

                    asmOrderDao.addAsmOrder(asmOrder);

                    didSomething = true;
                }
            }
        }
        return  didSomething;
    }

    private boolean suspendOrCloseServiceInstances(ServiceInstanceDao serviceInstanceDao, AsmOrderDao asmOrderDao){
        final String METHOD_NAME = LOG_PREFIX + " suspendOrCloseServiceInstances ";

        boolean didSomething = false;

        List<ServiceInstance> serviceInstances = serviceInstanceDao.getServiceInstanceToClose(1);

        if(LOGGER.isInfoEnabled()){
            LOGGER.info(METHOD_NAME + "customer list size: " + serviceInstances.size());
        }

        if(serviceInstances.size() > 0){
            for (ServiceInstance serviceInstance: serviceInstances){
                // create the request
                ServiceInstanceRequest request = null;
                OperationType operationType = OperationType.UPDATE;
                MigrationStatus migrationStatus = MigrationStatus.WAITING_CLOSE;

                // All the resources are already closed...
                // Time to generate the suspend/ update (close) or update_suspend (close after suspend) request
                if(serviceInstance.getMigStatus().toUpperCase().equals(MigrationStatus.CREATED.name())){
                    // service instance  in created status
                    if(serviceInstance.getAdministrativeStatus().equals(AdministrativeStatus.SUSPENDED)){
                        request = ServiceInstanceRequestFactory.getServiceInstanceSuspendRequest(serviceInstance);
                        operationType = OperationType.SUSPEND;
                        migrationStatus = MigrationStatus.WAITING_SUSPENDED;
                    }else{
                        // Active state...
                        request = ServiceInstanceRequestFactory.getServiceInstanceUpdateRequest(serviceInstance);
                    }
                }else if (serviceInstance.getMigStatus().toUpperCase().equals(MigrationStatus.SUSPENDED.name())){
                    // service instance already in SUSPEND state... send update/suspend
                    request = ServiceInstanceRequestFactory.getServiceInstanceUpdateSuspendRequest(serviceInstance);
                }

                Optional<ServiceInstanceResponse> response = Optional.empty();

                if (request != null){
                    response = this.asmClient.serviceInstancePost(request);
                }

                if (response.isPresent()){

                    if(LOGGER.isInfoEnabled()){
                        LOGGER.info(METHOD_NAME + "error code " + response.get().getErrorCode());
                    }

                    if(response.get().getErrorCode().equals("ASM_0008")){
                        // ASM validate and accept the request for async process
                        // change the customer record to the "WAITING_CREATE" state
                        serviceInstanceDao.updateServiceInstanceMigrationState(serviceInstance, migrationStatus.name());
                    }

                    AsmOrder asmOrder = new AsmOrder();
                    asmOrder.setOrderExternalId(response.get().getOrderExternalId());
                    asmOrder.setOrderCorrelationId(response.get().getOrderCorrelationId());
                    asmOrder.setEntityType(EntityType.SERVICE_INSTANCE);
                    asmOrder.setOperation(operationType);
                    asmOrder.setOrderStatus(response.get().getOrderStatus());

                    asmOrderDao.addAsmOrder(asmOrder);

                    didSomething = true;
                }
            }
        }
        return  didSomething;
    }
}
