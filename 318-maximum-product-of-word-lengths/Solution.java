public class Solution {
    // lower case letters - 26 bits required, So int is sufficient
    public int maxProduct(String[] words) {
        int n = words.length;
        if(n == 0)
            return 0;

        int[] map = new int[n];
        Arrays.fill(map, 0);
        
        for(int i=0; i<n; i++){
            for(int j=0; j<words[i].length(); j++){
                map[i] |= 1<<(words[i].charAt(j) - 'a');
            }
        }
        
        int maxProd = 0;
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                // no common letters between ith word and jth word
                if((map[i] & map[j]) == 0){
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }        
        return maxProd;
    }
}