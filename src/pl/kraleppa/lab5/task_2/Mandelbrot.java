package pl.kraleppa.lab5.task_2;

import pl.kraleppa.lab5.task_1.MandelbrotTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Mandelbrot extends JFrame {
    private BufferedImage image;
    private final List<MandelbrotTile> tiles = new ArrayList<>();
    private final TaskScheduler taskScheduler;

    public Mandelbrot(int numberOfTasks, int numberOfThreads) {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        taskScheduler = new TaskScheduler(numberOfTasks, numberOfThreads, getWidth(), getHeight());
    }

    public Mandelbrot(int numberOfThreads){
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        taskScheduler = new TaskScheduler(getWidth() * getHeight(), numberOfThreads, getWidth(), getHeight());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public void start() throws ExecutionException, InterruptedException {
        long start = System.nanoTime();
        List<Future<List<ProcessedPoint>>> list = taskScheduler.start();
        for (Future<List<ProcessedPoint>> future : list){
            List<ProcessedPoint> processedPoints = future.get();
            processedPoints
                    .forEach(point -> image.setRGB(point.x(), point.y(), point.value() | (point.value() << 8)));
        }
        long time = System.nanoTime() - start;

        System.out.println((double) time / 1_000_000_000);
    }
}
