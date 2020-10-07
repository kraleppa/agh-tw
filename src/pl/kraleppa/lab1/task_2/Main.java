package pl.kraleppa.lab1.task_2;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
	    Thread incrementer = new Thread(() -> {
	        for (long i = 1L; i < 100000000; i++){
	            counter.increment();
            }
        });

        Thread decrementer = new Thread(() -> {
            for (long i = 1L; i < 100000000; i++){
                counter.decrement();
            }
        });

        decrementer.start();
        incrementer.start();

        decrementer.join();
        incrementer.join();

        System.out.println(counter);
    }
}
