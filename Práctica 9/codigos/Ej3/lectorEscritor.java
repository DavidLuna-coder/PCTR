import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author David Luna Jurado
 * @version v1.0.0
 * @see ReentrantLock
 */
public class lectorEscritor {
    /**
     * Inicialización del monitor
     */
    private ReentrantLock L = new ReentrantLock();
    private int lectores = 0;
    private boolean escribiendo = false;
    private Condition lector = L.newCondition();
    private Condition escritor = L.newCondition();

    /**
     * Método que inicia la lectura bajo exclusión mútua
     */
    public void iniciaLectura() {
        L.lock();
        try {
            while (escribiendo) {
                try {
                    lector.await();
                } catch (Exception e) {
                    L.unlock();
                }
            }
            lectores++;
            lector.signal();

        } finally {
            L.unlock();
        }

    }

    /**
     * Método que acaba la lectura bajo exclusión mútua, si no quedan lectores le da paso a un escritor
     */
    void acabarLectura() {
        L.lock();
        try {
            lectores--;
            if (lectores == 0) {
                escritor.signal();
            }
        } finally {
            L.unlock();
        }
    }

    /**
     * Método que inicia la escritura y protege el acceso al recurso compartido
     */
    void iniciaEscritura() {
        L.lock();
        try {
            while (lectores != 0 || escribiendo) {
                try {

                    escritor.await();
                } catch (Exception e) {
                    L.unlock();
                }
            }
            escribiendo = true;
        } finally {
            L.unlock();
        }
    }

    /**
     * Acaba la escritura, da primero paso a un lector.
    */

    void acabarEscritura() {
        L.lock();
        try {
            escribiendo = false;
            lector.signal();
            escritor.signal();
        } finally {
            L.unlock();
        }
    }
}
