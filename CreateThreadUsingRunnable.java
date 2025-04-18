package demo.miscellaneous;

public class CreateThreadUsingRunnable {

	public static void main(String[] args) {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello from a new thread!");
			}
		});
		thread.start();
	}
}
