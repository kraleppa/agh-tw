package pl.kraleppa.lab5;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Mandelbrot().setVisible(true);
    }
}
