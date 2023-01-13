#include <thread>
#include <iostream>
#include <vector>
#include <stdlib.h>
#include <mutex>
#include <ctime>

void monteCarlo(long puntosTarea, std::mutex &Lock, int &totalHits)
{
    // Codigo monteCarlo
    double contador_exitos = 0;
    double x = 0;
    double y = 0;
    long aciertos = 0;
    for (int i = 0; i < puntosTarea; i++)
    {
        x = (static_cast<double>(rand()) / RAND_MAX);
        y = (static_cast<double>(rand()) / RAND_MAX);
        // std::cout<< "X = " << x << "\tY = "<< y << "\n";
        if (y <= (x * x))
        {
            aciertos++;
            // std::cout << totalHits << "\n";
        }
    }
    Lock.lock();
    totalHits +=aciertos;
    Lock.unlock();
}
int main()
{
    long numeroPuntos = 0;
    int numeroHilos = 1;
    int totalHits = 0;
    std::mutex Lock;
    std::cout << "Introduce el numero de Puntos: ";
    std::cin >> numeroPuntos;
    std::cout << "Introduce el numero de Hilos: ";

    std::cin >> numeroHilos;

    long puntosTarea = numeroPuntos / numeroHilos;
    int tareaFinal = puntosTarea + (numeroPuntos % numeroHilos);

    std::vector<std::thread> Hilos;

    clock_t start = clock();

    for (size_t i = 0; i < numeroHilos - 1; i++)
    {
        Hilos.push_back(std::thread(monteCarlo, (puntosTarea), std::ref(Lock), std::ref(totalHits)));
    }

    Hilos.push_back(std::thread(monteCarlo, (tareaFinal), std::ref(Lock), std::ref(totalHits)));

    for (size_t i = 0; i < numeroHilos; i++)
    {
        Hilos[i].join();
    }

    clock_t end = clock();

    double time = ((double)(end - start) / CLOCKS_PER_SEC);

    std::cout << "Tiempo: " << time  << " segundos\n";
    std::cout << "Valor aproximado = " << (static_cast<double>(totalHits) / numeroPuntos) << std::endl;
    std::cout << "Hebras: " << numeroHilos << std::endl;
    return 0;
}
