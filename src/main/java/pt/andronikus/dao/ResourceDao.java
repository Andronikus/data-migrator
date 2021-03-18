package pt.andronikus.dao;

import pt.andronikus.entities.Resource;
import pt.andronikus.entities.ServiceInstance;

import java.util.List;

public interface ResourceDao {
    List<Resource> getResourceToCreate(int nbrRecordsToLoad);
    boolean updateResourceMigrationState(Resource resource, String migrationStatus);
}
