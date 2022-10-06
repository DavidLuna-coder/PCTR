public class Usa_ParImpar
{
    public static void main(String[] args) throws Exception{
        Runnable hiloImpar = new ParImpar("impar", 85);
        Runnable hiloPar = new ParImpar("par", 45);
        Thread HILO = new Thread(hiloImpar);
        HILO.start();
        new Thread(hiloPar).start();
    }
}