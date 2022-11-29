/**
 * @(#)Tarea_concurrente.java
 * @author Antonio Tomeu
 * @version 1.00 2011/5/1
 */

import java.util.concurrent.*;

public class Tarea_concurrente
	extends Thread
{
    Semaphore s;
    public Tarea_concurrente(Semaphore param)
      {s = param;}

    public void run ()
    {
    	for(;;)
    	{
    		try {s.acquire();} catch (InterruptedException e) {}
    		System.out.println("Hilo "+this.getName()+" entrando a seccion critica");
    		s.release();
    		System.out.println("Hilo "+this.getName()+" saliendo de seccion critica");
    	}
    }


}