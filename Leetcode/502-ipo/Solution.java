/*
Suppose LeetCode will start its IPO soon. In order to sell a good price of its 
shares to Venture Capital, LeetCode would like to work on some projects to 
increase its capital before the IPO. Since it has limited resources, it can only 
finish at most k distinct projects before the IPO. Help LeetCode design the best 
way to maximize its total capital after finishing at most k distinct projects.

You are given several projects. For each project i, it has a pure profit Pi and 
a minimum capital of Ci is needed to start the corresponding project. Initially, 
you have W capital. When you finish a project, you will obtain its pure profit 
and the profit will be added to your total capital.

To sum up, pick a list of at most k distinct projects from given projects to 
maximize your final capital, and output your final maximized capital.

Example 1:
Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].

Output: 4

Explanation: Since your initial capital is 0, you can only start the project indexed 0.
             After finishing it you will obtain profit 1 and your capital becomes 1.
             With capital 1, you can either start the project indexed 1 or the project indexed 2.
             Since you can choose at most 2 projects, you need to finish the project 
             indexed 2 to get the maximum capital.
             Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
Note:
You may assume all numbers in the input are non-negative integers.
The length of Profits array and Capital array will not exceed 50,000.
The answer is guaranteed to fit in a 32-bit signed integer.
*/
public class Solution {
    class Pair{
        int profit;
        int cap;
        // Keep an id for multi treeset
        int id;
        public Pair(int p, int c, int id){
            profit = p;
            cap = c;
            this.id = id;
        }
        @Override
        public boolean equals(Object ob){
            if(ob instanceof Pair){
                Pair p = (Pair)ob;
                return p.profit == profit && p.cap == cap && p.id == id;
            }
            return false;
        }
        @Override
        public int hashCode(){
            return Objects.hash(profit, cap, id);
        }
    }

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        if(k == 0)      return 0;
        int n = Profits.length;
        
        Pair[] a = new Pair[n];
        for(int i=0; i<n; ++i){
            a[i] = new Pair(Profits[i], Capital[i], i);    
        }   
        
        Arrays.sort(a, new Comparator<Pair>(){
            // Increasing order of capacity
            @Override
            public int compare(Pair x, Pair y){
                if(x.cap == y.cap)
                    // Decreasing order profit
                    return Integer.compare(y.profit, x.profit);     
                return Integer.compare(x.cap, y.cap);
            }
        });
        
        // Active set
        // Multi TreeSet
        TreeSet<Pair> active = new TreeSet<>(new Comparator<Pair>(){
            @Override
            public int compare(Pair x, Pair y){  
                if(x.profit != y.profit) {
                    return Integer.compare(y.profit, x.profit);
                }
                if(x.cap != y.cap) {
                    return Integer.compare(x.cap, y.cap);
                }
                return Integer.compare(x.id, y.id);
            }
        });
        int i = 0;
        for(i=0; i<n; ++i){
            if(W >= a[i].cap){
                active.add(a[i]);
            }
            else
                break;
        }
        int curCap = W;
        while(!active.isEmpty()){
            if(k == 0)      break;
            Pair p = active.first();
            active.remove(p);

            curCap = curCap + p.profit;
            // Check if we need to update active set
            while(i < n && curCap >= a[i].cap){
                active.add(a[i++]);
            }
            --k;
        }
        return curCap;
    }
}
