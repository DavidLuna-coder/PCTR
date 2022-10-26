package Ej1;

import java.util.Random;

public class prodEscalarParalelo extends Thread {
    public static double[] vector1, vector2, vecResult;

    private int id, ini, fin;

    public prodEscalarParalelo(int idHebra, int inicio, int fin) {
        id = idHebra;
        ini = inicio;
        this.fin = fin;
    }

    public void run() {
        vecResult[id] = 0;
        for (int i = ini; i < fin; i++) {
            vecResult[id] += vector1[i] * vector2[i];
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int tamVector = (int)1e8;
        int nHilos = Integer.parseInt(args[0]);
        System.out.println("Rellenando Vectores...");
        vector1 = new double[tamVector];
        vector2 = new double[tamVector];
        vecResult = new double[nHilos];

        int divisiones = tamVector/nHilos;
        Thread[] Hilos = new Thread[nHilos];
        Random r = new Random();
        for (int i = 0; i < vector1.length; i++) {
            vector1[i] = r.nextDouble();
            vector2[i] = r.nextDouble();
        }

        int i;
        int inicio = 0;
        int fin = divisiones;
        long inicCron = System.nanoTime();
        for (i = 0; i < Hilos.length - 1; i++) {
            Hilos[i] = new prodEscalarParalelo(i,inicio, fin);
            Hilos[i].start();
            inicio += divisiones;
            fin += divisiones;
        }

        Hilos[i] = new prodEscalarParalelo(i, inicio, tamVector);
        Hilos[i].start();
        for (int j = 0; j < Hilos.length; j++) {
            Hilos[i].join();
        }

        double resultado = 0;
        for (int j = 0; j < vecResult.length; j++) {
            resultado += vecResult[i];
        }
        long finCron = System.nanoTime();
        System.out.println("Tiempo tardado: " + (finCron-inicCron)/(long)1e3+ " microsegundos");
        // System.out.println("Resultado: " + resultado);

    }
}
