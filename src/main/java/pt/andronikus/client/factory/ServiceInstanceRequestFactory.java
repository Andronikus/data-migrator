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

        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/serviceInstance");
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
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.LOYALTY_PERIOD, serviceInstance.getLoyaltyPeriod());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.COST_BY_DAY, serviceInstance.getCostByDay());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SUPPORT_LEVEL, serviceInstance.getSupportLevel().getSupportLevel());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SUPPORT_LEVEL_COST, serviceInstance.getSupportLevel().getSupportLevelCost());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_CONSOLE_COST, serviceInstance.getServicesCost().getSmsConsoleCost());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.LOCATION_SERVICE_COST, serviceInstance.getServicesCost().getLocationServiceCost());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.API_ACCESS_COST, serviceInstance.getServicesCost().getApiAccessCost());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DEDICATED_APN_COST, serviceInstance.getServicesCost().getDedicatedApnCost());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.CUSTOMER_TRAINING_COST, serviceInstance.getServicesCost().getCustomerTrainingCost());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_SERVICE, serviceInstance.getCommunicationServices().isDataPsService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_SERVICE, serviceInstance.getCommunicationServices().isDataCsService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_SERVICE, serviceInstance.getCommunicationServices().isVoiceService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_SERVICE, serviceInstance.getCommunicationServices().isSmsService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_SERVICE_DEFAULT, serviceInstance.getCommunicationServices().isDataPsServiceDefault());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_SERVICE_DEFAULT, serviceInstance.getCommunicationServices().isDataCsServiceDefault());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_SERVICE_DEFAULT, serviceInstance.getCommunicationServices().isVoiceServiceDefault());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_SERVICE_DEFAULT, serviceInstance.getCommunicationServices().isSmsServiceDefault());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_DEFAULT_TARIFF, serviceInstance.getServiceDefaultTariff().getDataPsDefaultTariff());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_DEFAULT_TARIFF, serviceInstance.getServiceDefaultTariff().getDataCsDefaultTariff());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_DEFAULT_TARIFF, serviceInstance.getServiceDefaultTariff().getVoiceDefaultTariff());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_DEFAULT_TARIFF, serviceInstance.getServiceDefaultTariff().getSmsDefaultTariff());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_TARIFFS, serviceInstance.getDataPsTariffs());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_TARIFFS, serviceInstance.getDataCsTariffs());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_TARIFFS, serviceInstance.getVoiceTariffs());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_TARIFFS, serviceInstance.getSmsTariffs());

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_COST_PER_STATE,serviceInstance.getDataPsCostPerState());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_COST_PER_STATE,serviceInstance.getDataCsCostPerState());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_COST_PER_STATE,serviceInstance.getVoiceCostPerState());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_COST_PER_STATE,serviceInstance.getSmsCostPerState());

        // LFC transition costs
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.LIVE_TO_SUSPEND,serviceInstance.getLifeCycleTransitionsCost().getLiveToSuspend());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.LIVE_TO_STOPPED,serviceInstance.getLifeCycleTransitionsCost().getLiveToStopped());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STOPPED_TO_LIVE,serviceInstance.getLifeCycleTransitionsCost().getStoppedToLive());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STOPPED_TO_SUSPEND,serviceInstance.getLifeCycleTransitionsCost().getStoppedToSuspend());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STOPPED_TO_STANDBY,serviceInstance.getLifeCycleTransitionsCost().getStoppedToStandby());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.PREACTIVE_TO_STOPPED,serviceInstance.getLifeCycleTransitionsCost().getPreActiveToStopped());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STANDBY_TO_SUSPEND,serviceInstance.getLifeCycleTransitionsCost().getStandbyToSuspend());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SUSPEND_TO_STANDBY,serviceInstance.getLifeCycleTransitionsCost().getSuspendToStandby());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STANDBY_TO_STOPPED,serviceInstance.getLifeCycleTransitionsCost().getStandbyToStopped());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SUSPEND_TO_STOPPED,serviceInstance.getLifeCycleTransitionsCost().getSuspendToStopped());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SUSPEND_TO_LIVE,serviceInstance.getLifeCycleTransitionsCost().getSuspendToLive());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.STANDBY_TO_LIVE,serviceInstance.getLifeCycleTransitionsCost().getStandbyToLive());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.LIVE_TO_STANDBY,serviceInstance.getLifeCycleTransitionsCost().getLiveToStandby());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.TEST_TO_STOPPED,serviceInstance.getLifeCycleTransitionsCost().getTestToStopped());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.PREACTIVE_TO_TEST,serviceInstance.getLifeCycleTransitionsCost().getPreActiveToTest());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.TEST_TO_LIVE,serviceInstance.getLifeCycleTransitionsCost().getTestToLive());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.PREACTIVE_TO_LIVE,serviceInstance.getLifeCycleTransitionsCost().getPreActiveToLive());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADM_ACTIVE_TO_CANCELED,serviceInstance.getLifeCycleTransitionsCost().getAdmActiveToCanceled());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADM_ACTIVE_TO_SUSPEND,serviceInstance.getLifeCycleTransitionsCost().getAdmActiveToSuspended());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADM_SUSPEND_TO_ACTIVE,serviceInstance.getLifeCycleTransitionsCost().getAdmSuspendedToActive());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ADM_SUSPEND_TO_CANCELED,serviceInstance.getLifeCycleTransitionsCost().getAdmSuspendedToCanceled());

        // apns
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APNS_DEFAULT,serviceInstance.getApnsDefault());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APNS1_AUTHORIZED_ELIGIBLE,serviceInstance.getApns1AuthorizedEligible());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APNS1_DEDICATED_ELIGIBLE,serviceInstance.getApns1DedicatedEligible());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APNS2_AUTHORIZED_ELIGIBLE,serviceInstance.getApns2AuthorizedEligible());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APNS2_DEDICATED_ELIGIBLE,serviceInstance.getApns2DedicatedEligible());

        // qos
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.QOS_ENABLED,serviceInstance.getQosInfo().isQosEnabled());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.QOS_RESOURCE_DEFAULT,serviceInstance.getQosInfo().getQosResourceDefault());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.QOS_LEVELS,serviceInstance.getQosInfo().getQosLevels());

        // initial balance in test
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_INITIAL_BALANCE_IN_TESTS,serviceInstance.getInitialBalanceInTests().getDataPsInitialBalance());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_INITIAL_BALANCE_IN_TESTS,serviceInstance.getInitialBalanceInTests().getDataCsInitialBalance());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_INITIAL_BALANCE_IN_TESTS,serviceInstance.getInitialBalanceInTests().getVoiceInitialBalance());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_INITIAL_BALANCE_IN_TESTS,serviceInstance.getInitialBalanceInTests().getSmsInitialBalance());

        // service in test
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_SERVICE_IN_TESTS,serviceInstance.getServiceInTests().isDataPsServiceInTests());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_SERVICE_IN_TESTS,serviceInstance.getServiceInTests().isDataCsServiceInTests());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_SERVICE_IN_TESTS,serviceInstance.getServiceInTests().isVoiceServiceInTests());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_SERVICE_IN_TESTS,serviceInstance.getServiceInTests().isSmsServiceInTests());

        // max days until active
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MAX_DAYS_UNTIL_ACTIVE, serviceInstance.getMaxDaysUntilActive());

        // APNs List
        if (Objects.nonNull(serviceInstance.getApnList())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APN_LIST, serviceInstance.getApnList());
        }

        // mig flag
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, MigrationFlag.IN_MIGRATION);

        orderItem.setOfferParams(fulfillmentParams);

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(METHOD_NAME);
            LOGGER.debug(JSONUtils.toJSON(orderExecution));
        }

        return orderExecution;
    }

    public static ServiceInstanceRequest getServiceInstanceSuspendRequest(ServiceInstance serviceInstance){
        final String METHOD_NAME = LOG_PREFIX + " getServiceInstanceSuspendRequest - ";

        ServiceInstanceRequest orderExecution = new ServiceInstanceRequest();

        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/serviceInstance");
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

        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/serviceInstance");
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

        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/serviceInstance");
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
