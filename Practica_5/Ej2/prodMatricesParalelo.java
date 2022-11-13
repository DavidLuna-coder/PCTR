package Ej2;

import java.util.Random;
import java.util.concurrent.*;;

/**
 * Implementación de productos de matrices de enteros de tamaño {@code m x m} de forma paralela a través del uso de la interfaz {@code Runnable}
 * @author David Luna Jurado
 * @see {@code Runnable}
 * 
 */
public class prodMatricesParalelo implements Runnable {
    private final int fila_inic, fila_fin;
    static public int m;
    static public int[][] mat1, mat2, mat3;

    /**
     * Constructor de la clase parametrizado, crea un objeto que procesará el rango de filas [fi,fj].
     * @param fi Primera fila a procesar en la multiplicación.
     * @param ff Última fila a procesar en la multiplicación.
     */
    prodMatricesParalelo(int fi, int ff) {
        fila_inic = fi;
        fila_fin = ff;
    }

    /**
     * Implementación del método {@code run} de la interfaz {@code Runnable}.
     * Realizará las multiplicación para las posiciones de la matriz que se encuentren entre las filas {@code fila_inic}, {@code fila_fin}, ambas filas incluidas.
     */
    public void run() {
        for (int fila = fila_inic; fila <= fila_fin; fila++) {
            for (int columna = 0; columna < m; columna++) {
                for (int k = 0; k < m; k++) {
                    mat3[fila][columna] += mat1[fila][k] * mat2[k][columna];
                }
            }
        }
    }

    /**
     * Método main que recibirá como argumento el tamaño de las matrices a multiplicar.
     * El programa divirá el problema en {@code n} tareas. Ese número vendrá dado por la ecuación de Subramanian.
     * Como el coeficiente de bloqueo es 0 al ser un problema de computación numérica. Por lo que el número de tareas será el mismo que procesadores disponibles tenga el programa
     * @param args  {@code args[0]} será el numero de filas y columnas de las matrices.
     */
    public static void main(String[] args) {
        m = Integer.parseInt(args[0]);
        mat1 = new int[m][m];
        mat2 = new int[m][m];
        mat3 = new int[m][m];

        Random r = new Random();

        System.out.println("Llenando Matrices\n");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                mat1[i][j] = r.nextInt(10) + 1;
                mat2[i][j] = r.nextInt(10) + 1;
            }
        }
        int nHebras = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(nHebras);
        int filasPorHebra = m / nHebras;

        int ini = 0;
        int fin = filasPorHebra - 1;
        
        System.out.println("Matrices Rellenadas, calculando...");
        long timeini = System.nanoTime();
        for (int i = 0; i < nHebras - 1; i++) {
            pool.execute(new prodMatricesParalelo(ini, fin));
            ini = fin + 1;
            fin += filasPorHebra;
        }

        pool.execute(new prodMatricesParalelo(ini, m - 1));
        pool.shutdown();

        // System.out.printf("Matriz 1:\n");
        // for (int i = 0; i < m; i++) {
        // System.out.printf("[ ");
        // for (int j = 0; j < m; j++) {
        // System.out.printf(mat1[i][j] + " ");
        // }
        // System.out.printf("]\n");
        // }

        // System.out.printf("Matriz 2:\n");
        // for (int i = 0; i < m; i++) {
        // System.out.printf("[ ");
        // for (int j = 0; j < m; j++) {
        // System.out.printf(mat2[i][j] + " ");
        // }
        // System.out.printf("]\n");
        // }

        while (!pool.isTerminated());
        long timefin = System.nanoTime();

        System.out.println("Calculado:\nTiempo = " + ((timefin - timeini)/(long)1e9) + " segundos");
        // for (int i = 0; i < m; i++) {
        // System.out.printf("[ ");
        // for (int j = 0; j < m; j++) {
        // System.out.printf(mat3[i][j] + " ");
        // }
        // System.out.printf("]\n");
        // }
    }
}
