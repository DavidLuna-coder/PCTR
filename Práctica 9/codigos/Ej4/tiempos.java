import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 */
public class tiempos {
    public static void main(String[] args) {
        long iteraciones = Long.parseLong(args[0]);

        tiempoRL tRl = new tiempoRL();
        tiempoSem tSem = new tiempoSem();
        tiempoSyn tSyn = new tiempoSyn();

        System.out.println("Empiezan a contar los tiempos");
        System.out.println("Tiempo con Synchronized: " + tSyn.f(iteraciones));
        System.out.println("Tiempo con Reentrant Lock: " + tRl.f(iteraciones));
        System.out.println("Tiempo con Semáforos: " + tSem.f(iteraciones));
    }
}

/**
 * Clase para medir el tiempo usando ReentrantLocs
 */
class tiempoRL {

    private int n = 0;
    private ReentrantLock L = new ReentrantLock();

    /**
     * Numero de iteraciones
     * 
     * @param iter
     * @return Tiempo tardado
     */
    public long f(long iter) {
        long ini = System.nanoTime();
        for (int i = 0; i < iter; i++) {
            L.lock();
            n++;
            L.unlock();
        }
        long fin = System.nanoTime();

        return (fin - ini);
    }
}

/**
 * Clase para medir el tiempo usando Semaphore
 */
class tiempoSem {

    private int n = 0;
    private Semaphore Sem = new Semaphore(1);

    /**
     * Numero de iteraciones
     * 
     * @param iter
     * @return Tiempo tardado
     */
    public long f(long iter) {
        long ini = System.nanoTime();
        for (int i = 0; i < iter; i++) {
            try {
                Sem.acquire();
            } catch (Exception e) {
            }
            n++;
            Sem.release();
        }
        long fin = System.nanoTime();

        return (fin - ini);
    }
}

/**
 * Clase para medir el tiempo usando synchronized
 */
class tiempoSyn {
    private int n = 0;

    /**
     * Numero de iteraciones
     * 
     * @param iter
     * @return Tiempo tardado
     */
    public long f(long iter) {
        long ini = System.nanoTime();
        for (int i = 0; i < iter; i++) {
            synchronized (this) {
                n++;
            }
        }
        long fin = System.nanoTime();

        return (fin - ini);
    }

}
