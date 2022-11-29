/**
 * @(#)regCritica.java
 * @author A.T.
 * @version 1.00 2012/11/11
 * Solucion al control de la e.m. con regiones critica
 * Satisface todas las condiciones de correccion.
 */

public class regCritica
  extends Thread
{
    private	static int n = 0; //RECURSO COMPARTIDO...
    private        int tipoHilo;
    private static int nVuel = 10000;
    private static Object cerrojo = new Object();

    public regCritica(int t)
	{this.tipoHilo = t;}

	public void run()
	{
	  	switch(tipoHilo){
          case 1:{for(int i=0; i<nVuel; i++){
          	        synchronized(cerrojo){n++;} //region critica...
                 }
        	    break;}
          case 2: {for(int j=0; j<nVuel; j++){
          	        synchronized(cerrojo){n--;} //region critica...
        	     }
        	    break;}
      }
	}

    public static void main(String[] args)
      throws InterruptedException
    {
        regCritica h1 = new regCritica(1);
        regCritica h2 = new regCritica(2);
        h1.start(); h2.start();
        h1.join();  h2.join();
        System.out.println(n);
    }
}
