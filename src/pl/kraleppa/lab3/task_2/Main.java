package pl.kraleppa.lab3.task_2;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        Log.waiter = waiter;
        Random random = new Random();


        for (int i = 0; i < 10; i++){
            new Person(i, i + "a", random.nextInt(2000), waiter).start();
            new Person(i, i + "b", random.nextInt(2000), waiter).start();
        }
    }
}
