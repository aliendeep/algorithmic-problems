/*
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
*/
import java.util.*;

// Storing all possible sum takes O(n^2) space
public class TwoSum {
  // contains all unique number
  List<Integer> nums;
  // Contains numbers and count
  Map<Integer, Integer> map;

  public TwoSum(){
    nums = new ArrayList<>();
    map = new HashMap<>();
  }

  // Add the number to an internal data structure.
  public void add(int number) {
    if(map.containsKey(number)){
      map.put(number, 2);
      return;
    }
    map.put(number, 1);
    nums.add(number);
  }
  // Find if there exists any pair of numbers which sum is equal to the value.
  public boolean find(int value) {
    for(int n : nums){
      int target = value - n;
      // if same number needed, 4 + 4 or -1 + 1 needed
      if(target == n){
        if(map.get(n) == 2)
          return true;
      }
      else if(map.containsKey(target))
          return true;
    }
    return false;
  }

  public static void main(String[] args){
    TwoSum twoSum = new TwoSum();
    twoSum.add(0);
    twoSum.add(-1);
    twoSum.add(1);
    System.out.println(twoSum.find(0));    
  }
}

// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);