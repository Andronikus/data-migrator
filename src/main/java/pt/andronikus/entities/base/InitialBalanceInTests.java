package pt.andronikus.entities.base;

public class InitialBalanceInTests {
    private Double dataPsInitialBalance;
    private Double dataCsInitialBalance;
    private Double voiceInitialBalance;
    private Integer smsInitialBalance;

    public InitialBalanceInTests() {
    }

    public InitialBalanceInTests(Double dataPsInitialBalance, Double dataCsInitialBalance, Double voiceInitialBalance, Integer smsInitialBalance) {
        this.dataPsInitialBalance = dataPsInitialBalance;
        this.dataCsInitialBalance = dataCsInitialBalance;
        this.voiceInitialBalance = voiceInitialBalance;
        this.smsInitialBalance = smsInitialBalance;
    }

    public Double getDataPsInitialBalance() {
        return dataPsInitialBalance;
    }

    public void setDataPsInitialBalance(Double dataPsInitialBalance) {
        this.dataPsInitialBalance = dataPsInitialBalance;
    }

    public Double getDataCsInitialBalance() {
        return dataCsInitialBalance;
    }

    public void setDataCsInitialBalance(Double dataCsInitialBalance) {
        this.dataCsInitialBalance = dataCsInitialBalance;
    }

    public Double getVoiceInitialBalance() {
        return voiceInitialBalance;
    }

    public void setVoiceInitialBalance(Double voiceInitialBalance) {
        this.voiceInitialBalance = voiceInitialBalance;
    }

    public Integer getSmsInitialBalance() {
        return smsInitialBalance;
    }

    public void setSmsInitialBalance(Integer smsInitialBalance) {
        this.smsInitialBalance = smsInitialBalance;
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
