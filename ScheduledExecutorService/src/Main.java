import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        /*
        initialDelay：初始化延时
        period：两次开始执行最小间隔时间
        unit：计时单位
         */
        final ScheduledFuture<?> taskHandler = scheduledExecutorService.scheduleAtFixedRate(new MyTask(), 2, 2, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                taskHandler.cancel(true);
                scheduledExecutorService.shutdown();
            }
        }, 10, TimeUnit.SECONDS);
    }

    static private class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println("test task");
        }
    }
}
