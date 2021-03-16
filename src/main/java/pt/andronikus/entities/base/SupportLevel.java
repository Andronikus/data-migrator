package pt.andronikus.entities.base;

public class SupportLevel {
    private String supportLevel;
    private String supportLevelCost;

    public SupportLevel() {
    }

    public SupportLevel(String supportLevel, String supportLevelCost) {
        this.supportLevel = supportLevel;
        this.supportLevelCost = supportLevelCost;
    }

    public String getSupportLevel() {
        return supportLevel;
    }

    public void setSupportLevel(String supportLevel) {
        this.supportLevel = supportLevel;
    }

    public String getSupportLevelCost() {
        return supportLevelCost;
    }

    public void setSupportLevelCost(String supportLevelCost) {
        this.supportLevelCost = supportLevelCost;
    }

    @Override
    public String toString() {
        return "SupportLevel{" +
                "supportLevel='" + supportLevel + '\'' +
                ", supportLevelCost=" + supportLevelCost +
                '}';
    }
}
