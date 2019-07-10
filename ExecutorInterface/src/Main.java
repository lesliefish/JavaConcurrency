import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(new Task());
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) executor;
        poolExecutor.shutdown();
        System.out.println("Hello World!");
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Long duration = (long) (Math.random() * 5);
                System.out.println("Running task!");
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("Task Completed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
