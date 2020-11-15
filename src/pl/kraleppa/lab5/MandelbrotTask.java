package pl.kraleppa.lab5;

import java.util.concurrent.Callable;

public class MandelbrotTask implements Callable<Integer> {

    private final double cX;
    private final double cY;
    private final int MAX_ITER;

    public MandelbrotTask(double cX, double cY, int MAX_ITER) {
        this.cX = cX;
        this.cY = cY;
        this.MAX_ITER = MAX_ITER;
    }

    @Override
    public Integer call() {
        double zy;
        double zx = zy = 0;
        int iter = MAX_ITER;
        while (zx * zx + zy * zy < 4 && iter > 0) {
            double tmp = zx * zx - zy * zy + cX;
            zy = 2.0 * zx * zy + cY;
            zx = tmp;
            iter--;
        }
        return iter;
    }
}
