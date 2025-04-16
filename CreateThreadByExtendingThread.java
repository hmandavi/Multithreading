package demo.practice.multhreading;

public class CreateThreadByExtendingThread extends Thread {

	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " : " +i);
		}
	}

	public static void main(String[] args) {

		CreateThreadByExtendingThread t = new CreateThreadByExtendingThread();
		t.start();

	}
}
