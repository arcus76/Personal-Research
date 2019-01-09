public class Nap {
     
     public static void nap (int milliSekunden) {
          try {
               Thread.sleep(milliSekunden);
          }
          catch (InterruptedException ie) {
               System.out.println(ie);
          }
     }
     
     public static void randomNap (int minMillisekunden, int maxMillisekunden) {
          int ms = minMillisekunden + (int)(Math.random() * ((maxMillisekunden - minMillisekunden) + 1));
          nap(ms);
     }
     
} 