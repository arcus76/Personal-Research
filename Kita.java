import java.util.concurrent.Semaphore;

public class Kita {
     
     private static int[] L = new int[2];  //L[0] - Kinder, L[1] - Erzieherinen
     
     private static Semaphore Kinder = new Semaphore(1);
     private static Semaphore Erzieherinen = new Semaphore(1);
     
     public static void main(String[] args) throws InterruptedException {
          for (int i = 1; i <= 5; i++) {
               new Thread(new Kind(i)).start();
               Nap.randomNap(200,3000);
          }
          for (int i = 1; i <=3; i++) {
               new Thread(new Erzieherin(i)).start();
               Nap.randomNap(200,3000);
          }
          for (int i = 6; i <= 12; i++) {
               new Thread(new Kind(i)).start();
               Nap.randomNap(200,3000);
          }
          for (int i = 4; i <=8; i++) {
               new Thread(new Erzieherin(i)).start();
               Nap.randomNap(200,3000);
          }
          System.out.println(L[0]+ " " + L[1]);
     }
     
     
     public static class Kind extends Thread {
          private int nummer;

          
          public Kind(int nummer) {
               this.nummer = nummer;
          }
          
          public void run() {
               System.out.printf("Kind %d ist gekommen\n", this.nummer);
               System.out.println(L[0] + " " + L[1]);
               try {
                    
                    Kinder.acquire();
                    
                    if (L[0]+1 > L[1]*4) {
                         Kinder.acquire();
                    }
                   
                    synchronized(L) { L[0]++; }
                    

                   System.out.printf("Kind %d ist in Kindergarten\n", this.nummer);
                   
                   Nap.randomNap(2000, 5000);
                   
                   synchronized(L) { L[0]--; }
                   
                   System.out.printf("Kind %d hat Kindergarten verlassen\n", this.nummer);
                   if ( L[1]*4 >= L[0] ) Erzieherinen.release();
               }
               catch (InterruptedException ie) {
               System.out.println(ie);
               }
          }
     }
     
     public static class Erzieherin extends Thread {
          private int nummer;
          
          public Erzieherin (int nummer) {
               this.nummer = nummer;
          }
          
          public void run() {
               System.out.printf("Erzieherin %d ist gekommen\n", this.nummer);
               System.out.println(L[0] + " " + L[1]);
               try {
                    
                    
                    
                    synchronized(L) { L[1]++; }

                    if (L[0]+1 < L[1]*4) Kinder.release();
                    
                    Nap.randomNap(2000, 5000);
                    
                    if (L[1]*4 < L[0]) {
                         Erzieherinen.acquire();
                    }
                    
                    synchronized(L) { L[1]--; }
                    System.out.printf("Erziehrin %d hat Kindergarten verlassen\n", this.nummer);
               }
               catch (InterruptedException ie) {
               System.out.println(ie);
               }
          }
     }
}
               
               
                    
          
          
          