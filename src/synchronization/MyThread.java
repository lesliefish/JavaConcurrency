package synchronization;

public class MyThread extends Thread {
    private Thread thread;
    private String threadName;
    private PrintClass printClass;

    public MyThread(String name, PrintClass printClass) {
        this.threadName = name;
        this.printClass = printClass;
    }

    @Override
    public void start() {
        System.out.println("Starting " + threadName);

        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    @Override
    public void run() {
        synchronized (printClass) {
            printClass.print();
        }
        System.out.println("Thread " + threadName + " exiting.");
    }
}
