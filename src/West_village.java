import java.util.Random;
import java.util.concurrent.locks.Lock;

public class West_village implements Runnable{

    private void doAction( int villagerNumb, String villagerAction) throws InterruptedException {          // Villagers to perform an action
        System.out.println("West villager #" + villagerNumb + " " + villagerAction);    // prints villager's number and action
        Thread.sleep(((int) (Math.random() * 1000) + 1000));                               // waits 1 to 2 seconds
    }
    private Lock road = null;
    private int villagerNumb;
    public West_village(Lock road, int villagerNumb){
        this.road = road;
        this.villagerNumb = villagerNumb;
    }

    @Override
    public void run() {
        String[] villagerAction = {"is eating an apple", "is doing exercise", "is crossing a river"};
        Random random = new Random();
        int randomIndex = random.nextInt(villagerAction.length);
        try {
            doAction(villagerNumb, "is preparing to go to East Village");
            synchronized (road) {
                doAction(villagerNumb, "is on his way to East Village");
                doAction(villagerNumb, villagerAction[randomIndex]);
                try {
                    Thread.sleep(((int) (Math.random() * 4000) + 2000));                               // waits 1 to 3 seconds
                } catch (InterruptedException e) {

                }
                doAction(villagerNumb, "is back on his way to East Village");
                doAction(villagerNumb, "arrived to East Village. Now the road is available.");
                System.out.println();
            }
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return;
        }
    }
}
