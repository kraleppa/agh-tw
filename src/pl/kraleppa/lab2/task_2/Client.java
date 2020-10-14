package pl.kraleppa.lab2.task_2;

import java.util.Random;

public class Client extends Thread {
    private final int clientId;
    private final Shop shop;

    public Client(int clientId, Shop shop) {
        this.clientId = clientId;
        this.shop = shop;
    }

    @Override
    public void run() {
        super.run();
        try {
            shop.enterShop(this);
            sleep(new Random().nextInt(1000) + 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shop.exitShop(this);
    }

    public int getClientId() {
        return clientId;
    }
}
