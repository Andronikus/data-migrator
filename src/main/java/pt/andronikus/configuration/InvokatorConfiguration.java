package pt.andronikus.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class InvokatorConfiguration extends Configuration {
    @NotNull
    @Valid
    @JsonProperty("database")
    private OracleDB oracleDB = new OracleDB();

    @NotNull
    @Valid
    @JsonProperty("asmServers")
    private List<ASMServerConfiguration> asmServerConfigurations = new ArrayList<>();

    @NotNull
    @Valid
    @JsonProperty("callbackServer")
    private CallbackServerConfiguration callbackServerConfiguration;

    @NotNull
    @Valid
    @JsonProperty("migrationProcessInfo")
    private MigrationProcessInfo migrationProcessInfo;

    public OracleDB getOracleDB() {
        return oracleDB;
    }

    public void setOracleDB(OracleDB oracleDB) {
        this.oracleDB = oracleDB;
    }

    public List<ASMServerConfiguration> getAsmServers() {
        return asmServerConfigurations;
    }

    public void setAsmServers(List<ASMServerConfiguration> asmServerConfigurations) {
        this.asmServerConfigurations = asmServerConfigurations;
    }

    public CallbackServerConfiguration getCallbackServerConfiguration() {
        return callbackServerConfiguration;
    }

    public void setCallbackServerConfiguration(CallbackServerConfiguration callbackServerConfiguration) {
        this.callbackServerConfiguration = callbackServerConfiguration;
    }

    public MigrationProcessInfo getMigrationProcessInfo() {
        return migrationProcessInfo;
    }

    public void setMigrationProcessInfo(MigrationProcessInfo migrationProcessInfo) {
        this.migrationProcessInfo = migrationProcessInfo;
    }
}
