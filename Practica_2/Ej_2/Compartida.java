package Ej_2;

/**
 * @author David Luna Jurado
 * @version 1.0.0
 */

public class Compartida {
    private int n = 0;

    /**
     * Incrementa el valor de la variable privada del objeto ({@code n}).
     */
    public void incrementValue() {
        n += 100;
    }

    /**
     * Decrementa el valor de la variable privada del objeto ({@code n}).
     */
    public void decrementValue() {
        n -= 100;
    }

    /**
     * Devuelve el valor de la variable privada del objeto ({@code n}).
     * 
     * @return {@code this.n}
     */
    public int getValue() {
        return n;
    }
}
