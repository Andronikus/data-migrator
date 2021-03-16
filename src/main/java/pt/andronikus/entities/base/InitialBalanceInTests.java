package pt.andronikus.entities.base;

import pt.andronikus.utils.WhenNullValueThen;

public class InitialBalanceInTests {
    private String dataPsInitialBalance;
    private String dataCsInitialBalance;
    private String voiceInitialBalance;
    private String smsInitialBalance;

    public InitialBalanceInTests() {
    }

    public InitialBalanceInTests(String dataPsInitialBalance, String dataCsInitialBalance, String voiceInitialBalance, String smsInitialBalance) {
        this.dataPsInitialBalance = dataPsInitialBalance;
        this.dataCsInitialBalance = dataCsInitialBalance;
        this.voiceInitialBalance = voiceInitialBalance;
        this.smsInitialBalance = smsInitialBalance;
    }

    public String getDataPsInitialBalance() {
        return dataPsInitialBalance;
    }

    public void setDataPsInitialBalance(String dataPsInitialBalance) {
        this.dataPsInitialBalance = WhenNullValueThen.setStringOrInAbsence(dataPsInitialBalance, "");
    }

    public String getDataCsInitialBalance() {
        return dataCsInitialBalance;
    }

    public void setDataCsInitialBalance(String dataCsInitialBalance) {
        this.dataCsInitialBalance = WhenNullValueThen.setStringOrInAbsence(dataCsInitialBalance, "");
    }

    public String getVoiceInitialBalance() {
        return voiceInitialBalance;
    }

    public void setVoiceInitialBalance(String voiceInitialBalance) {
        this.voiceInitialBalance = WhenNullValueThen.setStringOrInAbsence(voiceInitialBalance, "");
    }

    public String getSmsInitialBalance() {
        return smsInitialBalance;
    }

    public void setSmsInitialBalance(String smsInitialBalance) {
        this.smsInitialBalance = WhenNullValueThen.setStringOrInAbsence(smsInitialBalance, "");
    }

    @Override
    public String toString() {
        return "InitialBalanceInTests{" +
                "dataPsInitialBalance=" + dataPsInitialBalance +
                ", dataCsInitialBalance=" + dataCsInitialBalance +
                ", voiceInitialBalance=" + voiceInitialBalance +
                ", smsInitialBalance=" + smsInitialBalance +
                '}';
    }
}
