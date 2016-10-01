class Solution {
public:
    string multiply(string num1, string num2) {
        // non negative
        // reverse the numbers
        reverse(num1.begin(), num1.end());
        reverse(num2.begin(), num2.end());
        
        int carry;
        int n1 = num1.size();
        int n2 = num2.size();
        string result(n1+n2, '0');
        for(int i=0; i<n1; i++){
            carry = 0;
            int x = num1[i] - '0';
            for(int j=0; j<n2 || carry; j++){
                int y = 0;
                if(j < n2)
                    y = num2[j] - '0';
                int tot = (result[i+j] - '0') + x*y + carry;
                result[i+j] = tot % 10 + '0';
                carry = tot/10;
            }
        }
        
        // remove the extra zeroes
        int i = n1 + n2 -1;
        while(result[i] == '0' && i){
            result.erase(i);
            i--;
        }
        reverse(result.begin(), result.end());
        return result;
        
    }
};