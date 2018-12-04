package interthreadcommunication;

public class Thread2 implements Runnable {

    Chat chat;
    String[] arrString = {"Hi\n", "I am good, what about you?\n", "Great!\n"};

    public Thread2(Chat chat) {
        this.chat = chat;
        new Thread(this, "Answer").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < arrString.length; i++) {
            chat.anser(arrString[i]);
        }
    }
}
