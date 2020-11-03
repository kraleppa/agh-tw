package pl.kraleppa.lab4.task_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataHolder extends Thread{
    private final Map<Long, Long> startTimeMap = new HashMap<>();
    private final List<Long> waitingTimeList = new ArrayList<>();

    public synchronized void startTime(Long threadId){
        startTimeMap.put(threadId, System.nanoTime());
    }

    public synchronized void stopTime(Long threadId){
        waitingTimeList.add(System.nanoTime() - startTimeMap.get(threadId));
        startTimeMap.remove(threadId);
    }

    private synchronized Map<Long, Long> getStartTimeMap() {
        return startTimeMap;
    }

    private synchronized double getAverage(){
        Long sum = 0L;
        waitingTimeList.addAll(startTimeMap.values().stream().map(value -> System.nanoTime() - value).collect(Collectors.toList()));
        for (Long number : waitingTimeList){
            sum += number;
        }

        return (double) sum / waitingTimeList.size();
    }


    @Override
    public void run() {
        super.run();
        try {
            sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getAverage());
        System.exit(0);
    }
}
