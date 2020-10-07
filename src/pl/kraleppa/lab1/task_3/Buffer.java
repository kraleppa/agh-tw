package pl.kraleppa.lab1.task_3;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private final List<String> messageList = new ArrayList<>();
    private final int maxListSize;
    private int currentSize;

    public Buffer(int maxListSize) {
        this.maxListSize = maxListSize;
        this.currentSize = 0;
    }

    public synchronized String take(){
        while(currentSize == 0){
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        notifyAll();
        return messageList.get(--currentSize);
    }

    public synchronized void put(String message) {
        while(currentSize == maxListSize){
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        messageList.add(currentSize, message);
        currentSize++;

        notifyAll();
    }
}
