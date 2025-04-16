package demo.practice.multhreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// Create an ExecutorService with a single thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Define a task using the Callable interface
        Callable<Integer> task = () -> {
            int result = 0;
            for (int i = 0; i < 5; i++) {
                System.out.println("Running in thread: " + Thread.currentThread().getName() + " - " + i);
                result += i;
                try {
                    Thread.sleep(1000); // Simulate some work by sleeping for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return result; // Return the sum of the numbers
        };

        // Submit the task to the executor and get a Future object
        Future<Integer> future = executorService.submit(task);

        try {
            // Get the result of the Callable task (blocking call until the task completes)
            Integer result = future.get(); 
            System.out.println("Task completed with result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Shut down the executor service
        executorService.shutdown();
    }
}
