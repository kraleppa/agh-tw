package pl.kraleppa.lab4.task_1;

public class Main {
    public static void main(String[] args) {
        int producersNumber = 3;
        int processorsNumber = 5;
        int consumersNumber = 1;

        Buffer buffer = new Buffer(10, 5);

        //Producers
        for (int i = 0; i < producersNumber; i++){
            new Producer(buffer).start();
        }

        //Processors
        for (int i = 1; i < processorsNumber + 1; i++){
            new Processor(buffer, i).start();
        }

        //Consumers
        for (int i = 0; i < consumersNumber; i++){
            new Consumer(buffer).start();
        }

    }
}
