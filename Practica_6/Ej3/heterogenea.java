package Ej3;

/**
 * @author David Luna Jurado
 * @version 1.0.0
 * 
 * 
 *          Clase con dos atributos {@code n} y {@code m} que provee métodos
 *          para incrementar esos valoress
 *          {@code n} protegiéndolo ante el problema de la exclusión mútua y m
 *          sin protegerlo
 */
public class heterogenea {
    private int n = 0, m = 0;

    /**
     * Método sincronizado que incrementa el valor del atributo {@code n}
     */
    public synchronized void increaseN() {

        n++;

    }

    /**
     * Método sincronizado que devuelve el valor del atributo {@code n}
     * 
     * @return n
     */
    public synchronized int getN() {
        return n;
    }

    /**
     * Método que incrementa el valor del atributo {@code m}
     * 
     */
    public void increaseM() {
        m++;
    }

    /**
     * Método sincronizado que incrementa el valor del atributo {@code m}
     * 
     * @return m
     */
    public int getM() {
        return m;
    }
}
