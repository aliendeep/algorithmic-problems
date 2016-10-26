## Idea

- Solution 1
Sort the numbers and swap adjacent pairs O(nlogn)

- Solution 2
- O(n) time complexity
- if((i % 2 == 0 && nums[i] > nums[i+1]) || (i%2 == 1 && nums[i] < nums[i+1])), then swap