package pt.andronikus.dao;

import pt.andronikus.entities.BillingAccount;
import pt.andronikus.entities.ServiceInstance;

import java.util.List;

public interface ServiceInstanceDao {
    List<ServiceInstance> getServiceInstanceToCreate(int nbrRecordsToLoad);
    boolean updateServiceInstanceMigrationState(ServiceInstance serviceInstance, String migrationStatus);

    ServiceInstance getServiceInstance();

}
