import java.rmi.Naming;

/**
 * @author David Luna Jurado
 * @version v1.0.0
 * @see iPiMonteCarlo
 */
public class cPiMonteCarlo {
/**
 * Función main que recibe el numero de puntos a generar como argumento, crea una referencia a un objeto remoto y ejecuta la función de generar puntos, y posterior mente muestra el resultado aproximado de Pi usando el método de MonteCarlo.
 * @param args numero de puntos a generar
 * @throws Exception
 */
    public static void main(String[] args) throws Exception {
        int nPuntos = Integer.parseInt(args[0]);

        iPiMonteCarlo RefServer = (iPiMonteCarlo) Naming.lookup("//localhost/Servidor");

        RefServer.masPuntos(nPuntos);
        System.out.println("Aprox Actual: " + RefServer.aproxActual());
    }
}
