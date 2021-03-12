package pt.andronikus.entities.base;

public class SupportLevel {
    private String supportLevel;
    private float supportLevelCost;

    public SupportLevel() {
    }

    public SupportLevel(String supportLevel, Float supportLevelCost) {
        this.supportLevel = supportLevel;
        this.supportLevelCost = supportLevelCost;
    }

    public String getSupportLevel() {
        return supportLevel;
    }

    public void setSupportLevel(String supportLevel) {
        this.supportLevel = supportLevel;
    }

    public float getSupportLevelCost() {
        return supportLevelCost;
    }

    public void setSupportLevelCost(float supportLevelCost) {
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
