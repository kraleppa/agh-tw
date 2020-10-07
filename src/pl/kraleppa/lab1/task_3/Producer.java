package pl.kraleppa.lab1.task_3;

public class Producer extends Worker implements Runnable {

    public Producer(Buffer buffer, Long quantity) {
        super(buffer, quantity);
    }

    public void run() {

        for(int i = 0; i < quantity; i++) {
            buffer.put("message "+ i + " " + Thread.currentThread().getId());
        }

    }
}
