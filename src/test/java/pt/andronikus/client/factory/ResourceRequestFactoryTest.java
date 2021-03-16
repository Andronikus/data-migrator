package pt.andronikus.client.factory;

import org.joda.time.Interval;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.andronikus.client.request.ResourceRequest;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.configuration.CallbackServerConfiguration;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.configuration.MigrationProcessInfo;
import pt.andronikus.entities.Resource;
import pt.andronikus.singletons.AppConfiguration;


import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ResourceRequestFactoryTest {
    private static boolean alreadySetUp = false;

    @BeforeEach
    void setUp() {
        if(!alreadySetUp){

            CallbackServerConfiguration callbackCfg = new CallbackServerConfiguration();
            callbackCfg.setIpAddress("10.112.45.21");
            callbackCfg.setPort(7777);
            callbackCfg.setEndpoint("fulfillment");

            MigrationProcessInfo mig = new MigrationProcessInfo();
            mig.setOrderSource("MIG-R8-R9");
            mig.setChannel("M2M-MIG");

            InvokatorConfiguration cfg = new InvokatorConfiguration();
            cfg.setCallbackServerConfiguration(callbackCfg);
            cfg.setMigrationProcessInfo(mig);

            AppConfiguration.INSTANCE.setAppCfg(cfg);

            alreadySetUp = true;
        }
    }

    @Test
    void shouldReturnAValidResourceRequest(){
        ResourceRequest resourceRequest = ResourceRequestFactory.getResourceCreateRequest(createResource());

        System.out.println(JSONUtils.toJSON(resourceRequest));
    }


    private Resource createResource(){
        Resource resource = new Resource();

        resource.setOrderCorrelationId("MIG_" + UUID.randomUUID().toString());
        resource.setCorrelationId("MIG_RESOURCE_" + UUID.randomUUID().toString());

        resource.setOperatorId(1);
        resource.setMsisdn("345789446");
        resource.setAgreementId("agreement");
        resource.setServiceInstanceId("serviceInstance");
        resource.setResourceHomeNetwork("MEO");
        resource.setCatalogSpec("catalogSpec");
        resource.setOfferSpec("OfferSpec");
        resource.setSecondaryMsisdn(null);
        resource.setIccid("56743523523");
        resource.setServiceResourceStatus("ACTIVE");
        resource.setTestingLifeCycleEnabled(true);
        resource.setCommStatus("commStatus");
        resource.setReactivateCommStatus("reactive comm status");

        Resource.CommServices commServices = new Resource.CommServices();
        commServices.setDataPsService("45");
        commServices.setDataCsService("67");
        commServices.setSmsService("78");
        commServices.setVoiceService(null);
        resource.setCommServices(commServices);

        Resource.TariffPlans tariffPlans = new Resource.TariffPlans();
        tariffPlans.setDataPsTariffPlan("80002");
        tariffPlans.setDataCsTariffPlan("80001");
        tariffPlans.setSmsTariffPlan("80003");
        tariffPlans.setVoiceTariffPlan(null);
        resource.setTariffPlans(tariffPlans);

        resource.setAdminResourceStatus("ACTIVE");
        resource.setCreationDate("2020-05-26T14:14:06.019Z");
        resource.setFirstActivationDate("2020-05-26T14:14:06.019Z");
        resource.setDesignation("");
        resource.setRoamingStatus("");
        resource.setEndpoint("");
        resource.setEndpointGroups("");

        Resource.ApnInfo apnInfo = new Resource.ApnInfo();
        apnInfo.setApnQos("");

        return resource;
    }
}