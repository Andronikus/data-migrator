package pt.andronikus.service;

import pt.andronikus.dto.MigrationStatusResponse;
import pt.andronikus.singletons.*;
import pt.andronikus.thread.BillingAccountThread;
import pt.andronikus.thread.CustomerThread;
import pt.andronikus.thread.ResourceThread;
import pt.andronikus.thread.ServiceInstanceThread;

public class MigrationManageService {
    public MigrationStatusResponse operateMigration(String entity, String command){

        switch (entity.toLowerCase()){
            case "customer":
                if (command.toLowerCase().equals("start")) {
                    startCustomerMigration();
                } else {
                    stopCustomerMigration();
                }
                break;
            case "billingaccount":
                if (command.toLowerCase().equals("start")) {
                    startBillingAccountMigration();
                } else {
                    stopBillingAccountMigration();
                }
                break;
            case "serviceinstance":
                if (command.toLowerCase().equals("start")) {
                    startServiceInstanceMigration();
                } else {
                    stopServiceInstanceMigration();
                }
                break;
            case "resource":
                if (command.toLowerCase().equals("start")) {
                    startResourceMigration();
                } else {
                    stopResourceMigration();
                }
                break;
            case "all":
                if (command.toLowerCase().equals("start")) {
                    startAll();
                } else {
                    stopAll();
                }
            default:
        }

        return migrationStatus();
    }

    private void startCustomerMigration(){
        if(CustomerMigration.INSTANCE.getStatus().equals(CustomerMigration.Status.RUNNING)){
            return;
        }

        CustomerMigration.INSTANCE.start();
        Thread customerThread = new Thread(new CustomerThread());
        customerThread.start();
    }

    private void stopCustomerMigration(){
        if(CustomerMigration.INSTANCE.getStatus().equals(CustomerMigration.Status.STOPPED)){
            return;
        }
        CustomerMigration.INSTANCE.stop();
    }

    private void startBillingAccountMigration(){
        if(BillingAccountMigration.INSTANCE.getStatus().equals(BillingAccountMigration.Status.RUNNING)){
            return;
        }

        BillingAccountMigration.INSTANCE.start();
        Thread billingAccountThread = new Thread(new BillingAccountThread());
        billingAccountThread.start();
    }

    private void stopBillingAccountMigration(){
        if(BillingAccountMigration.INSTANCE.getStatus().equals(BillingAccountMigration.Status.STOPPED)){
            return;
        }
        BillingAccountMigration.INSTANCE.stop();
    }

    private void startServiceInstanceMigration(){
        if(ServiceInstanceMigration.INSTANCE.getStatus().equals(ServiceInstanceMigration.Status.RUNNING)){
            return;
        }

        ServiceInstanceMigration.INSTANCE.start();
        Thread serviceInstanceThread = new Thread(new ServiceInstanceThread());
        serviceInstanceThread.start();
    }

    private void stopServiceInstanceMigration(){
        if(ServiceInstanceMigration.INSTANCE.getStatus().equals(ServiceInstanceMigration.Status.STOPPED)){
            return;
        }
        ServiceInstanceMigration.INSTANCE.stop();
    }

    private void startResourceMigration(){
        if(ResourceMigration.INSTANCE.getStatus().equals(ResourceMigration.Status.RUNNING)){
            return;
        }

        ResourceMigration.INSTANCE.start();
        Thread resourceThread = new Thread(new ResourceThread());
        resourceThread.start();
    }

    private void stopResourceMigration(){
        if(ResourceMigration.INSTANCE.getStatus().equals(ResourceMigration.Status.STOPPED)){
            return;
        }
        ResourceMigration.INSTANCE.stop();
    }

    private void startAll(){
        startCustomerMigration();
        startBillingAccountMigration();
        startServiceInstanceMigration();
        startResourceMigration();
    }

    private void stopAll(){
        stopResourceMigration();
        stopServiceInstanceMigration();
        stopBillingAccountMigration();
        stopCustomerMigration();
    }


    public MigrationStatusResponse migrationStatus(){
        MigrationStatusResponse message = new MigrationStatusResponse();

        message.setCustomerMigStatus(CustomerMigration.INSTANCE.getStatus().equals(CustomerMigration.Status.STOPPED) ? "Migration is Stopped" : "Migration is Running");
        message.setBillingAccMigStatus(BillingAccountMigration.INSTANCE.getStatus().equals(BillingAccountMigration.Status.STOPPED) ? "Migration is Stopped" : "Migration is Running");
        message.setServiceInstanceMigStatus(ServiceInstanceMigration.INSTANCE.getStatus().equals(ServiceInstanceMigration.Status.STOPPED) ? "Migration is Stopped" : "Migration is Running");
        message.setResourceMigStatus(ResourceMigration.INSTANCE.getStatus().equals(ResourceMigration.Status.STOPPED) ? "Migration is Stopped" : "Migration is Running");

        return message;
    }

}
