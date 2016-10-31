/*
http://www.cs.princeton.edu/courses/archive/spr05/cos423/lectures/06dynamic-programming.pdf
http://www.geeksforgeeks.org/weighted-job-scheduling/

Given N jobs where every job is represented by following three elements of it.

- Start Time
- Finish Time
- Profit or Value Associated
Find the maximum profit subset of jobs such that no two jobs in the subset overlap.
Example:

Input: Number of Jobs n = 4
       Job Details {Start Time, Finish Time, Profit}
       Job 1:  {1, 2, 50} 
       Job 2:  {3, 5, 20}
       Job 3:  {6, 19, 100}
       Job 4:  {2, 100, 200}
Output: The maximum profit is 250.
We can get the maximum profit by scheduling jobs 1 and 4.
Note that there is longer schedules possible Jobs 1, 2 and 3 
but the profit with this schedule is 20+50+100 which is less than 250. 
*/
import java.util.*;

class Job{
  int start;
  int finish;
  int profit;

  public Job(int s, int e, int val){
    start = s;
    finish = e;
    profit = val;
  }
}

// Time Complexity: O(n^2)
public class MaxProfitWeightedJobScheduling{
  public int latestNonConflictingJob(Job[] jobs, int n){
    for(int i=n-1; i>=0; i--){
      if(jobs[i].finish <= jobs[n].start)
          return i;
    }
    return -1;
  }

  // Memoization
  public int maxProfitRecur(Job[] jobs, int n, int[] dp){
    if(n < 0)          return 0;
    if(dp[n] != -1)    return dp[n];

    // include nth job or not
    int profitIncludingCur = jobs[n].profit;
    int nextJob = latestNonConflictingJob(jobs, n);
    if(nextJob != -1)
      profitIncludingCur += maxProfitRecur(jobs, nextJob, dp);

    int profitExcludingProfit = maxProfitRecur(jobs, n-1, dp); 
    dp[n] = Math.max(profitIncludingCur, profitExcludingProfit);
    return dp[n];
  }

  public int maxProfitMemoization(Job[] jobs){
    int n = jobs.length;
    // Sort by finish time
    Arrays.sort(jobs, new Comparator<Job>(){
      @Override
      public int compare(Job a, Job b){
        return Integer.compare(a.finish, b.finish);
      }
    });

    int[] dp = new int[n];
    Arrays.fill(dp, -1);
    return maxProfitRecur(jobs, n-1, dp);
  }

  // Table
  public int maxProfit(Job[] jobs){
    int n = jobs.length;
    // Sort by finish time
    Arrays.sort(jobs, new Comparator<Job>(){
      @Override
      public int compare(Job a, Job b){
        return Integer.compare(a.finish, b.finish);
      }
    });

    int[] dp = new int[n];
    dp[0] = jobs[0].profit;

    for(int i=1; i<n; ++i){
      dp[i] = Math.max(jobs[i].profit, dp[i-1]);
      // Find non-conflicting job before
      for(int j=i-1; j>=0; j--){
        if(jobs[j].finish <= jobs[i].start){
          dp[i] = Math.max(jobs[i].profit + dp[j], dp[i-1]);          
        }
      }
    }

    return dp[n-1];
  }

  public static void main(String[] args){
    int a[][] = {{3, 10, 20}, {1, 2, 50}, {6, 19, 100}, {2, 100, 200}};

    int n = a.length;
    Job[] t = new Job[n];
    for(int i=0; i<n; ++i){
      t[i] = new Job(a[i][0], a[i][1], a[i][2]);
    }

    MaxProfitWeightedJobScheduling s = new MaxProfitWeightedJobScheduling();
    System.out.println(s.maxProfit(t));
  }
}