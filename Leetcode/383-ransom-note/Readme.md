## Idea
- Use a map to count the frequency of the letter appeared in the random note
- For all characters in the magazine, if the letter covers a character in the ransom note, then decrement the frequency of that letter.
- If all letters of the ransom note is covered, then return true (check whether the frequency map is empty)

### Alternative
Use a 256 count array

Time Complexity: O(m + n)
Space Complexity: O(L) where L is the number of distinct characters appearing in the letter
