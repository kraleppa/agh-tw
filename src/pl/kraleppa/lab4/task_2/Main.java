package pl.kraleppa.lab4.task_2;

import pl.kraleppa.lab4.task_1.Processor;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new JustBuffer(5);
        int producersNumber = 2;
        int consumersNumber = 2;

        //Producers
        for (int i = 0; i < producersNumber; i++){
            new Producer(buffer).start();
        }

        //Consumers
        for (int i = 0; i < consumersNumber; i++){
            new Consumer(buffer).start();
        }
    }
}
