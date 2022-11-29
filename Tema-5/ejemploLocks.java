import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class ejemploLocks implements Runnable{
	private static ReentrantLock lock = new ReentrantLock();
	public static int n;
	
	public ejemploLocks(){}

	public void run() {
		for(int i=0; i<1000000; i++){
		  lock.lock();
		  try {n++;}finally {lock.unlock();} //asegura que se libera el lock.
	    }
    }

	public static void main(String[] args) throws Exception{
		Thread h1 = new Thread(new ejemploLocks());
        Thread h2 = new Thread(new ejemploLocks());
        h1.start(); h2.start();
        h1.join(); h2.join();
        System.out.println("El numero total es "+n);
        
	}
}