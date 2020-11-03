package pl.kraleppa.lab4.task_2.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Portion {
    public List<Long> timeList;
    public Double average;
    public Long sizeOfPortion;

    public Portion(Long time, long sizeOfPortion) {
        this.timeList = new ArrayList<>();
        this.timeList.add(time);
        this.sizeOfPortion = sizeOfPortion;
    }

    public void countAverage(){
        Long sum = 0L;
        for (Long time : timeList){
            sum += time;
        }
        average = (double) sum / timeList.size();
    }


}
