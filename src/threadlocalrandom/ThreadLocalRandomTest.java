package threadlocalrandom;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomTest {

    public static void test() {
        System.out.println("Random Integer: " + new Random().nextInt());
        System.out.println("Seeded Random Integer: " + new Random(15).nextInt());

        System.out.println(
                "Thread Local Random Integer: " + ThreadLocalRandom.current().nextInt(0, 1000));
        final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
//      threadLocalRandom.setSeed(15);  运行出错  不能设置
        System.out.println("Seeded Thread Local Random Integer: " + threadLocalRandom.nextInt());
    }

}
