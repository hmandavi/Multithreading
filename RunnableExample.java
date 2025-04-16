package demo.practice.multhreading;

public class RunnableExample {

	public static void main(String[] args) {
		// Using lambda expression to create a Runnable
		Runnable myRunnable = () -> {
			for (int i = 0; i < 5; i++) {
				System.out.println("Running in thread: " + Thread.currentThread().getName() + " - " + i);
				try {
					Thread.sleep(1000); // Simulate work by sleeping for 1 second
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		// Create a new thread using the Runnable
		Thread thread = new Thread(myRunnable);

		// Start the thread
		thread.start();

	}
}
