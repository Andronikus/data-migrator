package pt.andronikus.client.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.andronikus.client.request.ServiceInstanceRequest;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.configuration.CallbackServerConfiguration;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.configuration.MigrationProcessInfo;
import pt.andronikus.entities.ServiceInstance;
import pt.andronikus.entities.base.*;
import pt.andronikus.enums.AdministrativeStatus;
import pt.andronikus.singletons.AppConfiguration;
import pt.andronikus.utils.ParserUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ServiceInstanceRequestFactoryTest {
    private static final boolean alreadySetUp = false;
    private ServiceInstance serviceInstance;

    @BeforeEach
    void setUp() {
        if(!alreadySetUp){
            this.serviceInstance = createServiceInstance();

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
        }
    }

    @Test
    void shouldCreateRequestHaveAValidServiceInstanceOrderItem() {
        ServiceInstanceRequest request = ServiceInstanceRequestFactory.getServiceInstanceCreateRequest(this.serviceInstance);

        System.out.println(JSONUtils.toJSON(request));
    }

    private ServiceInstance createServiceInstance() {
        ServiceInstance newServiceInstance = new ServiceInstance();

        newServiceInstance.setOrderCorrelationId("MIG_SER_" + UUID.randomUUID().toString());
        newServiceInstance.setCorrelationId("MIG_" + UUID.randomUUID().toString());

        newServiceInstance.setAccountId("d40ac04b01db70");
        newServiceInstance.setServiceInstanceId("troque.ixs.meo_01");
        newServiceInstance.setAgreementId("MIG_" + UUID.randomUUID().toString());
        newServiceInstance.setResourceHomeNetwork("MEO");
        newServiceInstance.setCatalogSpec("5dc1c6bd705297252bfd1562");
        newServiceInstance.setOfferSpec("5e28ad1d7052979f71ce88d0");

        AdminInformation adminInformation = new AdminInformation();
        adminInformation.setAdminEmail("troque.ixs.meo_01@mail.pt");
        adminInformation.setAdminLogin("login.ixs.meo_01@mail.pt");
        adminInformation.setAdminName("admin.ixs.meo_01@mail.pt");
        adminInformation.setAdminMobile("00351925009347");
        newServiceInstance.setAdminInfo(adminInformation);

        newServiceInstance.setSecondaryImsiEnabled(false);
        newServiceInstance.setAuthorizationFlagSecImsi(false);
        newServiceInstance.setTestingLifeCycleEnabled(true);
        newServiceInstance.setAuthorizationFlagLifeCycle(false);

        RoamingInfo roamingInfo = new RoamingInfo();
        roamingInfo.setRoamingEnabled(true);
        roamingInfo.setInitialRoamingStatus("ALLOW");
        newServiceInstance.setRoamingInfo(roamingInfo);

        newServiceInstance.setDiagnosticEnabled(false);
        newServiceInstance.setResourceOrderMinQty(1);
        newServiceInstance.setResourceOrderMaxQty(10);

        newServiceInstance.setInitialResourceStatus("preactive");
        newServiceInstance.setLoyaltyPeriod("730");
        newServiceInstance.setCostByDay("0.09996");

        SupportLevel supportLevel = new SupportLevel();
        supportLevel.setSupportLevel("SLA_GOLD");
        supportLevel.setSupportLevelCost("1.50000");
        newServiceInstance.setSupportLevel(supportLevel);

        ServicesCost servicesCost = new ServicesCost();
        servicesCost.setSmsConsoleCost("0.00001");
        servicesCost.setLocationServiceCost("0.00010");
        servicesCost.setApiAccessCost("1");
        servicesCost.setDedicatedApnCost("100");
        servicesCost.setCustomerTrainingCost("10");
        newServiceInstance.setServicesCost(servicesCost);

        CommunicationServices commServices = new CommunicationServices();
        commServices.setDataPsService(true);
        commServices.setDataCsService(true);
        commServices.setVoiceService(true);
        commServices.setSmsService(true);
        commServices.setDataPsServiceDefault(true);
        commServices.setDataCsServiceDefault(true);
        commServices.setVoiceServiceDefault(false);
        commServices.setSmsServiceDefault(true);
        newServiceInstance.setCommunicationServices(commServices);

        ServiceDefaultTariff serviceDefaultTariff = new ServiceDefaultTariff();
        serviceDefaultTariff.setDataPsDefaultTariff("81001");
        serviceDefaultTariff.setDataCsDefaultTariff("83001");
        serviceDefaultTariff.setVoiceDefaultTariff(null);
        serviceDefaultTariff.setSmsDefaultTariff("84001");
        newServiceInstance.setServiceDefaultTariff(serviceDefaultTariff);

        newServiceInstance.setDataPsTariffs(Arrays.asList("81001","81002"));
        newServiceInstance.setDataCsTariffs(Arrays.asList("83001", "83002"));
        newServiceInstance.setVoiceTariffs(Arrays.asList("82001", "82002"));
        newServiceInstance.setSmsTariffs(Arrays.asList("84001","84002","84003"));


        newServiceInstance.setDataPsCostPerState(Arrays.asList(new TariffCostPerState("81002","1","1","1","1","1","0.00002"),
                new TariffCostPerState("81001","1","1","1","1","1","0.00002")));

        newServiceInstance.setDataCsCostPerState(Arrays.asList(new TariffCostPerState("83002","1","1","1","1","1","0.00002"),
                new TariffCostPerState("83001","0.11111","0.11111","0.11111","0.11111","0.11111","0.00002")));

        newServiceInstance.setVoiceCostPerState(Arrays.asList(new TariffCostPerState("82002","1","1","1","1","1","0.00002"),
                new TariffCostPerState("82001","1","1","1","1","1","0.00002")));

        newServiceInstance.setSmsCostPerState(Arrays.asList(new TariffCostPerState("84003","1","1","1","1","1","0.00002"),
                new TariffCostPerState("84002","1","1","1","1","1","0.00002"),
                new TariffCostPerState("84001","1","1","1","1","1","0.00002")));

        LifeCycleTransitionsCost lfcCosts = new LifeCycleTransitionsCost();
        lfcCosts.setLiveToSuspend("1");
        lfcCosts.setLiveToStopped("2");
        lfcCosts.setStoppedToLive("4");
        lfcCosts.setStoppedToSuspend("5");
        lfcCosts.setStoppedToStandby("6");
        lfcCosts.setPreActiveToStopped("0.00001");
        lfcCosts.setStandbyToSuspend("0.00001");
        lfcCosts.setSuspendToStandby("0.00001");
        lfcCosts.setStandbyToStopped("0.00001");
        lfcCosts.setSuspendToStopped("0.00001");
        lfcCosts.setSuspendToLive("0.00001");
        lfcCosts.setStandbyToLive("1");
        lfcCosts.setLiveToStandby("1");
        lfcCosts.setTestToStopped("1");
        lfcCosts.setPreActiveToTest("0.00001");
        lfcCosts.setTestToLive("0.00001");
        lfcCosts.setPreActiveToLive("0.00002");
        lfcCosts.setAdmActiveToCanceled("0.00004");
        lfcCosts.setAdmActiveToSuspended("0.00005");
        lfcCosts.setAdmSuspendedToActive("0.00301");
        lfcCosts.setAdmSuspendedToCanceled("0");
        newServiceInstance.setLifeCycleTransitionsCost(lfcCosts);

        newServiceInstance.setApnsDefault(Arrays.asList("neopost-sfr","spiem2m","m2minternet","pmtvpn-sfr"));

        newServiceInstance.setApns1AuthorizedEligible(new ArrayList<>());
        newServiceInstance.setApns1DedicatedEligible(new ArrayList<>());
        newServiceInstance.setApns2AuthorizedEligible(Arrays.asList("m2minternet","platftest-sie.3gtmn.pt","ggpic8.tmn.pt"));
        newServiceInstance.setApns2DedicatedEligible(Arrays.asList("spiem2m","neopost-sfr","pmtvpn-sfr"));

        QosInfo qosInfo = new QosInfo(true);
        qosInfo.setQosResourceDefault("Advanced");
        qosInfo.setQosLevels(Arrays.asList("5G","Advanced","Basic","Top"));
        newServiceInstance.setQosInfo(qosInfo);

        InitialBalanceInTests initialBalanceInTests = new InitialBalanceInTests();
        initialBalanceInTests.setDataPsInitialBalance("100000");
        initialBalanceInTests.setDataCsInitialBalance("111");
        initialBalanceInTests.setVoiceInitialBalance(null);
        initialBalanceInTests.setSmsInitialBalance(null);
        newServiceInstance.setInitialBalanceInTests(initialBalanceInTests);

        ServiceInTests serviceInTests = new ServiceInTests();
        serviceInTests.setDataPsServiceInTests(true);
        serviceInTests.setDataCsServiceInTests(true);
        serviceInTests.setVoiceServiceInTests(false);
        serviceInTests.setSmsServiceInTests(false);
        newServiceInstance.setServiceInTests(serviceInTests);

        newServiceInstance.setMaxDaysUntilActive(10);

        // newServiceInstance.setAdministrativeStatus(AdministrativeStatus.valueOf("ACTIVE"));

        // newServiceInstance.setApnList(ParserUtils.parseApnList("m2minternet,PUBLIC,IPV4,IPV4:true:10.10.1.1/16#192.168.1.1/32,IPV4:false:|m2minternet2,PUBLIC,IPV4,IPV4:true:10.10.1.1/16#192.168.1.1/32,IPV4:false:"));

        return newServiceInstance;
    }
}