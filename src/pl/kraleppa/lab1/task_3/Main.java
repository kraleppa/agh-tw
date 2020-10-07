package pl.kraleppa.lab1.task_3;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(2);


        for (int i = 0; i < 4; i++){
            new Thread(new Producer(buffer, 2L)).start();
        }

        for (int i = 0; i < 1; i++){
            new Thread(new Consumer(buffer, 8L)).start();
        }
    }
}
