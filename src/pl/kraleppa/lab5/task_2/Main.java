package pl.kraleppa.lab5.task_2;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Mandelbrot mandelbrot = new Mandelbrot();
        mandelbrot.start();
        mandelbrot.setVisible(true);

    }
}
