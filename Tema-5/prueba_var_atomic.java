import java.util.concurrent.atomic.*;
import java.util.concurrent.*;

class Hilo implements Runnable{
   AtomicInteger cont; //Instancia compartida por todas las tareas
   int valor;
   public Hilo(AtomicInteger cont) {this.cont=cont;}
   public void run(){
     for(int i=0; i<100; i++)valor = this.cont.incrementAndGet();

     }
}

public class prueba_var_atomic {
 public static void main(String[] args)throws Exception{
    AtomicInteger cont = new AtomicInteger(0);
    ThreadPoolExecutor miPool = new ThreadPoolExecutor(10, 10, 60000L,
    	  TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    	miPool.prestartAllCoreThreads();
    	Hilo[] tareas = new Hilo [100];
    	for (int i=0; i<100; i++){
       tareas[i] = new Hilo(cont);
    	  miPool.execute(tareas[i]);
    	}
    	miPool.shutdown();
    Thread t= Thread.currentThread();
    t.sleep(3000);
    System.out.println(cont.get());
        
 }
}
