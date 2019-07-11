import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);//2个线程的线程池

        ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;

        // 线程执行前状态
        System.out.println("Largest executions: " + pool.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + pool.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + pool.getPoolSize());
        System.out.println("Currently executing threads: " + pool.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + pool.getTaskCount());

        executor.submit(new Task());
        executor.submit(new Task());
        executor.submit(new Task());

        //线程执行后状态
        System.out.println("Core threads: " + pool.getCorePoolSize());
        System.out.println("Largest executions: " + pool.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + pool.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + pool.getPoolSize());
        System.out.println("Currently executing threads: " + pool.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + pool.getTaskCount());

        executor.shutdown();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Long duration = (long) (Math.random() * 5);
                System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
                System.out.println("sleep time : " + duration + " seconds.");
                TimeUnit.SECONDS.sleep(duration);

                System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
Largest executions: 0
Maximum allowed threads: 2
Current threads in pool: 0
Currently executing threads: 0
Total number of threads(ever scheduled): 0
Core threads: 2
Largest executions: 2
Maximum allowed threads: 2
Current threads in pool: 2
Currently executing threads: 2
Total number of threads(ever scheduled): 2
Running Task! Thread Name: pool-1-thread-1
sleep time : 3 seconds.
Running Task! Thread Name: pool-1-thread-2
sleep time : 2 seconds.
Task Completed! Thread Name: pool-1-thread-2
Task Completed! Thread Name: pool-1-thread-1

 */