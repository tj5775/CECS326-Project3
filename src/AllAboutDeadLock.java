/**
 * Albert Toscano and Dorothy Nguyen
 * November 6, 2021
 * CECS 326 - Operating Systems
 * Project 3: All about deadlock
 *
 * This program represents two villages, East and West village, where the road(resource) between them
 * can only be used by one villager at a time. Once a villager from either village uses that road,
 * no other villager can have access to it. The road will be again available, until the villager who was
 * using it arrives to his destination.
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AllAboutDeadLock {
    public static void main(String[] args){
        Lock road = new ReentrantLock();                            // road to represent the resource
        for(int index = 1; index < 6; index++) {                    // for loop to create 5 threads
            Runnable East_village = new East_village(road, index);  // instantiating an East_village object
            Runnable West_village = new West_village(road, index);  // instantiating a West_village object

            Thread thread1 = new Thread(East_village);              // assigning East_village as a thread
            Thread thread2 = new Thread(West_village);              // assigning West_village as a thread

            thread1.start();                                        // starting East_village thread
            thread2.start();                                        // starting West_village thread
        }
    }
}

