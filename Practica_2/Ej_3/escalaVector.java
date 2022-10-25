package Ej_3;

/**
 * @author David Luna Jurado
 * @version 1.0.0
 */
import java.util.Random;

public class escalaVector {
    /**
     * Método main, crea un vector de enteros de tamaño {@code args[0]} con valores entre -100 y 100 y realiza la operación de producto escalar dado por {@code args[1]}
     */
    public static void main(String[] args) {
        int[] vector = new int[Integer.parseInt(args[0])];
        Random r = new Random();

        int escalar = Integer.parseInt(args[1]);
        for (int i = 0; i < vector.length; i++) {
            vector[i] = r.nextInt() % 101;
        }

        for (int i = 0; i < vector.length; i++) {
            vector[i] *= escalar;
        }
    }
}
