package Ej_4;
/**
 * @author David Luna Jurado
 * @version 1.0.0
 */
public class cuentaCorriente {
    public long numero;
    private double saldo;

    /**
     * Constructor parametrizado
     * @param numero Número de cuenta
     */
    cuentaCorriente(long numero)
    {
        this.numero = numero;
        saldo = 0;
    }

    /**
     * Deposita la cantidad indicada en la cuenta, aumentando su saldo
     * @param n Cantidad a depositar
     */
    public void deposito(double n)
    {
        saldo += n;
    }

    /**
     * Retira la cantidad indicada en la cuenta, reduciendo su saldo
     * @param n Cantidad a retirar
     */
    public void reintegro(double n)
    {
        saldo -= n;
    }

    /**
     * Devuelve el saldo de la cuenta
     * @return {@code saldo} Saldo de la cuenta
     */
    public double getSaldo()
    {
        return saldo;
    }
}
