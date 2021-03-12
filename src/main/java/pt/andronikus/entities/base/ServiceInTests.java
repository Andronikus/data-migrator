package pt.andronikus.entities.base;

public class ServiceInTests {
    private boolean dataPsServiceInTests;
    private boolean dataCsServiceInTests;
    private boolean voiceServiceInTests;
    private boolean smsServiceInTests;

    public ServiceInTests() {
    }

    public ServiceInTests(boolean dataPsServiceInTests, boolean dataCsServiceInTests, boolean voiceServiceInTests, boolean smsServiceInTests) {
        this.dataPsServiceInTests = dataPsServiceInTests;
        this.dataCsServiceInTests = dataCsServiceInTests;
        this.voiceServiceInTests = voiceServiceInTests;
        this.smsServiceInTests = smsServiceInTests;
    }

    public boolean isDataPsServiceInTests() {
        return dataPsServiceInTests;
    }

    public void setDataPsServiceInTests(boolean dataPsServiceInTests) {
        this.dataPsServiceInTests = dataPsServiceInTests;
    }

    public boolean isDataCsServiceInTests() {
        return dataCsServiceInTests;
    }

    public void setDataCsServiceInTests(boolean dataCsServiceInTests) {
        this.dataCsServiceInTests = dataCsServiceInTests;
    }

    public boolean isVoiceServiceInTests() {
        return voiceServiceInTests;
    }

    public void setVoiceServiceInTests(boolean voiceServiceInTests) {
        this.voiceServiceInTests = voiceServiceInTests;
    }

    public boolean isSmsServiceInTests() {
        return smsServiceInTests;
    }

    public void setSmsServiceInTests(boolean smsServiceInTests) {
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
