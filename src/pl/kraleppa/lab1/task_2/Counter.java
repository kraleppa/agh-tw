package pl.kraleppa.lab1.task_2;

public class Counter {
    private Long counter = 0L;

    public synchronized void increment(){
        counter -= 1;
    }

    public synchronized void decrement(){
        counter += 1;
    }

    @Override
    public String toString() {
        return counter.toString();
    }
}
