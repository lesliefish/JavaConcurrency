import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        try {
            executorService.execute(new Task());
            System.out.println("shutdown executor");
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS); // 5秒内阻塞，5秒内没执行完就不阻塞了
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!executorService.isTerminated()) {
                System.out.println("cancel non-finished tasks");
            }
            executorService.shutdownNow(); // shutdownNow 尝试停止所有正在执行的任务，停止等待任务的处理，并返回等待执行的任务列表
            System.out.println("shutdown finished");
        }
    }


    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Long duration = (long) (Math.random() * 20);
                System.out.println("Running task");
                System.out.println("sleep " + duration + " seconds.");
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("sleep over.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
shutdown executor
Running task
sleep 17 seconds.
cancel non-finished tasks
java.lang.InterruptedException: sleep interrupted
shutdown finished
	at java.lang.Thread.sleep(Native Method)
	at java.lang.Thread.sleep(Thread.java:340)
	at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
	at Main$Task.run(Main.java:34)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

Process finished with exit code 0
 */