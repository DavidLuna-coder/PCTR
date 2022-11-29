package Ej4;


/**

 * 
 * @author David Luna Jurado
 * @version 1.0.0
 * @see Thread
 * 
 */
public class deadlock extends Thread {
    private final Object lockA;
    private final Object lockB;
    private final Object lockC;

    /**
     * Constructor de la clase, recibe tres objetos que se usarán como cerrojo
     * @param o
     * @param p
     * @param q
     */
    deadlock(Object o, Object p, Object q) {
        lockA = o;
        lockB = p;
        lockC = q;
    }

    /**
     * Sobreescritura del método run. El objeto toma tres cerrojos e imprime el nombre del hilo por pantalla.
     */
    public void run() {
        while (true) {
            synchronized (lockA) {
                synchronized (lockB) {
                    synchronized (lockC) {
                        System.out.println(this.getName());
                    }

                }
            }

        }
    }

    /**
     * Método main que lanza tres hilos con tres cerrojos como parámetros en orden distinto, obteniendo un interbloqueo en la ejecución.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Object cerrojoA = new Object(), cerrojoB = new Object(), cerrojoC = new Object();

        Thread h1 = new deadlock(cerrojoA, cerrojoB, cerrojoC);
        Thread h2 = new deadlock(cerrojoC, cerrojoA,cerrojoB);
        Thread h3 = new deadlock(cerrojoB, cerrojoC,cerrojoA);


        h1.start();
        h2.start();
        h3.start();

        h1.join();
        h2.join();
        h3.join();


        System.out.println("Terminado");
    }
}
