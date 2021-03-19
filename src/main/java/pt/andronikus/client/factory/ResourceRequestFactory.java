package pt.andronikus.client.factory;

import pt.andronikus.client.constantes.FulfillmentParamsAtt;
import pt.andronikus.client.dto.*;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.ResourceRequest;
import pt.andronikus.constants.Global;
import pt.andronikus.constants.MigrationFlag;
import pt.andronikus.entities.Resource;
import pt.andronikus.singletons.AppConfiguration;

import java.util.Objects;

public class ResourceRequestFactory {
    public static ResourceRequest getResourceCreateRequest(Resource resource){
        ResourceRequest orderExecution = new ResourceRequest();

        // define execution mode
        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/resource/create");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(resource.getOrderCorrelationId());

        // order item definition
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.CREATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setReason(new Reason("OTHER", "Migration CREATE Resource"));
        orderItem.setCorrelationId(resource.getCorrelationId());

        orderItem.setAgreementId(resource.getAgreementId());
        orderItem.setParentAgreementId(resource.getParentAgreementId());
        orderItem.setCatalogSpec(resource.getCatalogSpec());
        orderItem.setOfferSpec(resource.getOfferSpec());

        //offerParams
        FulfillmentParams fulfillmentParams = new FulfillmentParams();
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ICCID, resource.getIccid());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_MSISDN, resource.getMsisdn());

        if(Objects.nonNull(resource.getSecondaryMsisdn())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_SECONDARY_MSISDN, resource.getSecondaryMsisdn());
        }

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SERVICE_RESOURCE_STATUS, resource.getServiceResourceStatus());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.TESTING_LIFECYCLE_ENABLED, resource.getTestingLifeCycleEnabled());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.COMM_STATUS, resource.getCommStatus());

        if(Objects.nonNull(resource.getReactivateCommStatus())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.REACTIVATE_COMM_STATUS, resource.getReactivateCommStatus());
        }

        if(Objects.nonNull(resource.getCommServices().getSmsService())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_SERVICE, resource.getCommServices().getSmsService());
        }

        if(Objects.nonNull(resource.getCommServices().getVoiceService())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_SERVICE, resource.getCommServices().getVoiceService());
        }

        if(Objects.nonNull(resource.getCommServices().getDataCsService())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_SERVICE, resource.getCommServices().getDataCsService());
        }

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_SERVICE, resource.getCommServices().getDataPsService());

        if(Objects.nonNull(resource.getTariffPlans().getSmsTariffPlan())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_TARIFF_PLAN, resource.getTariffPlans().getSmsTariffPlan());
        }

        if(Objects.nonNull(resource.getTariffPlans().getSmsTariffPlan())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_TARIFF_PLAN, resource.getTariffPlans().getSmsTariffPlan());
        }

        if(Objects.nonNull(resource.getTariffPlans().getDataCsTariffPlan())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_TARIFF_PLAN, resource.getTariffPlans().getDataCsTariffPlan());
        }

        if(Objects.nonNull(resource.getTariffPlans().getDataPsTariffPlan())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_TARIFF_PLAN, resource.getTariffPlans().getDataPsTariffPlan());
        }

        if(Objects.nonNull(resource.getFirstActivationDate())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FIRST_ACTIVATION_DATE, resource.getFirstActivationDate());
        }

        if(Objects.nonNull(resource.getRoamingStatus())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ROAMING_STATUS, resource.getRoamingStatus());
        }

        // TODO is a list? what is the format of string to parse?
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APN_LIST, resource.getApnInfo());

        // TODO Need to make the calculation of this value
        if(Objects.nonNull(resource.getLoyaltyPeriodRemaining())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FULFILLED_LOYALTY, resource.getLoyaltyPeriodRemaining());
        }

        if(Objects.nonNull(resource.getLoyaltyLastUpdate())){
            fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_LOY_LAST_UPDATE_DATE, resource.getLoyaltyLastUpdate());
        }

        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, MigrationFlag.IN_MIGRATION);
        orderItem.setOfferParams(fulfillmentParams);

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);
        return orderExecution;
    }

    public static ResourceRequest getResourceSuspendRequest(Resource resource){
        ResourceRequest orderExecution = new ResourceRequest();

        // define execution mode
        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/resource/suspend");
        orderExecution.setExecutionMode(executionMode);

        orderExecution.setOrderCorrelationId(resource.getOrderCorrelationId());

        // order item definition
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.SUSPEND);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setCorrelationId(resource.getCorrelationId());
        orderItem.setAgreementId(resource.getAgreementId());
        orderItem.setReason(new Reason("OTHER", "Migration SUSPEND Resource"));

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);
        return orderExecution;
    }

    public static ResourceRequest getResourceUpdateRequest(Resource resource){
        ResourceRequest orderExecution = new ResourceRequest();

        // define execution mode
        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/resource/update");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(resource.getOrderCorrelationId());

        // order item definition
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.UPDATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setReason(new Reason("OTHER", "Migration UPDATE Resource"));
        orderItem.setAgreementId(resource.getAgreementId());
        orderItem.setCorrelationId(resource.getCorrelationId());

        FulfillmentParams fulfillmentParams = new FulfillmentParams();
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, MigrationFlag.END_MIGRATION);
        orderItem.setOfferParams(fulfillmentParams);

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);
        return orderExecution;
    }

    public static ResourceRequest getResourceUpdateSuspendRequest(Resource resource){
        ResourceRequest orderExecution = new ResourceRequest();

        // define execution mode
        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/resource/updsuspend");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(resource.getOrderCorrelationId());

        // order item definition
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.UPDATE_SUSPEND);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setAgreementId(resource.getAgreementId());
        orderItem.setCorrelationId(resource.getCorrelationId());
        orderItem.setReason(new Reason("OTHER", "Migration UPDATE/SUSPEND Resource"));

        FulfillmentParams fulfillmentParams = new FulfillmentParams();
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, MigrationFlag.END_MIGRATION);
        orderItem.setOfferParams(fulfillmentParams);

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);
        return orderExecution;
    }
}
