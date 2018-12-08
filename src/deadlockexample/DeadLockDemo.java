package deadlockexample;

public class DeadLockDemo {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void test() {

        new TestThread1().start();
        new TestThread2().start();
    }


    private static class TestThread1 extends Thread {
        @Override
        public void run() {

            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock 1...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1: Waiting for lock 2...");

                synchronized (lock2) {
                    System.out.println("Thread 1: Holding lock 1&2...");
                }
            }
        }
    }

    private static class TestThread2 extends Thread {
        @Override
        public void run() {

            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock 2...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2: Waiting for lock 1...");

                synchronized (lock1) {
                    System.out.println("Thread 2: Holding lock 1&2...");
                }
            }
        }
    }
}
