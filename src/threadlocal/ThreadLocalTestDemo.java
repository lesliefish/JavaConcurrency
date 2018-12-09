package threadlocal;

public class ThreadLocalTestDemo {
    public static void test() {
        RunableDemo runableDemo = new RunableDemo();

        new Thread(runableDemo).start();
        new Thread(runableDemo).start();
        new Thread(runableDemo).start();
        new Thread(runableDemo).start();
    }
}
