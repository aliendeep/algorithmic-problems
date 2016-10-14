  public class LetterCombinationsPhoneNumber {
    String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public void bktk(String digits, int lev, StringBuilder cur, List<String> result){
        if(lev == digits.length()){
            result.add(cur.toString());
            return;
        }
        
        String t = mapping[digits.charAt(lev) - '0'];
        for(int i=0; i<t.length(); i++){
            cur.append(t.charAt(i));
            bktk(digits, lev+1, cur, result);
            cur.deleteCharAt(cur.length()-1);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length() == 0)
            return result;
        StringBuilder cur = new StringBuilder();
        bktk(digits, 0, cur, result);
        return result;
    }
}