/**
 * @(#)codBloqueo.java
 * @author A.T.
 * @version 1.00 2011/11/18
 */


public class codBloqueo
{
    private int numVueltas;
    public codBloqueo(int vueltas){numVueltas = vueltas;}

    public void metodo()
    {
    	synchronized(this){  //bloqueo para acceso a seccion critica
    	 for(int i=1; i<=numVueltas; i++) System.out.print(i+" ");
    	}
    }


}