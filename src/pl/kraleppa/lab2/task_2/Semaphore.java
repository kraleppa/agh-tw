package pl.kraleppa.lab2.task_2;

public class Semaphore {
    private int counter;

    public Semaphore(int counter) {
        if (counter < 0){
            throw new IllegalArgumentException("Semaphore counter cannot be less than 0!");
        }
        this.counter = counter;
    }

    public synchronized void acquire() throws InterruptedException {
        while (counter == 0){
            wait();
        }
        counter--;
    }

    public synchronized void release(){
        counter++;
        notifyAll();
    }
}
