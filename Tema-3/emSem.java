/**
 * @(#)tryOne.java
 * @author  A.T.
 * @version 1.00 2012/11/9
 * Solucion al control de la e.m. con semaforos
 * Satisface todas las condiciones de correccion.
 */

import java.util.concurrent.*;
public class emSem
  extends Thread
{
	private int tipoHilo;
	private static int nVueltas = 1000000;
	private static int n = 0;
	private static Semaphore s = new Semaphore(1);


    public emSem(int tipoHilo)
    {this.tipoHilo=tipoHilo;}
    public void run()
    {
      switch(tipoHilo){
        case 1:{for(int i=0; i<nVueltas; i++){
        	      try{s.acquire();}catch(InterruptedException e){} //wait(S)
        	      n++;
        	      s.release();                                     //signal(S)
        	    }
        	    break;}
        case 2: {for(int i=0; i<nVueltas;i++){                     //wait(S)
        	      try{s.acquire();}catch(InterruptedException e){}
        	      n--;
        	      s.release();                                     //signal(S)
                }
        	    }break;
      }
    }

    public static void main(String[] args)
      throws InterruptedException
    {
      emSem h1 = new emSem(1);
      emSem h2 = new emSem(2);
      h1.start(); h2.start();
      h1.join(); h2.join();
      System.out.println(n);
    }
}
