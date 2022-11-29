/**
 * @(#)DeadLock2.java
  * @author 
 * @version 1.00 2012/5/16
 */

public class DeadLock2 {
public static void main(String[] args)
 {

  final Object region_A = new Object();
  final Object region_B = new Object();

   Thread Hilo_A = new Thread(new Runnable(){
   public void run()
   {
   	 synchronized(region_A)
   	 { for(int i=0; i<100000; i++);
   	  synchronized(region_B)
   	  {
   		System.out.println("hilo A");
   	  }
   	 }
   }
  });

	Thread Hilo_B = new Thread(new Runnable(){
	public void run()
   	{
   	 synchronized(region_B)
   	 {for(int i=0; i<100000; i++);
   	  synchronized(region_A)
   	  {
        System.out.println("hilo B");
   	  }
   	 }
    }
   });


  Hilo_B.start();
  Hilo_A.start();
 }
}