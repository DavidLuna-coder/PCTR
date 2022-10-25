package Ej_4;

/**
 * @author David Luna Jurado
 * @version 1.0
 * @see {@code Runnable} 
 * @see {@code cuentaCorriente}
 */
public class cajero implements Runnable {
    private cuentaCorriente cuenta;
    public int operacion;
    private double cantidad;

    /**
     * Constructor parametrizado de la clase Cajero
     * @param cuenta Referencia al objeto de la clase cuentaCorriente a la que accede el cajero
     * @param operacion Tipo de operacion a realizar 0 {@code depositar} 1 {@code retirar}
     * @param cantidad Cantidad a depositar o sacar
     */
    cajero(cuentaCorriente cuenta, int operacion, double cantidad) {
        this.cuenta = cuenta;
        this.operacion = operacion;
        this.cantidad = cantidad;
    }

    /** 
     * Implementación del método run deposita o retira la cantidad en función de la operación.
    */
    @Override
    public void run() {
        switch (operacion) {
            case 0:
                cuenta.deposito(cantidad);
                break;
            case 1:
                cuenta.reintegro(cantidad);
                break;
            default:
                break;
        }
    }
}
