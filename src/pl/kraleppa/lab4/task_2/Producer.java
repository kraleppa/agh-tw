package pl.kraleppa.lab4.task_2;

import pl.kraleppa.lab4.task_2.Buffer;

import java.util.Random;

public class Producer extends Thread{
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                buffer.add(new Random().nextInt(buffer.getBufferSize() / 2) + 1);
                sleep(new Random().nextInt(500) + 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
