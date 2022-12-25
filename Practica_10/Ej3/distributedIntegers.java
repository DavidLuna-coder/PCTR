
import mpi.*;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 * distributedIntegers
 */
public class distributedIntegers {

    /**
     * Función main que ejecuta varios procesos, si el proceso es el emisor, divide 10⁷ entre el numero de procesos.
     * Posteriormente manda un array a todos los procesos con los rangos a trabajar. Cada proceso trabajará en contar los números primos que existen dentro de su rango dado.
     * Finalmente con la operación reduce se devuelve la suma de esos valores al proceso emisor.
     * @param args
     */
    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int emisor = 0;
        long[] ranges = new long[size - 1];
        int[] data = new int[1];
        int[] contador = new int[1];

        if(rank == emisor)
        {
            contador[0] = 0;
            long range = (long)1e7/(size - 1);
            int i;
            for(i = 0; i < size - 2; i++)
            {
                ranges[i] = range;
                range += (long)1e7/size;
                System.out.println("RANGE: " + range);

            }
            ranges[i] = (long)1e7;
            System.out.println("RANGE: " + ranges[i]);

            System.out.println("Broadcasting ranges...");
        }

        MPI.COMM_WORLD.Bcast(ranges,0,size - 1,MPI.LONG,emisor);

        if (rank != emisor)
        {
            long inicio;
            boolean primo = true;
            contador[0] = 0;
            if(rank == 1)
            {
                inicio = 2;
            }
            else
            {
                inicio = ranges[rank - 2];
            }

            for (long i = inicio; i < ranges[rank - 1]; i++) {
                primo = true;
                for (long j = 2; j <= Math.sqrt(i); j++) {
                    if(i%j == 0 && i != j)
                    {
                        primo = false;
                    }
                }
                if(primo)
                {
                    contador[0]++;
                }
            }
            
            System.out.println("Proceso: " + rank +" encontrado "+ contador[0]);
            
        }
        MPI.COMM_WORLD.Reduce(contador,0,data,0,1,MPI.INT,MPI.SUM,emisor);
        if(rank == emisor)
        {
            System.out.println("Resultado: " + data[0]);
        }

        MPI.Finalize();
    }
}