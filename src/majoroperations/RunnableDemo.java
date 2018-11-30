package majoroperations;

public class RunnableDemo implements Runnable {

    public Thread thread;
    private String threadName;
    boolean suspended = false;

    RunnableDemo(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " +  threadName );

        try{
            for (int i = 10; i > 0 ; i--) {
                System.out.println("Thread: " + threadName + ", " + i);

                // 歇歇
                Thread.sleep(300);

                synchronized(this) {
                    while(suspended) {
                        wait();
                    }
                }
            }
        }
        catch (InterruptedException e){
            System.out.println("Thread " + " interrupted.");
        }

        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);

        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    void suspend() {
        suspended = true;
    }

    synchronized void resume() {
        suspended = false;
        notify();
    }
}
