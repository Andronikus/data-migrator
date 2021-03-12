package pt.andronikus.entities.base;

public class CommunicationServices {
    private boolean dataPsService;
    private boolean dataCsService;
    private boolean voiceService;
    private boolean smsService;
    private boolean dataPsServiceDefault;
    private boolean dataCsServiceDefault;
    private boolean voiceServiceDefault;
    private boolean smsServiceDefault;

    public CommunicationServices() {
    }

    public boolean isDataPsService() {
        return dataPsService;
    }

    public void setDataPsService(boolean dataPsService) {
        this.dataPsService = dataPsService;
    }

    public boolean isDataCsService() {
        return dataCsService;
    }

    public void setDataCsService(boolean dataCsService) {
        this.dataCsService = dataCsService;
    }

    public boolean isVoiceService() {
        return voiceService;
    }

    public void setVoiceService(boolean voiceService) {
        this.voiceService = voiceService;
    }

    public boolean isSmsService() {
        return smsService;
    }

    public void setSmsService(boolean smsService) {
        this.smsService = smsService;
    }

    public boolean isDataPsServiceDefault() {
        return dataPsServiceDefault;
    }

    public void setDataPsServiceDefault(boolean dataPsServiceDefault) {
        this.dataPsServiceDefault = dataPsServiceDefault;
    }

    public boolean isDataCsServiceDefault() {
        return dataCsServiceDefault;
    }

    public void setDataCsServiceDefault(boolean dataCsServiceDefault) {
        this.dataCsServiceDefault = dataCsServiceDefault;
    }

    public boolean isVoiceServiceDefault() {
        return voiceServiceDefault;
    }

    public void setVoiceServiceDefault(boolean voiceServiceDefault) {
        this.voiceServiceDefault = voiceServiceDefault;
    }

    public boolean isSmsServiceDefault() {
        return smsServiceDefault;
    }

    public void setSmsServiceDefault(boolean smsServiceDefault) {
        this.smsServiceDefault = smsServiceDefault;
    }

    @Override
    public String toString() {
        return "CommunicationServices{" +
                "dataPsService=" + dataPsService +
                ", dataCsService=" + dataCsService +
                ", voiceService=" + voiceService +
                ", smsService=" + smsService +
                ", dataPsServiceDefault=" + dataPsServiceDefault +
                ", dataCsServiceDefault=" + dataCsServiceDefault +
                ", voiceServiceDefault=" + voiceServiceDefault +
                ", smsServiceDefault=" + smsServiceDefault +
                '}';
    }
}
