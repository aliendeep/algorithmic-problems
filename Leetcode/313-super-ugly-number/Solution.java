/*
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
*/

public class Solution {
    // Time complexity: O(nlogk)
    class Entry{
      int num;
      int index;
      int basePrime;
      public Entry(int n, int i, int prime){
          num = n;
          // current index
          index = i;
          basePrime = prime;
      }
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        // Size of min heap : k
        PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(primes.length, new Comparator<Entry>(){
            @Override
            public int compare(Entry a, Entry b){
                return a.num - b.num;
            }
        });
        
        // Add the primes in the minHeap
        // klogk
        for(int prime : primes){
            minHeap.add(new Entry(prime, 1, prime));    
        }
        
        int[] ugly = new int[n];
        ugly[0] = 1;
        
        for(int i=1; i<n; i++){
            ugly[i] =  minHeap.peek().num;
            while(minHeap.peek().num == ugly[i]){
                // insert the next number in the minHeap
                Entry e = minHeap.poll();
                minHeap.add(new Entry(e.basePrime*ugly[e.index], e.index + 1, e.basePrime));
            }
            
        }
        return ugly[n-1];
    }
}