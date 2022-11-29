/**
 * @(#)aDormir2.java
 * @author A.T.
 * @version 1.00 2011/11/25
 */

public class aDormir2 extends Thread
{
	Object lock;
    public aDormir2(Object l) {lock=l;}
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
    {
    	Object cerrojo = new Object();
    	aDormir2 [] h = new aDormir2[10];
        for(int i=0; i<10;i++)
        {h[i]=new aDormir2(cerrojo); h[i].start();}
        h[5].despertar();
        h[5].despertarTodos();
		System.out.print("Todos terminaron...");

    }
}