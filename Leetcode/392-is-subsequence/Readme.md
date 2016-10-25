## Greedy

- See if it's possible to increment curPos
- If curPos is equal to t.length(), then return true

### Follow up
- Use Map<Character, TreeSet<Integer>> map to keep the mapping of character, set of indices
- TreeSet is used so that we can use the ceiling method to find the next valid index