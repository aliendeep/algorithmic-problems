public class Solution {
    public int compareVersion(String version1, String version2) {
        if(version1.compareTo(version2) == 0){
            return 0;
        }
        
        // Need to escape the dot as dot means anything (regex)
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        // Compare common parts
        int l1 = v1.length;
        int l2 = v2.length;
        int l = Math.min(l1, l2);
        for(int i=0; i<l; i++){
            // To compare, 01 and 1
            int x = Integer.parseInt(v1[i]);
            int y = Integer.parseInt(v2[i]);
            if(x > y)       return 1;
            if(y > x)       return -1;
        }
        
        if(l1 == l2)
            return 0;
        // handle cases like "1" and "1.1"
        if(l1 < l2){
            for(int i=l1; i<l2; i++){
                int x = Integer.parseInt(v2[i]);
                // v2 is greater than v1
                if(x != 0)
                    return -1;
            }
        }        
        else{
            for(int i=l2; i<l1; i++){
                int x = Integer.parseInt(v1[i]);
                // v1 is greater than v2
                if(x != 0)
                    return 1;
            }
        } 
        return 0;
    }
}