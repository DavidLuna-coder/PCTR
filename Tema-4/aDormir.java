/**
 * @(#)aDormir.java
 * @author A.T.
 * @version 1.00 2011/11/25
 */

public class aDormir extends Thread
{
    public aDormir() {}
    public void run()
    {
    	System.out.println("El hilo "+this.getName()+" dijo: mi vida activa fue breve...");
    	synchronized(this)
    	{
    		try{wait();}catch (InterruptedException e){} //cada hilo dormido sobre su propio cerrojo
    		System.out.println(this.getName()+" dijo: pero he revivido...");
    	}
    }

   public void despertar(){synchronized (this){notify();}}

    public static void main(String[] args)
      throws Exception
    {
    	aDormir [] h = new aDormir[10];
        for(int i=0; i<10;i++)
        {h[i]=new aDormir(); h[i].start();}
        try{Thread.currentThread().sleep(1000);
          }catch (InterruptedException e){}
        Thread.currentThread().sleep(2000); //retardo evita perdida de senial...  
        h[5].despertar();
    }
}
