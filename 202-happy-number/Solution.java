public class Solution {
    public List<Integer> split(int n){
        // Split the number into digits
        List<Integer> cur = new ArrayList<>();
        while(n != 0){
            cur.add(n%10);
            n = n/10;
        }
        return cur;
    }
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<Integer>();
        while(n != 1){
            // cycle
            if(set.contains(n))
                return false;
            set.add(n);
            List<Integer> cur = split(n);
            int sum = 0;
            for(int i : cur){
                sum += i*i;
            }
            n = sum;
        }
        return true;
    }
}