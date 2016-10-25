### Idea
- List<Integer> nums
- Use a HashMap <val, location> 

Insert:
Insert the num to the set

Remove:
- Find the position of the number in the list.
- Replace the value at that position by the last number and update the location value of the previous last number
- Delete the key from hashmap, remove the last entry from the list and 

Random:
- Generate a random number between <0, list.size()-1> 
- Simple return the corresponding entry