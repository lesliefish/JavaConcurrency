import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * 原子数组变量
 */
public class Main {
    private static String[] source = new String[10];
    private static AtomicReferenceArray<String> atomicReferenceArray = new AtomicReferenceArray<String>(source);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < atomicReferenceArray.length(); i++) {
            atomicReferenceArray.set(i, "item-2");
        }

        Thread t1 = new Thread(new Increment());
        Thread t2 = new Thread(new Compare());
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    static class Increment implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < atomicReferenceArray.length(); i++) {
                String add = atomicReferenceArray.getAndSet(i, "item-" + (i + 1));
                System.out.println("Thread " + Thread.currentThread().getId() + ", index " + i + ", value: " + add);
            }
        }
    }

    static class Compare implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < atomicReferenceArray.length(); i++) {
                System.out.println("Thread " + Thread.currentThread().getId() + ", index " + i + ", value: " + atomicReferenceArray.get(i));
                boolean swapped = atomicReferenceArray.compareAndSet(i, "item-2", "updated-item-2");
                System.out.println("Item swapped: " + swapped);

                if (swapped) {
                    System.out.println("Thread " + Thread.currentThread().getId() + ", index " + i + ", updated-item-2");
                }
            }
        }
    }
}
