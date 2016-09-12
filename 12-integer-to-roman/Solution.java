import java.util.SortedMap;

public class Solution {
    public String intToRoman(int num) {
        // sorted order
        SortedMap<Integer, String> mapping = new TreeMap<Integer, String>(Collections.reverseOrder());
        mapping.put(1000, "M");
        mapping.put(900, "CM");
        mapping.put(500, "D");
        mapping.put(400, "CD");
        mapping.put(100, "C");
        mapping.put(90, "XC");
        mapping.put(50, "L");
        mapping.put(40, "XL");
        mapping.put(10, "X");
        mapping.put(9, "IX");
        mapping.put(5, "V");
        mapping.put(4, "IV");
        mapping.put(1, "I");
        
        StringBuffer r = new StringBuffer();
        for(Map.Entry<Integer, String> entry: mapping.entrySet()){
            while(entry.getKey() <= num){
                r.append(entry.getValue());
                num -= entry.getKey();
            }
        }
        return r.toString();
    }
}