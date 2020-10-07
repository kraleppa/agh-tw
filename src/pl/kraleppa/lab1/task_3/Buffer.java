package pl.kraleppa.lab1.task_3;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private final List<String> messageList = new ArrayList<>();;
    private final int maxListSize;
    private int currentSize;

    public Buffer(int maxListSize) {
        this.maxListSize = maxListSize;
        this.currentSize = 0;
    }

    public synchronized String take(){
        return "";
    }

    public synchronized void put(String message){

    }
}
