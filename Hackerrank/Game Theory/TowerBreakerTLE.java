import java.io.*;
import java.util.*;

public class Solution {
    static class Tower{
        public int[] towers;
        public Tower(int[] t){
            towers = t;
        }        
        @Override
        public boolean equals(Object ob){
            if(ob instanceof Tower){
                Tower p = (Tower) ob;
                return p.hashCode() == towers.hashCode();                
            }
            return false;
        }
        @Override
        public int hashCode(){
            int xor = 0;
            for(int n : towers)
                xor ^= n;
            return xor;
        }
    }
    public static boolean isPrime(int n){
        if(n == 1)
            return false;
        if(n == 2)
            return true;
        if(n % 2 == 0)
            return false;
        for(int i=3; i*i<=n; i+=2)
            if(n % 2 == 0)
                return false;
        return true;
    }
    
    public static boolean canMove(Tower tower){
        for(int height : tower.towers){
            if(height == 1)
                continue;
            // else possible to move
            return true;
        }   
        return false;
    }
    
    public static boolean getWinner(Tower tower, Map<Tower, Boolean> dp){
        if(!canMove(tower))
            return false;
        if(dp.containsKey(tower))
            return dp.get(tower);
        
        for(int i=0; i<tower.towers.length; ++i){
            int height = tower.towers[i]; 
            if(height == 1)
                continue;
            
            // even number
            if(height % 2 == 0){
                // for all divisible of height
                for(int k=2; k*k<=height; k+=2){
                    tower.towers[i] = k;             
                    if(!getWinner(tower, dp)){
                        dp.put(new Tower(tower.towers), true);
                        return true;
                    }
                    tower.towers[i] = height;             
                }
            }
            // odd
            else{
                for(int k=3; k*k<=height; k+=2){
                    tower.towers[i] = k;             
                    if(!getWinner(tower, dp)){
                        dp.put(new Tower(tower.towers), true);                        
                        return true;
                    }
                    tower.towers[i] = height;             
                }                
            }
        }
        dp.put(new Tower(tower.towers), false);        
        return false;        
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Map<Tower, Boolean> dp = new HashMap<>();
        while(t-- > 0){
            int n = in.nextInt();
            int m = in.nextInt();
            int[] towers = new int[n];
            Arrays.fill(towers, m);
            Tower tow = new Tower(towers);
            if(getWinner(tow, dp))
                System.out.println("1");
            else
                System.out.println("2");                
        }
    }
}