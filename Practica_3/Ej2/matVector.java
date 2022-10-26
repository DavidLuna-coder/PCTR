package Ej2;

import java.util.Random;

public class matVector {
    public static void main(String[] args) {
        System.out.println("Rellenando Matriz y vector, no cuenta para el tiempo total... ");

        int nComponentes = 30000;

        int[][] A = new int[nComponentes][nComponentes];
        int[] b = new int[nComponentes];
        int[] resultado = new int[nComponentes];
        Random r = new Random();
        for (int i = 0; i < nComponentes; i++) {
            b[i] = r.nextInt(10);
            for (int j = 0; j < nComponentes; j++) {
                A[i][j] = r.nextInt(10);
            }
        }

        // System.out.println("Matriz inicial: ");
        // imprimirMatriz(A);
        System.out.println("Vector:");
        // imprimirVector(b);

        long TiempoInicial = System.nanoTime();
        for (int i = 0; i < nComponentes; i++) {
            for (int j = 0; j < nComponentes; j++) {
                resultado[i] += A[i][j] * b[j];
            }
        }
        long TiempoFinal = System.nanoTime();

        System.out.println("Resultado:");
        // imprimirVector(resultado);
        System.out.println("Tiempo final: " + (TiempoFinal - TiempoInicial) / (long)1e6);

    }

    static public void imprimirMatriz(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.printf("[ ");

            for (int j = 0; j < mat.length; j++) {
                System.out.printf("%d ", mat[i][j]);
            }
            System.out.println("]");
        }
    }

    static public void imprimirVector(int[] vector) {

        for (int i = 0; i < vector.length; i++) {

            System.out.println("[" + vector[i] + "]");
        }

    }
}
