import java.rmi.*;
import java.rmi.server.*;
import java.util.Random;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 * @see UnicastRemoteObject
 * @see iBonoLoto
 */
public class sBonoLoto extends UnicastRemoteObject implements iBonoLoto {

    int[] numeros;
    /**
     * Constructor del objeto remoto
     * @throws RemoteException
     */
    sBonoLoto() throws RemoteException {
        numeros = new int[6];
        resetServidor();
    }

    /**
     * Implementación del método resetServidor. Genera 6 números del 1 al 49 de forma aleatoria
     * @throws RemoteException
     */
    public void resetServidor() throws RemoteException {

        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            numeros[i] = r.nextInt(49) + 1;
            System.out.println(numeros[i]);
        }
    }

    /**
     * Implementación de la función compApuesta que comprueba si una apuesta coincide con el número premiado
     * @param apuesta Array de enteros con 6 números del 1 al 49
     * @return premio Verdadero o falso en función de si conincide la apuesta con el número guardado
     * @throws RemoteException
     */
    public boolean compApuesta(int[] apuesta) throws RemoteException {

        boolean premio = true;

        if (apuesta.length != 6)
            premio = false;

        for (int i = 0; i < 6 && premio == true; i++) {
            if (apuesta[i] != numeros[i])
                premio = false;
        }

        return premio;
    }

    public static void main(String[] args) throws Exception {
        sBonoLoto loteria = new sBonoLoto();

        Naming.bind("Servidor", loteria);

        System.out.println("Server Ready");
    }
}
