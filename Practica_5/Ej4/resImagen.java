package Ej4;

import java.util.Random;

/**
 * @author David Luna Jurado
 * @see {@code Runnable}
 */
public class resImagen {
    /**
     * Método main que aplica un filtro de resaltado a una imagen.
     */
    public static void main(String[] args) {
        System.out.println("Generando imagen");
        int k = 20000; // Tamaño de la imagen
        int imagen[][] = new int[k][k];
        Random R = new Random();

        // Generamos la Imagen
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                imagen[i][j] = R.nextInt(256);
            }
        }
        // System.out.println(Arrays.deepToString(imagen));

        // Resaltamos
        System.out.println("Resaltando...");
        long tinit = System.nanoTime();
        for (int i = 1; i < k - 1; i++) {
            for (int j = 1; j < k - 1; j++) {
                imagen[i][j] = (4 * imagen[i][j] - imagen[i + 1][j] - imagen[i][j + 1] - imagen[i - 1][j]
                        - imagen[i][j - 1]) / 8;
                if (imagen[i][j] > 255) {
                    imagen[i][j] = 255;
                }

                if (imagen[i][j] < 0) {
                    imagen[i][j] = 0;
                }
            }
        }
        long tfin = System.nanoTime();
        System.out.println("Tiempo: " + (tfin - tinit) / (long) 1e6 + " Milisegundos");
    }
}
