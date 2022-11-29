/**
 * @(#)aDormir3.java
 * @author A.T.
 * @version 1.00 2011/11/25
 */

public class aDormir3 extends Thread
{
	Object lock;
    public aDormir3(Object l) {lock=l;}
    public void run()
    {
    	System.out.println("El hilo "+this.getName()+" dijo: mi vida activa fue breve...");
    	synchronized(lock)
    	{
    		try{lock.wait();}catch (InterruptedException e){} //cada hilo dormido sobre su propio cerrojo
    		System.out.println(this.getName()+" dijo: pero he revivido...");
    	}
    }

   public void despertar(){synchronized (lock){lock.notify();}}
   public void despertarTodos(){synchronized (lock){lock.notifyAll();}}

    public static void main(String[] args)
      throws InterruptedException
    {
    	Object cerrojo = new Object();
    	aDormir3 [] h = new aDormir3[10];
        for(int i=0; i<10;i++)
        {h[i]=new aDormir3(cerrojo); h[i].start();}
        h[5].despertar();
        Thread el = currentThread();
        el.sleep(2000);
		h[5].despertarTodos();
		System.out.print("Todos terminaron...");

    }
}