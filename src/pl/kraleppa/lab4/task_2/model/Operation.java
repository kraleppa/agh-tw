package pl.kraleppa.lab4.task_2.model;

public class Operation {
    public long size;
    public long enterTime;
    public long exitTime;

    public Operation(long size) {
        this.size = size;
        this.enterTime = System.nanoTime();
    }

}
