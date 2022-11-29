/**
 * @(#)protocolo_sincronizacion_semaphore.java
 *
 *
 * @author
 * @version 1.00 2011/5/1
 */


import java.util.concurrent.*;

class HiloReceptor extends Thread
{
	Semaphore sem;
	public HiloReceptor(Semaphore s){sem = s;}
	public void run()
	{
		System.out.println("Hilo Receptor esta esperando la senal");
		try{sem.acquire();} catch (InterruptedException e) {}
		System.out.println("Hilo Receptor ha recibido la senal");
	}
}


class HiloSenalador extends Thread
{
	Semaphore sem;
	public HiloSenalador(Semaphore s){sem = s;}
	public void run()
	{
		sem.release();
		System.out.println("Hilo Senalador enviando senal...");
	}
}

public class protocolo_sincronizacion_semaphore {

    public static void main(String[] args)
    {
      int v_inic_sem = 0;
      Semaphore s = new Semaphore(v_inic_sem);

      new HiloSenalador (s).start();
new HiloReceptor(s).start();
    }
}
