package pt.andronikus.singletons;

import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.constants.Global;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum AppConfiguration {
    INSTANCE;

    private final Map<String,Object> appCfg;

    AppConfiguration() {
        this.appCfg = new HashMap<>();
    }

    public void setAppCfg(InvokatorConfiguration cfg) {
        int partition = Objects.isNull(cfg.getOracleDB().getPartition()) ? 1 : cfg.getOracleDB().getPartition();
        this.appCfg.putIfAbsent(Global.TABLE_PARTITION, partition);
    }

    public Object getConfiguration(String confName){
        return this.appCfg.getOrDefault(confName,"");
    }

    @Override
    public String toString() {
        return "AppConfiguration{" +
                "appCfg=" + appCfg +
                '}';
    }
}
