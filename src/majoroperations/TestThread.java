package majoroperations;

public class TestThread {
    public static void test() {
        RunnableDemo r1 = new RunnableDemo("Thread 1");
        r1.start();

        RunnableDemo r2 = new RunnableDemo("Thread 2");
        r2.start();

        try {
            Thread.sleep(1000);
            r1.suspend();
            System.out.println("Suspending First Thread");
            Thread.sleep(1000);
            r1.resume();
            System.out.println("Resuming First Thread");

            r2.suspend();
            System.out.println("Suspending thread Two");
            Thread.sleep(1000);
            r2.resume();
            System.out.println("Resuming thread Two");
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        try {
            System.out.println("Waiting for threads to finish.");
            r1.thread.join();
            r2.thread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
    }
}
