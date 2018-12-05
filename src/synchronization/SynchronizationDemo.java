package synchronization;

public class SynchronizationDemo {
    public static void test() {
        PrintClass printClass = new PrintClass();

        MyThread thread1 = new MyThread("Thread 1 --- ", printClass);
        MyThread thread2 = new MyThread("Thread 2 --- ", printClass);
        thread1.start();
        thread2.start();

        // wait for threads to end
        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}
