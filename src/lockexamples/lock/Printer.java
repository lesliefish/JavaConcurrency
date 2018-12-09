package lockexamples.lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    private final Lock queueLock = new ReentrantLock();

    public void print() {
        queueLock.lock();

        try {
            int duration = ThreadLocalRandom.current().nextInt(1, 5);
            System.out.println(Thread.currentThread().getName() + " Time Taken " + duration + " seconds.");
            Thread.sleep(duration * 1000);

        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {

            System.out.printf("%s printed the document successfully. \n", Thread.currentThread().getName());
            queueLock.unlock();
        }
    }
}
