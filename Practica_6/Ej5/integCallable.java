package Ej5;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author David Luna Jurado
 * @version 1.0.0
 * @see Callable
 * 
 *      Clase que implementa la interfaz {@code Callable} que calcula la
 *      integral de una función en el rango [0,1] aplicando el método de
 *      montecarlo, dividiendo el trabajo en tareas dado un rango de valores en
 *      funcion del numero de puntos aleatorios generados.
 */
public class integCallable implements Callable<Double> {
    public static int n;
    private int liminf, limsup;

    /**
     * Constructor parametrizado
     * 
     * @param li límite inferior del intervalo [a,b) sobre el que calcular el método
     *           de montecarlo
     * @param ls límite superior del intervalo [a,b) sobre el que calcular el método
     *           de montecarlo
     */
    integCallable(int li, int ls) {
        liminf = li;
        limsup = ls;
    }

    /**
     * Implementación del método {@code call}
     * Ejecuta el método de montecarlo para la función cos(x) un total de {@code ls} - {@code li} veces.
     */
    public Double call() {
        double contador = 0;
        double x = 0;
        double y = 0;
        for (int i = liminf; i < limsup; i++) {
            x = Math.random();
            y = Math.random();

            if (y <= Math.cos(x)) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Método main que calcula la integral definida en el intervalo de una función dado un número {@code n} de puntos aleatorios en general. El procesamiento y generación de puntos es dividida en tareas gestionadas por un pool de threads de tamaño fijo.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        integCallable.n = Integer.parseInt(args[0]);

        int nTasks = Runtime.getRuntime().availableProcessors();
        int Ventana = integCallable.n / nTasks;

        ExecutorService pool = Executors.newFixedThreadPool(nTasks);

        List<Future<Double>> resultadosParciales = Collections.synchronizedList(new ArrayList<Future<Double>>());
        int inicio = 0;
        int fin = Ventana;

        System.out.println("Calculando...");
        for (int i = 0; i < nTasks - 1; i++) {
            resultadosParciales.add(pool.submit(new integCallable(inicio, fin)));
            inicio = fin;
            fin += Ventana;
        }

        resultadosParciales.add(pool.submit(new integCallable(inicio, integCallable.n)));

        pool.shutdown();

        while (!pool.isTerminated())
            ;

        double resultado = 0;

        for (Future<Double> res : resultadosParciales) {
            resultado += res.get();
        }

        resultado = resultado / n;

        System.out.println("Resultado: " + resultado);
    }
}