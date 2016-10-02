/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
*/
public class Solution {
    public enum STATE {
        START, NUMBER, DOT, DECIMAL, EPOW, EPOWSIGN, EPOWNUM, DOT_AFTER_NUMBER, UNKNOWN
    }
    
    public static STATE getNextState(STATE curState, char c){
        switch(curState){
            case START:
                // found dot
                if(c == '.')
                    return STATE.DOT;
                else if(Character.isDigit(c))
                    return STATE.NUMBER;
                break;
            case NUMBER:
                if(Character.isDigit(c))
                    return STATE.NUMBER;
                else if(c == '.')
                    return STATE.DOT_AFTER_NUMBER;
                else if(c == 'e')
                    return STATE.EPOW;
                break;
            case DOT:
                if(Character.isDigit(c))
                    return STATE.DECIMAL;
                break;
            case DOT_AFTER_NUMBER:
                if(Character.isDigit(c))
                    return STATE.DECIMAL;
                else if(c == 'e')
                    return STATE.EPOW;
                break;
            case DECIMAL:
                if(Character.isDigit(c))
                    return STATE.DECIMAL;
                else if(c == 'e')
                    return STATE.EPOW;
                break;
            case EPOW:
                if(c == '+' || c == '-')        
                    return STATE.EPOWSIGN;         
                else if(Character.isDigit(c)) 
                    return STATE.EPOWNUM;         
                break;                          
            case EPOWSIGN:
            case EPOWNUM:
                if(Character.isDigit(c))      
                    return STATE.EPOWNUM;
                break;
            default:
                return STATE.UNKNOWN;
        }   
        return STATE.UNKNOWN;
    }
    
    // DFA
    public boolean isNumber(String str) {
        int n = str.length();
        if(n == 0)
            return false;
        
        String s = str.trim();
        if(s.length() == 0)
            return false;
        // Skip the sign
        int cur = 0;
        if(s.charAt(0) == '+' || s.charAt(0) == '-')
            cur++;
            
        STATE curState = STATE.START;
        for(int i=cur; i<s.length(); i++){
            curState = getNextState(curState, s.charAt(i));
            if(curState == STATE.UNKNOWN)
                return false;
        }
       // No number after pow, no number before or after dot return false
        if(curState == STATE.EPOW || curState == STATE.DOT || curState == STATE.EPOWSIGN)
            return false;
        return true;
    }
}