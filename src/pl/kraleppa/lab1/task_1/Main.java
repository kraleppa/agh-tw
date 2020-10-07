package pl.kraleppa.lab1.task_1;

public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter();
	    Thread incrementer = new Thread(() -> {
	        for (int i = 1; i < 100000000; i++){
	            counter.increment();
            }
        });

        Thread decrementer = new Thread(() -> {
            for (int i = 1; i < 100000000; i++){
                counter.decrement();
            }
        });

        decrementer.start();
        incrementer.start();

        System.out.println(counter);
    }
}
