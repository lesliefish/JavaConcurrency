package interthreadcommunication;

// 内部线程通信
public class InterThreadCommunicationDemo {

    public static void test() {

        System.out.println("内部线程通信----start");

        Chat chat = new Chat();
        new Thread1(chat);
        new Thread2(chat);

        System.out.println("内部线程通信----end");
    }
}
