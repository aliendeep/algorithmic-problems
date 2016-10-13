public class TwoSum {
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


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);