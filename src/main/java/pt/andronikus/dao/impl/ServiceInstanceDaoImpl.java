package pt.andronikus.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.constants.Global;
import pt.andronikus.dao.ServiceInstanceDao;
import pt.andronikus.database.tables.CustomerTable;
import pt.andronikus.database.tables.ServiceInstanceTable;
import pt.andronikus.entities.ServiceInstance;
import pt.andronikus.entities.base.*;
import pt.andronikus.enums.AdministrativeStatus;
import pt.andronikus.singletons.AppConfiguration;
import pt.andronikus.utils.ParserUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceInstanceDaoImpl implements ServiceInstanceDao {
    private final Logger LOGGER = LoggerFactory.getLogger(ServiceInstanceDaoImpl.class);
    private final String LOG_PREFIX = ServiceInstanceDaoImpl.class.getSimpleName() + " :: ";
    private final Connection connection;

    public ServiceInstanceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public final static String GET_SERVICE_INSTANCE_TO_CREATE = String.format("SELECT * FROM (SELECT * FROM %s WHERE %s = %s ORDER BY %s ASC) WHERE ROWNUM < ?",
            ServiceInstanceTable.VW_SERVICE_INST_TO_CREATE,
            ServiceInstanceTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION),
            ServiceInstanceTable.CREATED_AT);

    public final static String UPDATE_SERVICE_INSTANCE_STATE = String.format("UPDATE %s set %s = ?, UPDATED_AT = SYSDATE where %s = %s and %s = ? and %s = ? and %s = ?",
            ServiceInstanceTable.CDM_SERVICE_INSTANCE,
            ServiceInstanceTable.MIG_STATUS,
            ServiceInstanceTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION),
            ServiceInstanceTable.OPERATOR_ID,
            ServiceInstanceTable.SERVICE_INSTANCE_ID,
            ServiceInstanceTable.ORDER_CORRELATION_ID);

    public final static String GET_SERVICE_INSTANCE_TO_CLOSE = String.format("SELECT * FROM %s WHERE %s = %s AND ROWNUM < ?",
            ServiceInstanceTable.VW_SERVICE_INST_TO_CLOSE,
            CustomerTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION));

    @Override
    public List<ServiceInstance> getServiceInstanceToCreate(int nbrRecordsToLoad) {
        final String METHOD_NAME = LOG_PREFIX + " getServiceInstanceToCreate ";

        List<ServiceInstance> serviceInstances = new ArrayList<>();

        if( nbrRecordsToLoad < 1){
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + " nbrRecordsToLoad must be >= 1");
            }
            return serviceInstances;
        }

        try(PreparedStatement stm = connection.prepareStatement(GET_SERVICE_INSTANCE_TO_CREATE)){

            stm.setInt(1, nbrRecordsToLoad + 1);

            try(ResultSet resultSet = stm.executeQuery()){
                while (resultSet.next()){
                    serviceInstances.add(createServiceInstance(resultSet));
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

        return serviceInstances;
    }

    @Override
    public List<ServiceInstance> getServiceInstanceToClose(int nbrRecordsToLoad) {
        final String METHOD_NAME = LOG_PREFIX + " getServiceInstanceToClose ";

        List<ServiceInstance> serviceInstances = new ArrayList<>();

        if( nbrRecordsToLoad < 1){
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + " nbrRecordsToLoad must be >= 1");
            }
            return serviceInstances;
        }

        try(PreparedStatement stm = connection.prepareStatement(GET_SERVICE_INSTANCE_TO_CLOSE)){

            stm.setInt(1, nbrRecordsToLoad + 1);

            try(ResultSet resultSet = stm.executeQuery()){
                while (resultSet.next()){
                    serviceInstances.add(createLiteServiceInstance(resultSet));
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

        return serviceInstances;
    }

    @Override
    public boolean updateServiceInstanceMigrationState(ServiceInstance serviceInstance, String migrationStatus) {
        final String METHOD_NAME = LOG_PREFIX + " updateServiceInstanceMigrationState ";

        try (PreparedStatement stm = connection.prepareStatement(UPDATE_SERVICE_INSTANCE_STATE)) {

            stm.setString(1, migrationStatus);
            stm.setInt(2, serviceInstance.getOperatorId());
            stm.setString(3, serviceInstance.getServiceInstanceId());
            stm.setString(4, serviceInstance.getOrderCorrelationId());

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

    @Override
    public ServiceInstance getServiceInstance() {
        final String METHOD_NAME = LOG_PREFIX + " getServiceInstance ";

        try(PreparedStatement stm = connection.prepareStatement(GET_SERVICE_INSTANCE_TO_CREATE)){
            try(ResultSet resultSet = stm.executeQuery()){
                if(resultSet.next()){
                    return createServiceInstance(resultSet);
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

    private ServiceInstance createLiteServiceInstance(ResultSet resultSet) throws SQLException {
        ServiceInstance service = new ServiceInstance();

        service.setOperatorId(resultSet.getInt(ServiceInstanceTable.OPERATOR_ID));
        service.setServiceInstanceId(resultSet.getString(ServiceInstanceTable.SERVICE_INSTANCE_ID));
        service.setAgreementId(resultSet.getString(ServiceInstanceTable.AGREEMENT_ID));
        service.setCorrelationId(resultSet.getString(ServiceInstanceTable.CORRELATION_ID));
        service.setOrderCorrelationId(resultSet.getString(ServiceInstanceTable.ORDER_CORRELATION_ID));
        service.setMigStatus(resultSet.getString(ServiceInstanceTable.MIG_STATUS));
        service.setAdministrativeStatus(AdministrativeStatus.valueOf(resultSet.getString(ServiceInstanceTable.ADMINISTRATIVE_STATUS)));

        return service;
    }


    private ServiceInstance createServiceInstance(ResultSet resultSet) throws SQLException {
        ServiceInstance service = new ServiceInstance();

        service.setOperatorId(resultSet.getInt(ServiceInstanceTable.OPERATOR_ID));
        service.setCorrelationId(resultSet.getString(ServiceInstanceTable.CORRELATION_ID));
        service.setOrderCorrelationId(resultSet.getString(ServiceInstanceTable.ORDER_CORRELATION_ID));
        service.setMigStatus(resultSet.getString(ServiceInstanceTable.MIG_STATUS));
        service.setMigFlag(resultSet.getInt(ServiceInstanceTable.MIG_FLAG));
        service.setPf(resultSet.getInt(ServiceInstanceTable.PF));

        service.setAccountId(resultSet.getString(ServiceInstanceTable.ACCOUNT_ID));
        service.setServiceInstanceId(resultSet.getString(ServiceInstanceTable.SERVICE_INSTANCE_ID));
        service.setAgreementId(resultSet.getString(ServiceInstanceTable.AGREEMENT_ID));
        service.setResourceHomeNetwork(resultSet.getString(ServiceInstanceTable.RESOURCE_HOME_NETWORK));
        service.setCatalogSpec(resultSet.getString(ServiceInstanceTable.CATALOG_SPEC));
        service.setOfferSpec(resultSet.getString(ServiceInstanceTable.OFFER_SPEC));


        // admin info
        service.setAdminInfo(getAdminInformationFromResultSet(resultSet));

        service.setSecondaryImsiEnabled(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.SECONDARY_IMSI_ENABLED)));
        service.setAuthorizationFlagSecImsi(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.AUTHORIZATION_FLAG_SEC_IMSI)));
        service.setTestingLifeCycleEnabled(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.TESTING_LIFE_CYCLE_ENABLED)));
        service.setAuthorizationFlagLifeCycle(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.AUTHORIZATION_FLAG_LIFE_CYCLE)));

        // Roaming info
        service.setRoamingInfo(getRoamingInfoFromResultSet(resultSet));

        service.setDiagnosticEnabled(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.DIAGNOSTIC_ENABLED)));
        service.setResourceOrderMinQty(resultSet.getInt(ServiceInstanceTable.RESOURCE_ORDER_MIN_QTY));
        service.setResourceOrderMinQty(resultSet.getInt(ServiceInstanceTable.RESOURCE_ORDER_MAX_QTY));
        service.setInitialResourceStatus(resultSet.getString(ServiceInstanceTable.INITIAL_RESOURCE_STATUS));
        service.setLoyaltyPeriod(String.valueOf(resultSet.getInt(ServiceInstanceTable.LOYALTY_PERIOD)));
        service.setCostByDay(String.valueOf(resultSet.getDouble(ServiceInstanceTable.COST_BY_DAY)));

        // Support Level
        service.setSupportLevel(getSupportLevelFromResultSet(resultSet));

        // Service Cost
        service.setServicesCost(getServicesCostFromResultSet(resultSet));

        // Comm Services
        service.setCommunicationServices(getCommServicesFromResultSet(resultSet));

        // Service Default Tariff
        service.setServiceDefaultTariff(getServiceDefaultTariffFromResultSet(resultSet));

        service.setDataPsTariffs(ParserUtils.parseTariffs(resultSet.getString(ServiceInstanceTable.DATA_PS_SERVICE_TARIFFS)));
        service.setDataCsTariffs(ParserUtils.parseTariffs(resultSet.getString(ServiceInstanceTable.DATA_CS_SERVICE_TARIFFS)));
        service.setVoiceTariffs(ParserUtils.parseTariffs(resultSet.getString(ServiceInstanceTable.VOICE_SERVICE_TARIFFS)));
        service.setSmsTariffs(ParserUtils.parseTariffs(resultSet.getString(ServiceInstanceTable.SMS_SERVICE_TARIFFS)));

        service.setDataPsCostPerState(ParserUtils.parseTariffCostsPerState(resultSet.getString(ServiceInstanceTable.DATA_PS_COST_PER_STATE)));
        service.setDataCsCostPerState(ParserUtils.parseTariffCostsPerState(resultSet.getString(ServiceInstanceTable.DATA_CS_COST_PER_STATE)));
        service.setVoiceCostPerState(ParserUtils.parseTariffCostsPerState(resultSet.getString(ServiceInstanceTable.VOICE_COST_PER_STATE)));
        service.setSmsCostPerState(ParserUtils.parseTariffCostsPerState(resultSet.getString(ServiceInstanceTable.SMS_COST_PER_STATE)));

        service.setLifeCycleTransitionsCost(getLifeCycleTransitionsCostFromResultSet(resultSet));

        service.setApnsDefault(ParserUtils.parseApns(resultSet.getString(ServiceInstanceTable.APNS_DEFAULT)));
        service.setApns1AuthorizedEligible(ParserUtils.parseApns(resultSet.getString(ServiceInstanceTable.APNS1_AUTHORIZED_ELIGIBLE)));
        service.setApns1DedicatedEligible(ParserUtils.parseApns(resultSet.getString(ServiceInstanceTable.APNS1_DEDICATED_ELIGIBLE)));
        service.setApns2AuthorizedEligible(ParserUtils.parseApns(resultSet.getString(ServiceInstanceTable.APNS2_AUTHORIZED_ELIGIBLE)));
        service.setApns2DedicatedEligible(ParserUtils.parseApns(resultSet.getString(ServiceInstanceTable.APNS2_DEDICATED_ELIGIBLE)));

        service.setQosInfo(getQosInfoFromResultSet(resultSet));
        service.setInitialBalanceInTests(getInitialBalanceInTestsFromResultSet(resultSet));
        service.setServiceInTests(getServiceInTestsFromResultSet(resultSet));

        service.setMaxDaysUntilActive(resultSet.getInt(ServiceInstanceTable.MAX_DAYS_UNTIL_ACTIVE));

        service.setAdministrativeStatus(AdministrativeStatus.valueOf(resultSet.getString(ServiceInstanceTable.ADMINISTRATIVE_STATUS)));
        service.setApnList(ParserUtils.parseApnList(resultSet.getString(ServiceInstanceTable.APN_LIST)));

        return service;
    }

    private SupportLevel getSupportLevelFromResultSet(ResultSet resultSet) throws SQLException {
        SupportLevel supportLevel = new SupportLevel();
        supportLevel.setSupportLevel(resultSet.getString(ServiceInstanceTable.SUPPORT_LEVEL));
        supportLevel.setSupportLevelCost(String.valueOf(resultSet.getDouble(ServiceInstanceTable.SUPPORT_LEVEL_COST)));

        return supportLevel;
    }

    private AdminInformation getAdminInformationFromResultSet(ResultSet resultSet) throws SQLException {
        AdminInformation adminInfo = new AdminInformation();
        adminInfo.setAdminName(resultSet.getString(ServiceInstanceTable.ADMIN_NAME));
        adminInfo.setAdminLogin(resultSet.getString(ServiceInstanceTable.ADMIN_LOGIN));
        adminInfo.setAdminMobile(resultSet.getString(ServiceInstanceTable.ADMIN_MOBILE));
        adminInfo.setAdminEmail(resultSet.getString(ServiceInstanceTable.ADMIN_EMAIL));

        return adminInfo;
    }

    private RoamingInfo getRoamingInfoFromResultSet(ResultSet resultSet) throws SQLException {
        RoamingInfo roamingInfo = new RoamingInfo();
        roamingInfo.setRoamingEnabled(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.ROAMING_ENABLED)));
        roamingInfo.setInitialRoamingStatus(resultSet.getString(ServiceInstanceTable.INITIAL_ROAMING_STATUS));

        return roamingInfo;
    }

    private ServicesCost getServicesCostFromResultSet(ResultSet resultSet) throws SQLException {
        ServicesCost servicesCost = new ServicesCost();
        servicesCost.setCustomerTrainingCost(String.valueOf(resultSet.getDouble(ServiceInstanceTable.CUSTOMER_TRAINING_COST)));
        servicesCost.setDedicatedApnCost(String.valueOf(resultSet.getDouble(ServiceInstanceTable.DEDICATED_APN_COST)));
        servicesCost.setApiAccessCost(String.valueOf(resultSet.getDouble(ServiceInstanceTable.API_ACCESS_COST)));
        servicesCost.setLocationServiceCost(String.valueOf(resultSet.getDouble(ServiceInstanceTable.LOCATION_SERVICE_COST)));
        servicesCost.setSmsConsoleCost(String.valueOf(resultSet.getDouble(ServiceInstanceTable.SMS_CONSOLE_COST)));

        return servicesCost;
    }

    private CommunicationServices getCommServicesFromResultSet(ResultSet resultSet) throws SQLException {
        CommunicationServices commServices = new CommunicationServices();
        commServices.setDataPsService(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.DATA_PS_SERVICE)));
        commServices.setDataCsService(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.DATA_CS_SERVICE)));
        commServices.setVoiceService(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.VOICE_SERVICE)));
        commServices.setSmsService(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.SMS_SERVICE)));
        commServices.setDataPsServiceDefault(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.DATA_PS_SERVICE_DEFAULT)));
        commServices.setDataCsServiceDefault(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.DATA_CS_SERVICE_DEFAULT)));
        commServices.setVoiceServiceDefault(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.VOICE_SERVICE_DEFAULT)));
        commServices.setSmsServiceDefault(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.SMS_SERVICE_DEFAULT)));

        return commServices;
    }

    private ServiceDefaultTariff getServiceDefaultTariffFromResultSet(ResultSet resultSet) throws SQLException {
        ServiceDefaultTariff defaultTariff = new ServiceDefaultTariff();
        defaultTariff.setDataPsDefaultTariff(resultSet.getString(ServiceInstanceTable.DATA_PS_SERVICE_DEFAULT_TARIFF));
        defaultTariff.setDataCsDefaultTariff(resultSet.getString(ServiceInstanceTable.DATA_CS_SERVICE_DEFAULT_TARIFF));
        defaultTariff.setVoiceDefaultTariff(resultSet.getString(ServiceInstanceTable.VOICE_SERVICE_DEFAULT_TARIFF));
        defaultTariff.setSmsDefaultTariff(resultSet.getString(ServiceInstanceTable.SMS_SERVICE_DEFAULT_TARIFF));

        return defaultTariff;
    }

    private LifeCycleTransitionsCost getLifeCycleTransitionsCostFromResultSet(ResultSet resultSet) throws SQLException {
        LifeCycleTransitionsCost lifeCycleTransitionsCost = new LifeCycleTransitionsCost();
        lifeCycleTransitionsCost.setLiveToSuspend(String.valueOf(resultSet.getDouble(ServiceInstanceTable.LIVE_TO_SUSPEND)));
        lifeCycleTransitionsCost.setLiveToStopped(String.valueOf(resultSet.getDouble(ServiceInstanceTable.LIVE_TO_STOPPED)));
        lifeCycleTransitionsCost.setStoppedToLive(String.valueOf(resultSet.getDouble(ServiceInstanceTable.STOPPED_TO_LIVE)));
        lifeCycleTransitionsCost.setStoppedToSuspend(String.valueOf(resultSet.getDouble(ServiceInstanceTable.STOPPED_TO_SUSPEND)));
        lifeCycleTransitionsCost.setStoppedToStandby(String.valueOf(resultSet.getDouble(ServiceInstanceTable.STOPPED_TO_STANDBY)));
        lifeCycleTransitionsCost.setPreActiveToStopped(String.valueOf(resultSet.getDouble(ServiceInstanceTable.PREACTIVE_TO_STOPPED)));
        lifeCycleTransitionsCost.setStandbyToSuspend(String.valueOf(resultSet.getDouble(ServiceInstanceTable.STANDBY_TO_SUSPEND)));
        lifeCycleTransitionsCost.setSuspendToStandby(String.valueOf(resultSet.getDouble(ServiceInstanceTable.SUSPEND_TO_STANDBY)));
        lifeCycleTransitionsCost.setStandbyToStopped(String.valueOf(resultSet.getDouble(ServiceInstanceTable.STANDBY_TO_STOPPED)));
        lifeCycleTransitionsCost.setSuspendToStopped(String.valueOf(resultSet.getDouble(ServiceInstanceTable.SUSPEND_TO_STOPPED)));
        lifeCycleTransitionsCost.setSuspendToLive(String.valueOf(resultSet.getDouble(ServiceInstanceTable.SUSPEND_TO_LIVE)));
        lifeCycleTransitionsCost.setStandbyToLive(String.valueOf(resultSet.getDouble(ServiceInstanceTable.STANDBY_TO_LIVE)));
        lifeCycleTransitionsCost.setLiveToStandby(String.valueOf(resultSet.getDouble(ServiceInstanceTable.LIVE_TO_STANDBY)));
        lifeCycleTransitionsCost.setTestToStopped(String.valueOf(resultSet.getDouble(ServiceInstanceTable.TEST_TO_STOPPED)));
        lifeCycleTransitionsCost.setPreActiveToTest(String.valueOf(resultSet.getDouble(ServiceInstanceTable.PREACTIVE_TO_TEST)));
        lifeCycleTransitionsCost.setTestToLive(String.valueOf(resultSet.getDouble(ServiceInstanceTable.TEST_TO_LIVE)));
        lifeCycleTransitionsCost.setPreActiveToLive(String.valueOf(resultSet.getDouble(ServiceInstanceTable.PREACTIVE_TO_LIVE)));
        lifeCycleTransitionsCost.setAdmActiveToCanceled(String.valueOf(resultSet.getDouble(ServiceInstanceTable.ADM_ACTIVE_TO_CANCELED)));
        lifeCycleTransitionsCost.setAdmActiveToSuspended(String.valueOf(resultSet.getDouble(ServiceInstanceTable.ADM_ACTIVE_TO_SUSPENDED)));
        lifeCycleTransitionsCost.setAdmSuspendedToActive(String.valueOf(resultSet.getDouble(ServiceInstanceTable.ADM_SUSPENDED_TO_ACTIVE)));
        lifeCycleTransitionsCost.setAdmSuspendedToCanceled(String.valueOf(resultSet.getDouble(ServiceInstanceTable.ADM_SUSPENDED_TO_CANCELED)));

        return lifeCycleTransitionsCost;
    }

    private QosInfo getQosInfoFromResultSet(ResultSet resultSet) throws SQLException {
        QosInfo qosInfo = new QosInfo(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.QOS_ENABLED)));
        qosInfo.setQosResourceDefault(resultSet.getString(ServiceInstanceTable.QOS_DEFAULT));
        qosInfo.setQosLevels(ParserUtils.parseQosLevels(resultSet.getString(ServiceInstanceTable.QOS_LEVELS)));
        return qosInfo;
    }

    private InitialBalanceInTests getInitialBalanceInTestsFromResultSet(ResultSet resultSet) throws SQLException {
        InitialBalanceInTests initialBalanceInTests = new InitialBalanceInTests();

        initialBalanceInTests.setDataPsInitialBalance(String.valueOf(resultSet.getDouble(ServiceInstanceTable.DATA_PS_INITIAL_BALANCE_IN_TESTS)));
        initialBalanceInTests.setDataCsInitialBalance(String.valueOf(resultSet.getDouble(ServiceInstanceTable.DATA_CS_INITIAL_BALANCE_IN_TESTS)));
        initialBalanceInTests.setVoiceInitialBalance(String.valueOf(resultSet.getDouble(ServiceInstanceTable.VOICE_INITIAL_BALANCE_IN_TESTS)));
        initialBalanceInTests.setSmsInitialBalance(String.valueOf(resultSet.getInt(ServiceInstanceTable.SMS_INITIAL_BALANCE_IN_TESTS)));

        return initialBalanceInTests;
    }

    private ServiceInTests getServiceInTestsFromResultSet(ResultSet resultSet) throws SQLException {
        ServiceInTests serviceInTests = new ServiceInTests();

        serviceInTests.setDataPsServiceInTests(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.DATA_PS_SERVICE_IN_TESTS)));
        serviceInTests.setDataCsServiceInTests(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.DATA_CS_SERVICE_IN_TESTS)));
        serviceInTests.setVoiceServiceInTests(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.VOICE_SERVICE_IN_TESTS)));
        serviceInTests.setSmsServiceInTests(Boolean.parseBoolean(resultSet.getString(ServiceInstanceTable.SMS_SERVICE_IN_TESTS)));

        return serviceInTests;
    }
}
