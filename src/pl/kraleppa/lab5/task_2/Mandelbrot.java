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

    public Mandelbrot() {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        taskScheduler = new TaskScheduler(4, getWidth(), getHeight());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public void start() throws ExecutionException, InterruptedException {
        List<Future<List<ProcessedPoint>>> list = taskScheduler.start();
        for (Future<List<ProcessedPoint>> future : list){
            List<ProcessedPoint> processedPoints = future.get();
            processedPoints
                    .forEach(point -> image.setRGB(point.x(), point.y(), point.value() | (point.value() << 8)));
        }
    }
}
