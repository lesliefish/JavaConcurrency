import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  锁的使用，保证多个线程访问同一数据时的访问顺序实例
 */
public class Main {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread t1 = new ThreadDemo("thread 1", printer);
        Thread t2 = new ThreadDemo("thread 2", printer);
        Thread t3 = new ThreadDemo("thread 3", printer);
        Thread t4 = new ThreadDemo("thread 4", printer);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class Printer {
    private final Lock queueLock = new ReentrantLock(); // 可重入锁

    public void print() {
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + " time taken " + (duration / 1000) + " seconds.");
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            System.out.printf("%s printed the document successfully.\n", Thread.currentThread().getName());
            queueLock.unlock();
        }
    }
}

class ThreadDemo extends Thread {
    Printer printer;

    ThreadDemo(String name, Printer p) {
        super(name);
        this.printer = p;
    }

    @Override
    public void run() {
        System.out.printf("%s starts printing a document\n", Thread.currentThread().getName());
        printer.print();
    }
}

/*
thread 1 starts printing a document
thread 3 starts printing a document
thread 4 starts printing a document
thread 2 starts printing a document
thread 1 time taken 3 seconds.
thread 1 printed the document successfully.
thread 3 time taken 7 seconds.
thread 3 printed the document successfully.
thread 4 time taken 6 seconds.
thread 4 printed the document successfully.
thread 2 time taken 3 seconds.
thread 2 printed the document successfully.

Process finished with exit code 0
*/