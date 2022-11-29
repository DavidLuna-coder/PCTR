/**
 * @(#)incDecMonitor.java
 * @author A:T:
 * @version 1.00 2012/11/9
 *          Monitor java que incorpora acceso seguro al recurso encapsulado
 *          mediante
 *          metodos sincronizados.
 */

public class incDecMonitor {
    private static int n = 0;

    public incDecMonitor() {
    }

    public synchronized void inc() {
        n++;
    }

    public synchronized void dec() {
        n--;
    }

    public synchronized int ver() {
        return (n);
    }

}