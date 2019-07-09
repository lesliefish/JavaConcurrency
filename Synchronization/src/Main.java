class Printer {
    public void printCount() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Counter ----- " + i);
            }
        } catch (Exception ex) {
            System.out.println("thread interrupted");
        }
    }
}

class CounterThread extends Thread {
    private Thread t;
    private String threadName;
    Printer printer;

    public CounterThread(String threadName, Printer printer) {
        this.threadName = threadName;
        this.printer = printer;
    }

    public void run() {
        synchronized (printer) {
            printer.printCount();
        }

        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Printer printer = new Printer();

        CounterThread thread1 = new CounterThread("thread 1", printer);
        CounterThread thread2 = new CounterThread("thread 2", printer);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            System.out.println("Interrupted.");
        }

    }
}
