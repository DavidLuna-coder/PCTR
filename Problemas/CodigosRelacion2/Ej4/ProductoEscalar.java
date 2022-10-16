/**
 * ProductoEscalar
 */
import java.lang.Thread;
import java.util.Random;
import java.util.Scanner;
public class ProductoEscalar implements Runnable{

    static public double[] v1, v2, vResult;
    static public int nComponents; 
    int component;
    ProductoEscalar(int i)
    {   
        component = i;
    }

    public static void main(String[] args)  throws InterruptedException{
        System.out.println("Introduce el número de componentes de los vectores:");
        Scanner scanner = new Scanner(System.in);
        nComponents = scanner.nextInt();
        scanner.close();

        v1 = new double[nComponents];
        v2 = new double[nComponents];
        vResult = new double[nComponents];
        Random r = new Random();

        for (int i = 0; i < nComponents; i++) {
            v1[i] = r.nextDouble() * 100;
            v2[i] = r.nextDouble() * 100;
        }

        Thread[] Hilos = new Thread[nComponents];

        for (int i = 0; i < nComponents; i++) {
            Runnable prod = new ProductoEscalar(i);
            Hilos[i] = new Thread(prod);
            Hilos[i].start();
        }

        for (int i = 0; i < Hilos.length; i++) {
            Hilos[i].join();
        }

        double result = 0;

        for (int i = 0; i < nComponents; i++) {
            result  += vResult[i];
        }

        System.out.println("Vector 1: ");
        printArray(v1);
        System.out.println("Vector 2: ");
        printArray(v2);
        // printArray(vResult);
        
        System.out.println("RESULT : " + result);
    }

    static public void printArray(double v[])
    {
        System.out.printf("[ ");
        for (int i = 0; i < nComponents; i++) {
            System.out.printf("%f ", v[i]);
        }
        System.out.printf("]\n");
    }
    public void run() {
        vResult[component] = v1[component]*v2[component];
    }
}