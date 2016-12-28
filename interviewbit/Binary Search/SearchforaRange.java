public class Solution {
  // DO NOT MODIFY THE LIST
  public int lowerBound(final List<Integer> a, int target){
      int l = 0, h = a.size()-1;
      while(h - l > 3){
          int mid = (l+h)/2;
          if(a.get(mid) == target)
              h = mid;
          else if(a.get(mid) < target){
              l = mid + 1;
          }
          else if(a.get(mid) > target){
              h = mid - 1;
          }
      }
      for(int i=l; i<=h; ++i){
          if(a.get(i) == target)
              return i;
      }
      return -1;
  }

  public int upperBound(final List<Integer> a, int target){
      int l = 0, h = a.size()-1;
      while(h - l > 3){
          int mid = (l+h)/2;
          if(a.get(mid) == target)
              l = mid;
          else if(a.get(mid) < target){
              l = mid + 1;
          }
          else if(a.get(mid) > target){
              h = mid - 1;
          }
      }
      for(int i=h; i>=l; --i){
          if(a.get(i) == target)
              return i;
      }
      return -1;
  }
  public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
      ArrayList<Integer> r = new ArrayList<>();
      r.add(lowerBound(a, b));
      r.add(upperBound(a, b));
      return r;
  }
}
