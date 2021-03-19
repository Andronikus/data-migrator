package pt.andronikus.service;

import pt.andronikus.dto.MigrationStatusResponse;
import pt.andronikus.singletons.Migration;
import pt.andronikus.thread.BillingAccountThread;
import pt.andronikus.thread.CustomerThread;
import pt.andronikus.thread.ResourceThread;
import pt.andronikus.thread.ServiceInstanceThread;

public class MigrationManageService {
    public MigrationStatusResponse operateMigration(String command){
        String message;

        if(command.toLowerCase().equals("start")){
            message = startMigration();
        }else {
            message = stopMigration();
        }
        return new MigrationStatusResponse(message);
    }

    public MigrationStatusResponse migrationStatus(){
        String message = Migration.INSTANCE.getStatus().equals(Migration.Status.STOPPED) ? "Migration is Stopped" : "Migration is Running";
        return new MigrationStatusResponse(message);
    }

    private String startMigration(){
        if(Migration.INSTANCE.getStatus().equals(Migration.Status.RUNNING)){
            return "Migration already running";
        }

        Migration.INSTANCE.start();
        Thread customerThread = new Thread(new CustomerThread());
        Thread billingAccountThread = new Thread(new BillingAccountThread());
        // Thread serviceInstanceThread = new Thread(new ServiceInstanceThread());
        // Thread resourceThread = new Thread(new ResourceThread());

        customerThread.start();
        billingAccountThread.start();
        // serviceInstanceThread.start();
        // resourceThread.start();

        return "Migration threads started";
    }

    private String stopMigration(){
        if(Migration.INSTANCE.getStatus().equals(Migration.Status.STOPPED)){
            return "Migration already stopped";
        }

        Migration.INSTANCE.stop();

        return "Send signal to stop migration threads...";
    }
}
