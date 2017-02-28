import java.util.*;

class Main{
  public static int numberOfContainers(long[] bucket, long capacity){
    // Not sorted, Need to take in order 
    int cnt = 1;
    long run = 0;
    for(long b : bucket){
      run += b;
      if(run > capacity){
        cnt++;
        run = b;
      }
    }
    return cnt;
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    while(in.hasNext()){
      int n = in.nextInt();
      int m = in.nextInt();
      long[] bucket = new long[n];
      long sum = 0;
      long minCap = Integer.MIN_VALUE;
      for(int i=0; i<n; ++i){
        bucket[i] = in.nextLong();
        sum += bucket[i];
        minCap = Math.max(minCap, bucket[i]);
      }

      long l = minCap;
      long h = sum;
      while(h - l > 5){
        long mid = (h - l)/2 + l;
        int containers = numberOfContainers(bucket, mid);
        // increase capacity
        if(containers > m){
          l = mid + 1;
        }
        // containers >= m
        else{
          h = mid;
        }
      }
      long resultCap = -1;
      for(long i=l; i<=h; ++i){
        if(numberOfContainers(bucket, i) <= m){
          resultCap = i;
          break;
        }
      }
      System.out.println(resultCap);
    }
  }  
}