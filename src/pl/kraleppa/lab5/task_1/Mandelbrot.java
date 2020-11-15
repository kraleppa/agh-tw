package pl.kraleppa.lab5.task_1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import javax.swing.JFrame;

public class Mandelbrot extends JFrame {

    private final int MAX_ITER = 570;
    private final double ZOOM = 150;
    private final int xPosition = 400;
    private final int yPosition = 300;
    private BufferedImage I;
    private final List<MandelbrotTile> tiles = new ArrayList<>();
    private final ExecutorService executor;

    public Mandelbrot() throws ExecutionException, InterruptedException {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        executor = Executors.newFixedThreadPool(4);
        count();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    private void count() throws ExecutionException, InterruptedException {
        long start = System.nanoTime();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                Future<Integer> result = executor.submit(new MandelbrotTask((x - xPosition) / ZOOM, (y - yPosition) / ZOOM, MAX_ITER));
                tiles.add(new MandelbrotTile(x, y, result));
            }
        }
        for (MandelbrotTile tile : tiles){
            int res = tile.value().get();
            I.setRGB(tile.x(), tile.y(), res | (res << 8));
        }

        long time = System.nanoTime() - start;

        System.out.println((double) time / 1_000_000_000);
    }
}
