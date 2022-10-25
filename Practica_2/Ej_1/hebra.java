package Ej_1;

import java.lang.Thread;

/**
 * @author David Luna Jurado
 * @version 1.0.0
 * @see {@code Thread}
*/
public class hebra extends Thread {
    public static int n = 0;
    private int tipo;
    private int iteraciones;

    /**
     * Método constructor parametrizado 
     * @param tipo Tipo de Hilo en función de la tarea que realice 0 o 1
     * @param iter Número de iteraciones que realizará el método run del hilo
     */
    hebra(int tipo, int iter) {
        this.tipo = tipo;
        iteraciones = iter;
    }

    /**
     * <p> Implementación del método run del hilo.</p><br /> 
     * <p>Si {@code tipo == 0} se incrementa la variable {@code n} en 10 unidades</p><br />
     * <p>Si {@code tipo == 1} se decrementa la variable {@code n} en 10 unidades</p>
     */
    public void run() {
        switch (tipo) {
            case 0:
                for (int i = 0; i < iteraciones; i++)
                    n+=10;
                break;
            case 1:
                for (int i = 0; i < iteraciones; i++)
                    n-=10;
                break;
            default:
                break;
        }
    }
    
}
