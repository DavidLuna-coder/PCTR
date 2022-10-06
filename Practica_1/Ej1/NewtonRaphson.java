import java.util.Scanner;

/**
 * NewtonRaphson
 */

public class NewtonRaphson {

    public void newtonRaphson(double aproximacionInicial,int iteraciones, Function f1, Function f2)
    {
        for (int i = 0; i < iteraciones; i++) {
            System.out.println(aproximacionInicial - (f1.value(aproximacionInicial) / f2.value(aproximacionInicial)));
        }
    }
    public static void main(String[] args) {
    double aproximacion = 0.0;
    int iteraciones = 0;
    Scanner scanner = new Scanner(System.in);
    System.out.println("Introduce la aproximacion inicial:");
    aproximacion = scanner.nextDouble();
    System.out.println("Introduce el numero de iteraciones:");
    iteraciones = scanner.nextInt();

    Function f = (double x) -> {return Math.cos(x) - x*x*x;}; 
    Function f2 = (double x) -> {return Math.cos(x) - (3)/2;}; // f'
    Function f3 = (double x) -> {return 

    }
}

interface Function {
    double value(double x);
}