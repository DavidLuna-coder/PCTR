/**
 * @(#)prodConControlado.java
 * @author A.T.
 * @version 1.00 2013/11/114
 * ESPECIFICACION: productor-consumidor controlado
 */

import java.util.*;
import java.util.concurrent.*;
public class prodConControlado
  extends Thread
 {
    private static int tamBuffer       = 100;
    private static double [] buffer;
    private static int InPtr           = 0;
    private static int OutPtr          = 0;
    private static Semaphore em        = new Semaphore(1);
    private static Semaphore espacios  = new Semaphore(tamBuffer);
    private static Semaphore elementos = new Semaphore(0);
    
    private int tipoHilo;

      public prodConControlado(int tipo) {tipoHilo = tipo;}
      public void run()
      {
      	switch(tipoHilo){
      		case 0:{
      			for(;;)
      			{
      				try{espacios.acquire();}catch(InterruptedException e){}
      				try{em.acquire();}catch(InterruptedException e){}
      				buffer[InPtr]=Math.random();
      				System.out.println("Hilo productor insertando "+buffer[InPtr]+" en buffer");
      				InPtr=(InPtr+1)%tamBuffer;
      				em.release();
      				elementos.release();
      			}
      		}
      		case 1:{
      			for(;;){
      		          try{elementos.acquire();}catch(InterruptedException e){}
      		          try{em.acquire();}catch(InterruptedException e){}
      			  System.out.println("Hilo consumidor extrayendo "+buffer[OutPtr]+" de buffer"); OutPtr=(OutPtr+1)%tamBuffer;
      			  em.release();
      			  espacios.release();
      			}	  
      		}
      	}
      }


    public static void main(String[] args)
    {  buffer = new double[tamBuffer];
       new prodConControlado(0).start();
       new prodConControlado(1).start();
    }
}
