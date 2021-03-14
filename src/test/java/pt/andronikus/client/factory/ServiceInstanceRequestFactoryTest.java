package pt.andronikus.client.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.andronikus.client.request.ServiceInstanceRequest;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.entities.ServiceInstance;
import pt.andronikus.entities.base.*;
import pt.andronikus.enums.AdministrativeStatus;
import pt.andronikus.utils.ParserUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ServiceInstanceRequestFactoryTest {
    private static final boolean alreadySetUp = false;
    private ServiceInstance serviceInstance;

    @BeforeEach
    void setUp() {
        if(!alreadySetUp){
            this.serviceInstance = createServiceInstance();
        }
    }

    @Test
    void shouldCreateRequestHaveAValidServiceInstanceOrderItem() {
        ServiceInstanceRequest request = ServiceInstanceRequestFactory.getServiceInstanceRequest(this.serviceInstance);

        System.out.println(JSONUtils.toJSON(request));
    }

    private ServiceInstance createServiceInstance() {
        ServiceInstance newServiceInstance = new ServiceInstance();
        newServiceInstance.setAccountId("d40ac04b01db62");
        newServiceInstance.setServiceInstanceId("troque.ixs.meo_01");
        newServiceInstance.setAgreementId("MIG_b2c28623-5a1b-493e-af10-bd672ea1a41e");
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

        newServiceInstance.setDiagnosticEnabled(true);
        newServiceInstance.setResourceOrderMinQty(1);
        newServiceInstance.setResourceOrderMaxQty(1);

        newServiceInstance.setInitialResourceStatus("preactive");
        newServiceInstance.setLoyaltyPeriod(730);
        newServiceInstance.setCostByDay(0.09996);

        SupportLevel supportLevel = new SupportLevel();
        supportLevel.setSupportLevel("SLA_GOLD");
        supportLevel.setSupportLevelCost(1.50000);
        newServiceInstance.setSupportLevel(supportLevel);

        ServicesCost servicesCost = new ServicesCost();
        servicesCost.setSmsConsoleCost(0.00001);
        servicesCost.setLocationServiceCost(0.00010);
        servicesCost.setApiAccessCost(1D);
        servicesCost.setDedicatedApnCost(100D);
        servicesCost.setCustomerTrainingCost(10D);
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


        newServiceInstance.setDataPsCostPerState(Arrays.asList(new TariffCostPerState("81002",1d,1d,1d,1d,1d,0.00002),
                new TariffCostPerState("81001",1d,1d,1d,1d,1d,0.00002)));

        newServiceInstance.setDataCsCostPerState(Arrays.asList(new TariffCostPerState("83002",1d,1d,1d,1d,1d,0.00002),
                new TariffCostPerState("83001",0.11111,0.11111,0.11111,0.11111,0.11111,0.00002)));

        newServiceInstance.setVoiceCostPerState(Arrays.asList(new TariffCostPerState("82002",1d,1d,1d,1d,1d,0.00002),
                new TariffCostPerState("82001",1d,1d,1d,1d,1d,0.00002)));

        newServiceInstance.setSmsCostPerState(Arrays.asList(new TariffCostPerState("84003",1d,1d,1d,1d,1d,0.00002),
                new TariffCostPerState("84002",1d,1d,1d,1d,1d,0.00002),
                new TariffCostPerState("84001",1d,1d,1d,1d,1d,0.00002)));

        LifeCycleTransitionsCost lfcCosts = new LifeCycleTransitionsCost();
        lfcCosts.setLiveToSuspend(1d);
        lfcCosts.setLiveToStopped(2d);
        lfcCosts.setStoppedToLive(4d);
        lfcCosts.setStoppedToSuspend(5d);
        lfcCosts.setStoppedToStandby(6d);
        lfcCosts.setPreActiveToStopped(0.00001);
        lfcCosts.setStandbyToSuspend(0.00001);
        lfcCosts.setSuspendToStandby(0.00001);
        lfcCosts.setStandbyToStopped(0.00001);
        lfcCosts.setSuspendToStopped(0.00001);
        lfcCosts.setSuspendToLive(0.00001);
        lfcCosts.setStandbyToLive(1d);
        lfcCosts.setLiveToStandby(1d);
        lfcCosts.setTestToStopped(1d);
        lfcCosts.setPreActiveToTest(0.00001);
        lfcCosts.setTestToLive(0.00001);
        lfcCosts.setPreActiveToLive(0.00002);
        lfcCosts.setAdmActiveToCanceled(0.00004);
        lfcCosts.setAdmActiveToSuspended(0.00005);
        lfcCosts.setAdmSuspendedToActive(0.00301);
        lfcCosts.setAdmSuspendedToCanceled(0d);
        newServiceInstance.setLifeCycleTransitionsCost(lfcCosts);

        newServiceInstance.setApnsDefault(Arrays.asList("neopost-sfr","spiem2m","m2minternet","pmtvpn-sfr"));

        newServiceInstance.setApns1AuthorizedEligible(new ArrayList<>());
        newServiceInstance.setApns2DedicatedEligible(new ArrayList<>());
        newServiceInstance.setApns2AuthorizedEligible(Arrays.asList("m2minternet","platftest-sie.3gtmn.pt","ggpic8.tmn.pt"));
        newServiceInstance.setApns2DedicatedEligible(Arrays.asList("spiem2m","neopost-sfr","pmtvpn-sfr"));

        QosInfo qosInfo = new QosInfo(true);
        qosInfo.setQosResourceDefault("Advanced");
        qosInfo.setQosLevels(Arrays.asList("5G","Advanced","Basic","Top"));
        newServiceInstance.setQosInfo(qosInfo);

        InitialBalanceInTests initialBalanceInTests = new InitialBalanceInTests();
        initialBalanceInTests.setDataPsInitialBalance(100000d);
        initialBalanceInTests.setDataCsInitialBalance(11d);
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

        newServiceInstance.setAdministrativeStatus(AdministrativeStatus.valueOf("ACTIVE"));

        newServiceInstance.setApnList(ParserUtils.parseApnList("m2minternet,PUBLIC,IPV4,IPV4:true:10.10.1.1/16#192.168.1.1/32,IPV4:false:|m2minternet2,PUBLIC,IPV4,IPV4:true:10.10.1.1/16#192.168.1.1/32,IPV4:false:"));

        return newServiceInstance;
    }
}