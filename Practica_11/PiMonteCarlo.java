import java.rmi.*;
import java.rmi.server.*;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 * @see UnicastRemoteObject
 * @see iPiMonteCarlo
 */
public class PiMonteCarlo extends  UnicastRemoteObject implements iPiMonteCarlo{

    private double cx,cy;
    double aprox = 0;
    long intentos = 0;
    long puntosGenerados = 0;
    /**
     * Constructor por defecto
     * @throws RemoteException
     */
    PiMonteCarlo() throws RemoteException
    {
        reset();
    }
    /**
     * Implementación de la función reset que pone a 0 los intentos los puntos generados y la aproximación a Pi
     */
    public void reset() throws RemoteException{
        aprox = 0;
        intentos = 0;
        puntosGenerados = 0;
    }

    /**
     * Implementación de la función masPuntos, genera {@code n} puntos y aumenta el contador de intentos y puntos generados.
     * 
     * @param nPuntos numero de puntos a generar
     * @throws RemoteException
     */
    public void masPuntos(int nPuntos)  throws RemoteException{
   
        for(long i=0; i<nPuntos; i++){
            cx = Math.random();
            cy = Math.random();
            puntosGenerados ++;
            if(Math.sqrt(Math.pow(cx, 2)+Math.pow(cy, 2))<=1)
                intentos++;
        }
    }

    /**
     * Implementación de la función aprox actual que devuelve la aproximación a PI por el método de MonteCarlo en el momento actual.
     * @return aprox
     * @throws RemoteException
     */
    public double aproxActual() throws RemoteException{
        if(puntosGenerados > 0)
        aprox = 4.0*intentos/puntosGenerados;
        return aprox;
    }

    /**
     * Función main que pone en marcha el servidor
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        PiMonteCarlo PI = new PiMonteCarlo();

        Naming.bind("Servidor", PI);
        System.out.println("Servidor Empezado...");
    }
}
