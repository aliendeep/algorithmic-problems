/*
@Copyright:LintCode
@Author:   mts
@Problem:  http://www.lintcode.com/problem/min-stack
@Language: C++
@Datetime: 15-10-16 04:57
*/

class MinStack {
    stack<int> S;
    stack<int> minS;
public:
    MinStack() {
    }

    void push(int number) {
        S.push(number);
        if(minS.empty() || minS.top() >= number)
            minS.push(number);
    }

    int pop() {
        int t = S.top();
        S.pop();
        if(t == minS.top())
            minS.pop();
        return t;
    }

    int min() {
        return minS.top();
    }
};

