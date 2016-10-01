public class Solution {
    public String getHint(String secret, String guess) {
        int n = secret.length();
        int bull = 0;
        int cow = 0;
        int[] cnt = new int[10];
        Arrays.fill(cnt, 0);
        
        for(int i=0; i<n; i++){
            if(secret.charAt(i) == guess.charAt(i))
                bull++;
            else
                cnt[guess.charAt(i)-'0']++;
        }
        
        for(int i=0; i<n; i++){
            if(secret.charAt(i) != guess.charAt(i) && cnt[secret.charAt(i)-'0'] > 0){
                cnt[secret.charAt(i)-'0']--;
                cow++;
            }
        }  
        StringBuffer r = new StringBuffer();
        r.append(Integer.toString(bull));
        r.append("A");
        r.append(Integer.toString(cow));
        r.append("B");
        return r.toString();
    }
}