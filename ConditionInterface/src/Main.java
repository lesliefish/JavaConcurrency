import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用锁以及条件变量测试生产者消费者处理流程
 */
public class Main {

    public static void main(String[] args) {
        ItemQueue itemQueue = new ItemQueue(3);

        // 创建生产者和消费者
        Thread producer = new Producer(itemQueue);
        Thread consumer = new Consumer(itemQueue);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * item队列
     */
    static class ItemQueue {
        private Object[] items = null;
        private int current = 0;
        private int placeIndex = 0;
        private int removeIndex = 0;

        private final Lock lock;
        private final Condition isEmpty;
        private final Condition isFull;

        public ItemQueue(int capacity) {
            this.items = new Object[capacity];
            lock = new ReentrantLock();
            isEmpty = lock.newCondition();
            isFull = lock.newCondition();
        }

        /**
         * 添加item
         *
         * @param item
         * @throws InterruptedException
         */
        public void add(Object item) throws InterruptedException {
            lock.lock();
            while (current >= items.length) {
                System.out.println("队列满了，等有空间再生产");
                isFull.await();
            }

            items[placeIndex] = item;
            placeIndex = (placeIndex + 1) % items.length;
            ++current;

            // 通知消费者有数据可用
            isEmpty.signal();
            System.out.println("有数据可以取了");

            lock.unlock();
        }

        /**
         * 从队列中删除元素
         *
         * @return
         * @throws InterruptedException
         */
        public Object remove() throws InterruptedException {
            Object item = null;
            lock.lock();
            while (current <= 0) {
                System.out.println("队列空了，等有数据了再取");
                isEmpty.await();
            }

            item = items[removeIndex];
            removeIndex = (removeIndex + 1) % items.length;
            --current;

            // 通知生产者有空间可用
            isFull.signal();
            System.out.println("有空闲空间可以生产了");

            lock.unlock();

            return item;
        }

        /**
         * 队列数据是否为空
         *
         * @return
         */
        public boolean isEmpty() {
            return (items.length == 0);
        }
    }

    /**
     * 生产者
     */
    static class Producer extends Thread {
        private final ItemQueue itemQueue;

        public Producer(ItemQueue q) {
            this.itemQueue = q;
        }

        @Override
        public void run() {
            String[] numbers = {"1", "2", "3", "4", "5"};

            try {
                for (String number : numbers) {
                    itemQueue.add(number);
                    System.out.println("[+++++生产者+++++],生产出" + number + "可供取出");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费者
     */
    static class Consumer extends Thread {
        private final ItemQueue itemQueue;

        public Consumer(ItemQueue q) {
            this.itemQueue = q;
        }

        @Override
        public void run() {
            try {
                do {
                    Object number = itemQueue.remove();
                    System.out.println("[-----消费者-----],已取出" + number);

                    if (number == null) {
                        return;
                    }
                } while (!itemQueue.isEmpty());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

/*
有数据可以取了
[+++++生产者+++++],生产出1可供取出
有空闲空间可以生产了
有数据可以取了
[+++++生产者+++++],生产出2可供取出
有数据可以取了
[+++++生产者+++++],生产出3可供取出
有数据可以取了
[+++++生产者+++++],生产出4可供取出
队列满了，等有空间再生产
[-----消费者-----],已取出1
有空闲空间可以生产了
[-----消费者-----],已取出2
有空闲空间可以生产了
[-----消费者-----],已取出3
有空闲空间可以生产了
[-----消费者-----],已取出4
队列空了，等有数据了再取
有数据可以取了
[+++++生产者+++++],生产出5可供取出
有空闲空间可以生产了
[-----消费者-----],已取出5
队列空了，等有数据了再取
 */