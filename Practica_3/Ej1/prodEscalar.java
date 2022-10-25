package Ej1;

import java.util.Random;

public class prodEscalar {
    public static void main(String[] args) {
        int tamVector = (int) 1.0e8;
        double[] vector1 = new double[tamVector];
        double[] vector2 = new double[tamVector];
        double resultado = 0;

        long inicCron = System.nanoTime();
        Random r = new Random();
        for (int i = 0; i < vector1.length; i++) {
            vector1[i] = r.nextInt() % 100;
            vector2[i] = r.nextInt() % 100;
        }

        for (int i = 0; i < tamVector; i++) {
            resultado += vector1[i] * vector2[i];
        }
        long finCron = System.nanoTime();
        System.out.println("Tiempo: " + (finCron-inicCron) + " nanosegundos") ;
        System.out.println("Resultado: " + resultado);
    }
}
