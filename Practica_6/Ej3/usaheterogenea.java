package Ej3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Clase que usa objetos de la clase que utiliza métodos de la clase
 * {@code heterogenea} para incrementar los valores de {@code n} y
 * {@code m}
 * 
 * @author David Luna Jurado
 * @version 1.0.0
 * 
 * 
 * 
 * @see Runnable
 * @see heterogenea
 * 
 * 
 */
public class usaheterogenea implements Runnable {
    private int id;
    private heterogenea operadores;

    /**
     * Contructor parametrizado
     * 
     * @param id identificador del hilo, indica si incrementar n o m
     * @param o  objeto de la clase heterogenea que realiza las operaciones de
     *           incremento.
     */
    usaheterogenea(int id, heterogenea o) {
        this.id = id;
        operadores = o;
    }

    /**
     * Implementación del método run, si la {@code id} del objeto es {@code 0}
     * incrementa {@code n}, y es {@code 1} incrementa {@code m}
     */
    public void run() {
        switch (id) {
            case 0:
                operadores.increaseN();
                break;
            case 1:
                operadores.increaseM();

                break;
            default:
                break;
        }
    }

    /**
     * Método main que pone en marcha un pool de hilos que lanza 10000 tareas que
     * utilizan un objeto de la clase {@code heterogenea} para incrementar los
     * valores de n y m de forma concurrente y al final imprime los valores por
     * pantalla. Al ejecutar el programa el valor de {@code n} será 5000 y el de
     * {@code m} no será determinista
     * 
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        heterogenea h = new heterogenea();
        int nHilos = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = new ThreadPoolExecutor(nHilos, nHilos, 0, TimeUnit.NANOSECONDS,
                new LinkedBlockingQueue<Runnable>());
        int id = 0;

        for (int i = 0; i < 10000; i++) {
            pool.submit(new usaheterogenea(id, h));
            if (id == 0)
                id = 1;
            else
                id = 0;
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
        }

        System.out.println("N = " + h.getN());
        System.out.println("M = " + h.getM());
    }
}
