public class Critica {
    public static int x;

    Critica()
    {
        x = 5;
    }
    Critica(int n)
    {
        x = n;
    }
    public static void increase()
    {
        x++;
    }

    public static void decrease()
    {
        x--;
    }

    public static int display()
    {
        System.out.printf("Valor: %d\n",x);
        return x;
    }
}
