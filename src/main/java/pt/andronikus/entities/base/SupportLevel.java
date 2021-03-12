package pt.andronikus.entities.base;

public class SupportLevel {
    private final String supportLevel;
    private final float supportLevelCost;

    public SupportLevel(String supportLevel, Float supportLevelCost) {
        this.supportLevel = supportLevel;
        this.supportLevelCost = supportLevelCost;
    }

    public String getSupportLevel() {
        return supportLevel;
    }

    public Float getSupportLevelCost() {
        return supportLevelCost;
    }

    @Override
    public String toString() {
        return "SupportLevel{" +
                "supportLevel='" + supportLevel + '\'' +
                ", supportLevelCost=" + supportLevelCost +
                '}';
    }
}
