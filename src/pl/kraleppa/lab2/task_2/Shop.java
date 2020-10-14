package pl.kraleppa.lab2.task_2;

public class Shop {
    private final Semaphore semaphore;

    public Shop(int basketNumber) {
        this.semaphore = new Semaphore(basketNumber);
    }

    public void enterShop(Client client) throws InterruptedException {
        semaphore.acquire();
        System.out.println("Client " + client.getClientId() + " enters the store!\n");
    }

    public void exitShop(Client client) {
        System.out.println("Client " + client.getClientId() + " leaves the store!");
        semaphore.release();
    }
}
