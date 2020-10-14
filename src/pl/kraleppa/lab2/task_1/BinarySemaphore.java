package pl.kraleppa.lab2.task_1;

public class BinarySemaphore {
    private boolean signal;

    public BinarySemaphore(boolean signal) {
        this.signal = signal;
    }

    public BinarySemaphore() {
        this(true);
    }

    public synchronized void acquire() throws InterruptedException {
        while (!signal){
            wait();
        }
        signal = false;
    }

    public synchronized void release(){
        signal = true;
        notifyAll();
    }
}
