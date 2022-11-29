package Ej1;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.io.*;


/**
 * @author David Luna Jurado
 * @version 1.0.0
 * @see Runnable
 */
public class ServidorHiloConPool implements Runnable {
    Socket enchufe;

    /**
     * Constructor parametrizado
     * @param s socket que utilizará el servidor
     */
    public ServidorHiloConPool(Socket s) {
        enchufe = s;
    }

    /**
     * Implementación del método Run. Procesa una petición realizada al servidor
     */
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(
                            enchufe.getInputStream()));
            String datos = entrada.readLine();
            int j;
            int i = Integer.valueOf(datos).intValue();
            for (j = 1; j <= 20; j++) {
                System.out.println("El hilo " + Thread.currentThread().getName() + " escribiendo el dato " + i);
            }
            enchufe.close();
            System.out.println("El hilo " + Thread.currentThread().getName() + "\ncierra su conexion...");
        } catch (Exception e) {
            System.out.println("Error...");
        }
    }

    /**
     * Método main, crea un pool de hilos, pone en marcha un servidor y crea objetos de la clase {@code ServidorHiloConPool} para que procesen las peticiones. 
     * @param args
     */
    public static void main(String[] args) {
        int puerto = 3000;
        ThreadPoolExecutor pool = new ThreadPoolExecutor(12, 12, 10000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        try {
            ServerSocket chuff = new ServerSocket(puerto, 3000);
            try {
                while (true) {
                    System.out.println("Esperando solicitud");
                    Socket cable = chuff.accept();
                    pool.execute(new ServidorHiloConPool(cable));
                }
            } catch (Exception e) {
                System.out.println("Error al recibir la conexión");
                chuff.close();
            }
        } catch (Exception e) {
            System.out.println("Error al abrir puerto");
        }

    }
}
