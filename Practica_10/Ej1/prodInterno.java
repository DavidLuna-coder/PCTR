import mpi.*;

/**
 * Sistema Operativo Utilizado Linux
 * COMPILACION: javac -cp .:$MPJ_HOME/lib/mpj.jar prodInterno.java
 * EJECUCION: mpjrun.sh -np 2 prodInterno
 */
/**
 * prodInterno
 */
public class prodInterno {

    public static void main(String[] args) throws Exception{
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int nProcess = MPI.COMM_WORLD.Size();
        int master = 0;
        int slave = 1;
        int tag = 100;
        int unitSize  = 4;
        if(rank == 0)
        {
            int[] arr1 = new int[4];
            int[] arr2 = new int[4];
            for (int i = 0; i < 4; i++) {
                arr1[i] = i+1;
                arr2[i] = i+2;
            }
            int[] resProd = new int[1];
            MPI.COMM_WORLD.Send(arr1, 0, unitSize, MPI.INT, slave,tag);
            MPI.COMM_WORLD.Send(arr2, 0, unitSize, MPI.INT, slave,tag);
            MPI.COMM_WORLD.Recv(resProd, 0, 1, MPI.INT, slave,tag);
            
            System.out.println("Recibido: " + resProd[0]);
        }
        else if(rank == 1)
        {
            int[] arr1 = new int[4];
            int[] arr2 = new int[4];
            MPI.COMM_WORLD.Recv(arr1, 0, unitSize, MPI.INT, master, tag);
            MPI.COMM_WORLD.Recv(arr2, 0, unitSize, MPI.INT, master, tag);

            int prodEscalar = 0;
            for (int i = 0; i < 4; i++)
            {
                prodEscalar += arr1[i] * arr2[i];
            }

            int[] respuesta = new int[1];
            respuesta[0] = prodEscalar;
            MPI.COMM_WORLD.Send(respuesta,0, 1, MPI.INT, master, tag);
        }
        MPI.Finalize();
    }
}