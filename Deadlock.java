package demo.practice.multhreading;

class Deadlock {

    // Shared objects to simulate deadlock scenario
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        
        // Task 1: Print even numbers
        Runnable task1 = () -> {
            for (int i = 0; i < 20; i++) {
                if (i % 2 == 0) {
                    synchronized (lock1) {  // Hold lock1
                        System.out.println("Even Thread : " + i);
                        try {
                            Thread.sleep(50);  // Simulate some work
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                        synchronized (lock2) {  // Try to acquire lock2 (causing a potential deadlock)
                            System.out.println("Even Thread trying to acquire lock2");
                        }
                    }
                }
            }
        };

        // Task 2: Print odd numbers
        Runnable task2 = () -> {
            for (int i = 1; i < 20; i++) {
                if (i % 2 != 0) {
                    synchronized (lock2) {  // Hold lock2
                        System.out.println("Odd Thread : " + i);
                        try {
                            Thread.sleep(50);  // Simulate some work
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                        synchronized (lock1) {  // Try to acquire lock1 (causing a potential deadlock)
                            System.out.println("Odd Thread trying to acquire lock1");
                        }
                    }
                }
            }
        };

        // Create threads for each task
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        // Start threads
        t1.start();
        t2.start();

        // Join threads to ensure the main thread waits for both threads to finish
        t1.join();
        t2.join();
    }
}
