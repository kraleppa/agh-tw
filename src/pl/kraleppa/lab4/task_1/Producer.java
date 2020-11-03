package pl.kraleppa.lab4.task_1;

import java.util.Arrays;
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
                buffer.produce();
                sleep(new Random().nextInt(500) + 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
