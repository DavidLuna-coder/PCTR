package Ej2;
import java.util.Random;
public class matVectorConcurrente implements Runnable {
    static public int[][] A;
    static public int[] b;
    static public int[] resultado;
    private int ini, fin;

    matVectorConcurrente(int ini, int fin) {
        this.ini = ini;
        this.fin = fin;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Rellenando Matriz y Vector...");
        int tamaño = 30000;
        int nHilos = 6;
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

        System.out.println("Tiempo: " + (TiempoFinal - TiempoInicial)/(long)1e6);
    }

    public void run() {
        for (int i = ini; i < fin; i++) {
            for (int j = 0; j < A.length; j++) {
                resultado[i] += A[i][j] * b[j];
            }
        }
    }
}
