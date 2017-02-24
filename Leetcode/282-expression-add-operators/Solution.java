/*
Given a string that contains only digits 0-9 and a target value, 
return all possibilities to add binary operators (not unary) +, -, or * between 
the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
*/

// Char Array
public class Solution {
    public void addOperatorHelper(char[] num, int target, 
                                    int lev, StringBuilder curExpr, long cur, long prev, 
                                    List<String> result){
        if(lev == num.length){
            if(cur == target){
                result.add(curExpr.toString());
                return;
            }
        }
        long val = 0;
        for(int i=lev; i<num.length; i++){
            // Can't start with 0 if length > 1
            if(num[lev] == '0' && i != lev)    
                break;
            int slen = curExpr.length();
            val = val*10 + num[i] - '0';
            // Binary Operator (Not unary)
            if(lev == 0){
                curExpr.append(val);
                addOperatorHelper(num, target, i+1, curExpr, val, val, result);
                curExpr.setLength(slen);
            }
            else{
                curExpr.append('+');
                curExpr.append(val);
                addOperatorHelper(num, target, i+1, curExpr, cur + val, val, result);
                curExpr.setLength(slen);

                curExpr.append('-');
                curExpr.append(val);
                addOperatorHelper(num, target, i+1, curExpr, cur - val, -val, result);
                curExpr.setLength(slen);

                curExpr.append('*');
                curExpr.append(val);
                addOperatorHelper(num, target, i+1, curExpr, cur - prev + prev*val, prev*val, result);
                curExpr.setLength(slen);
            }
        }
    }
    
    public List<String> addOperators(String num, int target) {
        List<String> r = new ArrayList<>();
        if(num.length() == 0) 
            return r;
         
        addOperatorHelper(num.toCharArray(), target, 0, new StringBuilder(), 0, 0, r);
        return r;
    }
}

class Solution2 {
    public void addOperatorHelper(String num, int target, 
                                    int lev, StringBuilder runExpr, long cur, long prev, 
                                    List<String> result){
        if(lev == num.length()){
            if(cur == target){
                result.add(runExpr.toString());
                return;
            }
        }
        long val = 0;
        for(int i=lev; i<num.length(); ++i){
            // Can't start with 0 if length > 1
            if(i > lev && num.charAt(lev) == '0')    
                return;

            int slen = runExpr.length();
            val = val*10 + num.charAt(i) - '0';
            
            if(lev == 0){
                runExpr.append(val);
                addOperatorHelper(num, target, i+1, runExpr, val, val, result);
                runExpr.setLength(slen);
            }
            else{
                runExpr.append('+');
                runExpr.append(val);
                addOperatorHelper(num, target, i+1, runExpr, cur + val, val, result);
                runExpr.setLength(slen);

                runExpr.append('-');
                runExpr.append(val);
                addOperatorHelper(num, target, i+1, runExpr, cur - val, -val, result);
                runExpr.setLength(slen);

                runExpr.append('*');
                runExpr.append(val);
                addOperatorHelper(num, target, i+1, runExpr, cur - prev + prev*val, prev*val, result);
                runExpr.setLength(slen);
            }
        }
    }
    
    public List<String> addOperators(String num, int target) {
        List<String> r = new ArrayList<>();
        if(num.length() == 0) 
            return r;
         
        addOperatorHelper(num, target, 0, new StringBuilder(), 0, 0, r);
        return r;
    }
}
