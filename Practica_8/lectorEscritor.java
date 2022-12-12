/**
 * @author David Luna Jurado
 * @version v1.0.0
 *          Monitor para el problema de Lector-Escritor con prioridad de
 *          escritores
 */

public class lectorEscritor {
    private int lectores;
    private int lector;
    private boolean escritor;
    private boolean escribiendo;

    /**
     * Inicialización del monitor
     */
    public lectorEscritor() {
        lectores = 0;
        escribiendo = false;
        lector = 0;
        escritor = true;
    }

    /**
     * Método de inicio de la lectura bajo exclusión mútua
     */
    synchronized void iniciaLectura() {
        lector++;
        while (escribiendo) {
            try {
                wait();
            } catch (Exception e) {
            }

        }

        lector--;
        lectores++;
        notifyAll();
    }

    /**
     * Método que finaliza la lectura, si no quedan lectores leyendo que entre un
     * escritor
     */
    synchronized void acabarLectura() {
        lectores--;
        if (lectores == 0) {
            escritor = true;
        }
        notifyAll();
    }

    /**
     * Método que inicia la escritura protegiendo contra la exclusión mútua. 
     */
    synchronized void iniciaEscritura() {
        while (lectores != 0 || escribiendo || !escritor) {
            try {
                wait();
            } catch (Exception e) {
            }
        }

        escritor = false;
        escribiendo = true;
    }

    /**
     * Método que finaliza la escritura, si no hay lectores en espera permite que pase un escritor
     */
    synchronized void acabarEscritura() {
        escribiendo = false;
        if (lector == 0) // No hay lectores en cola
        {
            escritor = true; // Pasa escritor
        }
        notifyAll();
    }

}
