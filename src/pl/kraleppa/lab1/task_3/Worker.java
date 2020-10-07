package pl.kraleppa.lab1.task_3;

public abstract class Worker {
    protected final Buffer buffer;
    protected final Long quantity;

    public Worker(Buffer buffer, Long quantity) {
        this.buffer = buffer;
        this.quantity = quantity;
    }
}
