package pt.andronikus.entities.base;

public class ServiceInTests {
    private Boolean dataPsServiceInTests;
    private Boolean dataCsServiceInTests;
    private Boolean voiceServiceInTests;
    private Boolean smsServiceInTests;

    public ServiceInTests() {
    }

    public ServiceInTests(Boolean dataPsServiceInTests, Boolean dataCsServiceInTests, Boolean voiceServiceInTests, Boolean smsServiceInTests) {
        this.dataPsServiceInTests = dataPsServiceInTests;
        this.dataCsServiceInTests = dataCsServiceInTests;
        this.voiceServiceInTests = voiceServiceInTests;
        this.smsServiceInTests = smsServiceInTests;
    }

    public Boolean getDataPsServiceInTests() {
        return dataPsServiceInTests;
    }

    public void setDataPsServiceInTests(Boolean dataPsServiceInTests) {
        this.dataPsServiceInTests = dataPsServiceInTests;
    }

    public Boolean getDataCsServiceInTests() {
        return dataCsServiceInTests;
    }

    public void setDataCsServiceInTests(Boolean dataCsServiceInTests) {
        this.dataCsServiceInTests = dataCsServiceInTests;
    }

    public Boolean getVoiceServiceInTests() {
        return voiceServiceInTests;
    }

    public void setVoiceServiceInTests(Boolean voiceServiceInTests) {
        this.voiceServiceInTests = voiceServiceInTests;
    }

    public Boolean getSmsServiceInTests() {
        return smsServiceInTests;
    }

    public void setSmsServiceInTests(Boolean smsServiceInTests) {
        this.smsServiceInTests = smsServiceInTests;
    }

    @Override
    public String toString() {
        return "ServiceInTests{" +
                "dataPsServiceInTests=" + dataPsServiceInTests +
                ", dataCsServiceInTests=" + dataCsServiceInTests +
                ", voiceServiceInTests=" + voiceServiceInTests +
                ", smsServiceInTests=" + smsServiceInTests +
                '}';
    }
}
