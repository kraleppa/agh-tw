package pl.kraleppa.lab3.task_2;

import java.util.Random;

public class Person extends Thread{
    private final int pairId;
    private final String personId;
    private final int lateTime;
    private final Waiter waiter;

    public Person(int pairId, String personId, int lateTime, Waiter waiter) {
        this.pairId = pairId;
        this.personId = personId;
        this.lateTime = lateTime;
        this.waiter = waiter;
    }

    @Override
    public void run() {
        super.run();
        try {
            sleep(lateTime);
            Log.log("Person " + personId + " appears!");
            waiter.acquireTable(pairId);

            Log.log("Person " + personId + " is eating");
            sleep(new Random().nextInt(1000) + 200);


            Log.log("Person " + personId + " leaves!");
            waiter.releaseTable();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
