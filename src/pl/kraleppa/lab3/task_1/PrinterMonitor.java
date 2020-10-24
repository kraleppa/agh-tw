package pl.kraleppa.lab3.task_1;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMonitor {
    private final SortedSet<Integer> freePrinters = new TreeSet<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notBusy = lock.newCondition();

    public PrinterMonitor(int printerNumber) {
        for (int i = 0; i < printerNumber; i++){
            freePrinters.add(i);
        }
    }

    public int acquire(){
        int result = -1;
        lock.lock();
        try {
            while (freePrinters.size() == 0){
                notBusy.await();
            }
            result = freePrinters.first();
            freePrinters.remove(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return result;
    }

    public void release(int printerId){
        lock.lock();
        freePrinters.add(printerId);
        notBusy.signalAll();
        lock.unlock();
    }
}
