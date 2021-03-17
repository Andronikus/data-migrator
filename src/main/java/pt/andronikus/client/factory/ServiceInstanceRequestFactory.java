package pt.andronikus.client.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.client.constantes.FulfillmentParamsAtt;
import pt.andronikus.client.dto.*;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.ServiceInstanceRequest;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.constants.Global;
import pt.andronikus.constants.MigrationFlag;
import pt.andronikus.entities.ServiceInstance;
import pt.andronikus.singletons.AppConfiguration;

import java.util.Objects;

public class ServiceInstanceRequestFactory {
    final static Logger LOGGER = LoggerFactory.getLogger(ServiceInstanceRequestFactory.class);
    final static String LOG_PREFIX = ServiceInstanceRequestFactory.class.getSimpleName() + " :: ";

    public static ServiceInstanceRequest getServiceInstanceCreateRequest(ServiceInstance serviceInstance){
        final String METHOD_NAME = LOG_PREFIX + " getServiceInstanceCreateRequest - ";

        ServiceInstanceRequest orderExecution = new ServiceInstanceRequest();

        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/serviceInstance/create");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(serviceInstance.getOrderCorrelationId());

        // order item
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.CREATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setReason(new Reason("OTHER", "Migration CREATE Service Account"));
        orderItem.setCorrelationId(serviceInstance.getCorrelationId());

        orderItem.setAccountId(serviceInstance.getAccountId());
        orderItem.setAgreementId(serviceInstance.getAgreementId());
        orderItem.setCatalogSpec(serviceInstance.getCatalogSpec());
        orderItem.setOfferSpec(serviceInstance.getOfferSpec());

        //offerParams
        FulfillmentParams fulfillmentParams = new FulfillmentParams();

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SERVICE_INSTANCE_ID, serviceInstance.getServiceInstanceId());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.RESOURCE_HOME_NETWORK, serviceInstance.getResourceHomeNetwork());


        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADMIN_EMAIL, serviceInstance.getAdminInfo().getAdminEmail());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADMIN_LOGIN, serviceInstance.getAdminInfo().getAdminLogin());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADMIN_NAME, serviceInstance.getAdminInfo().getAdminName());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADMIN_MOBILE, serviceInstance.getAdminInfo().getAdminMobile());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SECONDARY_IMSI_ENABLED, serviceInstance.isSecondaryImsiEnabled());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.AUTHORIZATION_FLAG_SEC_IMSI, serviceInstance.isAuthorizationFlagSecImsi());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.TESTING_LIFECYCLE_ENABLED, serviceInstance.isTestingLifeCycleEnabled());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.AUTHORIZATION_FLAG_LIFECYCLE, serviceInstance.isAuthorizationFlagLifeCycle());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ROAMING_ENABLED, serviceInstance.getRoamingInfo().isRoamingEnabled());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.INITIAL_ROAMING_STATUS, serviceInstance.getRoamingInfo().getInitialRoamingStatus());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DIAGNOSTIC_ENABLED, serviceInstance.isDiagnosticEnabled());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.RESOURCE_ORDER_MIN_QTY, serviceInstance.getResourceOrderMinQty());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.RESOURCE_ORDER_MAX_QTY, serviceInstance.getResourceOrderMaxQty());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.INITIAL_RESOURCE_STATUS, serviceInstance.getInitialResourceStatus());

        if(Objects.nonNull(serviceInstance.getLoyaltyPeriod())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.LOYALTY_PERIOD, serviceInstance.getLoyaltyPeriod());
        }

        if(Objects.nonNull(serviceInstance.getCostByDay())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.COST_BY_DAY, serviceInstance.getCostByDay());
        }

        if(Objects.nonNull(serviceInstance.getSupportLevel().getSupportLevel())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SUPPORT_LEVEL, serviceInstance.getSupportLevel().getSupportLevel());
        }
        if(Objects.nonNull(serviceInstance.getSupportLevel().getSupportLevelCost())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SUPPORT_LEVEL_COST, serviceInstance.getSupportLevel().getSupportLevelCost());
        }

        if(Objects.nonNull(serviceInstance.getServicesCost().getSmsConsoleCost())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_CONSOLE_COST, serviceInstance.getServicesCost().getSmsConsoleCost());
        }
        if(Objects.nonNull(serviceInstance.getServicesCost().getLocationServiceCost())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.LOCATION_SERVICE_COST, serviceInstance.getServicesCost().getLocationServiceCost());
        }
        if(Objects.nonNull(serviceInstance.getServicesCost().getApiAccessCost())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.API_ACCESS_COST, serviceInstance.getServicesCost().getApiAccessCost());
        }
        if(Objects.nonNull(serviceInstance.getServicesCost().getDedicatedApnCost())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DEDICATED_APN_COST, serviceInstance.getServicesCost().getDedicatedApnCost());
        }
        if(Objects.nonNull(serviceInstance.getServicesCost().getCustomerTrainingCost())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.CUSTOMER_TRAINING_COST, serviceInstance.getServicesCost().getCustomerTrainingCost());
        }

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_SERVICE, serviceInstance.getCommunicationServices().isDataPsService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_SERVICE, serviceInstance.getCommunicationServices().isDataCsService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_SERVICE, serviceInstance.getCommunicationServices().isVoiceService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_SERVICE, serviceInstance.getCommunicationServices().isSmsService());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_SERVICE_DEFAULT, serviceInstance.getCommunicationServices().isDataPsServiceDefault());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_SERVICE_DEFAULT, serviceInstance.getCommunicationServices().isDataCsServiceDefault());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_SERVICE_DEFAULT, serviceInstance.getCommunicationServices().isVoiceServiceDefault());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_SERVICE_DEFAULT, serviceInstance.getCommunicationServices().isSmsServiceDefault());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_DEFAULT_TARIFF, serviceInstance.getServiceDefaultTariff().getDataPsDefaultTariff());

        if(Objects.nonNull(serviceInstance.getServiceDefaultTariff().getDataCsDefaultTariff())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_DEFAULT_TARIFF, serviceInstance.getServiceDefaultTariff().getDataCsDefaultTariff());
        }
        if(Objects.nonNull(serviceInstance.getServiceDefaultTariff().getVoiceDefaultTariff())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_DEFAULT_TARIFF, serviceInstance.getServiceDefaultTariff().getVoiceDefaultTariff());
        }
        if(Objects.nonNull(serviceInstance.getServiceDefaultTariff().getSmsDefaultTariff())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_DEFAULT_TARIFF, serviceInstance.getServiceDefaultTariff().getSmsDefaultTariff());
        }

        // tariffs
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_TARIFFS, serviceInstance.getDataPsTariffs());
        if(Objects.nonNull(serviceInstance.getDataCsTariffs()) && serviceInstance.getDataCsTariffs().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_TARIFFS, serviceInstance.getDataCsTariffs());
        }
        if(Objects.nonNull(serviceInstance.getVoiceTariffs()) && serviceInstance.getVoiceTariffs().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_TARIFFS, serviceInstance.getVoiceTariffs());
        }
        if(Objects.nonNull(serviceInstance.getSmsTariffs()) && serviceInstance.getSmsTariffs().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_TARIFFS, serviceInstance.getSmsTariffs());
        }

        // cost per state
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_COST_PER_STATE,serviceInstance.getDataPsCostPerState());
        if(Objects.nonNull(serviceInstance.getDataCsCostPerState()) && serviceInstance.getDataCsCostPerState().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_COST_PER_STATE,serviceInstance.getDataCsCostPerState());
        }
        if(Objects.nonNull(serviceInstance.getVoiceCostPerState()) && serviceInstance.getVoiceCostPerState().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_COST_PER_STATE,serviceInstance.getVoiceCostPerState());
        }
        if(Objects.nonNull(serviceInstance.getSmsCostPerState()) && serviceInstance.getSmsCostPerState().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_COST_PER_STATE,serviceInstance.getSmsCostPerState());
        }

        // LFC transition costs
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getLiveToSuspend())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.LIVE_TO_SUSPEND,serviceInstance.getLifeCycleTransitionsCost().getLiveToSuspend());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getLiveToStopped())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.LIVE_TO_STOPPED,serviceInstance.getLifeCycleTransitionsCost().getLiveToStopped());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getStoppedToLive())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STOPPED_TO_LIVE,serviceInstance.getLifeCycleTransitionsCost().getStoppedToLive());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getStoppedToSuspend())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STOPPED_TO_SUSPEND,serviceInstance.getLifeCycleTransitionsCost().getStoppedToSuspend());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getStoppedToStandby())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STOPPED_TO_STANDBY,serviceInstance.getLifeCycleTransitionsCost().getStoppedToStandby());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getPreActiveToStopped())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.PREACTIVE_TO_STOPPED,serviceInstance.getLifeCycleTransitionsCost().getPreActiveToStopped());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getStandbyToSuspend())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STANDBY_TO_SUSPEND,serviceInstance.getLifeCycleTransitionsCost().getStandbyToSuspend());
        }

        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getSuspendToStandby())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SUSPEND_TO_STANDBY,serviceInstance.getLifeCycleTransitionsCost().getSuspendToStandby());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getStandbyToStopped())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STANDBY_TO_STOPPED,serviceInstance.getLifeCycleTransitionsCost().getStandbyToStopped());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getSuspendToStopped())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SUSPEND_TO_STOPPED,serviceInstance.getLifeCycleTransitionsCost().getSuspendToStopped());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getSuspendToLive())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SUSPEND_TO_LIVE,serviceInstance.getLifeCycleTransitionsCost().getSuspendToLive());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getStandbyToLive())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STANDBY_TO_LIVE,serviceInstance.getLifeCycleTransitionsCost().getStandbyToLive());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getLiveToStandby())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.LIVE_TO_STANDBY,serviceInstance.getLifeCycleTransitionsCost().getLiveToStandby());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getTestToStopped())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.TEST_TO_STOPPED,serviceInstance.getLifeCycleTransitionsCost().getTestToStopped());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getPreActiveToTest())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.PREACTIVE_TO_TEST,serviceInstance.getLifeCycleTransitionsCost().getPreActiveToTest());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getTestToLive())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.TEST_TO_LIVE,serviceInstance.getLifeCycleTransitionsCost().getTestToLive());
        }

        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getPreActiveToLive())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.PREACTIVE_TO_LIVE,serviceInstance.getLifeCycleTransitionsCost().getPreActiveToLive());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getAdmActiveToCanceled())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADM_ACTIVE_TO_CANCELED,serviceInstance.getLifeCycleTransitionsCost().getAdmActiveToCanceled());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getAdmActiveToSuspended())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADM_ACTIVE_TO_SUSPEND,serviceInstance.getLifeCycleTransitionsCost().getAdmActiveToSuspended());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getAdmSuspendedToActive())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADM_SUSPEND_TO_ACTIVE,serviceInstance.getLifeCycleTransitionsCost().getAdmSuspendedToActive());
        }
        if(Objects.nonNull(serviceInstance.getLifeCycleTransitionsCost().getAdmSuspendedToCanceled())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADM_SUSPEND_TO_CANCELED,serviceInstance.getLifeCycleTransitionsCost().getAdmSuspendedToCanceled());
        }

        // apns
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APNS_DEFAULT,serviceInstance.getApnsDefault());
        if(Objects.nonNull(serviceInstance.getApns1AuthorizedEligible()) && serviceInstance.getApns1AuthorizedEligible().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APNS1_AUTHORIZED_ELIGIBLE,serviceInstance.getApns1AuthorizedEligible());
        }
        if(Objects.nonNull(serviceInstance.getApns1DedicatedEligible()) && serviceInstance.getApns1DedicatedEligible().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APNS1_DEDICATED_ELIGIBLE,serviceInstance.getApns1DedicatedEligible());
        }
        if(Objects.nonNull(serviceInstance.getApns2AuthorizedEligible()) && serviceInstance.getApns2AuthorizedEligible().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APNS2_AUTHORIZED_ELIGIBLE,serviceInstance.getApns2AuthorizedEligible());
        }
        if(Objects.nonNull(serviceInstance.getApns2DedicatedEligible()) && serviceInstance.getApns2DedicatedEligible().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APNS2_DEDICATED_ELIGIBLE,serviceInstance.getApns2DedicatedEligible());
        }

        // qos
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.QOS_ENABLED,serviceInstance.getQosInfo().isQosEnabled());
        if(Objects.nonNull(serviceInstance.getQosInfo().getQosResourceDefault()) && serviceInstance.getQosInfo().getQosResourceDefault().length() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.QOS_RESOURCE_DEFAULT,serviceInstance.getQosInfo().getQosResourceDefault());
        }
        if(Objects.nonNull(serviceInstance.getQosInfo().getQosLevels()) && serviceInstance.getQosInfo().getQosLevels().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.QOS_LEVELS,serviceInstance.getQosInfo().getQosLevels());
        }


        // initial balance in test
        if(Objects.nonNull(serviceInstance.getInitialBalanceInTests().getDataPsInitialBalance()) && serviceInstance.getInitialBalanceInTests().getDataPsInitialBalance().length() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_INITIAL_BALANCE_IN_TESTS,serviceInstance.getInitialBalanceInTests().getDataPsInitialBalance());
        }
        if(Objects.nonNull(serviceInstance.getInitialBalanceInTests().getDataCsInitialBalance()) && serviceInstance.getInitialBalanceInTests().getDataCsInitialBalance().length() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_INITIAL_BALANCE_IN_TESTS,serviceInstance.getInitialBalanceInTests().getDataCsInitialBalance());
        }
        if(Objects.nonNull(serviceInstance.getInitialBalanceInTests().getVoiceInitialBalance()) && serviceInstance.getInitialBalanceInTests().getVoiceInitialBalance().length() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_INITIAL_BALANCE_IN_TESTS,serviceInstance.getInitialBalanceInTests().getVoiceInitialBalance());
        }
        if(Objects.nonNull(serviceInstance.getInitialBalanceInTests().getSmsInitialBalance()) && serviceInstance.getInitialBalanceInTests().getSmsInitialBalance().length() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_INITIAL_BALANCE_IN_TESTS,serviceInstance.getInitialBalanceInTests().getSmsInitialBalance());
        }

        // service in test
        if(Objects.nonNull(serviceInstance.getServiceInTests().getDataPsServiceInTests())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_SERVICE_IN_TESTS,serviceInstance.getServiceInTests().getDataPsServiceInTests());
        }
        if(Objects.nonNull(serviceInstance.getServiceInTests().getDataCsServiceInTests())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_SERVICE_IN_TESTS,serviceInstance.getServiceInTests().getDataCsServiceInTests());
        }
        if(Objects.nonNull(serviceInstance.getServiceInTests().getVoiceServiceInTests())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_SERVICE_IN_TESTS,serviceInstance.getServiceInTests().getVoiceServiceInTests());
        }
        if(Objects.nonNull(serviceInstance.getServiceInTests().getSmsServiceInTests())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_SERVICE_IN_TESTS,serviceInstance.getServiceInTests().getSmsServiceInTests());
        }

        // max days until active
        if(Objects.nonNull(serviceInstance.getMaxDaysUntilActive())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MAX_DAYS_UNTIL_ACTIVE, serviceInstance.getMaxDaysUntilActive());
        }

        // APNs List
        if (Objects.nonNull(serviceInstance.getApnList()) && serviceInstance.getApnList().size() > 0){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APN_LIST, serviceInstance.getApnList());
        }

        // mig flag
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, MigrationFlag.IN_MIGRATION);

        orderItem.setOfferParams(fulfillmentParams);

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);

        return orderExecution;
    }

    public static ServiceInstanceRequest getServiceInstanceSuspendRequest(ServiceInstance serviceInstance){
        final String METHOD_NAME = LOG_PREFIX + " getServiceInstanceSuspendRequest - ";

        ServiceInstanceRequest orderExecution = new ServiceInstanceRequest();

        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/serviceInstance/suspend");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(serviceInstance.getOrderCorrelationId());

        // order item
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.SUSPEND);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setReason(new Reason("OTHER", "Migration SUSPEND Service Instance"));
        orderItem.setCorrelationId(serviceInstance.getCorrelationId());

        orderItem.setAgreementId(serviceInstance.getAgreementId());

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(METHOD_NAME);
            LOGGER.debug(JSONUtils.toJSON(orderExecution));
        }

        return orderExecution;
    }

    public static ServiceInstanceRequest getServiceInstanceUpdateRequest(ServiceInstance serviceInstance){
        final String METHOD_NAME = LOG_PREFIX + " getServiceInstanceUpdateRequest - ";

        ServiceInstanceRequest orderExecution = new ServiceInstanceRequest();

        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/serviceInstance/update");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(serviceInstance.getOrderCorrelationId());

        // order item
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.UPDATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setReason(new Reason("OTHER", "Migration UPDATE Service Instance"));
        orderItem.setCorrelationId(serviceInstance.getCorrelationId());

        orderItem.setAgreementId(serviceInstance.getAgreementId());

        // mig flag
        FulfillmentParams fulfillmentParams = new FulfillmentParams();
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, MigrationFlag.END_MIGRATION);
        orderItem.setOfferParams(fulfillmentParams);

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(METHOD_NAME);
            LOGGER.debug(JSONUtils.toJSON(orderExecution));
        }

        return orderExecution;
    }

    public static ServiceInstanceRequest getServiceInstanceUpdateSuspendRequest(ServiceInstance serviceInstance){
        final String METHOD_NAME = LOG_PREFIX + " getServiceInstanceUpdateSuspendRequest - ";

        ServiceInstanceRequest orderExecution = new ServiceInstanceRequest();

        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/serviceInstance/updSuspend");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(serviceInstance.getOrderCorrelationId());

        // order item
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.UPDATE_SUSPEND);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setReason(new Reason("OTHER", "Migration UPDATE/SUSPEND Service Instance"));
        orderItem.setCorrelationId(serviceInstance.getCorrelationId());

        orderItem.setAgreementId(serviceInstance.getAgreementId());

        // mig flag
        FulfillmentParams fulfillmentParams = new FulfillmentParams();
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, MigrationFlag.END_MIGRATION);
        orderItem.setOfferParams(fulfillmentParams);

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(METHOD_NAME);
            LOGGER.debug(JSONUtils.toJSON(orderExecution));
        }

        return orderExecution;
    }
}
