package pl.kraleppa.lab1.task_1;

public class Counter {
    private int counter = 0;

    public void increment(){
        counter -= 1;
    }

    public void decrement(){
        counter += 1;
    }

    @Override
    public String toString() {
        return Integer.toString(counter);
    }
}
