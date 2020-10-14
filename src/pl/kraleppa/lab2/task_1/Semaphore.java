package pl.kraleppa.lab2.task_1;

public class Semaphore {
    private boolean signal;

    public Semaphore(boolean signal) {
        this.signal = signal;
    }

    public Semaphore() {
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
