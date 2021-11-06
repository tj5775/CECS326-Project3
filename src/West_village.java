/**
 * West_village.java
 *
 * This class represents West villagers thread.
 * East and West villagers alternate when using the road(resource) to go to each other's village.
 *
 */
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class West_village implements Runnable{

    private void doAction( int villagerNumb, String villagerAction) throws InterruptedException {// Villagers to perform an action
        System.out.println("West villager #" + villagerNumb + " " + villagerAction);             // prints villager's number and action
        Thread.sleep(((int) (Math.random() * 1000) + 1000));                                     // waits 1 to 2 seconds
    }
    private Lock road = null;                                                                    // road is the resource
    private int villagerNumb;                                                                    // to track the village number

    public West_village(Lock road, int villagerNumb){                                            // West_village constructor
        this.road = road;
        this.villagerNumb = villagerNumb;
    }

    @Override
    public void run() {
        String[] villagerAction = {"is eating an apple",                                         // villagers array with things to do
                "is doing exercise", "is crossing a river"};
        Random random = new Random();                                                            // random number to select things to do
        int randomIndex = random.nextInt(villagerAction.length);                                 // index of villagerAction
        try {
            doAction(villagerNumb, "is preparing to go to East Village");            // west villager is preparing to depart
            synchronized (road) {                                                                // the road is locked, so no other villager can use it
                doAction(villagerNumb, "is on his way to East Village");             // west villager is using the road
                doAction(villagerNumb, villagerAction[randomIndex]);                             // west villager is doing something in middle of the road
                try {
                    Thread.sleep(((int) (Math.random() * 4000) + 2000));                         // west villager spends 4 to 6 seconds doing something
                } catch (InterruptedException e) {

                }
                doAction(villagerNumb, "is back on his way to East Village");        // west villager finished the thing he was doing
                doAction(villagerNumb, "arrived to East Village. " +                 // west villager arrived to East Village, so other villager can usu the road
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
