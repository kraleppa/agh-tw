package pl.kraleppa.lab4.task_1;

import java.util.Arrays;
import java.util.Random;

public class Processor extends Thread{
    private final Buffer buffer;
    private final int processorId;

    public Processor(Buffer buffer, int processorId) {
        this.buffer = buffer;
        this.processorId = processorId;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try {
                buffer.process(processorId);
                sleep(new Random().nextInt(500) + 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
