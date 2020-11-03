package pl.kraleppa.lab4.task_2;

public interface Buffer {
    void add(int size) throws InterruptedException;
    void get(int size) throws InterruptedException;
    int getBufferSize();
}
