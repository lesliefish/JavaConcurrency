/**
 * 死锁的例子
 */

public class Main {
    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        t1.start();
        t2.start();
    }

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread1 holding the lock");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
                System.out.println("Thread1 waiting for lock2");

                synchronized (Lock2) {
                    System.out.println("Thread1: Holding lock 1 & 2...");
                }
            }
        }
    }


    private static class Thread2 extends Thread {
        public void run() {
            synchronized (Lock2) {
                System.out.println("Thread2: Holding lock 2 ...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
                System.out.println("Thread2 waiting for lock 1");

                synchronized (Lock1) {
                    System.out.println("Thread2: holding lock 1 & 2");
                }
            }
        }
    }
}


