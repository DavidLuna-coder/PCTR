package Ej1;

import java.util.Random;

public class prodEscalar {
    public static void main(String[] args) {
        System.out.println("Rellenando Vector...");
        int tamVector = (int) 1.0e8;
        double[] vector1 = new double[tamVector];
        double[] vector2 = new double[tamVector];
        double resultado = 0;
        Random r = new Random();
        for (int i = 0; i < vector1.length; i++) {
            vector1[i] = r.nextInt() % 100;
            vector2[i] = r.nextInt() % 100;
        }

        long inicCron = System.nanoTime();
        for (int i = 0; i < tamVector; i++) {
            resultado += vector1[i] * vector2[i];
        }
        long finCron = System.nanoTime();
        System.out.println("Tiempo: " + (finCron - inicCron)/(long)1e3 + " microsegundos");
        // System.out.println("Resultado: " + resultado);
    }
}
