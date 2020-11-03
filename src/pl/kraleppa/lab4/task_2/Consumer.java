package pl.kraleppa.lab4.task_2;

import java.util.Random;

public class Consumer extends Thread{
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                buffer.get(new Random().nextInt(buffer.getBufferSize() / 2) + 1);
                sleep(new Random().nextInt(500) + 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
