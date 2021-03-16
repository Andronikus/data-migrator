package pt.andronikus.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.dao.ResourceDao;
import pt.andronikus.database.tables.ResourceTable;
import pt.andronikus.database.tables.ServiceInstanceTable;
import pt.andronikus.entities.Resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceDaoImpl implements ResourceDao {
    private final Logger LOGGER = LoggerFactory.getLogger(ResourceDaoImpl.class);
    private final String LOG_PREFIX = ResourceDaoImpl.class.getSimpleName() + " :: ";
    private final Connection connection;

    public ResourceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Resource getResource() {
        final String METHOD_NAME = LOG_PREFIX + " getResource ";

        try(PreparedStatement stm = connection.prepareStatement(ResourceTable.GET_RESOURCE)){
            try(ResultSet resultSet = stm.executeQuery()){
                if(resultSet.next()){
                    return createResource(resultSet);
                }
            }
        } catch (SQLException sqlException) {
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + "SQLException - " + sqlException.getMessage() + " " + sqlException.getSQLState());
            }
        } catch (Exception e){
            LOGGER.error(METHOD_NAME + e.getMessage());
        }
        return null;
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
        resource.setResourceHomeNetwork(resultSet.getString(ResourceTable.RESOURCE_HOME_NETWORK));
        resource.setCatalogSpec(resultSet.getString(ResourceTable.CATALOG_SPEC));
        resource.setOfferSpec(resultSet.getString(ResourceTable.OFFER_SPEC));
        resource.setSecondaryMsisdn(resultSet.getString(ResourceTable.SECONDARY_MSISDN));
        resource.setIccid(resultSet.getString(ResourceTable.ICCID));
        resource.setServiceResourceStatus(resultSet.getString(ResourceTable.SERVICE_RESOURCE_STATUS));
        resource.setTestingLifeCycleEnabled(Boolean.parseBoolean(resultSet.getString(ResourceTable.TESTING_LIFE_CYCLE_ENABLED)));
        resource.setCommStatus(resultSet.getString(ResourceTable.COMM_STATUS));
        resource.setReactivateCommStatus(resultSet.getString(ResourceTable.REACTIVATE_COMM_STATUS));

        Resource.CommServices commServices = new Resource.CommServices();
        commServices.setDataPsService(resultSet.getString(ResourceTable.DATA_PS_SERVICE));
        commServices.setDataCsService(resultSet.getString(ResourceTable.DATA_CS_SERVICE));
        commServices.setSmsService(resultSet.getString(ResourceTable.SMS_SERVICE));
        commServices.setVoiceService(resultSet.getString(ResourceTable.VOICE_SERVICE));
        resource.setCommServices(commServices);

        Resource.TariffPlans tariffPlans = new Resource.TariffPlans();
        tariffPlans.setDataPsTariffPlan(resultSet.getString(ResourceTable.DATA_PS_TARIFF_PLAN));
        tariffPlans.setDataCsTariffPlan(resultSet.getString(ResourceTable.DATA_CS_TARIFF_PLAN));
        tariffPlans.setSmsTariffPlan(resultSet.getString(ResourceTable.SMS_TARIFF_PLAN));
        tariffPlans.setVoiceTariffPlan(resultSet.getString(ResourceTable.VOICE_TARIFF_PLAN));
        resource.setTariffPlans(tariffPlans);

        resource.setAdminResourceStatus(resultSet.getString(ResourceTable.ADMIN_RESOURCE_STATUS));
        resource.setCreationDate(resultSet.getString(ResourceTable.CREATION_DATE));
        resource.setFirstActivationDate(resultSet.getString(ResourceTable.FIRST_ACTIVATION_DATE));
        resource.setDesignation(resultSet.getString(ResourceTable.DESIGNATION));
        resource.setRoamingStatus(resultSet.getString(ResourceTable.ROAMING_STATUS));
        resource.setEndpoint(resultSet.getString(ResourceTable.ENDPOINT));
        resource.setEndpointGroups(resultSet.getString(ResourceTable.ENDPOINT_GROUPS));

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
