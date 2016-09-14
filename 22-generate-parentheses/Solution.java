public class Solution {
    public void genParenthesis(int leftParenRem, int rightParenRem, StringBuffer cur, List<String> r){
        if(leftParenRem == 0 && rightParenRem == 0){
            r.add(new String(cur.toString()));
            return;
        }
        if(leftParenRem > 0){
            cur.append('(');
            genParenthesis(leftParenRem-1, rightParenRem, cur, r);
            cur.deleteCharAt(cur.length()-1);
        }
        if(rightParenRem > 0 && rightParenRem > leftParenRem){
            cur.append(')');
            genParenthesis(leftParenRem, rightParenRem-1, cur, r);
            cur.deleteCharAt(cur.length()-1);
        }
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> r = new ArrayList<>();
        if(n == 0)
            return r;
        StringBuffer cur = new StringBuffer();
        genParenthesis(n, n, cur, r);
        return r;
    }
}