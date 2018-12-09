package threadlocal;

public class ThreadLocalTestDemo {
    public static void test() {
        RunnableDemo runnableDemo = new RunnableDemo();

        new Thread(runnableDemo).start();
        new Thread(runnableDemo).start();
        new Thread(runnableDemo).start();
        new Thread(runnableDemo).start();
    }
}
