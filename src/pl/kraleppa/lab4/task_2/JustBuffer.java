package pl.kraleppa.lab4.task_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JustBuffer implements Buffer {
    private final int bufferSize;
    private int currentSize = 0;
    private final Lock lock = new ReentrantLock(true);
    private final Condition waitForBecomeFirstConsumer = lock.newCondition();
    private final Condition waitFirstToConsume = lock.newCondition();

    private final Condition waitForBecomeFirstProducer = lock.newCondition();
    private final Condition waitFirstToProduce = lock.newCondition();
    DataHolder dataHolder = new DataHolder();

    private int waitingProducers = 0;
    private int waitingConsumers = 0;
    private boolean isFirstProducerFree = true;
    private boolean isFirstConsumerFree = true;

    public JustBuffer(int m) {
        this.bufferSize = m * 2;
        dataHolder.start();
    }

    public void add(int size) throws InterruptedException {
        lock.lock();

        dataHolder.startTime(size);
        while (!isFirstProducerFree){
            waitingProducers++;
            waitForBecomeFirstProducer.await();
            waitingProducers--;
        }

        System.out.println("Waiting to add " + size);


        isFirstProducerFree = false;
        while (currentSize + size > bufferSize){
            System.out.println("Waiting to add " + size);
            waitFirstToProduce.await();
        }

        currentSize += size;
        isFirstProducerFree = true;
        waitFirstToConsume.signal();
        waitForBecomeFirstProducer.signal();

        dataHolder.stopTime();

        System.out.println("Added: " + size + " Current size: " + currentSize + " Waiting producers: "
                + waitingProducers + " Waiting consumers: " + waitingConsumers);

        lock.unlock();
    }

    public void get(int size) throws InterruptedException {
        lock.lock();

        dataHolder.startTime(size);
        while (!isFirstConsumerFree){
            waitingConsumers++;
            waitForBecomeFirstConsumer.await();
            waitingConsumers--;
        }

        isFirstConsumerFree = false;
        while(currentSize < size){
            System.out.println("Waiting to consume " + size);
            waitFirstToConsume.await();
        }


        currentSize -= size;
        isFirstConsumerFree = true;
        waitFirstToProduce.signal();
        waitForBecomeFirstConsumer.signal();

        dataHolder.stopTime();

        System.out.println("Consumed: " + size + " Current size: " + currentSize + " Waiting producers: "
                + waitingProducers+ " Waiting consumers: " + waitingConsumers);

        lock.unlock();
    }

    public int getBufferSize() {
        return bufferSize;
    }
}
