import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的使用例子
 */
public class Main {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static String message = "a";

    public static void main(String[] args) {
        Thread t1 = new Thread(new WriterA());
        t1.setName("Writer A");
        Thread t2 = new Thread(new WriterB());
        t2.setName("Writer B");
        Thread t3 = new Thread(new Reader());
        t3.setName("Reader");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 读操作
     */
    static class Reader implements Runnable {
        @Override
        public void run() {
            if (lock.isWriteLocked()) {
                System.out.println("Write Lock Present.");
            }
            lock.readLock().lock(); // 等待写操作完成才去读
            try {
                Long duration = (long) (Math.random() * 10000);
                System.out.println(Thread.currentThread().getName() + " Time Taken " + (duration / 1000) + " seconds.");
                Thread.sleep(duration);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + ": " + message);
                lock.readLock().unlock();
            }
        }
    }


    /**
     *  写操作A
     */
    static class WriterA implements Runnable {
        @Override
        public void run() {
            lock.writeLock().lock();
            try {
                Long duration = (long) (Math.random() * 10000);
                System.out.println(Thread.currentThread().getName() + " Time Taken " + (duration / 1000) + " seconds.");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                message = message.concat("a");
                lock.writeLock().unlock();
            }
        }
    }

    /**
     *  写操作B
     */
    static class WriterB implements Runnable {
        @Override
        public void run() {
            lock.writeLock().lock();
            try {
                Long duration = (long) (Math.random() * 10000);
                System.out.println(Thread.currentThread().getName() + " Time Taken " + (duration / 1000) + " seconds.");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                message = message.concat("b");
                lock.writeLock().unlock();
            }
        }

    }
}

/*
Write Lock Present.
Writer A Time Taken 2 seconds.
Writer B Time Taken 1 seconds.
Reader Time Taken 2 seconds.
Reader: aab

Process finished with exit code 0
*/