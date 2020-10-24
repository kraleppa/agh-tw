package pl.kraleppa.lab3.task_1;

public class PrintingTask extends Thread{
    private final int taskId;
    private final int printingTime;
    private final PrinterMonitor printerMonitor;


    public PrintingTask(int taskId, int printingTime, PrinterMonitor printerMonitor) {
        this.taskId = taskId;
        this.printingTime = printingTime;
        this.printerMonitor = printerMonitor;
    }

    @Override
    public void run() {
        super.run();
        try {
            int printerId = printerMonitor.acquire();
            System.out.println("Printer " + printerId + " is printing task " + taskId);
            sleep(printingTime);
            System.out.println("Task " + taskId + " released printer  " + printerId);
            printerMonitor.release(printerId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
