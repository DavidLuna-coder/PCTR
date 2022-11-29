package Ej1;

/**
 * Clase que crea hebras de productores y consumidores que usan un monitor para
 * realizar las operaciones
 * 
 * @author David Luna Jurado
 * @version 1.0.0
 * @see prodCon
 * @see Runnable
 */
public class usaprodCon implements Runnable {
    private prodCon monitor;
    int tipo;

    /**
    * Constructor parametrizado
    * @param pC Monitor {@code prodCon} que se usará
    * @tipo 0 para Productores, 1 para consumidores
    */
    usaprodCon(prodCon pC, int t) {
        tipo = t;
        monitor = pC;
    }

    /**
     * Implementación del método Run. Si el tipo es 0 la hebra será Productora si es
     * 1 consumidor
     */
    public void run() {
        switch (tipo) {
            case 0: // Productor
                while (true) {
                    monitor.Añadir(5);
                    System.out.println("Añadido " + 5);
                }

            case 1: // Consumidor
                int e;
                while (true) {
                    e = monitor.Consumir();
                    System.out.println("Consumido " + e);
                }
            default:
                break;
        }
    }

    /**
     * Método main que crea productores y consumidores y los deja deja listos para su ejecución
     */
    public static void main(String[] args) {
        prodCon p = new prodCon(100);
        Thread productor = new Thread(new usaprodCon(p, 0));
        // Thread consumidor = new Thread(new usaprodCon(p, 1));
        Thread[] consumidores = new Thread[40];

        // Thread[] productores = new Thread[40];

        for (int i = 0; i < consumidores.length; i++) {
        consumidores[i] = new Thread(new usaprodCon(p, 1));
        consumidores[i].start();
        }

        // for (int i = 0; i < productores.length; i++) {
        //     productores[i] = new Thread(new usaprodCon(p, 0));
        //     productores[i].start();
        // }
        productor.start();
        // consumidor.start();

        try {
            productor.join();
            // consumidor.join();
            for (int i = 0; i < consumidores.length; i++) {
            consumidores[i].join();
            }

            // for (int i = 0; i < productores.length; i++) {
            // productores[i].join();
            // }
        } catch (InterruptedException e) {
        }
        ;
    }
}
