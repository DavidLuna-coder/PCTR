import java.util.concurrent.CyclicBarrier;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 * @see Runnable
 * Clase crea hilos que llegan a una barrera dada
 */
public class barrera implements Runnable
{
    private CyclicBarrier barrier;

    /**
     * Constructor parametrizado
     * @param b Barrera sobre la que esperan los hilos
     */
    public barrera (CyclicBarrier b)
    {
        barrier = b;
    }

    /**
     * Implementación del método run 
     * El hilo espera sobre una barrera, una vez llegue el ultimo hilo esperado dado por el atributo barrier, seguirá su ejecución
     */
    public void run()
    {
        System.out.println("Legando a la barrera...");

        try {
            barrier.await();
        } catch (Exception e) {
        }

        System.out.println("Saliendo de la barrera :D");
    }

    /**
     * Método main que crea una barrera que espera a tres hilos, el método crea esos tres hilos y los lanza.
     * @param args
     */
    static public void main (String[] args)
    {
        CyclicBarrier Barrera = new CyclicBarrier(3);

        Thread h1 = new Thread(new barrera(Barrera));
        Thread h2 = new Thread(new barrera(Barrera));
        Thread h3 = new Thread(new barrera(Barrera));
        h1.start();
        h2.start();
        h3.start();

        try {
            h1.join();
            h2.join();
            h3.join();
        } catch (Exception e) {
        }

        System.out.println("Todos los hilos acabaron");
    }
}