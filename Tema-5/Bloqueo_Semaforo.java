/*
 *@(#)Bloqueo_Semaforo.java
 * @author Antonio Tomeu
 * @version 1.00 2011/5/1
 */

import java.util.concurrent.*;

public class Bloqueo_Semaforo {

    public static void main(String[] args)
    	throws InterruptedException
    {
    	Semaphore sem = new Semaphore (2);
    	sem.acquire(2);
    	System.out.println("Semaforo actualizado a valor 0...");
    	System.out.println("y su estado es: "+sem.toString());
    	System.out.println("Ahora intentamos adquirirlo...");
    	sem.tryAcquire();
    	System.out.println("sin bloqueo por no conseguirlo");
    	System.out.println("Ahora intentamos adquirirlo...");
    	sem.tryAcquire(3L, TimeUnit.SECONDS);
    	System.out.println("tras esperar lo indicado sin consguirlo...");
    	sem.acquire();
    	System.out.println("Aquí no llegaremos nunca...");

    }
}
