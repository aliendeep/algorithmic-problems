// Hashset to detect duplicates
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        
        List<List<Integer>> result = new ArrayList<>();
        if(n < 4)   return result;
        Arrays.sort(nums);
        
        Set<List<Integer>> set = new HashSet<>();
        for(int i = 0; i<n-3; i++){
            for(int j = i+1; j<n-2; j++){
                int k = j + 1;
                int l = n - 1;
                
                while(k < l){
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if(sum == target){
                        List<Integer> r = new ArrayList<Integer>();
                        r.add(nums[i]);
                        r.add(nums[j]);
                        r.add(nums[k]);
                        r.add(nums[l]);   
                        // unique set
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

// Without Hashset
class Solution2 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        
        List<List<Integer>> result = new ArrayList<>();
        if(n < 4)   return result;
        Arrays.sort(nums);
        
        Set<List<Integer>> set = new HashSet<>();
        for(int i = 0; i<n-3; i++){
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            for(int j = i+1; j<n-2; j++){
                if(j > i+1 && nums[j] == nums[j-1])
                    continue;
                int k = j + 1;
                int l = n - 1;
                
                while(k < l){
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if(sum == target){
                        List<Integer> r = new ArrayList<Integer>();
                        r.add(nums[i]);
                        r.add(nums[j]);
                        r.add(nums[k]);
                        r.add(nums[l]);   
                        // unique set
                        if(!set.contains(r)){
                            set.add(r);
                            result.add(r);
                        }
                        k++;
                        l--;
                        if(k < l && nums[l] == nums[l+1])
                            l--;
                        if(k < l && nums[k] == nums[k-1])
                            k++;
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

// TLE
class Solution3 {
    // Bruteforce : O(n^4)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        
        List<List<Integer>> result = new ArrayList<>();
        if(n < 4)   return result;
        Arrays.sort(nums);
        
        Set<List<Integer>> set = new HashSet<>();
        for(int i=0; i<n-3; i++){
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            for(int j=i+1; j<n-2; j++){
                if(j > i+1 && nums[j] == nums[j-1])
                    continue;
                for(int k=j+1; k<n-1; k++){
                    if(k > j+1 && nums[k] == nums[k-1])
                        continue;
                    for(int l=k+1; l<n; l++){
                        if(l > k+1 && nums[l] == nums[l-1])
                            continue;
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if(sum == target){
                            List<Integer> r = new ArrayList<Integer>();
                            r.add(nums[i]);
                            r.add(nums[j]);
                            r.add(nums[k]);
                            r.add(nums[l]);   
                            result.add(r);
                        }
                    }
                }
            }
        }
        return result;
    }
}