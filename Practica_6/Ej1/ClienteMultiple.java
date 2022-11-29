package Ej1;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author David Luna Jurado
 *         Clase Cliente que realiza n peticiones a un servidor
 */
public class ClienteMultiple {
    /**
     * Método main que realiza peticiones a un servidor en el puerto 3000
     * 
     * @param args {@code args[0]} Número de peticiones que hacer al servidor.
     */
    public static void main(String[] args) {
        int nPeticiones = Integer.parseInt(args[0]);
        int puerto = 3000;
        for (int i = 0; i < nPeticiones; i++) {
            try {
                System.out.println("Realizando conexión\n");
                Socket conexion = new Socket("localhost", puerto);
                System.out.println("Conexión Realizada 👌");
                PrintWriter respuesta = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(conexion.getOutputStream())));
                respuesta.println(i);
                respuesta.flush();
                conexion.close();
            } catch (Exception e) {

            }
        }
    }
}
