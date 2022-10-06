/**
 * MonteCarlo
 */
public class MonteCarlo {
    public static double monteCarlo(int n, Function function) {
        double contador_exitos = 0;
        double x;
        double y;
        for (int i = 0; i < n; i++) {
            x = Math.random();
            y = Math.random();
            if (y <= function.func(x)) {
                contador_exitos++;
            }
        }

        return contador_exitos / n;

    }
    
    public static void main(String[] args) {
        Function funcion1 = new sin();
        Function funcion2 = new f();
        System.out.println(monteCarlo(1000000,funcion1));
        System.out.println(monteCarlo(1000000,funcion2));
    }
}