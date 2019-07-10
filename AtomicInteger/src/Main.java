import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子变量之整型测试
 */
public class Main {

    public static void main(String[] args) {
        final Counter counter = new Counter();

        for (int i = 0; i < 100; ++i) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    counter.increment();
                    System.out.println("+ing:" + counter.value());
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("value is (should be 100) : " + counter.value());
    }

    static class Counter {
        private AtomicInteger i = new AtomicInteger(0);

        public void increment() {
            i.getAndIncrement();
        }

        public int value() {
            return i.get();
        }
    }
}
