public class ParImpar extends Thread {
    ParImpar(String s, int n) {
        if (s == "par") {
            esPar = true;
        } else if (s == "impar") {
            esPar = false;
        }
        numero = n;
    }

    public void run() {
        int inicial = 0;
        if (esPar) {
            for (int i = 0; i < numero; i++) {
                System.out.println(inicial);
                inicial += 2;
                Critica.display();
                Critica.increase();
            }
        }

        else {
            inicial = 1;
            for (int i = 0; i < numero; i++) {
                System.out.println(inicial);
                inicial += 2;
                Critica.display();
                Critica.increase();
            }
        }

    }
    
    boolean esPar;
    int numero;
}
