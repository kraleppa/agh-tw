package pl.kraleppa.lab5.task_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class TaskScheduler {
    private final int numberOfTasks;
    private final int width;
    private final int height;
    private final List<Task> taskList;
    private final ExecutorService executorService;

    public TaskScheduler(int numberOfTasks, int width, int height) {
        this.numberOfTasks = numberOfTasks;
        this.width = width;
        this.height = height;
        this.taskList = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(numberOfTasks);
        for (int i = 0; i < numberOfTasks; i++){
            taskList.add(new Task());
        }
    }

    public List<Future<List<ProcessedPoint>>> start(){
        splitPoints();
        return taskList.stream()
                .map(executorService::submit)
                .collect(Collectors.toList());
    }

    private void splitPoints(){
        int i = 0;
        List<Point> points = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                taskList.get(i % numberOfTasks).addPoint(new Point(x, y));
                i++;
            }
        }
    }
}
