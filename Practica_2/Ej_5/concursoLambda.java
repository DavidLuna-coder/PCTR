package Ej_5;
/**
 * @author David Luna Jurado
 * @version 1.0
 */
public class concursoLambda {
    static public int nCompartida = 0;
    /**
     * Función main que lanza dos hilos que actúan sobre la misma variable compartida usando expresiones lamda
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args)  throws InterruptedException{
        Runnable r1 = ()->{for (int i=0; i<100000;i++){nCompartida+=i*i;}};
        Runnable r2 = ()->{for (int i=0; i<100000;i++){nCompartida-= i;}};

        Thread h1 = new Thread(r1);
        Thread h2 = new Thread(r2);
        h1.start();
        h2.start();

        h1.join();
        h2.join();

        System.out.println("Valor de nCompartida: " + nCompartida);
    }
}
