
package Ej4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase resImagenPar para aplicar resaltado a una matriz que simula una imagen de tamaño 2000x2000 de forma paralela.
 * @author David Luna Jurado
 * @see {@code Runnable}
 *
 */

public class resImagenPar implements Runnable {
    static public int[][] imagenIn, imagenOut;
    private int f_ini, f_fin;
    static public int k = 20000;

    /**
     * Constructor de la clase parametrizado. Acepta un rango [fi,ff] de filas a aplicar el resaltado.
     * @param fi Primera fila del rango. 
     * @param ff Última fila del rango.
     */
    resImagenPar(int fi, int ff) {
        f_ini = fi;
        f_fin = ff;
    }

    /**
     * Implementación del método run, aplica el resaltado a un rango de filas, dado al construir el objeto, de la imagen.
     */
    public void run() {
        for (int i = f_ini; i <= f_fin; i++) {
            for (int j = 0; j < k; j++) {
                if (i == 0 || i == k - 1 || j == 0 || j == k - 1) {
                    imagenOut[i][j] = imagenIn[i][j];
                } else {
                    imagenOut[i][j] = (4 * imagenIn[i][j] - imagenIn[i + 1][j] - imagenIn[i][j + 1] - imagenIn[i - 1][j]
                            - imagenIn[i][j - 1]) / 8;
                    if (imagenOut[i][j] > 255) {
                        imagenOut[i][j] = 255;
                    }

                    if (imagenOut[i][j] < 0) {
                        imagenOut[i][j] = 0;
                    }
                }

            }
        }
    }
/**
 * Método main para aplicar el resaltado en la matriz gestionando, crea un número de tareas igual al número de núcleos disponibles en la máquina.
 */
    public static void main(String[] args) {
        // Tamaño de la imagen
        int nTareas = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(nTareas);
        int filasPorTarea = k / nTareas;

        System.out.println("Generando imagen");

        imagenIn = new int[k][k];
        imagenOut = new int[k][k];
        Random R = new Random();

        // Generamos la Imagen
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                imagenIn[i][j] = R.nextInt(256);
            }
        }

        // System.out.println(Arrays.deepToString(imagenIn));

        int fInicial = 0;
        int fFinal = filasPorTarea - 1;
        long tinit = System.nanoTime();
        System.out.println("Resaltando");
        for (int i = 0; i < nTareas - 1; i++) {
            pool.execute(new resImagenPar(fInicial, fFinal));
            fInicial = fFinal + 1;
            fFinal += filasPorTarea;
        }
        pool.execute(new resImagenPar(fInicial, k - 1));

        pool.shutdown();
        while (!pool.isTerminated())
            ;
        long tfin = System.nanoTime();

        System.out.println("Tiempo: " + (tfin - tinit) / (long) 1e6 + " Milisegundos");

        // System.out.println(Arrays.deepToString(imagenOut));

    }
}
