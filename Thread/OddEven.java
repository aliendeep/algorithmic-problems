// EPI
/* 
Thread t1 prints odd numbers from 1 to 100 and Thread t2 prints even numbers from 1 to 100 

- Use wait() and notify()
*/

public class OddEven{
  public static class OddEvenMonitor{
    public static final boolean ODD_TURN = true;
    public static final boolean EVEN_TURN = false;
    // Current turn
    private boolean turn = ODD_TURN;

    public synchronized void waitTurn(boolean myTurn){
      // Check if current turn is my turn
      while(turn != myTurn){
        try{
          wait();
        } 
        catch(Exception e){

        }
      }
    }

    public synchronized void toggleTurn(){
      turn ^= true;
      notify();
    }
  }

  public static class OddThread implements Runnable{
    private final OddEvenMonitor monitor;
    public OddThread(OddEvenMonitor m){
      monitor = m;
    }

    @Override
    public void run(){
      for(int i=1; i<=100; i+=2){
        // Check if it's my turn
        monitor.waitTurn(monitor.ODD_TURN);
        System.out.print(i + " ");
        // notify the other thread that it's their turn
        monitor.toggleTurn();
      }
    }
  }

  public static class EvenThread implements Runnable{
    private final OddEvenMonitor monitor;
    public EvenThread(OddEvenMonitor m){
      monitor = m;
    }

    @Override
    public void run(){
      for(int i=2; i<=100; i+=2){
        // Check if it's my turn
        monitor.waitTurn(monitor.EVEN_TURN);
        System.out.print(i + " ");
        // notify the other thread that it's their turn
        monitor.toggleTurn();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    OddEvenMonitor monitor = new OddEvenMonitor();
    Thread t1 = new Thread(new OddThread(monitor));
    Thread t2 = new Thread(new EvenThread(monitor));
    t1.start();
    t2.start();
    t1.join();
    t2.join();    
  }
}