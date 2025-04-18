package demo.miscellaneous;

/*
How does a Java thread die?
When a thread has done all the operations it was programmed to do, it dies. And after that happens, you will not be able to start it again. 
To demonstrate this, I wrote this code, which is not necessary for you to analyze, but may be helpful to understand the topic
*/

public class HowJavaThreadDies {

	public static void main(String[] args) throws Exception {
		Thread stopTestThread = new Thread(() -> {
			System.out.println("Hello from " + Thread.currentThread().getName());
			
			for (int i = 0; i < 3; i++) {
				System.out.println("I'm running " + (i + 1));
				// this method tells the current thread (which is stopTestThread in our
				// case, as the code is written within Runnable run() method) to pause
				// for 1 sec (1000 milliseconds)
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			System.out.println("Now I'm going to stop");
		}, "StopTestThread");

		stopTestThread.start();
		stopTestThread.join();

		System.out.println();
		System.out.println("Hello from " + Thread.currentThread().getName());
		System.out.println("Trying to invoke " + stopTestThread.getName());

		// We'll get IllegalThreadStateException, because a thread can't be started for the second time
		stopTestThread.start();
	}

}
