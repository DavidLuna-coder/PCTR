package Ej_2;

public class Usa_tareaRunnable {
    public static void main(String[] args) throws InterruptedException {
        Compartida C = new Compartida();
        Runnable r1 = new tareaRunnable(C, 10000, 0);
        Runnable r2 = new tareaRunnable(C, 10000, 1);
        Thread h1 = new Thread(r1);
        Thread h2 = new Thread(r2);
        h1.start();
        h2.start();
        
        h1.join();
        h2.join();

        System.out.println(C.getValue());
    }
}
