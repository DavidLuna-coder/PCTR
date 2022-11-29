package Ej1;

/**
 * Monitor que simula el problema de productor consumidor
 * 
 * @author David Luna Jurado
 * @version 1.0.0
 */
public class prodCon {
    private int[] buffer;
    private int n; // Tamaño del Buffer
    private int In_Ptr, Out_Ptr;
    private int count;
    private boolean full, empty;

    /**
     * Inicialización del Monitor (Constructor de Clase)
     * 
     * @param tamaño Tamaño del buffer
     */
    public prodCon(int tamaño) {
        n = tamaño;
        buffer = new int[n];
        In_Ptr = 0;
        Out_Ptr = 0;
        empty = true;
        full = false;
    }

    /**
     * Método para añadir elementos al buffer
     * 
     * @param e Elemento a introducir en el Buffer
     */
    public synchronized void Añadir(int e) {
        while (full) {
            try {
                wait();
            } catch (InterruptedException except) {
            }
        }

        buffer[In_Ptr] = e;
        In_Ptr = (In_Ptr + 1) % n;
        ++count;
        full = (count == n);
        empty = false;
        System.out.println("Elementos en el Buffer: " + count);
        notifyAll();
    }

    /**
     * Método para consumir elementos del buffer
     */
    public synchronized int Consumir() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        int elemento = buffer[Out_Ptr];
        Out_Ptr = (Out_Ptr + 1) % n;
        --count;
        empty = (count == 0);
        full = false;
        System.out.println("Elementos en el Buffer: " + count);

        notifyAll();
        return elemento;
    }
}
