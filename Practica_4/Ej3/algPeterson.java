package Ej3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 * @see {@code Runnable}
 */
public class algPeterson implements Runnable {

    static volatile public boolean wantp = false, wantq = false;
    static volatile public int last = 1;
    static public int n = 0;
    private int id;

    /**
     * Constructor parametrizado
     * 
     * @param id identificador de la hebra
     */
    algPeterson(int id) {
        this.id = id;
    }

    /**
     * Sobreescritura del método run segun el id de la hebra, si {@code id==1}
     * {@code n} se incrementa.
     * si {@code id==2} {@code n} se decrementa utilizando el algoritmo de Peterson
     * para proteger la sección crítica.
     */
    public void run() {
        if (id == 1) {
            while (true) {
                wantp = true;
                last = 1;
                while (wantq && last != 2)
                    ;
                // SC
                System.out.println(Thread.currentThread().getName());
                n++;
                System.out.println(n);

                wantp = false;
            }
        }

        if (id == 2) {
            while (true) {

                wantq = true;
                last = 2;
                while (wantp && last != 1)
                    ;
                // SC
                System.out.println(Thread.currentThread().getName());
                n--;
                System.out.println(n);

                wantq = false;
            }
        }
    }

    /**
     * Método main que crea dos hebras y las deja lista para su ejecución.
     * 
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(new algPeterson(1));
        pool.execute(new algPeterson(2));
        pool.shutdown();

        while (!pool.isTerminated())
            ;

    }
}