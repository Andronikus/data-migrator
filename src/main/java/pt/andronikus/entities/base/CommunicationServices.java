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

    @Override
    public String toString() {
        return "CommunicationServices{" +
                "dataPsService=" + dataPsService +
                ", dataCsService=" + dataCsService +
                ", voiceService=" + voiceService +
                ", smsService=" + smsService +
                '}';
    }
}
