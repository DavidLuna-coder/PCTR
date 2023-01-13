#include <iostream>
#include <random>
#include <thread>
#include <mutex>
#include <chrono>

using namespace std;

// Variable compartida para acumular el número de puntos que caen bajo la curva
long totalHits = 0;
// Cerrojo para proteger la variable compartida totalHits
std::mutex totalHitsMutex;

// Función que realiza el cálculo de la integral de x^2 en el intervalo [0,1]
double f(double x){return x*x;}


// utilizando el método de Monte Carlo
void monteCarloIntegration(int numPoints){
    double x,y;
    //Generador de números aleatorio
    srand(std::time(nullptr));

    //Lanzar los puntos y contar cuántos caen bajo la curva
    long hits = 0;

    for (int i = 0; i < numPoints; i++) {
        x = (float)(rand()) / ((float)(RAND_MAX/(1)));
        y = (float)(rand()) / ((float)(RAND_MAX/(1)));
        if (y <= f(x)) {
            hits++;
        }
    }

    //Adquirir el cerrojo y actualizar la variable compartida
    totalHitsMutex.lock();
    totalHits += hits;
    totalHitsMutex.unlock();
}

int main() {
  //Leer el número de puntos a lanzar y el número de tareas paralelas
  
  cout << "Ingrese el número de puntos a lanzar: ";
  int numPoints;
  cin >> numPoints;
  cout << "Ingrese el número de tareas paralelas: ";
  int numTasks;
  cin >> numTasks;

  auto iniciar=std::chrono::system_clock::now();
  // Dividir el trabajo entre las tareas paralelas
  int pointsPerTask = numPoints / numTasks;

  thread *tareas = new thread[numTasks];

  for (int i = 0; i < numTasks; i++) {
    tareas[i] = thread(monteCarloIntegration, pointsPerTask);
  }

  // Esperar a que todas las tareas terminen
  for (int i = 0; i < numTasks; i++) {
    tareas[i].join();
  }

  // Calcular y mostrar el resultado
  double result = (double)totalHits / numPoints;

  auto acabar=std::chrono::system_clock::now();
  auto lapso2 = std::chrono::duration_cast<std::chrono::milliseconds>(acabar - iniciar);
  cout << "Paralelo en   " << lapso2.count() << " milisegundos, con  " << numTasks << " hebras\n";
  cout << result ;
}