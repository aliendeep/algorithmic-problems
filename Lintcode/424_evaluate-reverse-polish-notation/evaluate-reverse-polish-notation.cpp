/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/evaluate-reverse-polish-notation
@Language: C++
@Datetime: 15-10-20 00:56
*/

class Solution {
public:
    /**
     * @param tokens The Reverse Polish Notation
     * @return the value
     */
    int evalRPN(vector<string>& tokens) {
        // Write your code here
        stack<int> s;
        int a, b;
        for(int i=0; i<tokens.size(); i++){
        	if(tokens[i] == "+" || tokens[i] == "-"|| tokens[i] == "*" || tokens[i] == "/"){
        		b = s.top();
        		s.pop();
        		a = s.top();
        		s.pop();

        		if(tokens[i] == "+")
        			s.push(a + b);
        		else if(tokens[i] == "-")
        			s.push(a - b);
        		else if(tokens[i] == "*")
        			s.push(a*b);
        		else
        			s.push(a/b);
        	}
        	else
        		s.push(atoi(tokens[i].c_str()));
        }
        
        int t = s.top();
        s.pop();
        return t;
    }
};
