/**
 * @(#)prodCon.java
 * @author A.T.
 * @version 1.00 2011/11/17
 * ESPECIFICACION: productor-consumidor sin control de sincronizacion
 * y sin acceso al buffer en exclusion mutua.
 */

import java.util.*;
public class prodCon
  extends Thread
 {
    private static int tamBuffer = 100;
    private static double [] buffer;
    private static int InPtr=0;
    private static int OutPtr=0;
    private int tipoHilo;

      public prodCon(int tipo) {tipoHilo = tipo;}
      public void run()
      {
      	switch(tipoHilo){
      		case 0:{
      			for(;;)
      			{
      				buffer[InPtr]=Math.random();
      				System.out.println("Hilo productor insertando "+buffer[InPtr]+" en buffer");
      				InPtr=(InPtr+1)%tamBuffer;
      			}
      		}
      		case 1:{
      			for(;;){System.out.println("Hilo consumidor extrayendo "+buffer[OutPtr]+" de buffer"); OutPtr=(OutPtr+1)%tamBuffer;}
      		}
      	}
      }


    public static void main(String[] args)
    {  buffer = new double[tamBuffer];
       new prodCon(0).start();
       new prodCon(1).start();
    }
}
