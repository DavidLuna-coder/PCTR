/**
 * @author David Luna Jurado
 * @version v1.0.0
 * recurso
 */
public class recurso {
    private long n = 0;

    /**
     * Método que incrementa el recurso en 1
     */
    public void inc()
    {
        ++n;
    }

    /**
     * Método que devuelve el valor de n
     * @return n
     */
    public long observer()
    {
        return n;
    }
}