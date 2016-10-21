/*
A gene string can be represented by an 8-character long string, with choices from "A","C","G","T". 
Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string. 
For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation. 
Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string. 

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1. 

For example, 
bank: "AACCGGTA" 
start: "AACCGGTT" 
end: "AACCGGTA" 
return: 1

bank: "AACCGGTA", "AACCGCTA", "AAACGGTA"
start: "AACCGGTT"
end: "AAACGGTA"
return: 2
*/

// End word should be in the dictionary too
public class Solution {
    // BFS
    public int minMutation(String start, String end, String[] bank) {
        if(start.equals(end))       return 0;
        if(bank.length == 0)        return -1;
            
        Set<String> dict = new HashSet<>();
        for(String gene : bank)
            dict.add(gene);

        char[] dna = {'A', 'C', 'G', 'T'};
        
        Queue<String> Q = new LinkedList<>();
        Q.add(start);
        
        int steps = 0;
        int levCnt = Q.size();
        
        while(!Q.isEmpty()){
            String front = Q.remove();
            levCnt--;
            
            for(int pos=0; pos<front.length(); ++pos){
                StringBuilder t = new StringBuilder(front);
                for(int i=0; i<4; ++i){
                    t.setCharAt(pos, dna[i]);
                    String mutated = t.toString();
                    
                    if(dict.contains(mutated)){
                        if(mutated.equals(end))
                            return steps + 1;

                        Q.add(mutated);
                        dict.remove(mutated);
                    }
                    // reset
                    t.setCharAt(pos, front.charAt(pos));
                }
            }
            // traversed all possible mutations at this level
            if(levCnt == 0){
                levCnt = Q.size();
                steps++;
            }
        }
        return -1;
    }
}