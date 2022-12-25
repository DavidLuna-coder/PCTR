import java.util.concurrent.Semaphore;

/**
 * @author David Luna Jurado
 * @version 1.0.0
 */
public class ccSem {
    public long numero;
    private double saldo;
    Semaphore S;

    /**
     * Constructor parametrizado
     * 
     * @param numero Número de cuenta
     */
    ccSem(long numero) {
        this.numero = numero;
        saldo = 0;
        S = new Semaphore(1);
    }

    /**
     * Deposita la cantidad indicada en la cuenta, aumentando su saldo
     * 
     * @param n Cantidad a depositar
     */
    public void deposito(double n) {
        try {
            S.acquire();
            saldo += n;
        } catch (InterruptedException e) {
        }
        S.release();

    }

    /**
     * Retira la cantidad indicada en la cuenta, reduciendo su saldo
     * 
     * @param n Cantidad a retirar
     */
    public void reintegro(double n) {
        try {
            try {
                S.acquire();
            } catch (Exception e) {
            }
            saldo -= n;
        } finally {
            S.release();
        }
    }

    /**
     * Devuelve el saldo de la cuenta
     * 
     * @return {@code saldo} Saldo de la cuenta
     */
    public double getSaldo() {
        try {
            try {
                S.acquire();
            } catch (Exception e) {
            }
            return saldo;
        } finally {
            S.release();
        }
    }
}
