package pt.andronikus.entities;

import pt.andronikus.entities.base.*;
import pt.andronikus.enums.AdministrativeStatus;

import java.util.ArrayList;
import java.util.List;

public class ServiceInstance extends BaseEntity {
    private String accountId;
    private String serviceInstanceId;
    private Integer pf;
    private String agreementId;
    private String resourceHomeNetwork;
    private String catalogSpec;
    private String offerSpec;
    private AdminInformation adminInfo;

    private boolean secondaryImsiEnabled;
    private boolean authorizationFlagSecImsi;
    private boolean testingLifeCycleEnabled;
    private boolean authorizationFlagLifeCycle;
    private boolean roamingEnabled;

    private String initialRoamingStatus;
    private boolean diagnosticEnabled;

    private Integer resourceOrderMinQty;
    private Integer resourceOrderMaxQty;

    private String initialResourceStatus;
    private Integer loyaltyPeriod;
    private Float costByDay;

    private SupportLevel supportLevel;
    private ServicesCost servicesCost;
    private CommunicationServices communicationServices;
    private ServiceDefaultTariff serviceDefaultTariff;
    private List<String> dataPsTariffs;
    private List<String> dataCsTariffs;
    private List<String> voiceTariffs;
    private List<String> smsTariffs;
    private List<TariffCostPerState> dataPsCostPerState;
    private List<TariffCostPerState> dataCsCostPerState;
    private List<TariffCostPerState> voiceCostPerState;
    private List<TariffCostPerState> smsCostPerState;
    private LifeCycleTransitionsCost lifeCycleTransitionsCost;
    private List<String> apnsDefault;
    private List<String> apns1AuthorizedEligible = new ArrayList<>();
    private List<String> apns1DedicatedEligible = new ArrayList<>();
    private List<String> apns2AuthorizedEligible = new ArrayList<>();
    private List<String> apns2DedicatedEligible = new ArrayList<>();
    private QosInfo qosInfo;
    private InitialBalanceInTests initialBalanceInTests;
    private ServiceInTests serviceInTests;
    private Integer maxDaysUntilActive;
    private AdministrativeStatus administrativeStatus;
    // MEO Only
    private ApnInfo apnList;












}
