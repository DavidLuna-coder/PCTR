/**
 * @(#)UsacodBloqueo.java
 * @author A.T.
 * @version 1.00 2011/11/18
 */

public class UsacodBloqueo
  extends Thread
{
	codBloqueo cerrojo; //referencia a objeto compartido

    public UsacodBloqueo(codBloqueo l) {cerrojo = l;}
    public void run()
    {
    	cerrojo.metodo(); //llamada a metodo que tiene codigo sincronizado
    }


    public static void main(String[] args)
    {
        codBloqueo aux = new codBloqueo(200);
        UsacodBloqueo h1 = new UsacodBloqueo(aux);
        UsacodBloqueo h2 = new UsacodBloqueo(aux);
        h2.start();
        h1.start();
    }
}
