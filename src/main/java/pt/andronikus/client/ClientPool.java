package pt.andronikus.client;

import pt.andronikus.configuration.ASMServerConfiguration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public enum ClientPool {
    INSTANCE;

    private final Map<Integer, ClientConfiguration> clientPool = new HashMap<>();
    private int nbrClients = 0;

    public void createPool(List<ASMServerConfiguration> asmServerConfigurations){
        System.out.println("Create ASM Pool");

        for(ASMServerConfiguration server: asmServerConfigurations){
            this.clientPool.put(++this.nbrClients,new ClientConfiguration(server));
        }
    }

    public void destroyPool(){
        System.out.println("Destroy ASM Pool");
        for(ClientConfiguration clientConfiguration : this.clientPool.values()){
            clientConfiguration.getClient().close();
        }
    }

    public WebTarget getWebTarget(){
        if(this.nbrClients == 1){
            return this.clientPool.get(1).getTarget();
        }

        int serverNumber = (int) Math.floor(Math.random() * this.nbrClients + 1);

        return this.clientPool.get(serverNumber).getTarget();
    }
}
