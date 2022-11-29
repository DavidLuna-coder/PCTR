public class Deadlock {
	public static void main(String[] args) {

		final Object region_A = new Object();
		final Object region_B = new Object();

		Thread Hilo_A = new Thread(new Runnable() {
			public void run() {
				synchronized (region_A) {
					synchronized (region_B) {
						System.out.println("hilo A");
					}
				}
			}
		});

		Thread Hilo_B = new Thread(new Runnable() {
			public void run() {
				synchronized (region_B) {
					synchronized (region_A) {
						System.out.println("hilo B");
					}
				}
			}
		});

		Hilo_B.start();
		Hilo_A.start();
	}
}
