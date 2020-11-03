package pl.kraleppa.lab4.task_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NaiveBuffer implements Buffer {
    private final int bufferSize;
    private int currentSize = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition waitForConsume = lock.newCondition();
    private final Condition waitForProduce = lock.newCondition();

    private int waitingProducers = 0;
    private int waitingConsumers = 0;

    public NaiveBuffer(int m) {
        this.bufferSize = m * 2;
    }

    public void add(int size) throws InterruptedException {
        lock.lock();
        while (currentSize + size > bufferSize){
            waitingProducers++;
            waitForProduce.await();
        }
        currentSize += size;
        waitForConsume.signalAll();

        System.out.println("Added: " + size + " Current size: " + currentSize + " Waiting producers: " + waitingProducers + " Waiting consumers: " + waitingConsumers);

        lock.unlock();
    }

    public void get(int size) throws InterruptedException {
        lock.lock();
        while (currentSize < size){
            waitingConsumers++;
            waitForConsume.await();
        }
        currentSize -= size;
        waitForProduce.signalAll();

        System.out.println("Removed: " + size + " Current size: " + currentSize + " Waiting producers: " + waitingProducers + " Waiting consumers: " + waitingConsumers);

        lock.unlock();
    }

    public int getBufferSize() {
        return bufferSize;
    }
}
