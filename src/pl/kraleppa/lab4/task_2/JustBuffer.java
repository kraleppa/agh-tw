package pl.kraleppa.lab4.task_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JustBuffer implements Buffer {
    private final int bufferSize;
    private int currentSize = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition waitForSmallConsume = lock.newCondition();
    private final Condition waitForSmallProduce = lock.newCondition();

    private final Condition waitForBigConsume = lock.newCondition();
    private final Condition waitForBigProduce = lock.newCondition();
    DataHolder dataHolder = new DataHolder();

    private int waitingSmallProducers = 0;
    private int waitingBigProducers = 0;

    private int waitingSmallConsumers = 0;
    private int waitingBigConsumers = 0;

    public JustBuffer(int m) {
        this.bufferSize = m * 2;
        dataHolder.start();
    }

    public void add(int size) throws InterruptedException {
        lock.lock();
        dataHolder.startTime(Thread.currentThread().getId());
        if (size > bufferSize / 4) {
            waitingBigProducers++;
        } else {
            waitingSmallProducers++;
        }
        while (currentSize + size > bufferSize){
            if (size > bufferSize / 4) {
                waitForBigProduce.await();
            } else {
                waitForSmallProduce.await();
            }
        }

        dataHolder.stopTime(Thread.currentThread().getId());

        if (size > bufferSize / 4) {
            waitingBigProducers--;
        } else {
            waitingSmallProducers--;
        }

        currentSize += size;
        if (currentSize > bufferSize / 2 && waitingBigConsumers != 0) {
            waitForBigConsume.signalAll();
        } else {
            waitForSmallConsume.signalAll();
        }

        System.out.println("Added: " + size + " Current size: " + currentSize + " Waiting producers: "
                + (waitingSmallProducers + waitingBigProducers) + " Waiting consumers: " + (waitingSmallConsumers + waitingBigConsumers));

        lock.unlock();
    }

    public void get(int size) throws InterruptedException {
        lock.lock();
        dataHolder.startTime(Thread.currentThread().getId());
        if (size > bufferSize / 4){
            waitingBigConsumers++;
        } else {
            waitingSmallConsumers++;
        }
        while (currentSize < size){
            if (size > bufferSize / 4){
                waitForBigConsume.await();
            } else {
                waitForSmallConsume.await();
            }
        }
        dataHolder.stopTime(Thread.currentThread().getId());

        if (size > bufferSize / 4){
            waitingBigConsumers--;
        } else {
            waitingSmallConsumers--;
        }
        currentSize -= size;

        if (currentSize < bufferSize / 2 && waitingBigProducers != 0){
            waitForBigProduce.signalAll();
        } else {
            waitForSmallProduce.signalAll();
        }

        System.out.println("Added: " + size + " Current size: " + currentSize + " Waiting producers: "
                + (waitingSmallProducers + waitingBigProducers) + " Waiting consumers: " + (waitingSmallConsumers + waitingBigConsumers));


        lock.unlock();
    }

    public int getBufferSize() {
        return bufferSize;
    }
}
