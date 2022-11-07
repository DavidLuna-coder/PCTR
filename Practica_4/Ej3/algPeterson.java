package Ej3;
/**
 * @author David Luna Jurado
 * @version v1.0.0
 * @see {@code Runnable}
 */
public class algPeterson implements Runnable {

    static volatile public boolean wantp = false, wantq = false;
    static volatile public int last = 1;
    static public int n = 0;
    private int id;

    algPeterson(int id) {
        this.id = id;
    }

    public void run() {
        if (id == 1) {
            while(true) {
                wantp = true;
                last = 1;
                while(wantq && last != 2);
                // SC
                System.out.println(Thread.currentThread().getName());
                n++;
                System.out.println(n);

                wantp = false;
            }
        }

        if (id == 2) {
            while(true) {
               
                wantq = true;
                last = 2;
                while (wantp && last != 1);
                //SC
                System.out.println(Thread.currentThread().getName());
                n--;
                System.out.println(n);

                wantq = false;
            }
        }
    }


    public static void main(String[] args) {
        Thread p = new Thread(new algPeterson(1));
        Thread q = new Thread(new algPeterson(2));

        p.start();
        q.start();

        try {
            p.join();
            q.join();
        } catch (InterruptedException e) {
        }
    }
}