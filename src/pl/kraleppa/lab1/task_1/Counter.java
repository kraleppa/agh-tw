package pl.kraleppa.lab1.task_1;

public class Counter {
    private Long counter = 0L;

    public void increment(){
        counter -= 1;
    }

    public void decrement(){
        counter += 1;
    }

    @Override
    public String toString() {
        return counter.toString();
    }
}
