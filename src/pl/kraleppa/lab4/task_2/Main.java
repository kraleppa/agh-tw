package pl.kraleppa.lab4.task_2;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new JustBuffer(100000);
        int producersNumber = 1000;
        int consumersNumber = 1000;


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
