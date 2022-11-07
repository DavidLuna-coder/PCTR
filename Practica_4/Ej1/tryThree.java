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

    tryThree(int id) {
        this.id = id;
    }

    public void run() {
        if (id == 1) {
            for (int i = 0; i < 100000; i++) {
                wantp = true;
                while (wantq == true)
                    ;
                System.out.println(this.getName());
                n++;
                System.out.println(n);

                wantp = false;

            }
        }

        if (id == 2) {
            for (int i = 0; i < 100000; i++) {
                wantq = true;
                while (wantp == true)
                    ;
                System.out.println(this.getName());
                n--;
                System.out.println(n);

                wantq = false;
            }
        }
    }

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
