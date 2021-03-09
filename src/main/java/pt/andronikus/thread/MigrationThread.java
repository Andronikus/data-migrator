package pt.andronikus.thread;

import pt.andronikus.singletons.Migration;

public class MigrationThread implements Runnable{
    @Override
    public void run() {
        while(Migration.INSTANCE.getStatus().equals(Migration.Status.RUNNING)){

            try {
                System.out.println("I am Running migration");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
