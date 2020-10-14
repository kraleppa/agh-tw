package pl.kraleppa.lab2.task_2;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop(2);

        for (int i = 0; i < 10; i++){
            new Client(i, shop).start();
            sleep(new Random().nextInt(1000) + 500);
        }
    }
}
