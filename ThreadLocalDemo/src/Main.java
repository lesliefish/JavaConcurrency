/**
 * 线程本地数据 保证此数据对于线程内部是独立的，线程专属
 */

public class Main {

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);
        Thread t3 = new Thread(myRunnable);
        Thread t4 = new Thread(myRunnable);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException ex) {
            System.out.println("interrupted.");
        }
    }
}

class MyRunnable implements Runnable {
    private int counter;
    ThreadLocal<Integer> threadLocalInteger = new ThreadLocal<Integer>();

    @Override
    public void run() {
        counter++;
        if (threadLocalInteger.get() != null) {
            System.out.println("not null value");
            threadLocalInteger.set(threadLocalInteger.get().intValue() + 1);
        } else {
            System.out.println("null value");
            threadLocalInteger.set(0);
        }

        System.out.println("Counter: " + counter);
        System.out.println("threadLocalCounter: " + threadLocalInteger.get());
    }
}