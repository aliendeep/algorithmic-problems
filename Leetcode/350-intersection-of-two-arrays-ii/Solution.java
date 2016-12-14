/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such 
that you cannot load all elements into the memory at once?
*/

// Solution 1: Sorting O(nlogn) Solution
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> r = new ArrayList<Integer>();
        int x, y;
        int i = 0, j = 0;
        while(i<nums1.length && j<nums2.length){
            x = nums1[i];
            y = nums2[j];
            if(x == y){
                r.add(x);
                i++;
                j++;
            } 
            else if(x < y){
                i++;
            }
            else
                j++;
        }

        int[] t = new int[r.size()];
        for(i=0; i<r.size(); i++){
            t[i]= r.get(i);
        }
        return t;
    }
}

// Solution 2: HashMap O(n) time and O(n) Space
class Solution2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        // Use less memory
        if(nums1.length > nums2.length)
            return intersect(nums2, nums1);

        // num, cnt
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums1){
            if(!map.containsKey(n))
                map.put(n, 1);
            else
                map.put(n, map.get(n) + 1);
        }
        
        List<Integer> r = new ArrayList<Integer>();
        for(int num : nums2){
            if(map.containsKey(num)){
                r.add(num);
                int newCnt = map.get(num)-1;
                if(newCnt == 0) 
                    map.remove(num);
                else 
                    map.put(num, newCnt);
            }           
        }

        int[] t = new int[r.size()];
        for(int i=0; i<r.size(); i++){
            t[i]= r.get(i);
        }
        return t; 
    }
}
/*
Follow up:
- What if elements of nums2 are stored on disk, and the memory is limited such 
that you cannot load all elements into the memory at once?

Solution1: If all num1 elements can be loaded into memory, then put all elements 
of num1 into a Map. Read chunks of nums2 into memory and record the intersection

Solutuon2: If both num1 and num2 can't be loaded into memory, sort them using
external sort and read 1 element from each array and record intersection.

External Sort
https://en.wikipedia.org/wiki/External_sorting

One example of external sorting is the external merge sort algorithm, which sorts 
chunks that each fit in RAM, then merges the sorted chunks together. 
For example, for sorting 900 megabytes of data using only 100 megabytes of RAM:

1. Read 100 MB of the data in main memory and sort by some conventional method, 
like quicksort.

2. Write the sorted data to disk.

3. Repeat steps 1 and 2 until all of the data is in sorted 100 MB chunks 
(there are 900MB / 100MB = 9 chunks), which now need to be merged into one 
single output file.

4. Read the first 10 MB (= 100MB / (9 chunks + 1)) of each sorted chunk into 
input buffers in main memory and allocate the remaining 10 MB for an output buffer. 
(In practice, it might provide better performance to make the output buffer 
larger and the input buffers slightly smaller.)

5. Perform a 9-way merge and store the result in the output buffer. Whenever the 
output buffer fills, write it to the final sorted file and empty it. 
Whenever any of the 9 input buffers empties, fill it with the next 10 MB of its 
associated 100 MB sorted chunk until no more data from the chunk is available. 
This is the key step that makes external merge sort work externally -- because 
the merge algorithm only makes one pass sequentially through each of the chunks, 
each chunk does not have to be loaded completely; rather, sequential parts of 
the chunk can be loaded as needed.
*/
