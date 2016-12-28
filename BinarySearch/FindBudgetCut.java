import java.util.*;

// O(nlogn Solution)
// O(1) space
public class FindBudgetCut{
  // We can modify getbudget to return the budget in less than O(1) time.
  // but the optimization is not ncesseary as we have to sort the array
  // anyway
  public int getBudget(int[] grant, int cap){
    int n = grant.length;
    int sum = 0;
    for(int i=0; i<n; ++i){
      if(grant[i] <= cap)
        sum += grant[i];
      else
        sum += cap;
    }
    System.out.println(cap + " budget " + sum);
    return sum;
  }

  public int cap(int[] grant, int budget){
    int n = grant.length;
    if(n == 0)
        return 0;

    Arrays.sort(grant);    
    int sum = 0;
    for(int i=1; i<n; ++i)
        sum += grant[i];

    // total sum is within budget, then return the highest grant
    if(sum <= budget){
      return grant[n-1];
    }

    // Binary Search on the cap limit
    int l = grant[0], h = grant[n-1];
    while(h - l > 3){
      int mid = (l+h)/2;
      // need to cap more grants by lowering cap limit
      if(getBudget(grant, mid) > budget){
        h = mid - 1;   
      }
      // getBudget(grant, mid) < budget
      else{
        // we have cut way too many grants
        // need to increase cap limit
        l = mid;
      }
    }
    // we want to affect minimum number of grants, so we need to make the 
    // cut point as high as possible
    for(int i=h; i>=l; --i){
      if(getBudget(grant, i) <= budget)
        return i;
    }
    return 0;
  }

  public static void main(String[] args){
    int[] grant = {1, 2, 3, 4, 5, 6, 7};
    FindBudgetCut ob = new FindBudgetCut();
    System.out.println(ob.cap(grant, 10));
  }  
}