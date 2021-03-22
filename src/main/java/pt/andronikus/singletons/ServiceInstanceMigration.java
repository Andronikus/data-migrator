package pt.andronikus.singletons;

public enum ServiceInstanceMigration {
   INSTANCE;

    private Status migStatus = Status.STOPPED;

    public synchronized ServiceInstanceMigration.Status getStatus(){
        return migStatus;
    }

    public synchronized void stop(){
        migStatus = Status.STOPPED;
    }

    public synchronized void start(){
        migStatus = Status.RUNNING;
    }

    public enum Status {
        RUNNING,
        STOPPED
    }
}
