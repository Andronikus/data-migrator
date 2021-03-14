package pt.andronikus.entities;

import pt.andronikus.entities.base.BaseEntity;

public class Resource extends BaseEntity {
    private String msisdn;
    private String agreementId;
    private String serviceInstanceId;
    private String resourceHomeNetwork;
    private String catalogSpec;
    private String offerSpec;
    private String secondaryMsisdn;
    private String iccid;
    private String serviceResourceStatus;
    private Boolean testingLifeCycleEnabled;
    private String commStatus;
    private String reactivateCommStatus;

    private CommServices commServices;
    private TariffPlans tariffPlans;

    private String adminResourceStatus;
    private String creationDate;
    private String firstActivationDate;
    private String designation;
    private String roamingStatus;
    private String endpoint;
    private String endpointGroups;

    private ApnInfo apnInfo;

    private String loyaltyPeriodRemaining;
    private String loyaltyLastUpdate;

    public Resource() {
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
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

    public String getSecondaryMsisdn() {
        return secondaryMsisdn;
    }

    public void setSecondaryMsisdn(String secondaryMsisdn) {
        this.secondaryMsisdn = secondaryMsisdn;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getServiceResourceStatus() {
        return serviceResourceStatus;
    }

    public void setServiceResourceStatus(String serviceResourceStatus) {
        this.serviceResourceStatus = serviceResourceStatus;
    }

    public Boolean getTestingLifeCycleEnabled() {
        return testingLifeCycleEnabled;
    }

    public void setTestingLifeCycleEnabled(Boolean testingLifeCycleEnabled) {
        this.testingLifeCycleEnabled = testingLifeCycleEnabled;
    }

    public String getCommStatus() {
        return commStatus;
    }

    public void setCommStatus(String commStatus) {
        this.commStatus = commStatus;
    }

    public String getReactivateCommStatus() {
        return reactivateCommStatus;
    }

    public void setReactivateCommStatus(String reactivateCommStatus) {
        this.reactivateCommStatus = reactivateCommStatus;
    }

    public CommServices getCommServices() {
        return commServices;
    }

    public void setCommServices(CommServices commServices) {
        this.commServices = commServices;
    }

    public TariffPlans getTariffPlans() {
        return tariffPlans;
    }

    public void setTariffPlans(TariffPlans tariffPlans) {
        this.tariffPlans = tariffPlans;
    }

    public String getAdminResourceStatus() {
        return adminResourceStatus;
    }

    public void setAdminResourceStatus(String adminResourceStatus) {
        this.adminResourceStatus = adminResourceStatus;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getFirstActivationDate() {
        return firstActivationDate;
    }

    public void setFirstActivationDate(String firstActivationDate) {
        this.firstActivationDate = firstActivationDate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRoamingStatus() {
        return roamingStatus;
    }

    public void setRoamingStatus(String roamingStatus) {
        this.roamingStatus = roamingStatus;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpointGroups() {
        return endpointGroups;
    }

    public void setEndpointGroups(String endpointGroups) {
        this.endpointGroups = endpointGroups;
    }

    public ApnInfo getApnInfo() {
        return apnInfo;
    }

    public void setApnInfo(ApnInfo apnInfo) {
        this.apnInfo = apnInfo;
    }

    public String getLoyaltyPeriodRemaining() {
        return loyaltyPeriodRemaining;
    }

    public void setLoyaltyPeriodRemaining(String loyaltyPeriodRemaining) {
        this.loyaltyPeriodRemaining = loyaltyPeriodRemaining;
    }

    public String getLoyaltyLastUpdate() {
        return loyaltyLastUpdate;
    }

    public void setLoyaltyLastUpdate(String loyaltyLastUpdate) {
        this.loyaltyLastUpdate = loyaltyLastUpdate;
    }

    public static class CommServices {
        private boolean smsService;
        private boolean voiceService;
        private boolean dataCsService;
        private boolean dataPsService;

        public CommServices() {
        }

        public boolean isSmsService() {
            return smsService;
        }

        public void setSmsService(boolean smsService) {
            this.smsService = smsService;
        }

        public boolean isVoiceService() {
            return voiceService;
        }

        public void setVoiceService(boolean voiceService) {
            this.voiceService = voiceService;
        }

        public boolean isDataCsService() {
            return dataCsService;
        }

        public void setDataCsService(boolean dataCsService) {
            this.dataCsService = dataCsService;
        }

        public boolean isDataPsService() {
            return dataPsService;
        }

        public void setDataPsService(boolean dataPsService) {
            this.dataPsService = dataPsService;
        }

        @Override
        public String toString() {
            return "CommServices{" +
                    "smsService=" + smsService +
                    ", voiceService=" + voiceService +
                    ", dataCsService=" + dataCsService +
                    ", dataPsService=" + dataPsService +
                    '}';
        }
    }

    public static class TariffPlans {
        private String smsTariffPlan;
        private String voiceTariffPlan;
        private String dataCsTariffPlan;
        private String dataPsTariffPlan;

        public TariffPlans() {
        }

        public String getSmsTariffPlan() {
            return smsTariffPlan;
        }

        public void setSmsTariffPlan(String smsTariffPlan) {
            this.smsTariffPlan = smsTariffPlan;
        }

        public String getVoiceTariffPlan() {
            return voiceTariffPlan;
        }

        public void setVoiceTariffPlan(String voiceTariffPlan) {
            this.voiceTariffPlan = voiceTariffPlan;
        }

        public String getDataCsTariffPlan() {
            return dataCsTariffPlan;
        }

        public void setDataCsTariffPlan(String dataCsTariffPlan) {
            this.dataCsTariffPlan = dataCsTariffPlan;
        }

        public String getDataPsTariffPlan() {
            return dataPsTariffPlan;
        }

        public void setDataPsTariffPlan(String dataPsTariffPlan) {
            this.dataPsTariffPlan = dataPsTariffPlan;
        }

        @Override
        public String toString() {
            return "TariffPlans{" +
                    "smsTariffPlan='" + smsTariffPlan + '\'' +
                    ", voiceTariffPlan='" + voiceTariffPlan + '\'' +
                    ", dataCsTariffPlan='" + dataCsTariffPlan + '\'' +
                    ", dataPsTariffPlan='" + dataPsTariffPlan + '\'' +
                    '}';
        }
    }

    public static class ApnInfo {
        private String apnId;
        private String apnIndex;
        private String apnType;
        private String apnQos;

        public ApnInfo() {
        }

        public String getApnId() {
            return apnId;
        }

        public void setApnId(String apnId) {
            this.apnId = apnId;
        }

        public String getApnIndex() {
            return apnIndex;
        }

        public void setApnIndex(String apnIndex) {
            this.apnIndex = apnIndex;
        }

        public String getApnType() {
            return apnType;
        }

        public void setApnType(String apnType) {
            this.apnType = apnType;
        }

        public String getApnQos() {
            return apnQos;
        }

        public void setApnQos(String apnQos) {
            this.apnQos = apnQos;
        }

        @Override
        public String toString() {
            return "ApnInfo{" +
                    "apnId='" + apnId + '\'' +
                    ", apnIndex='" + apnIndex + '\'' +
                    ", apnType='" + apnType + '\'' +
                    ", apnQos='" + apnQos + '\'' +
                    '}';
        }
    }
}
