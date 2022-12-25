import java.util.concurrent.locks.ReentrantLock;

/**
 * @author David Luna Jurado
 * @version 1.0.0
 */
public class cCRL {
    public long numero;
    private double saldo;
    private ReentrantLock L;

    /**
     * Constructor parametrizado
     * 
     * @param numero Número de cuenta
     */
    cCRL(long numero) {
        this.numero = numero;
        saldo = 0;
        L = new ReentrantLock();
    }

    /**
     * Deposita la cantidad indicada en la cuenta, aumentando su saldo
     * Protege la operación con el uso de cerrojos reentrantes
     * @param n Cantidad a depositar
     */
    public void deposito(double n) {
        L.lock();
        try {
            saldo += n;

        } finally {
            L.unlock();
        }
    }

    /**
     * Retira la cantidad indicada en la cuenta, reduciendo su saldo
     * Protege la operación con el uso de cerrojos reentrantes
     * 
     * @param n Cantidad a retirar
     */
    public void reintegro(double n) {
        L.lock();
        try {
            saldo -= n;

        } finally {
            L.unlock();
        }
    }

    /**
     * Devuelve el saldo de la cuenta
     * Protege la operación con el uso de cerrojos reentrantes
     * 
     * @return {@code saldo} Saldo de la cuenta
     */
    public double getSaldo() {
        L.lock();
        try {
            return saldo;
        } finally {
            L.unlock();
        }
    }
}
