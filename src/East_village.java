/**
 * East_village.java
 *
 * This class represents East villagers thread.
 * East and West villagers alternate when using the road(resource) to go to each other's village.
 *
 */
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class East_village implements Runnable{

    private void doAction( int villagerNumb, String villagerAction) throws InterruptedException {// Villagers to perform an action
        System.out.println("East villager #" + villagerNumb + " " + villagerAction);             // prints villager's number and action
        Thread.sleep(((int) (Math.random() * 1000) + 1000));                                     // waits 1 to 2 seconds
    }
    private Lock road = null;                                                                    // road is the resource
    private int villagerNumb;                                                                    // to track the village number

    public East_village(Lock road, int villagerNumb){                                            // East_village constructor
        this.road = road;
        this.villagerNumb = villagerNumb;
    }


    @Override
    public void run() {
        String[] villagerAction = {"is running to get there faster",                             // villagers array with things to do
                "is drinking water", "is tired and having a nap"};
        Random random = new Random();                                                            // random number to select things to do
        int randomIndex = random.nextInt(villagerAction.length);                                 // index of villagerAction
        try {
            doAction(villagerNumb, "is preparing to go to West Village");            // east villager is preparing to depart
            synchronized (road) {                                                                // the road is locked, so no other villager can use it
                doAction(villagerNumb, "is on his way to West Village");             // east villager is using the road
                doAction(villagerNumb, villagerAction[randomIndex]);                             // east villager is doing something in middle of the road
                try {
                    Thread.sleep(((int) (Math.random() * 4000) + 2000));                         // east villager spends 4 to 6 seconds doing something
                } catch (InterruptedException e) {
                }
                doAction(villagerNumb, "is back on his way to West Village");        // east villager finished the thing he was doing
                doAction(villagerNumb, "arrived to West Village. " +                 // east villager arrived to West Village, so other villager can use the road
                        "Now the road is available.");
                System.out.println();
            }
        }
        catch (InterruptedException e){                                                          // in case there is an error, to catch the exception
            Thread.currentThread().interrupt();
            return;
        }
    }
}
