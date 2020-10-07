package pl.kraleppa.lab1.task_3;

public class Consumer extends Worker implements Runnable {

    public Consumer(Buffer buffer, Long quantity) {
        super(buffer, quantity);
    }

    public void run() {

        for(int i = 0; i < quantity; i++) {
            System.out.println(buffer.take() + " Consumer id: " + Thread.currentThread().getId());
        }

    }
}
