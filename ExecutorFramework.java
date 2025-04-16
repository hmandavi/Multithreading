package demo.practice.multhreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFramework {

	public static void main(String[] args) {
		// Create a fixed thread pool with 2 threads
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		// Submit tasks to the executor
		executorService.submit(() -> {
			for (int i = 0; i < 5; i++) {
				System.out.println("Running in thread: " + Thread.currentThread().getName() + " - " + i);
				try {
					Thread.sleep(1000); // Simulate work by sleeping for 1 second
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		executorService.submit(() -> {
			for (int i = 0; i < 5; i++) {
				System.out.println("Running in thread: " + Thread.currentThread().getName() + " - " + i);
				try {
					Thread.sleep(1000); // Simulate work by sleeping for 1 second
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// Shut down the executor after tasks are completed
		executorService.shutdown();
	}
}
