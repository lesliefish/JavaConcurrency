import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        System.out.println("Random Integer: " + new Random().nextInt());
        System.out.println("Seeded Random Integer: " + new Random(15).nextInt());
        System.out.println("Thread Local Random Integer: " + ThreadLocalRandom.current().nextInt());

        final ThreadLocalRandom random = ThreadLocalRandom.current();
        //random.setSeed(15); //exception will come as seeding is not allowed in ThreadLocalRandom.
        System.out.println("Seeded Thread Local Random Integer: " + random.nextInt());
        System.out.println("Seeded Thread Local Random Double: " + random.nextDouble());
    }
}

/*
Random Integer: -609208681
Seeded Random Integer: -1159716814
Thread Local Random Integer: -743385146
Seeded Thread Local Random Integer: 1619674173
Seeded Thread Local Random Double: 0.07727874195400386
*/