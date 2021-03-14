package pt.andronikus.client.factory;

import pt.andronikus.client.constantes.FulfillmentParamsAtt;
import pt.andronikus.client.dto.*;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.ResourceRequest;
import pt.andronikus.constants.MigrationFlag;
import pt.andronikus.entities.Resource;

public class ResourceRequestFactory {
    public static ResourceRequest getResourceCreateRequest(Resource resource){
        ResourceRequest orderExecution = new ResourceRequest();

        // define execution mode
        ExecutionMode executionMode = new AsyncExecutionMode("http://localhost:9090/migration/callback");
        orderExecution.setExecutionMode(executionMode);

        // order item definition
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.CREATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setReason(new Reason("OTHER", "Migration CREATE Resource"));

        orderItem.setAgreementId(resource.getAgreementId());
        // TODO how to construct this
        orderItem.setParentAgreementId("Parent agreement id");
        orderItem.setCatalogSpec(resource.getCatalogSpec());
        orderItem.setOfferSpec(resource.getOfferSpec());

        //offerParams
        FulfillmentParams fulfillmentParams = new FulfillmentParams();
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ICCID, resource.getIccid());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_MSISDN, resource.getMsisdn());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_SECONDARY_MSISDN, resource.getSecondaryMsisdn());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SERVICE_RESOURCE_STATUS, resource.getServiceResourceStatus());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.TESTING_LIFECYCLE_ENABLED, resource.getTestingLifeCycleEnabled());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.COMM_STATUS, resource.getCommStatus());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.REACTIVATE_COMM_STATUS, resource.getReactivateCommStatus());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_SERVICE, resource.getCommServices().isSmsService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_SERVICE, resource.getCommServices().isVoiceService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_SERVICE, resource.getCommServices().isDataCsService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_SERVICE, resource.getCommServices().isDataPsService());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.SMS_TARIFF_PLAN, resource.getTariffPlans().getSmsTariffPlan());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.VOICE_TARIFF_PLAN, resource.getTariffPlans().getVoiceTariffPlan());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_CS_TARIFF_PLAN, resource.getTariffPlans().getDataCsTariffPlan());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.DATA_PS_TARIFF_PLAN, resource.getTariffPlans().getDataPsTariffPlan());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FIRST_ACTIVATION_DATE, resource.getFirstActivationDate());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.ROAMING_STATUS, resource.getRoamingStatus());
        // TODO is a list? what is the format of string to parse?
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.APN_LIST, resource.getApnInfo());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FULFILLED_LOYALTY, resource.getLoyaltyPeriodRemaining());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_LOY_LAST_UPDATE_DATE, resource.getLoyaltyLastUpdate());
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, MigrationFlag.IN_MIGRATION);
        orderItem.setOfferParams(fulfillmentParams);

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);
        return orderExecution;
    }

    public static ResourceRequest getResourceSuspendRequest(Resource resource){
        ResourceRequest orderExecution = new ResourceRequest();

        // define execution mode
        ExecutionMode executionMode = new AsyncExecutionMode("http://localhost:9090/migration/callback");
        orderExecution.setExecutionMode(executionMode);

        // order item definition
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.SUSPEND);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setReason(new Reason("OTHER", "Migration SUSPEND Resource"));
        orderItem.setAgreementId(resource.getAgreementId());

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);
        return orderExecution;
    }

    public static ResourceRequest getResourceUpdateRequest(String agreementId, int migFlag){
        ResourceRequest orderExecution = new ResourceRequest();

        // define execution mode
        ExecutionMode executionMode = new AsyncExecutionMode("http://localhost:9090/migration/callback");
        orderExecution.setExecutionMode(executionMode);

        // order item definition
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.UPDATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setReason(new Reason("OTHER", "Migration UPDATE Resource"));
        orderItem.setAgreementId(agreementId);

        FulfillmentParams fulfillmentParams = new FulfillmentParams();
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, migFlag);
        orderItem.setOfferParams(fulfillmentParams);

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);
        return orderExecution;
    }

    public static ResourceRequest getResourceUpdateSuspendRequest(String agreementId, int migFlag){
        ResourceRequest orderExecution = new ResourceRequest();

        // define execution mode
        ExecutionMode executionMode = new AsyncExecutionMode("http://localhost:9090/migration/callback");
        orderExecution.setExecutionMode(executionMode);

        // order item definition
        AgreementOrderItem orderItem = new AgreementOrderItem(OperationType.UPDATE_SUSPEND);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setReason(new Reason("OTHER", "Migration UPDATE/SUSPEND Resource"));
        orderItem.setAgreementId(agreementId);

        FulfillmentParams fulfillmentParams = new FulfillmentParams();
        fulfillmentParams.addFulfillmentParam(FulfillmentParamsAtt.MIG_FLAG, migFlag);
        orderItem.setOfferParams(fulfillmentParams);

        orderExecution.setOrderItem(OrderItemType.AGREEMENT_ORDER_ITEM, orderItem);
        return orderExecution;
    }
}
