package lockexamples.lock;

public class LockTestDemo {

    public static void test(){
        Printer printer = new Printer();

        new ThreadDemo("Thread 1", printer).start();
        new ThreadDemo("Thread 2", printer).start();
        new ThreadDemo("Thread 3", printer).start();
        new ThreadDemo("Thread 4", printer).start();
    }
}
