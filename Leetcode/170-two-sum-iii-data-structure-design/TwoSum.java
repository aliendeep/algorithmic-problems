/*
Design and implement a TwoSum class. It should support the following operations: 
add and find .

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5); find(4) -> true find(7) -> false
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
    else
      nums.add(number);

    map.put(number, 1);    
  }
  // Find if there exists any pair of numbers which sum is equal to the value.
  public boolean find(int value) {
    for(int n : nums){
      int target = value - n;
      // if same number needed, 4 + 4 or -1 + 1 needed
      if(target == n){
        if(map.get(n) > 1)
          return true;
      }
      else if(map.containsKey(target))
          return true;
    }
    return false;
  }
}
// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);

public class TwoSum2 {
    // number, cnt
    Map<Integer, Integer> map;

    public TwoSum(){
      map = new HashMap<>();
    }
    
    // Add the number to an internal data structure.
  public void add(int number) {
      Integer cnt = map.get(number);
      if(cnt == null){
          cnt = 0;
      }
      map.put(number, cnt+1);
  }

    // Find if there exists any pair of numbers which sum is equal to the value.
  public boolean find(int value) {
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
          int diff = value - entry.getKey();
          if(map.containsKey(diff)) {
              if(diff == entry.getKey()){
                  if(entry.getValue() > 1)
                      return true;
              }
              else
                  return true;
          }
      }
      return false;
  }
}


public class TwoSum{
  Set<Integer> sum;
  Set<Integer> num;

  public TwoSum(){
    sum = new HashSet<>();
    num = new HashSet<>();
  }
  
  // O(n)
  // Add the number to an internal data structure.
  public void add(int number) {
    if(num.contains(number)){
      sum.add(number*2);
    }
    else{
      Iterator<Integer> it = num.iterator();
      while(it.hasNext()){
        sum.add(number + it.next());
      }
      num.add(number);
    }
  }

  // O(1)
  // Find if there exists any pair of numbers which sum is equal to the value.
  public boolean find(int value) {
    return sum.contains(value);
  }
}

public class TwoSum{
  // number, count
  Map<Integer, Integer> map;
  public TwoSum(){
    map = new HashMap<>();
  }
  
  // O(1)
  // Add the number to an internal data structure.
  public void add(int number) {
    if(map.containsKey(number))
      map.put(number, 2);
    else
      map.put(number, 1);
  }

  // O(n)
  // Find if there exists any pair of numbers which sum is equal to the value.
  public boolean find(int value) {
    Iterator<Integer> it = map.keySet().iterator();
    while(it.hasNext()){
      int a = it.next();
      int b = value - a;
      if(map.containsKey(a)){
        if(a != b || map.get(a) == 2)
          return true;
      }
    }
    return false;
  }
}
