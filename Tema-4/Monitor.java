/**
 * @(#)Monitor.java 
 * @author 
 * @version 1.00 2009/12/1
 */

class Monitor
{
	private static int Dato; //recurso protegido
	public Monitor(int VInic){Dato=VInic;}

	public synchronized void INC()
	{
		while(!(Dato<=0))
			try{System.out.println("Hilo Sumador bloqueado");
			     wait();
			   } catch (InterruptedException e){}
		Dato++;
		notifyAll();
	}

    public synchronized void DEC()
	{
		while(!(Dato>0))
		  try{System.out.println("Hilo Restador bloqueado");
			wait();
		     } catch (InterruptedException e){}
		Dato--;
		notifyAll();
	}
	
	public synchronized String toString()
	{return(new Integer(Dato).toString());}
}