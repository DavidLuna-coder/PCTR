public class Critica implements Runnable{
    static private int x = 13;

    static public void increase()
    {
        x++;
    }


    static public int getX()
    {
        return x;
    }

    public void run(){
        for (int i = 0; i < 1000; i++) {
            increase();
        }
    }

    static public void main(String[] args) throws Exception
    {
        Thread[] Hilos = new Thread[50];
        for (int i = 0; i < Hilos.length; i++) {
            Hilos[i] = new Thread(new Critica());
            Hilos[i].start();
        }
        for (int i = 0; i < Hilos.length; i++) {
            Hilos[i].join();
        }

        System.out.println(getX());
    }
}
