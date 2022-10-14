import java.util.Scanner;

public class intDefinidaMonteCarlo {
    int n;

    public static double MonteCarlo(int n, Function f) {
        double contador_exitos = 0;
        double x = 0;
        double y = 0;
        for (int i = 0; i < n; i++) {
            x = Math.random();
            y = Math.random();

            if (y <= f.value(x)) {
                contador_exitos++;
            }

            return contador_exitos / n;
        }

    }

    public static void main(String[] args) {
        System.out.println("Introduce el numero de Puntos para el Metodo Monte Carlo");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Function f = (double x) -> {
            return Math.sin(x);
        };
        Function g = (double x) -> {
            return x;
        };
        System.out.println("Integral definida en el rango [0,1] de sen(x): " + MonteCarlo(n, f));
        System.out.println("Integral definida en el rango [0,1] de x: " + MonteCarlo(n, g));
    }

    interface Function {
        double value(double x);
    }
}