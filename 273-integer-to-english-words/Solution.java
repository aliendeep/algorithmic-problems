public class Solution {
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