import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.BrokenBarrierException;

/*Die Idee ist folgende:
 * 2 Gänge für Wasserstoffatome und Sauerstoffatome mit Semaphoren
 * Und Wartezimmer für maximal 2H+ und 1O-- mit Barriere
 * Am Ende jedes Gangs steht ein Semaphore:
 * Maximal 2H+ und 1O-- sind erlaubt Wartezimmer einzutreten
 * Alle anderen müssen vor Semaphoren warten
 * Als 3 Atome Wartezimmer durch Barriere verlassen,
 * dürfen nächste 3 Atome Wartezimmer beitreten.
 * Prozess wiederholt sich also */

public class Water {
     private static CyclicBarrier BARRIERE = new CyclicBarrier(3, new Molecule());
     
     public static void main (String[] args) throws InterruptedException {
          for (int i = 0; i < 11; i++) {
               new Thread(new Wasserstoff(i)).start();
               Thread.sleep(200);
          }
          for (int i = 0; i < 8; i++) {
               new Thread(new Sauerstoff(i)).start();
               Thread.sleep(200);
          }
           for (int i = 0; i < 5; i++) {
               new Thread(new Wasserstoff(i)).start();
               Thread.sleep(200);
          }
     }
     
     public static class Molecule extends Thread {
          
          public void run() {
               try {
                    Thread.sleep(400);
                    System.out.println("H2O ist gebildet");
               }
               catch (InterruptedException ie) {
                    System.out.println(ie);
               }
          }
     }
     
     public static class Wasserstoff extends Thread {
          private int atomNummer;
          public static Semaphore H = new Semaphore(2);
          
          public Wasserstoff(int nummer) {
               this.atomNummer = nummer;
          }
          
          public void run() {
               try {
                    H.acquire();
                    System.out.printf("Wasserstoff %d steht vor Synthese-Kamera in Wartezimmer\n", atomNummer);
                    BARRIERE.await();
                    System.out.printf("Wasserstoff %d geht ins Synthese-Kamera\n", atomNummer);
                    H.release();
               }
               catch (InterruptedException ie) {
                    System.out.println(ie);
               }
               catch (BrokenBarrierException ie) {
                    System.out.println(ie);
               }
          }
     }
     
     public static class Sauerstoff extends Thread {
          private int atomNummer;
          public static Semaphore O = new Semaphore(1);
          
          public Sauerstoff(int nummer) {
               this.atomNummer = nummer;
          }
          
          public void run() {
               try {
                    O.acquire();
                    System.out.printf("Sauerstoff %d steht vor Synthese-Kamera in Wartezimmer\n", atomNummer);
                    BARRIERE.await();
                    System.out.printf("Sauerstoff %d geht ins Synthese-Kamera\n", atomNummer);
                    O.release();
               }
               catch (InterruptedException ie) {
                    System.out.println(ie);
               }
               catch (BrokenBarrierException ie) {
                    System.out.println(ie);
               }
          }
     }
}
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           
                                           