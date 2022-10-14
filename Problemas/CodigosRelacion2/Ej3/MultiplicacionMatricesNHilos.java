import java.util.Random;
import java.util.Scanner;

/**
 * MultiplicacionMatrices
 */
public class MultiplicacionMatricesNHilos implements Runnable {
    int row = 0;
    static int[][] mat, matriz1, matriz2;
    static int n;

    MultiplicacionMatricesNHilos(int row) {
        this.row = row;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduce el tamaño de la matriz n x n");
        n = s.nextInt();
        s.close();

        Random r = new Random();

        matriz1 = new int[n][n];
        matriz2 = new int[n][n];
        mat = new int[n][n];
        Thread[] Hilos = new Thread[n];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                matriz1[i][j] = r.nextInt() % 11;
                matriz1[j][i] = r.nextInt() % 11;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                matriz2[i][j] = r.nextInt() % 11;
                matriz2[j][i] = r.nextInt() % 11;
            }
        }

        Runnable Prueba;
        for (int i = 0; i < n; i++) {
            Prueba = new MultiplicacionMatricesNHilos(i);
            Hilos[i] = new Thread(Prueba);
            Hilos[i].start();
        }

        for (int i = 0; i < n; i++) {
            Hilos[i].join();
        }

        System.out.println("Matriz 1:");
        printMatrix(matriz1, n);

        System.out.println("Matriz 2:");
        printMatrix(matriz2, n);

        System.out.println("Multiplicacion Matrix:");
        printMatrix(mat, n);
    }

    static public void printMatrix(int[][] mat, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("[ ");
            for (int j = 0; j < n; j++) {
                System.out.printf("%d ", mat[i][j]);
            }
            System.out.print("]\n");
        }
    }

    public void run() {
        for (int j = 0; j < n; j++) {
            mat[row][j] = matriz1[row][j] * matriz2[j][row];
        }
    }
}
