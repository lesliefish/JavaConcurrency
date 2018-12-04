package interthreadcommunication;

public class Thread1 implements Runnable {

    Chat chat;
    String[] arrString = {"hi\n", "how are you today?\n", "i am also doing fine~\n"};

    public Thread1(Chat chat) {
        this.chat = chat;
        new Thread(this, "Question").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < arrString.length; i++) {
            chat.question(arrString[i]);
        }
    }
}
