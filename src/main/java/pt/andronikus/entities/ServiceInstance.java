package pt.andronikus.entities;

import pt.andronikus.entities.base.*;
import pt.andronikus.enums.AdministrativeStatus;
import pt.andronikus.utils.WhenNullValueThen;

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

    private RoamingInfo roamingInfo;

    private boolean diagnosticEnabled;

    private Integer resourceOrderMinQty;
    private Integer resourceOrderMaxQty;

    private String initialResourceStatus;
    private String loyaltyPeriod;
    private String costByDay;

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
    private List<ApnInfo> apnList;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    @Override
    public Integer getPf() {
        return pf;
    }

    @Override
    public void setPf(Integer pf) {
        this.pf = pf;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getResourceHomeNetwork() {
        return resourceHomeNetwork;
    }

    public void setResourceHomeNetwork(String resourceHomeNetwork) {
        this.resourceHomeNetwork = resourceHomeNetwork;
    }

    public String getCatalogSpec() {
        return catalogSpec;
    }

    public void setCatalogSpec(String catalogSpec) {
        this.catalogSpec = catalogSpec;
    }

    public String getOfferSpec() {
        return offerSpec;
    }

    public void setOfferSpec(String offerSpec) {
        this.offerSpec = offerSpec;
    }

    public AdminInformation getAdminInfo() {
        return adminInfo;
    }

    public void setAdminInfo(AdminInformation adminInfo) {
        this.adminInfo = adminInfo;
    }

    public boolean isSecondaryImsiEnabled() {
        return secondaryImsiEnabled;
    }

    public void setSecondaryImsiEnabled(boolean secondaryImsiEnabled) {
        this.secondaryImsiEnabled = secondaryImsiEnabled;
    }

    public boolean isAuthorizationFlagSecImsi() {
        return authorizationFlagSecImsi;
    }

    public void setAuthorizationFlagSecImsi(boolean authorizationFlagSecImsi) {
        this.authorizationFlagSecImsi = authorizationFlagSecImsi;
    }

    public boolean isTestingLifeCycleEnabled() {
        return testingLifeCycleEnabled;
    }

    public void setTestingLifeCycleEnabled(boolean testingLifeCycleEnabled) {
        this.testingLifeCycleEnabled = testingLifeCycleEnabled;
    }

    public boolean isAuthorizationFlagLifeCycle() {
        return authorizationFlagLifeCycle;
    }

    public void setAuthorizationFlagLifeCycle(boolean authorizationFlagLifeCycle) {
        this.authorizationFlagLifeCycle = authorizationFlagLifeCycle;
    }

    public RoamingInfo getRoamingInfo() {
        return roamingInfo;
    }

    public void setRoamingInfo(RoamingInfo roamingInfo) {
        this.roamingInfo = roamingInfo;
    }

    public boolean isDiagnosticEnabled() {
        return diagnosticEnabled;
    }

    public void setDiagnosticEnabled(boolean diagnosticEnabled) {
        this.diagnosticEnabled = diagnosticEnabled;
    }

    public Integer getResourceOrderMinQty() {
        return resourceOrderMinQty;
    }

    public void setResourceOrderMinQty(Integer resourceOrderMinQty) {
        this.resourceOrderMinQty = resourceOrderMinQty;
    }

    public Integer getResourceOrderMaxQty() {
        return resourceOrderMaxQty;
    }

    public void setResourceOrderMaxQty(Integer resourceOrderMaxQty) {
        this.resourceOrderMaxQty = resourceOrderMaxQty;
    }

    public String getInitialResourceStatus() {
        return initialResourceStatus;
    }

    public void setInitialResourceStatus(String initialResourceStatus) {
        this.initialResourceStatus = initialResourceStatus;
    }

    public String getLoyaltyPeriod() {
        return loyaltyPeriod;
    }

    public void setLoyaltyPeriod(String loyaltyPeriod) {
        this.loyaltyPeriod = WhenNullValueThen.setStringOrInAbsence(loyaltyPeriod, "");
    }

    public String getCostByDay() {
        return costByDay;
    }

    public void setCostByDay(String costByDay) {
        this.costByDay = costByDay;
    }

    public SupportLevel getSupportLevel() {
        return supportLevel;
    }

    public void setSupportLevel(SupportLevel supportLevel) {
        this.supportLevel = supportLevel;
    }

    public ServicesCost getServicesCost() {
        return servicesCost;
    }

    public void setServicesCost(ServicesCost servicesCost) {
        this.servicesCost = servicesCost;
    }

    public CommunicationServices getCommunicationServices() {
        return communicationServices;
    }

    public void setCommunicationServices(CommunicationServices communicationServices) {
        this.communicationServices = communicationServices;
    }

    public ServiceDefaultTariff getServiceDefaultTariff() {
        return serviceDefaultTariff;
    }

    public void setServiceDefaultTariff(ServiceDefaultTariff serviceDefaultTariff) {
        this.serviceDefaultTariff = serviceDefaultTariff;
    }

    public List<String> getDataPsTariffs() {
        return dataPsTariffs;
    }

    public void setDataPsTariffs(List<String> dataPsTariffs) {
        this.dataPsTariffs = dataPsTariffs;
    }

    public List<String> getDataCsTariffs() {
        return dataCsTariffs;
    }

    public void setDataCsTariffs(List<String> dataCsTariffs) {
        this.dataCsTariffs = dataCsTariffs;
    }

    public List<String> getVoiceTariffs() {
        return voiceTariffs;
    }

    public void setVoiceTariffs(List<String> voiceTariffs) {
        this.voiceTariffs = voiceTariffs;
    }

    public List<String> getSmsTariffs() {
        return smsTariffs;
    }

    public void setSmsTariffs(List<String> smsTariffs) {
        this.smsTariffs = smsTariffs;
    }

    public List<TariffCostPerState> getDataPsCostPerState() {
        return dataPsCostPerState;
    }

    public void setDataPsCostPerState(List<TariffCostPerState> dataPsCostPerState) {
        this.dataPsCostPerState = dataPsCostPerState;
    }

    public List<TariffCostPerState> getDataCsCostPerState() {
        return dataCsCostPerState;
    }

    public void setDataCsCostPerState(List<TariffCostPerState> dataCsCostPerState) {
        this.dataCsCostPerState = dataCsCostPerState;
    }

    public List<TariffCostPerState> getVoiceCostPerState() {
        return voiceCostPerState;
    }

    public void setVoiceCostPerState(List<TariffCostPerState> voiceCostPerState) {
        this.voiceCostPerState = voiceCostPerState;
    }

    public List<TariffCostPerState> getSmsCostPerState() {
        return smsCostPerState;
    }

    public void setSmsCostPerState(List<TariffCostPerState> smsCostPerState) {
        this.smsCostPerState = smsCostPerState;
    }

    public LifeCycleTransitionsCost getLifeCycleTransitionsCost() {
        return lifeCycleTransitionsCost;
    }

    public void setLifeCycleTransitionsCost(LifeCycleTransitionsCost lifeCycleTransitionsCost) {
        this.lifeCycleTransitionsCost = lifeCycleTransitionsCost;
    }

    public List<String> getApnsDefault() {
        return apnsDefault;
    }

    public void setApnsDefault(List<String> apnsDefault) {
        this.apnsDefault = apnsDefault;
    }

    public List<String> getApns1AuthorizedEligible() {
        return apns1AuthorizedEligible;
    }

    public void setApns1AuthorizedEligible(List<String> apns1AuthorizedEligible) {
        this.apns1AuthorizedEligible = apns1AuthorizedEligible;
    }

    public List<String> getApns1DedicatedEligible() {
        return apns1DedicatedEligible;
    }

    public void setApns1DedicatedEligible(List<String> apns1DedicatedEligible) {
        this.apns1DedicatedEligible = apns1DedicatedEligible;
    }

    public List<String> getApns2AuthorizedEligible() {
        return apns2AuthorizedEligible;
    }

    public void setApns2AuthorizedEligible(List<String> apns2AuthorizedEligible) {
        this.apns2AuthorizedEligible = apns2AuthorizedEligible;
    }

    public List<String> getApns2DedicatedEligible() {
        return apns2DedicatedEligible;
    }

    public void setApns2DedicatedEligible(List<String> apns2DedicatedEligible) {
        this.apns2DedicatedEligible = apns2DedicatedEligible;
    }

    public QosInfo getQosInfo() {
        return qosInfo;
    }

    public void setQosInfo(QosInfo qosInfo) {
        this.qosInfo = qosInfo;
    }

    public InitialBalanceInTests getInitialBalanceInTests() {
        return initialBalanceInTests;
    }

    public void setInitialBalanceInTests(InitialBalanceInTests initialBalanceInTests) {
        this.initialBalanceInTests = initialBalanceInTests;
    }

    public ServiceInTests getServiceInTests() {
        return serviceInTests;
    }

    public void setServiceInTests(ServiceInTests serviceInTests) {
        this.serviceInTests = serviceInTests;
    }

    public Integer getMaxDaysUntilActive() {
        return maxDaysUntilActive;
    }

    public void setMaxDaysUntilActive(Integer maxDaysUntilActive) {
        this.maxDaysUntilActive = maxDaysUntilActive;
    }

    public AdministrativeStatus getAdministrativeStatus() {
        return administrativeStatus;
    }

    public void setAdministrativeStatus(AdministrativeStatus administrativeStatus) {
        this.administrativeStatus = administrativeStatus;
    }

    public List<ApnInfo> getApnList() {
        return apnList;
    }

    public void setApnList(List<ApnInfo> apnList) {
        this.apnList = apnList;
    }
}
