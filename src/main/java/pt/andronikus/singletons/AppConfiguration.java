package pt.andronikus.singletons;

import io.dropwizard.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum AppConfiguration {
    INSTANCE;

    private Map<String,Object> appCfg;

    AppConfiguration() {
        this.appCfg = new HashMap<>();
    }

    public Map<String, Object> getAppCfg() {
        return appCfg;
    }

    public void setAppCfg(Map<String, Object> appCfg) {
        this.appCfg = appCfg;
    }

    @Override
    public String toString() {
        return "AppConfiguration{" +
                "appCfg=" + appCfg +
                '}';
    }
}
