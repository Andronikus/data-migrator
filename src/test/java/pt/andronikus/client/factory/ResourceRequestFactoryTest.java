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

        resource.setOrderCorrelationId("MIG_RSC_" + UUID.randomUUID().toString());
        resource.setCorrelationId("MIG_" + UUID.randomUUID().toString());

        resource.setAgreementId("MIG9_" + UUID.randomUUID().toString());
        // comes from service instance (agreement)
        resource.setParentAgreementId("MIG_e77feb26-1909-4cd5-a071-f5c1a4d22f44");
        resource.setCatalogSpec("5dc1c6bd705297252bfd1562");
        resource.setOfferSpec("5e2f27d2705297c8ecf78d3a");


        resource.setIccid("89331042180222471503");
        resource.setMsisdn("07000222471503");
        resource.setSecondaryMsisdn("07000222471503");
        resource.setServiceResourceStatus("active");
        resource.setTestingLifeCycleEnabled(true);
        resource.setCommStatus("active");
        resource.setReactivateCommStatus("manual");
        resource.setRoamingStatus("ALLOW");

        Resource.CommServices commServices = new Resource.CommServices();
        commServices.setDataPsService(true);
        commServices.setDataCsService(true);
        commServices.setSmsService(true);
        commServices.setVoiceService(null);
        resource.setCommServices(commServices);

        Resource.TariffPlans tariffPlans = new Resource.TariffPlans();
        tariffPlans.setDataPsTariffPlan("81002");
        tariffPlans.setDataCsTariffPlan("83001");
        tariffPlans.setSmsTariffPlan("84001");
        tariffPlans.setVoiceTariffPlan(null);
        resource.setTariffPlans(tariffPlans);

        resource.setFirstActivationDate("2020-05-26T14:14:06.019Z");

        Resource.ApnInfo apnInfo = new Resource.ApnInfo();
        apnInfo.setApnId("m2minternet");
        apnInfo.setApnIndex("01877");
        apnInfo.setApnType("PUBLIC");
        apnInfo.setApnQos(null);
        resource.setApnInfo(apnInfo);


        resource.setLoyaltyPeriodRemaining(60);
        resource.setLoyaltyLastUpdate("2020-05-26T14:14:06.019Z");

        resource.setAdminResourceStatus("ACTIVE");

        return resource;
    }
}