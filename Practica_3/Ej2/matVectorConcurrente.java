package Ej2;

import java.util.Random;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 */
public class matVectorConcurrente implements Runnable {
    static public int[][] A;
    static public int[] b;
    static public int[] resultado;
    private int ini, fin;

    /**
     * Constructor de la clase parametrizado
     * @param ini Primera fila que procesa la matriz.
     * @param fin Fila siguiente a la última que procesa la matriz
     */
    matVectorConcurrente(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }

    /**
     * Método main que lanza hilos de ejecución para calcular el producto de una Matriz de tamaño 30000 x 30000
     * por un vector de tamaño 30000
     * @param args {@code args[0]} es el numero de hilos que usaremos para el cálculo
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Rellenando Matriz y Vector...");
        int tamaño = 30000;
        int nHilos = Integer.parseInt(args[0]);
        A = new int[tamaño][tamaño];
        b = new int[tamaño];
        resultado = new int[tamaño];
        Random r = new Random();
        for (int i = 0; i < tamaño; i++) {
            b[i] = r.nextInt(10);
            for (int j = 0; j < tamaño; j++) {
                A[i][j] = r.nextInt(10);
            }
        }

        Thread[] Hilos = new Thread[nHilos];
        int divisionFilas = tamaño / nHilos;

        int ini = 0;
        int fin = divisionFilas;
        int i = 0;

        long TiempoInicial = System.nanoTime();
        for (i = 0; i < Hilos.length - 1; i++) {
            Hilos[i] = new Thread(new matVectorConcurrente(ini, fin));
            Hilos[i].start();
            ini += divisionFilas;
            fin += divisionFilas;
        }
        Hilos[i] = new Thread(new matVectorConcurrente(ini, tamaño));
        Hilos[i].start();

        for (i = 0; i < Hilos.length; i++) {
            Hilos[i].join();
        }
        long TiempoFinal = System.nanoTime();

        System.out.println("Resultado: ");
        // matVector.imprimirVector(resultado);

        System.out.println("Tiempo: " + (TiempoFinal - TiempoInicial) / (long) 1e6);
    }

    /**
     * Implementación del método run, calcula el producto de n filas de una matriz, desde ini hasta fin(no incluida)
     * y lo almacena en la posición del vector de resultados correspondiente.
     */
    public void run() {
        for (int i = ini; i < fin; i++) {
            for (int j = 0; j < A.length; j++) {
                resultado[i] += A[i][j] * b[j];
            }
        }
    }
}
