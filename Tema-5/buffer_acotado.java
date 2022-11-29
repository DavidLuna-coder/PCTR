import java.util.concurrent.locks.*;

class buffer_acotado{
 final int n             = 10;
 final Lock cerrojo      = new ReentrantLock();
 final Condition noLlena = cerrojo.newCondition();
 final Condition noVacia = cerrojo.newCondition();
 final int[] items       = new int[n];
 int putptr, takeptr, cont;

 public void put(int x) throws InterruptedException {
  cerrojo.lock();
  try {
   while (cont == items.length) noLlena.await();
   items[putptr] = x;
   putptr=(putptr+1)%n;
   cont++;
   noVacia.signal();
  } finally {
   cerrojo.unlock();
  }
 }

 public int take() throws InterruptedException {
  cerrojo.lock();
  try {
   while (cont == 0) noVacia.await();
   int temp = items[takeptr];
   takeptr=(takeptr+1)%n;
   cont--;
   noLlena.signal();
   return(temp);
  } finally {
   cerrojo.unlock();
  }
 }
}