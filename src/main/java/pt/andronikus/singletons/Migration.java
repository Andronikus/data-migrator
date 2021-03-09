package pt.andronikus.singletons;

public enum Migration {
   INSTANCE;

    private Status migStatus = Status.STOPPED;

    public synchronized Migration.Status getStatus(){
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
