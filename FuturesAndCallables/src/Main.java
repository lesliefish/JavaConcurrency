import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Long> result5 = executorService.submit(new FactorialService(5));
        Future<Long> result6 = executorService.submit(new FactorialService(6));

        System.out.println("result5 get value is " + result5.get());
        System.out.println("result6 get value is " + result6.get());

        executorService.shutdown();
    }


    static class FactorialService implements Callable<Long> {

        private int number;

        public FactorialService(int number) {
            this.number = number;
        }

        @Override
        public Long call() throws Exception {
            return factorial();
        }

        private Long factorial() throws InterruptedException {
            long result = 1;

            while (number != 0) {
                result = number * result;
                number--;
                Thread.sleep(100);
            }

            return result;
        }
    }
}
