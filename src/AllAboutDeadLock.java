import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AllAboutDeadLock {
    public static void main(String[] args){
        Lock road = new ReentrantLock();
        for(int index = 1; index < 6; index++) {
            Runnable East_village = new East_village(road, index);
            Runnable West_village = new West_village(road, index);

            Thread thread1 = new Thread(East_village);
            Thread thread2 = new Thread(West_village);

            thread1.start();
            thread2.start();
        }
    }
}

