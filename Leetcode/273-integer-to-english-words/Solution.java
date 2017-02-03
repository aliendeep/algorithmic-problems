/*
Convert a non-negative integer to its english words representation. Given input 
is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Hint:

Did you see a pattern in dividing the number into chunk of words? For example, 
123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that 
takes a number less than 1000 and convert just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work 
with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)
*/

// Recursive
public class Solution {
    public String[] lessThan20 = {" ", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", 
                                       "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", 
                                       "Seventeen", "Eighteen", "Nineteen"};
    public String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public String[] bigs = {"Hundred", "Thousand", "Million", "Billion"};
    public int[] numbers = {100, 1000, 1000000, 1000000000};

    public String numberToWords(int num) {
        if(num == 0)    return "Zero";

        StringBuilder r = new StringBuilder();
        for(int i=numbers.length - 1; i >= 0; --i){
            if(num < numbers[i])
                continue;
            int div = num/numbers[i];
            r.append(numberToWords(div)).append(" ").append(bigs[i]).append(" ");
            num = num % numbers[i];            
        }
        
        if(num < 20){
            r.append(lessThan20[num]);
        }
        else{
            r.append(tens[num/10]).append(" ").append(lessThan20[num%10]);
        }
        return r.toString().trim();
    }
}

// Recursive2
class Solution2 {
    public String[] lessThan20 = {" ", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", 
                                       "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", 
                                       "Seventeen", "Eighteen", "Nineteen"};
    public String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String helper(int num) {
        String result;
        if(num < 20)                result = lessThan20[num];
        else if(num < 100)          result = tens[num/10] + " " + lessThan20[num%10];
        else if(num < 1000)         result = helper(num/100) + " Hundred " +  helper(num % 100);
        else if(num < 1000000)      result = helper(num/1000) + " Thousand " + helper(num % 1000);
        else if(num < 1000000000)   result = helper(num/1000000) + " Million " + helper(num % 1000000);
        else                        result = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
        return result.trim();
    }
    
    public String numberToWords(int num) {
        if(num == 0)    return "Zero";
        return helper(num);
    }
}

// Recursive 3
class Solution3 {
    public String[] lessThan20 = {" ", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", 
                                       "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", 
                                       "Seventeen", "Eighteen", "Nineteen"};
    public String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public String[] bigs = {"Hundred", "Thousand", "Million", "Billion"};
    public int[] numbers = {100, 1000, 1000000, 1000000000};

    public String helper(int num) {
        String result = null;
        if(num < 20)                result = lessThan20[num];
        else if(num < 100)          result = tens[num/10] + " " + lessThan20[num%10];
        for(int i=numbers.length-1; i>=0; --i){
            int div = num / numbers[i];
            if(div == 0)
                continue;
            result = helper(div) + " " + bigs[i] + " " + helper(num % numbers[i]);
            break;
        }
        return result.trim();
    }
    
    public String numberToWords(int num) {
        if(num == 0)    return "Zero";
        return helper(num);
    }
}
// Iterative
class Solution4 {
    public String[] digits0 = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    public String[] digits1 = {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public String[] tens = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if(num == 0)
            return "Zero";
        int cnt = 0;
        StringBuffer r = new StringBuffer();
        if(num >= 1000000000){
            r.append(convert(num/1000000000));
            r.append(" Billion");
            num %= 1000000000;
        }

        if(num >= 1000000){
            r.append(convert(num/1000000));
            r.append(" Million");
            num %= 1000000;
        }
        if(num >= 1000){
            r.append(convert(num/1000));
            r.append(" Thousand");
            num %= 1000;
        }
        
        if(num > 0)
            r.append(convert(num));
            
        return r.toString().trim();
    }

    public String convert(int n){
        StringBuffer r = new StringBuffer();
        if(n >= 100){
            r.append(" ");
            r.append(digits0[n/100-1]);
            r.append(" Hundred");
            n = n % 100;
        }
        
        if(n >= 11 && n <= 19){
            r.append(" ");
            r.append(digits1[n-11]);
            return r.toString();
        }
        
        if(n == 10 || n >= 20){
            r.append(" ");
            r.append(tens[n/10 - 1]);
            n = n % 10;
        }
        
        if(n >= 1 && n < 10){
            r.append(" ");
            r.append(digits0[n - 1]);
        }
        return r.toString();
    }
}
