import java.rmi.Naming;
import java.util.Random;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 * @see iBonoLoto
 */
public class cBonoLoto {

    /**
     * Cliente que se conecta al servidor de Bonoloto y recibe la referencia a un objeto Remoto. El cliente genera un número y comprueba si es el correcto.
     * @param args 
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        iBonoLoto RefServer = (iBonoLoto) Naming.lookup("//localhost/Servidor");
        int[] apuesta = new int[6];
        Random r = new Random();

        for (int i = 0; i < 6; i++) {
            apuesta[i] = r.nextInt(49) + 1;
        }

        boolean resultado = RefServer.compApuesta(apuesta);
        if (resultado) {
            System.out.println("Ganaste!!!");
            RefServer.resetServidor();

        }

        else
            System.out.println("La próxima vez será :(");

    }
}