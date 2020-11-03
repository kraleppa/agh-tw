package pl.kraleppa.lab4.task_1;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final int[] tape;
    private final int bufferSize;
    private final int readyToConsume;
    private final Lock lock = new ReentrantLock();
    private final Condition waitForAdd = lock.newCondition();
    private final Condition[] waitForProcess;
    private final Condition waitForConsume = lock.newCondition();

    public Buffer(int bufferSize, int readyToConsume) {
        this.bufferSize = bufferSize;
        this.readyToConsume = readyToConsume;
        this.waitForProcess = new Condition[readyToConsume];
        this.tape = new int[bufferSize];
        for (int i = 0; i < bufferSize; i++){
            tape[i] = -1;
        }

        for (int i = 0; i < readyToConsume; i++){
            waitForProcess[i] = lock.newCondition();
        }
    }

    private int searchFor(int number) {
        for (int i = 0; i < bufferSize; i++){
            if (tape[i] == number){
                return i;
            }
        }
        return -1;
    }

    public void produce() throws InterruptedException {
        lock.lock();
        int index;
        while(true){
            index = searchFor(-1);
            if (index == -1){
                waitForAdd.await();
            } else {
                break;
            }
        }
        tape[index] = 0;
        System.out.println("Wyprodukowano " + Arrays.toString(tape));
        waitForProcess[0].signalAll();
        lock.unlock();
    }

    public void process(int processorId) throws InterruptedException {
        lock.lock();
        int index;
        while(true){
            index = searchFor(processorId - 1);
            if (index == -1){
                waitForProcess[processorId - 1].await();
            } else {
                break;
            }
        }
        tape[index] = processorId;
        System.out.println("Przetworzono " + processorId + " " + Arrays.toString(tape));
        if (processorId == readyToConsume){
            waitForConsume.signalAll();
        } else {
            waitForProcess[processorId].signalAll();
        }
        lock.unlock();
    }

    public void consume() throws InterruptedException {
        lock.lock();
        int index;
        while(true){
            index = searchFor(readyToConsume);
            if (index == -1){
                waitForConsume.await();
            } else {
                break;
            }
        }
        tape[index] = -1;
        System.out.println("Skonsumowano " + Arrays.toString(tape));
        waitForAdd.signalAll();
        lock.unlock();
    }

    public int[] getTape() {
        return tape;
    }
}
