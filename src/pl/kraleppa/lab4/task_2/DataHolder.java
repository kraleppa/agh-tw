package pl.kraleppa.lab4.task_2;

import pl.kraleppa.lab4.task_2.model.Operation;
import pl.kraleppa.lab4.task_2.model.Portion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHolder extends Thread{
    private final Map<Long, Operation> startTimeMap = new HashMap<>();
    private final List<Operation> waitingTimeList = new ArrayList<>();
    private final Map<Long, Portion> finalMap = new HashMap<>();

    public synchronized void startTime(long size){
        startTimeMap.put(Thread.currentThread().getId(), new Operation(size));
    }

    public synchronized void stopTime(){
        Operation operation = startTimeMap.get(Thread.currentThread().getId());
        operation.exitTime = System.nanoTime();
        waitingTimeList.add(operation);
        startTimeMap.remove(Thread.currentThread().getId());
    }

    private synchronized void fillPortions(){
        for (Operation operation : waitingTimeList){
            if (finalMap.containsKey(operation.size)){
                finalMap.get(operation.size).timeList.add(operation.exitTime - operation.enterTime);
            } else {
                finalMap.put(operation.size, new Portion(operation.exitTime - operation.enterTime, operation.size));
            }
        }

        for (Portion portion : finalMap.values()){
            portion.countAverage();
        }

    }


    @Override
    public void run() {
        super.run();
        try {
            sleep(20000);
            this.fillPortions();
            FileWriter myWriter = new FileWriter("data.txt");
            finalMap.values().forEach(value -> {
                try {
                    myWriter.write(value.sizeOfPortion + ";" + value.average.toString().replace('.', ',') + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
