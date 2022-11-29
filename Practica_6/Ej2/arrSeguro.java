package Ej2;

import java.util.Arrays;

/**
 * @author David Luna Jurado
 * @version 1.0.0
 * @see Thread
 * 
 * Clase que permite la manipulación de un array de forma segura.
 */
public class arrSeguro extends Thread {
    static public int[] array = { 0, 0, 0, 0 };
    static private Object lock = new Object();
    private final int index;

    /**
     * Constructor parametrizado
     * @param i Índice del vector a operar
     */
    arrSeguro(int i) {
        index = i;
    }

    /**
     * Sobreescritura del método run. Incrementa el valor de un índice del vector, protegiendo la sección crítica
     */
    public void run() {
        System.out.println("Incrementando " + index);
        synchronized (lock) {
            array[index]++;
        }
    }


    /**
     * Método main que lanza hilos que modifican el vector que comparte los objetos de la clase {@code arrSeguro}
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Thread[] Hilos = new Thread[10];

        for (int i = 0; i < Hilos.length; i++) {
            Hilos[i] = new arrSeguro(i % 4);
            Hilos[i].start();
        }

        for (int i = 0; i < Hilos.length; i++) {
            Hilos[i].join();
        }

        System.out.println(Arrays.toString(array));
    }
}
