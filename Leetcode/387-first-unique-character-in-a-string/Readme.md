## Idea

### Two pass solution (traverse the given string twice)
- Count the frequency of the character occurred in the string
- Return the first character that has the frequency of 1

### Alternative

- Count the frequency of the character occurred in the string and also keep the index of the character
- Find the minimum index with frequency one

So, one pass over the string and one pass over the 256 frequency counter