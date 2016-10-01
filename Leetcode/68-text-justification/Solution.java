public class Solution {
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