public class Solution {
    // StringBuilder
    public void addOperatorHelper(String num, int target, 
                                    int lev, StringBuilder curExpr, long prev, long mult, 
                                    List<String> result){
        if(lev == num.length()){
            if(prev == target){
                result.add(curExpr.toString());
                return;
            }
        }
        for(int i=lev; i<num.length(); i++){
            if(i > lev && num.charAt(lev) == '0')    
                break;
            int slen = curExpr.length();
            long val = Long.parseLong(num.substring(lev, i+1));
            if(lev == 0){
                curExpr.append(val);
                addOperatorHelper(num, target, i+1, curExpr, val, val, result);
                curExpr.setLength(slen);
            }
            else{
                curExpr.append('+');
                curExpr.append(val);
                addOperatorHelper(num, target, i+1, curExpr, prev + val, val, result);
                curExpr.setLength(slen);

                curExpr.append('-');
                curExpr.append(val);
                addOperatorHelper(num, target, i+1, curExpr, prev - val, -val, result);
                curExpr.setLength(slen);

                curExpr.append('*');
                curExpr.append(val);
                addOperatorHelper(num, target, i+1, curExpr, prev - mult + mult*val, mult*val, result);
                curExpr.setLength(slen);
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