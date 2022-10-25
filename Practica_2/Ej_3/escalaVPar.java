package Ej_3;

import java.util.Random;
import java.lang.Thread;
/**
 * @author David Luna Jurado
 * @version 1.0.0
 * @see {@code Runnable}
 */
public class escalaVPar implements Runnable {
    static public int[] vector;
    static public int escalar, nThreads;
    private int inicio, fin;

    /**
     * Constructor parametrizado, crea un objeto de la clase escalaVPar asignándole un intervalo de posiciones {@code ( [inicio, fin) )} del vector al que acceder
     * @param inicio primera posición del rango del vector al que accede el objeto
     * @param fin última posición del rango del vector
     */
    escalaVPar(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    /**
     * Implementación del método run de la interfaz Runnable. Multiplica un rango de posiciones de vector por el escalar.
     */
    @Override
    public void run() {

        // System.out.println("INICIO: " + inicio + " FIN " + fin);

        for (int i = inicio; i < fin; i++) {
            vector[i] *= escalar;
        }
    }

    /**
     * Método main que divide el vector en 12 hilos si el tamaño del vector es divisible entre 12 o en 13 si no lo es. A cada uno de esos Hilos se les asigna un rango de posiciones del vector en las que cada uno realizará el producto escalar de manera paralela
     * @param args {@code args[0]} tamaño del vector, {@code args[1]} valor del escalar
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        vector = new int[1000000];
        Random r = new Random();
        escalar = 2;
        for (int i = 0; i < vector.length; i++) {
            vector[i] = r.nextInt() % 100 + 1;
        }

        int nThreads = 12;
        int overflow = vector.length % nThreads;
        int celdaPorHilo = vector.length / nThreads;
        if (overflow != 0) {
            nThreads++;
        }

        Thread[] threads = new Thread[nThreads];
        int init = 0;
        int fin = celdaPorHilo;
        int contador = 0;
        for (contador = 0; contador < nThreads - 1; contador++) {

            threads[contador] = new Thread(new escalaVPar(init, fin));
            init += celdaPorHilo;
            fin += celdaPorHilo;

            threads[contador].start();
        }
        threads[contador] = new Thread(new escalaVPar(init, vector.length)); // Ultimo hilo que procesa las celdas que sobran
        threads[contador].start();
        for (int i = 0; i < nThreads; i++) {
            threads[i].join();
        }

     

    }

}
