package pl.kraleppa.lab2.task_1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread incrementer = new Thread(() -> {
            for (long i = 1L; i < 10000000; i++){
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread decrementer = new Thread(() -> {
            for (long i = 1L; i < 10000000; i++){
                try {
                    counter.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        decrementer.start();
        incrementer.start();

        decrementer.join();
        incrementer.join();

        System.out.println(counter);
    }
}
