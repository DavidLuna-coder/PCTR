/**
 * ParImpar
 */
public class ParImpar implements Runnable {
    ParImpar(String S, int n)
    {
        parImpar = S;
        numeros = n;
    }

    public void run(){
        int num;
        if(parImpar == "par")
        {
            num = 0;
            for (int i = 0; i < numeros; i++) {
                System.out.println(num);
                num+=2;
            }
        }
        else if(parImpar == "impar")
        {
            num = 1;
            for (int i = 0; i < numeros; i++) {
                System.out.println(num);
                num+=2;
            }
        }
    }
    
    private String parImpar;
    private int numeros;
}