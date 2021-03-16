package pt.andronikus.entities.base;

import pt.andronikus.utils.WhenNullValueThen;

public class ServiceDefaultTariff {
    private String dataPsDefaultTariff;
    private String dataCsDefaultTariff;
    private String voiceDefaultTariff;
    private String smsDefaultTariff;

    public ServiceDefaultTariff() {
    }

    public String getDataPsDefaultTariff() {
        return dataPsDefaultTariff;
    }

    public void setDataPsDefaultTariff(String dataPsDefaultTariff) {
        this.dataPsDefaultTariff = WhenNullValueThen.setStringOrInAbsence(dataPsDefaultTariff, "");
    }

    public String getDataCsDefaultTariff() {
        return dataCsDefaultTariff;
    }

    public void setDataCsDefaultTariff(String dataCsDefaultTariff) {
        this.dataCsDefaultTariff = WhenNullValueThen.setStringOrInAbsence(dataCsDefaultTariff, "");
    }

    public String getVoiceDefaultTariff() {
        return voiceDefaultTariff;
    }

    public void setVoiceDefaultTariff(String voiceDefaultTariff) {
        this.voiceDefaultTariff = WhenNullValueThen.setStringOrInAbsence(voiceDefaultTariff, "");
    }

    public String getSmsDefaultTariff() {
        return smsDefaultTariff;
    }

    public void setSmsDefaultTariff(String smsDefaultTariff) {
        this.smsDefaultTariff = WhenNullValueThen.setStringOrInAbsence(smsDefaultTariff, "");
    }

    @Override
    public String toString() {
        return "ServiceDefaultTariff{" +
                "dataPsDefaultTariff='" + dataPsDefaultTariff + '\'' +
                ", dataCsDefaultTariff='" + dataCsDefaultTariff + '\'' +
                ", voiceDefaultTariff='" + voiceDefaultTariff + '\'' +
                ", smsDefaultTariff='" + smsDefaultTariff + '\'' +
                '}';
    }
}
