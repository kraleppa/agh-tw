package pl.kraleppa.lab3.task_2;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {
    private final Lock lock = new ReentrantLock();
    private final Map<Integer, Condition> waitingConditions = new HashMap<>();
    private final Set<Integer> waitingForPair = new HashSet<>();

    private int eatingPeopleNumber = 0;
    private int eatingPair = -1;
    private final Condition tableIsNotOccupied = lock.newCondition();


    public void acquireTable(int pairId) throws InterruptedException {
        lock.lock();
        if (!waitingForPair.contains(pairId)){
            waitingConditions.put(pairId, lock.newCondition());
            waitingForPair.add(pairId);
            waitingConditions.get(pairId).await();

        } else {
            waitingConditions.get(pairId).signal();
            waitingConditions.remove(pairId);
            waitingForPair.remove(pairId);
        }

        Log.waitingForTable.add(pairId);
        while(eatingPeopleNumber != 0 && eatingPair != pairId){
            tableIsNotOccupied.await();
        }
        Log.waitingForTable.remove(pairId);


        eatingPeopleNumber++;
        eatingPair = pairId;

        lock.unlock();
    }

    public void releaseTable(){
        lock.lock();
        eatingPeopleNumber--;
        if(eatingPeopleNumber == 0){
            tableIsNotOccupied.signalAll();
        }
        lock.unlock();
    }

    public Set<Integer> getWaitingForPair() {
        return waitingForPair;
    }

    public int getEatingPair() {
        return eatingPair;
    }
}
