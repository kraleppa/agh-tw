package pl.kraleppa.lab2.task_1;

public class Counter {
    private Long counter = 0L;
    private final Semaphore semaphore = new Semaphore();

    public void increment() throws InterruptedException {
        semaphore.acquire();
        counter -= 1;
        semaphore.release();
    }

    public void decrement() throws InterruptedException {
        semaphore.acquire();
        counter += 1;
        semaphore.release();
    }

    @Override
    public String toString() {
        return counter.toString();
    }
}
