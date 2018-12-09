package lockexamples.lock;

public class ThreadDemo extends Thread {
    Printer printer;

    ThreadDemo(String name, Printer printer) {
        super(name);
        this.printer = printer;
    }

    @Override
    public void run() {
        System.out.printf("%s starts printing a document\n", Thread.currentThread().getName());
        printer.print();
    }
}
