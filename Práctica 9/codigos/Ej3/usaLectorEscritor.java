
/**
 * @author David Luna Jurado
 * @verson v1.0.0
 * @see Runnable
 * Clase que usa un monitor para solucionar el problema de Lectores Escritores
 */
public class usaLectorEscritor implements Runnable {
    private recurso r;
    private lectorEscritor le;
    private int id; // 0 lectores, 1 escritores
    static public long data;

    /**
     * Constructor parametrizado
     * @param r Recurso a compartir
     * @param le Monitor lectorEscritor
     * @param id Identificador del tipo de hilo 0 Lector 1 Escritor
     */

    public usaLectorEscritor(recurso r, lectorEscritor le, int id) {
        this.r = r;
        this.le = le;
        this.id = id;
    }

    /**
     * Implementación del método run, ejecuta 100000 de lecturas o un 1000000 de escrituras en función del tipo de hebra
     */
    public void run() {
        switch (id) {
            case 0:
                for (long i = 0; i < 1000000; i++) {
                    le.iniciaLectura();
                    data = r.observer();
                    le.acabarLectura();
                }
                break;

            case 1:
                for (long i = 0; i < 1000000; i++) {
                    le.iniciaEscritura();
                    r.inc();
                    le.acabarEscritura();
                }
                break;

            default:
                break;
        }
    }

    /**
     * Método main que pone en marcha 4 hebras escritoras y 2 lectoras, cuando acaban lee el recurso
     */
    public static void main(String[] args) {
        lectorEscritor le = new lectorEscritor();
        recurso r = new recurso();

        Thread Escritor = new Thread(new usaLectorEscritor(r, le, 1));
        Thread Escritor2 = new Thread(new usaLectorEscritor(r, le, 1));
        Thread Escritor3 = new Thread(new usaLectorEscritor(r, le, 1));
        Thread Escritor4 = new Thread(new usaLectorEscritor(r, le, 1));
        Thread Lector = new Thread(new usaLectorEscritor(r, le, 0));
        Thread Lector2 = new Thread(new usaLectorEscritor(r, le, 0));

        Escritor.start();
        Escritor2.start();
        Escritor3.start();
        Escritor4.start();
        Lector.start();
        Lector2.start();
        try {
            Escritor.join();
            Escritor2.join();
            Escritor3.join();
            Escritor4.join();
            Lector.join();
            Lector2.join();
        } catch (Exception e) {
        }

        System.out.println("Dato Final = " + r.observer());
    }
}
