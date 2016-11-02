// EPI 
/*
What is the minimum and maximum value of the counter (N = 100)?
- Maximum: 200
Scenario:
- Thread t1 reads the value of the counter and loads 0
- Thread t1 runs N times (counter = 100)
- Thread t2 reads the value of the counter and loads 100
- Thread t2 runs N times (counter = 200)

- Minimum: 2
Scenario:
- Thread t1 and t2 reads and loads the value of counter, which is 0
- Thread t2 executes the loop N-1 times
- Thread t1 doesn't know about the updated value of counter and writes 1 to the counter
- Thread t2 loads the value of the counter, which is 1
- Thread t1 executes the loop for the remaining N-1 times
- Thread t2 writes 2 to the counter
*/
public class TwoThreadIncrement{
  public static int counter;
  public static int N;

  public static class IncrementCounter implements Runnable{
    public void run(){
      for(int i=0; i<N; ++i){
        counter++;
      }
    }
  }
  public static void main(String[] args) throws Exception {
    N = 100;
    Thread t1 = new Thread(new IncrementCounter());
    Thread t2 = new Thread(new IncrementCounter());

    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println(counter);
  }
}
