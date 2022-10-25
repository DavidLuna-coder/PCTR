package Ej_1;
/**
 * @author David Luna Jurado
 * @version 1.0.0
 * @see hebra
 * 
*/
public class Usa_hebra {

/**
 *  Crea dos Hilos de la clase {@code hebra} e imprime por pantalla el valor de la variable compartida.
 *
 * @throws InterruptedException
 */    public static void main() throws InterruptedException {
        int iteraciones = 100000;
        Thread h1 = new hebra(0, iteraciones);
        Thread h2 = new hebra(1, iteraciones);
        h1.start();
        h2.start();
        h1.join();
        h2.join();

        System.out.println("Valor de n: " + hebra.n);
    }
}
