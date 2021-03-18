package pt.andronikus.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.constants.Global;
import pt.andronikus.dao.ResourceDao;
import pt.andronikus.database.tables.ResourceTable;
import pt.andronikus.entities.Resource;
import pt.andronikus.singletons.AppConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceDaoImpl implements ResourceDao {
    private final Logger LOGGER = LoggerFactory.getLogger(ResourceDaoImpl.class);
    private final String LOG_PREFIX = ResourceDaoImpl.class.getSimpleName() + " :: ";
    private final Connection connection;

    public final static String GET_RESOURCE_TO_CREATE = String.format("SELECT * FROM (SELECT * FROM %s WHERE %s = %s ORDER BY %s ASC) WHERE ROWNUM < ?",
            ResourceTable.VW_RESOURCE_TO_CREATE,
            ResourceTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION),
            ResourceTable.CREATED_AT);

    public final static String UPDATE_RESOURCE_STATE = String.format("UPDATE %s set %s = ?, UPDATED_AT = SYSDATE where %s = %s and %s = ? and %s = ?",
            ResourceTable.CDM_RESOURCE,
            ResourceTable.MIG_STATUS,
            ResourceTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION),
            ResourceTable.OPERATOR_ID,
            ResourceTable.ORDER_CORRELATION_ID);

    public ResourceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Resource> getResourceToCreate(int nbrRecordsToLoad) {
        final String METHOD_NAME = LOG_PREFIX + " getResourceToCreate ";

        List<Resource> resources = new ArrayList<>();

        if( nbrRecordsToLoad < 1){
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + " nbrRecordsToLoad must be >= 1");
            }
            return resources;
        }

        try(PreparedStatement stm = connection.prepareStatement(GET_RESOURCE_TO_CREATE)){

            stm.setInt(1, nbrRecordsToLoad + 1);

            try(ResultSet resultSet = stm.executeQuery()){
                while (resultSet.next()){
                    resources.add(createResource(resultSet));
                }
            }
        } catch (SQLException sqlException) {
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + "SQLException - " + sqlException.getMessage() + " " + sqlException.getSQLState());
            }
        } catch (Exception e){
            LOGGER.error(METHOD_NAME + e.getMessage());
            e.printStackTrace();
        }

        return resources;
    }

    @Override
    public boolean updateResourceMigrationState(Resource resource, String migrationStatus) {
        final String METHOD_NAME = LOG_PREFIX + " updateResourceMigrationState ";

        try (PreparedStatement stm = connection.prepareStatement(UPDATE_RESOURCE_STATE)) {

            stm.setString(1, migrationStatus);
            stm.setInt(2, resource.getOperatorId());
            stm.setString(3, resource.getOrderCorrelationId());

            if (stm.executeUpdate() > 0){
                return true;
            }
        }catch (SQLException e){
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn(METHOD_NAME + "SQLException - " + e.getMessage() + " " + e.getSQLState());
            }
        }catch (Exception e){
            LOGGER.error(METHOD_NAME + e.getMessage());
        }

        return false;
    }

    private Resource createResource(ResultSet resultSet) throws SQLException {
        Resource resource = new Resource();

        resource.setOperatorId(resultSet.getInt(ResourceTable.OPERATOR_ID));
        resource.setCorrelationId(resultSet.getString(ResourceTable.CORRELATION_ID));
        resource.setOrderCorrelationId(resultSet.getString(ResourceTable.ORDER_CORRELATION_ID));
        resource.setMigStatus(resultSet.getString(ResourceTable.MIG_STATUS));
        resource.setMsisdn(resultSet.getString(ResourceTable.MSISDN));
        resource.setAgreementId(resultSet.getString(ResourceTable.AGREEMENT_ID));
        resource.setServiceInstanceId(resultSet.getString(ResourceTable.SERVICE_INSTANCE_ID));
        resource.setCatalogSpec(resultSet.getString(ResourceTable.CATALOG_SPEC));
        resource.setOfferSpec(resultSet.getString(ResourceTable.OFFER_SPEC));
        resource.setSecondaryMsisdn(resultSet.getString(ResourceTable.SECONDARY_MSISDN));
        resource.setIccid(resultSet.getString(ResourceTable.ICCID));
        resource.setServiceResourceStatus(resultSet.getString(ResourceTable.SERVICE_RESOURCE_STATUS));
        resource.setTestingLifeCycleEnabled(Boolean.parseBoolean(resultSet.getString(ResourceTable.TESTING_LIFE_CYCLE_ENABLED)));
        resource.setCommStatus(resultSet.getString(ResourceTable.COMM_STATUS));
        resource.setReactivateCommStatus(resultSet.getString(ResourceTable.REACTIVATE_COMM_STATUS));

        Resource.CommServices commServices = new Resource.CommServices();
        commServices.setDataPsService(Boolean.parseBoolean(resultSet.getString(ResourceTable.DATA_PS_SERVICE)));
        commServices.setDataCsService(Boolean.parseBoolean(resultSet.getString(ResourceTable.DATA_CS_SERVICE)));
        commServices.setSmsService(Boolean.parseBoolean(resultSet.getString(ResourceTable.SMS_SERVICE)));
        commServices.setVoiceService(Boolean.parseBoolean(resultSet.getString(ResourceTable.VOICE_SERVICE)));
        resource.setCommServices(commServices);

        Resource.TariffPlans tariffPlans = new Resource.TariffPlans();
        tariffPlans.setDataPsTariffPlan(resultSet.getString(ResourceTable.DATA_PS_TARIFF_PLAN));
        tariffPlans.setDataCsTariffPlan(resultSet.getString(ResourceTable.DATA_CS_TARIFF_PLAN));
        tariffPlans.setSmsTariffPlan(resultSet.getString(ResourceTable.SMS_TARIFF_PLAN));
        tariffPlans.setVoiceTariffPlan(resultSet.getString(ResourceTable.VOICE_TARIFF_PLAN));
        resource.setTariffPlans(tariffPlans);

        resource.setAdminResourceStatus(resultSet.getString(ResourceTable.ADMIN_RESOURCE_STATUS));
        resource.setFirstActivationDate(resultSet.getString(ResourceTable.FIRST_ACTIVATION_DATE));
        resource.setRoamingStatus(resultSet.getString(ResourceTable.ROAMING_STATUS));

        Resource.ApnInfo apnInfo = new Resource.ApnInfo();
        apnInfo.setApnId(resultSet.getString(ResourceTable.APN_ID));
        apnInfo.setApnIndex(resultSet.getString(ResourceTable.APN_INDEX));
        apnInfo.setApnType(resultSet.getString(ResourceTable.APN_TYPE));
        apnInfo.setApnQos(resultSet.getString(ResourceTable.APN_QOS));
        resource.setApnInfo(apnInfo);

        resource.setLoyaltyPeriodRemaining(resultSet.getString(ResourceTable.LOYALTY_PERIOD_REMAINING));
        resource.setLoyaltyLastUpdate(resultSet.getString(ResourceTable.LOYALTY_LAST_UPDATE));

        return resource;
    }
}
