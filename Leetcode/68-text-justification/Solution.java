/*
Given an array of words and a length L, format the text such that each line has 
exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as 
you can in each line. Pad extra spaces ' ' when necessary so that each line has 
exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the 
number of spaces on a line do not divide evenly between words, the empty slots on 
the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is 
inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do 
in this case?
In this case, that line should be left-justified.
*/
// Cleaner Solution
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        List<String> result = new ArrayList<>();
        int i = 0;
        int j;
        while(i < n){
            int curLen = 0;
            // Check how many words can be fit into this line
            // (j - i) = 1 space for each word, there may be no space after last word
            for(j=i; j<n && curLen + words[j].length() + (j-i) <= maxWidth; j++){
                curLen += words[j].length(); 
            }
            // Compute evenly distributed spaces and extra spaces
            // There should be at least 1 space between each word
            int space = 1, extra = 0;
            // The line will accomodate more than 1 word and all words have not been processed
            if(j - i != 1 && j != n){
                // evenly distributed spaces
                space = (maxWidth - curLen) / (j - i - 1);
                // extra spaces that will be distributed in the earlier words
                extra = (maxWidth - curLen) % (j - i - 1);
            }
            StringBuilder line = new StringBuilder();
            // Add first word
            line.append(words[i]);
            for(int k=i+1; k<j; ++k){
                int s = space;
                while(s-- > 0)
                    line.append(' ');
                if(extra-- > 0)
                    line.append(' ');
                line.append(words[k]);
            }
            // For the last line (It should be left justified)
            int len = maxWidth - line.length();
            while(len-- > 0) 
                line.append(' ');
            result.add(line.toString());
            // start from the next word
            i = j;
        }
        return result;
    }
}

class Solution2 {
    // EPI Hard Problem
    public String getLineWithSpace(String[] words, int startIndex, int endIndex, int totalSpaces){
        int numWords =  endIndex - startIndex + 1;
        StringBuffer cur = new StringBuffer();
        for(int i=startIndex; i<endIndex; i++){
            cur.append(words[i]);
            numWords--;
            // the empty slots on the left will be assigned more spaces than the slots on the right if necessary
            int numSpaces = (int)Math.ceil((double)totalSpaces/numWords);
            for(int j=0; j<numSpaces; j++)
                cur.append(' ');
            totalSpaces -= numSpaces;
        }
        // last word
        cur.append(words[endIndex]);
        for(int j=0; j<totalSpaces; j++)
            cur.append(' ');
        return cur.toString();
    }
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        // start of current line
        int startIndex = 0;
        // number of words in the cur line
        int numWords = 0;
        // length of cur line
        int curLineLen = 0;
        List<String> result = new ArrayList<>();
        for(int i=0; i<words.length; i++){
            numWords++;
            // Calculate the would be word length if I include current word
            // curlen + length of the current word + spaces
            int lineLen = curLineLen + words[i].length() + (numWords - 1);
            if(lineLen == maxWidth){
                result.add(getLineWithSpace(words, startIndex, i, i-startIndex));
                // reset the start index of next line, line length of the next line, number of words
                startIndex = i+1;
                curLineLen = 0;
                numWords = 0;
            }
            // Not possible to include the cur word
            else if(lineLen >  maxWidth){
                result.add(getLineWithSpace(words, startIndex, i-1, maxWidth - curLineLen));
                startIndex = i;
                curLineLen = words[i].length();
                numWords = 1;
            }
            else
                curLineLen += words[i].length();
        }
        // Special case: last line
        // For the last line of text, it should be left justified and no extra space is inserted between words.
        if(numWords > 0){
            StringBuffer l = new StringBuffer(getLineWithSpace(words, startIndex, words.length-1, numWords-1));
            // Add extra spaces if necessary
            int nSpaces = maxWidth - curLineLen - (numWords-1);
            for(int i=0; i<nSpaces; i++)
                l.append(' ');

            result.add(l.toString());
        }
        return result;
    }
}