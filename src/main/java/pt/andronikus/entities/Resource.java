package pt.andronikus.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.andronikus.entities.base.BaseEntity;
import pt.andronikus.utils.WhenNullValueThen;

public class Resource extends BaseEntity {
    private String msisdn;
    private String agreementId;
    private String parentAgreementId;
    private String serviceInstanceId;
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
    private String firstActivationDate;
    private String roamingStatus;
    private ApnInfo apnInfo;
    private Integer loyaltyPeriod;
    private Integer loyaltyPeriodRemaining;
    private String loyaltyLastUpdate;
    private String lineId;

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

        this.commStatus = commStatus != null ? commStatus.toUpperCase() : "";
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

    public String getFirstActivationDate() {
        return firstActivationDate;
    }

    public void setFirstActivationDate(String firstActivationDate) {
        this.firstActivationDate = firstActivationDate;
    }

    public String getRoamingStatus() {
        return roamingStatus;
    }

    public void setRoamingStatus(String roamingStatus) {
        this.roamingStatus = roamingStatus;
    }

    public ApnInfo getApnInfo() {
        return apnInfo;
    }

    public void setApnInfo(ApnInfo apnInfo) {
        this.apnInfo = apnInfo;
    }

    public Integer getLoyaltyPeriod() {
        return loyaltyPeriod;
    }

    public void setLoyaltyPeriod(Integer loyaltyPeriod) {
        this.loyaltyPeriod = loyaltyPeriod;
    }

    public Integer getLoyaltyPeriodRemaining() {
        return loyaltyPeriodRemaining;
    }

    public void setLoyaltyPeriodRemaining(Integer loyaltyPeriodRemaining) {
        this.loyaltyPeriodRemaining = loyaltyPeriodRemaining;
    }

    public String getLoyaltyLastUpdate() {
        return loyaltyLastUpdate;
    }

    public void setLoyaltyLastUpdate(String loyaltyLastUpdate) {
        this.loyaltyLastUpdate = loyaltyLastUpdate;
    }

    public String getParentAgreementId() {
        return parentAgreementId;
    }

    public void setParentAgreementId(String parentAgreementId) {
        this.parentAgreementId = parentAgreementId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public static class CommServices {
        private Boolean smsService;
        private Boolean voiceService;
        private Boolean dataCsService;
        private Boolean dataPsService;

        public CommServices() {
        }

        public Boolean getSmsService() {
            return smsService;
        }

        public void setSmsService(Boolean smsService) {
            this.smsService = smsService;
        }

        public Boolean getVoiceService() {
            return voiceService;
        }

        public void setVoiceService(Boolean voiceService) {
            this.voiceService = voiceService;
        }

        public Boolean getDataCsService() {
            return dataCsService;
        }

        public void setDataCsService(Boolean dataCsService) {
            this.dataCsService = dataCsService;
        }

        public Boolean getDataPsService() {
            return dataPsService;
        }

        public void setDataPsService(Boolean dataPsService) {
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
        @JsonProperty("identifier")
        private String apnId;
        @JsonProperty("index")
        private String apnIndex;
        @JsonProperty("type")
        private String apnType;
        @JsonProperty("qos")
        private String apnQos;

        public ApnInfo() {
        }

        public String getApnId() {
            return apnId;
        }

        public void setApnId(String apnId) {
            this.apnId = WhenNullValueThen.setStringOrInAbsence(apnId,"");;
        }

        public String getApnIndex() {
            return apnIndex;
        }

        public void setApnIndex(String apnIndex) {
            this.apnIndex = WhenNullValueThen.setStringOrInAbsence(apnIndex,"");
        }

        public String getApnType() {
            return apnType;
        }

        public void setApnType(String apnType) {
            this.apnType = WhenNullValueThen.setStringOrInAbsence(apnType,"");
        }

        public String getApnQos() {
            return apnQos;
        }

        public void setApnQos(String apnQos) {
            this.apnQos = WhenNullValueThen.setStringOrInAbsence(apnQos,"");
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
