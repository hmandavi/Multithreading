package demo.practice.multhreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

	public static void main(String[] args) {

		// Start the first asynchronous task
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
			// Simulate a task that returns a result (e.g., a calculation)
			System.out.println("Performing first task...");
			try {
				Thread.sleep(2000); // Simulate work by sleeping for 2 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 5; // Task result
		});

		// Chain another task that depends on the result of the first task
		CompletableFuture<Integer> resultFuture = future.thenApply(result -> {
			System.out.println("Performing second task with result of first task: " + result);
			return result * 2; // Multiply result by 2
		});

		// Chain a third task that will run after the second task completes
		CompletableFuture<Integer> finalResult = resultFuture.thenApply(result -> {
			System.out.println("Performing third task with result of second task: " + result);
			return result + 3; // Add 3 to the result
		});

		// Handle exception using exceptionally()
		CompletableFuture<Integer> handledFuture = future.exceptionally(ex -> {
			System.out.println("Caught exception in exceptionally(): " + ex.getMessage());
			return -1; // Return a fallback value in case of an error
		});

		// Now, let's use handle() to both manage result and exception in one place
		CompletableFuture<Integer> finalResultWithHandle = resultFuture.handle((result, ex) -> {
			if (ex != null) {
				System.out.println("Exception occurred in handle(): " + ex.getMessage());
				return 0; // Return fallback value if there's an exception
			}
			System.out.println("Result processed successfully in handle(): " + result);
			return result; // Return result if no exception occurred
		});

		// Block and wait for the final result
		try {
			Integer finalValue = finalResultWithHandle.get(); // Wait for completion and retrieve the result
			System.out.println("Final result: " + finalValue); // Should print 13 (5 * 2 + 3)
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
