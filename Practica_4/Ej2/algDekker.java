package Ej2;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 * @see Thread
 */
public class algDekker extends Thread {
    static volatile boolean wantp = false, wantq = false;
    static volatile int turn = 1;
    static int n = 0;
    private int id;

    /**
     * Constructor parametrizado
     * 
     * @param id identificador de la hebra
     */
    algDekker(int id) {
        this.id = id;
    }

    /**
     * Sobreescritura del método run segun el id de la hebra, si {@code id==1}
     * {@code n} se incrementa.
     * si {@code id==2} {@code n} se decrementa utilizando el algoritmo de Dekker
     * para proteger la sección crítica.
     */
    public void run() {
        if (id == 1) {
            while (true) {
                wantp = true;
                while (wantq) {
                    if (turn == 2) {
                        wantp = false;
                        while (turn != 1)
                            ;
                        wantp = true;
                    }
                }
                System.out.println(this.getName());
                n++;
                System.out.println(n);

                turn = 2;
                wantp = false;
            }

        }

        if (id == 2) {
            while (true) {
                wantq = true;
                while (wantp) {
                    if (turn == 1) {
                        wantq = false;
                        while (turn != 2)
                            ;
                        wantq = true;
                    }
                }
                System.out.println(this.getName());
                n--;
                System.out.println(n);

                turn = 1;
                wantq = false;
            }
        }
    }

    /**
     * Método main que crea dos hebras y las deja lista para su ejecución.
     * 
     */
    public static void main(String[] args) {
        Thread p = new algDekker(1);
        Thread q = new algDekker(2);

        p.start();
        q.start();

        try {
            p.join();
            q.join();
        } catch (InterruptedException e) {
        }
    }
}
