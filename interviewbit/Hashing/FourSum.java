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
                    int sum = a.get(i) +a.get(j) + a.get(k) + a.get(l);
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
