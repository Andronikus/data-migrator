package pt.andronikus.entities.base;

public class SupportLevel {
    private String supportLevel;
    private double supportLevelCost;

    public SupportLevel() {
    }

    public SupportLevel(String supportLevel, double supportLevelCost) {
        this.supportLevel = supportLevel;
        this.supportLevelCost = supportLevelCost;
    }

    public String getSupportLevel() {
        return supportLevel;
    }

    public void setSupportLevel(String supportLevel) {
        this.supportLevel = supportLevel;
    }

    public double getSupportLevelCost() {
        return supportLevelCost;
    }

    public void setSupportLevelCost(double supportLevelCost) {
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
