import mpi.*;

/**
 * Sistema Utilizado: Linux
 * COMPILACION:javac -cp .:$MPJ_HOME/lib/mpj.jar escalMultiple.java
 * Ejecución: mpjrun.sh escalMultiple
 */

/**
 * @author David Luna Jurado
 * @version v1.0.0
 */
public class escalMultiple {
    /**
     * Función que ejecuta varios procesos, si el proceso es el primero envía al resto de procesos
     * un array de enteros. El resto de procesos multiplicará ese array por su rango y lo muestra por pantalla.
     * @param args Numero de procesos a ejecutar
     */
    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        int[] data = new int [10];
        if(rank == 0)
        {
            for(int i = 0; i < 10; i++)
            {
                data[i] = i;
            }

        }
        MPI.COMM_WORLD.Bcast(data,0, 10, MPI.INT, emisor);
        if(rank != 0)
        {
            for (int i = 0; i < 10; i++) {
                data[i] *= rank;
                System.out.printf(data[i] + " ");
            }
            System.out.printf("\n");
        }

        MPI.Finalize();
    }
}
