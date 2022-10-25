package Ej_2;

/**
 * tareaRunnable
 */
public class tareaRunnable implements Runnable {

    private Compartida C;
    int iteraciones;
    int tipoHilo = 0;
    tareaRunnable(Compartida C,int it, int tipo)
    {
        this.C = C;
        this.tipoHilo = tipo;
        this.iteraciones = it;
    }
    public void run() {
        switch (tipoHilo) {
            case 0:
                for (int i = 0; i < iteraciones; i++) {
                    C.incrementValue();
                }
                break;
        
            case 1:
                for (int i = 0; i < iteraciones; i++) {
                    C.decrementValue();
                }
                break;
            default:
                break;
        }
    }

}