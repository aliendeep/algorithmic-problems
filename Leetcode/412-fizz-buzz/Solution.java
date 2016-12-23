/*
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for 
the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
*/
public class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> r = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if(i % 3 == 0 && i % 5 == 0){
                r.add("FizzBuzz");
                continue;
            }
            if(i % 3 == 0){
                r.add("Fizz");
                continue;
            }
            if(i % 5 == 0){
                r.add("Buzz");
                continue;
            }
            r.add(Integer.toString(i));
        }
        return r;
    }
}

class Solution2 {
    // Not using % operation
    public List<String> fizzBuzz(int n) {
        List<String> r = new ArrayList<>();
        int fizz = 0, buzz = 0;
        for(int i=1; i<=n; i++){
            fizz++;
            buzz++;
            if(fizz == 3 && buzz == 5){
                r.add("FizzBuzz");
                fizz = 0;
                buzz = 0;
            }
            else if(fizz == 3){
                r.add("Fizz");
                fizz = 0;
            }
            else if(buzz == 5){
                r.add("Buzz");
                buzz = 0;
            }
            else
                r.add(Integer.toString(i));
        }
        return r;
    }
}