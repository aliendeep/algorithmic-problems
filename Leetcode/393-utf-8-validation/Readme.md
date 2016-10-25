## Idea

0xxxxxxx
110xxxxx 10xxxxxx
1110xxxx 10xxxxxx 10xxxxxx
11110xxx 10xxxxxx 10xxxxxx 10xxxxxx

- Keep a counter

If counter = 0, see if the number starts with
  110   - next 1 byte should be the data (cnt = 1)
  1110  - next 2 byte should be the data (cnt = 2)
  11110 - next 3 byte should be the data (cnt = 3)
  0   

Otherwise,
  - if((n >> 6) != 0b10) return false
  - decrement count

At the end, if counter == 0 return true

