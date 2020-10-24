package pl.kraleppa.lab3.task_2;

import java.util.HashSet;
import java.util.Set;

public class Log {
    public static Waiter waiter = null;
    public static Set<Integer> waitingForTable = new HashSet<>();

    public static void log(String string){
        System.out.println(string + "\t\t\t" + "\u001B[33m Waiting for pair: " + waiter.getWaitingForPair() + "\u001B[0m"
                + "\t\t\t" + "\u001B[31m Waiting for table: " + waitingForTable + "\u001B[0m");
    }

}
