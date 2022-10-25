package Ej_4;

/**
 * @author David Luna Jurado
 * @version 1.0
 * @see {@code cajero}
 * @see {@code cuentaCorriente}
 */
public class redCajeros {
    /**
     * Método main, crea y ejecuta 100000 hilos de cajeros que operan sobre la misma {@code cuentaCorriente}
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        cuentaCorriente c = new cuentaCorriente(47891923001L);
        int nThreads = 100000;
        Thread[] Hilos = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            Hilos[i] = new Thread(new cajero(c,i%2,100));
            Hilos[i].start();
        }

        for (int i = 0; i < Hilos.length; i++) {
            Hilos[i].join();
        }
        System.out.println("Saldo: " + c.getSaldo());
    }
}
