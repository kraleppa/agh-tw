package pl.kraleppa.lab3.task_1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        PrinterMonitor printerMonitor = new PrinterMonitor(10);
        Random random = new Random();

        for (int i = 0; i < 50; i ++){
            new PrintingTask(i, random.nextInt(1000), printerMonitor).start();
        }
    }
}
