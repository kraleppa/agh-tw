package pl.kraleppa.lab5.task_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Task implements Callable<List<ProcessedPoint>> {

    private final List<Point> pointsToProcess = new ArrayList<>();
    private final List<ProcessedPoint> processedPoints = new ArrayList<>();

    public void addPoint(Point point){
        pointsToProcess.add(point);
    }

    @Override
    public List<ProcessedPoint> call() {
        pointsToProcess.forEach(this::processPoint);
        return processedPoints;
    }

    private void processPoint(Point point){
        double cX = (point.x() - Settings.xPosition) / Settings.ZOOM;
        double cY = (point.y() - Settings.yPosition) / Settings.ZOOM;
        double zy = 0;
        double zx = 0;
        int iter = Settings.MAX_ITER;
        while (zx * zx + zy * zy < 4 && iter > 0) {
            double tmp = zx * zx - zy * zy + cX;
            zy = 2.0 * zx * zy + cY;
            zx = tmp;
            iter--;
        }

        processedPoints.add(new ProcessedPoint(point.x(), point.y(), iter));
    }
}
