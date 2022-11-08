package Ej1;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 * @see Thread
 */
public class tryThree extends Thread {
    static volatile boolean wantp = false, wantq = false;
    static int n = 0;
    private int id;

    /**
     * Constructor parametrizado
     * 
     * @param id identificador de la hebra
     */
    tryThree(int id) {
        this.id = id;
    }

    /**
     * Sobreescritura del método run segun el id de la hebra, si {@code id==1}
     * {@code n} se incrementa.
     * si {@code id==2} {@code n} se decrementa utilizando el intento 3 del
     * algoritmo de Dekker.
     */
    public void run() {
        switch (id) {
            case 1:
                for (int i = 0; i < 100000; i++) {
                    wantp = true;
                    while (wantq == true)
                        ;
                    System.out.println(this.getName());
                    n++;
                    System.out.println(n);

                    wantp = false;

                }
                break;
            case 2:
                for (int i = 0; i < 100000; i++) {
                    wantq = true;
                    while (wantp == true)
                        ;
                    System.out.println(this.getName());
                    n--;
                    System.out.println(n);

                    wantq = false;
                }
                break;
            default:

                break;
        }

    }

    /**
     * Método main que crea dos hebras y las deja lista para su ejecución.
     * 
     */
    public static void main(String[] args) {
        Thread p = new tryThree(1);
        Thread q = new tryThree(2);

        p.start();
        q.start();

        try {
            p.join();
            q.join();
        } catch (InterruptedException e) {
        }
    }
}
