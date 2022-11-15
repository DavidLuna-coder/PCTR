package Ej5;

import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.*;

/**
 * numPerfectorParalelos
 */
public class numPerfectosParalelos implements Callable<Long> {

    private long liminf, limsup;

    numPerfectosParalelos(long inf, long sup) {
        liminf = inf;
        limsup = sup;
    }

    public Long call() throws Exception {
        long contador = 0;
        for (long numero = liminf; numero <= limsup; numero++) {
            long suma = 1;
            for (long i = 2; i <= numero / 2; i++) {
                if (numero % i == 0) {
                    suma += (numero / i);
                }
            }
            if (suma == numero) {
                // System.out.println(numero + " es perfecto!");
                contador++;
            }
        }
        return contador;
    }

    public static void main(String[] args) {
        long inf = Long.parseLong(args[0]);
        long sup = Long.parseLong(args[1]);
        int nTareas = Runtime.getRuntime().availableProcessors();
        System.out.println("TAREAS: " + nTareas);
        long ventana = (sup - inf) / nTareas;
        ExecutorService pool = Executors.newFixedThreadPool(nTareas);

        List<Future<Long>> resultados = Collections.synchronizedList(new ArrayList<Future<Long>>());
        long ini = inf;
        long fin = ini + ventana;
        System.out.println("Calculando...");
        long tInit = System.nanoTime();
        for (int i = 0; i < nTareas - 1; i++) {
            resultados.add(pool.submit(new numPerfectosParalelos(ini, fin)));
            ini = fin + 1;
            fin += ventana;
        }
        resultados.add(pool.submit(new numPerfectosParalelos(ini, sup)));
        pool.shutdown();
        while (!pool.isTerminated())
            ;
        long res = 0;

        for (Future<Long> it : resultados) {
            try {
                res += it.get();
            } catch (Exception e) {System.out.println(e.getMessage());}
        }
        long tFin = System.nanoTime();

        System.out.println("Números Perfectos: " + res);
        System.out.println("Tiempo: " + (tFin - tInit)/(long)1e8 + " decisegundos");
    }
}