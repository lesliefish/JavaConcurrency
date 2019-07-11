import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {

    public static void main(String[] args) {
        int nThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("available processors size is " + nThreads);

        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;  // 1+2+3+....+100和
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool(nThreads);
        Long result = forkJoinPool.invoke(new SumTask(0, numbers.length, numbers));
        System.out.println("result is " + result);
    }

    static class SumTask extends RecursiveTask<Long> {
        private int low;
        private int high;
        private int[] array;

        public SumTask(int low, int high, int[] array) {
            this.low = low;
            this.high = high;
            this.array = array;

            System.out.println("low is " + low +", high is " + high);
        }

        @Override
        protected Long compute() {
            if (high - low <= 1) {
                long sum = 0;

                for (int i = low; i < high; ++i) {
                    sum += array[i];
                    return sum;
                }
            } else {
                int mid = low + (high - low) / 2;
                SumTask left = new SumTask(low, mid, array);
                SumTask right = new SumTask(mid, high, array);
                left.fork();

                long rightResult = right.compute();
                long leftResult = left.join();

                return leftResult + rightResult;
            }

            return null;
        }
    }
}
