package demo.practice.multhreading;

class UsingSynchronized {

	// Shared object to control synchronization
	private static final Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {

		// Task 1: Print even numbers
		Runnable task1 = () -> {
			for (int i = 0; i < 20; i++) {
				if (i % 2 == 0) {
					synchronized (lock) {
						System.out.println("Even Thread : " + i);
						lock.notify(); // Notify the odd thread to proceed
						try {
							if (i < 18) { // If not last even number, wait for odd thread to finish
								lock.wait();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};

		// Task 2: Print odd numbers
		Runnable task2 = () -> {
			for (int i = 1; i < 20; i++) {
				if (i % 2 != 0) {
					synchronized (lock) {
						System.out.println("Odd Thread : " + i);
						lock.notify(); // Notify the even thread to proceed
						try {
							if (i < 19) { // If not last odd number, wait for even thread to finish
								lock.wait();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
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
		// t1.join();
		// t2.join();
	}
}
