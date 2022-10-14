import java.util.Random;
import java.util.Scanner;

/**
 * MultiplicacionMatrices
 */
public class MultiplicacionMatrices implements Runnable {
    int row = 0, col = 0;
    static int[][] mat, matriz1, matriz2;

    MultiplicacionMatrices(int row, int column) {
        this.row = row;
        this.col = column;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduce el tamaño de la matriz n x n");
        int n = s.nextInt();
        s.close();

        Random r = new Random();

        matriz1 = new int[n][n];
        matriz2 = new int[n][n];
        mat = new int[n][n];
        Thread[][] Hilos = new Thread[n][n];

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
            for (int j = i; j < n; j++) {
                Prueba = new MultiplicacionMatrices(i, j);
                Hilos[i][j] = new Thread(Prueba);
                Hilos[i][j].start();

                Prueba = new MultiplicacionMatrices(j, i);
                Hilos[j][i] = new Thread(Prueba);
                Hilos[j][i].start();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                Hilos[i][j].join();
                Hilos[j][i].join();
            }
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
        mat[row][col] = matriz1[row][col] * matriz2[col][row];
    }
}
