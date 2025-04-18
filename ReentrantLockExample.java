package demo.miscellaneous;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
	// Shared resource accessed by multiple threads
    private static int sharedResource = 0;

    // ReentrantLock for thread synchronization
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
      
        // Creating two threads to 
        // increment the shared resource
        Thread t1 = new Thread(new IncrementTask());
        Thread t2 = new Thread(new IncrementTask());

        // Start both threads
        t1.start();
        t2.start();

        try {
          
            // Wait for both threads to complete
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        // Print final value of shared resource
        System.out.println("Final value of sharedResource: " 
                           + sharedResource);
    }

    // Task to increment the shared resource
    static class IncrementTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
              
                // Acquire the lock
                lock.lock(); 
                try {
                    sharedResource++;
                } finally {
                  
                    // Release the lock
                    lock.unlock(); 
                }
            }
        }
    }
}