import java.util.Scanner;

public class NewtonRaphson {
    static public double newtonRaphson(double x0, int iteraciones, Function f, Function df) {
        double xN = x0;
        double xN1 = 0;
        for (int i = 0; i < iteraciones; i++) {
            if (f.value(xN) != 0) {
                xN1 = xN - f.value(xN) / df.value(xN);
                System.out.println("Iteacion: " + i + " Aproximacion " + xN1);
            }
            xN = xN1;

        }
        return xN;
    }

    public interface Function {
        double value(double x);
    }

    public static void main(String[] args) {
        Function f = (double x) -> {return Math.cos(x) - Math.pow(x, 3);};
        Function df = (double x) -> {return -Math.sin(x) - 3 * Math.pow(x, 2);};

        Function g = (double x) -> x * x - 5;
        Function dg = (double x) -> 2 * x;

        Scanner s = new Scanner(System.in);
        System.out.println("Introduce un número en el intervalo [0,1]: ");
        double x0 = s.nextDouble();
        System.out.println("Introduce el numero de iteraciones: ");
        int n = s.nextInt();
        newtonRaphson(x0, n, f, df);

        System.out.println("Introduce un número en el intervalo [2,3]: ");
        x0 = s.nextDouble();
        System.out.println("Introduce el numero de iteraciones: ");
        n = s.nextInt();
        newtonRaphson(x0, n, g, dg);

    }
}
