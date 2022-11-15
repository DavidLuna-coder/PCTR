package Ej2;

/**
 * Implementación de productos de matrices de enteros de tamaño {@code m x m} de
 * forma secuencial.
 * 
 * @author David Luna Jurado
 * 
 */
public class prodMatricesSecuencial {

    /**
     * Método main que recibirá como argumento el tamaño de las matrices a
     * multiplicar.
     * 
     * @param args {@code args[0]} será el numero de filas y columnas de las
     *             matrices.
     */
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int[][] matriz1, matriz2, matriz3;
        matriz1 = new int[m][m];
        matriz2 = new int[m][m];
        matriz3 = new int[m][m];

        long timeini = System.nanoTime();
        for (int i3 = 0; i3 < m; i3++) {
            for (int j3 = 0; j3 < m; j3++) {
                for (int i = i3; i < m; i++) {
                    matriz3[i3][j3] += matriz1[i3][i] * matriz2[i][j3];
                }
            }
        }

        long timefin = System.nanoTime();
        System.out.println("Calculado:\nTiempo = " + ((timefin - timeini) / (long) 1e9) + " segundos");
    }

}