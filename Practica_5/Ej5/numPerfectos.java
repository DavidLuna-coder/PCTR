package Ej5;

/**
 * @author David Luna Jurado
 *         numPerfectos
 * 
 */

public class numPerfectos {

    public static void main(String[] args) {
        int inf = Integer.parseInt(args[0]);
        int sup = Integer.parseInt(args[1]);

        int numerosPerfectos = 0;
        System.out.println("Calculando...");
        long tInit = System.nanoTime();
        for (int numero = inf; numero <= sup; numero++) {
            int suma = 1;
            for (int i = 2; i <= numero / 2; i++) {
                if (numero % i == 0) {
                    suma += (numero / i);
                }
            }

            if (suma == numero) {
                // System.out.println(numero + " es perfecto!");
                numerosPerfectos++;
            }
        }
        long tFin = System.nanoTime();
        System.out.println("Encontrados " + numerosPerfectos + " números perfectos");
        System.out.println("Tiempo: " + (tFin - tInit) / (long) 1e9 + " segundos");
    }

}