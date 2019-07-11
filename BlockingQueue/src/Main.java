import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue 队列的使用实践
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

        Producer producer = new Producer(queue);
        Customer customer = new Customer(queue);

        new Thread(producer).start();
        new Thread(customer).start();

        Thread.sleep(4000);
    }

    static class Producer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            Random random = new Random();
            try {
                int result = random.nextInt(100);
                Thread.sleep(1000);
                queue.put(result);
                System.out.println("Added: " + result);

                result = random.nextInt(100);
                Thread.sleep(1000);
                queue.put(result);
                System.out.println("Added: " + result);

                result = random.nextInt(100);
                Thread.sleep(1000);
                queue.put(result);
                System.out.println("Added: " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Customer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Customer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println("Removed: " + queue.take());
                System.out.println("Removed: " + queue.take());
                System.out.println("Removed: " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
