/*
Given an array S of n integers, are there elements a, b, c, and d in S such that 
a + b + c + d = target? Find all unique quadruplets in the array which gives 
the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
Example : 
Given array S = {1 0 -1 0 -2 2}, and target = 0
A solution set is:

    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
    (-1,  0, 0, 1)
Also make sure that the solution set is lexicographically sorted.
Solution[i] < Solution[j] iff Solution[i][0] < Solution[j][0] OR 
(Solution[i][0] == Solution[j][0] AND ... Solution[i][k] < Solution[j][k])
*/
public class Solution {
  public ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> a, int target) {
        int n = a.size();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(n < 4)
            return result;
        Collections.sort(a);
        Set<ArrayList<Integer>> set = new HashSet<>();
        for(int i = 0; i < n-3; i++){
            for(int j = i+1; j < n-2; j++){
                int k = j + 1;
                int l = n - 1;
                
                while(k < l){
                    int sum = a.get(i) + a.get(j) + a.get(k) + a.get(l);
                    if(sum == target){
                        ArrayList<Integer> r = new ArrayList<Integer>();
                        r.add(a.get(i));
                        r.add(a.get(j));
                        r.add(a.get(k));
                        r.add(a.get(l));   
                        if(!set.contains(r)){
                            set.add(r);
                            result.add(r);
                        }
                        k++;
                        l--;
                    }
                    else if(sum > target){
                        l--;
                    }
                    else if(sum < target){
                        k++;
                    }
                }
            }
        }
        return result;
    }
}
