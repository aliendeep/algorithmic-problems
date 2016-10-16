public class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> r = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if(i % 3 == 0 && i % 5 == 0){
                r.add("FizzBuzz");
                continue;
            }
            if(i % 3 == 0){
                r.add("Fizz");
                continue;
            }
            if(i % 5 == 0){
                r.add("Buzz");
                continue;
            }
            r.add(Integer.toString(i));
        }
        return r;
    }
}