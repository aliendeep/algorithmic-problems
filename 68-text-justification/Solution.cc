class Solution {
public:
    string lineWithSpace(vector<string>& words, int start, int end, int num_spaces){
        int num_words_cur_line = end - start + 1;
        string line;

        for(int i=start; i<end; i++){
            line += words[i];
            num_words_cur_line--;
            int cur_spaces = ceil((double)num_spaces/num_words_cur_line);
            line.append(cur_spaces, ' ');
            num_spaces -= cur_spaces;
        }
        // last word
        line += words[end];
        line.append(num_spaces, ' ');
        return line;
    }
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        vector<string> result;
        int cur_line_start = 0, num_words_cur_line = 0, cur_line_length = 0;
        for(int i=0; i<words.size(); i++){
            num_words_cur_line++;
            // Assuming single space between words, -1 because no space before the first word
            int ahead_line_length = cur_line_length + words[i].size() + (num_words_cur_line - 1);
            if(ahead_line_length == maxWidth){
                result.emplace_back(lineWithSpace(words, cur_line_start, i, i-cur_line_start));
                // start next line, so reset
                cur_line_start = i+1;
                num_words_cur_line = 0;
                cur_line_length = 0;
            }
            else if(ahead_line_length > maxWidth){
                // remove the last word
                result.emplace_back(lineWithSpace(words, cur_line_start, i-1, maxWidth-cur_line_length));
                // start next line with ith word
                cur_line_start = i;
                num_words_cur_line = 1;
                cur_line_length = words[i].size();
            }
            else{
                // ahead_line_length < maxWidth                
                cur_line_length += words[i].size();
            }
        }
        //handle the last line, left justified
        if(num_words_cur_line > 0){
            string line = lineWithSpace(words, cur_line_start, words.size()-1, num_words_cur_line-1);
            // append extra spaces
            line.append(maxWidth - cur_line_length - (num_words_cur_line-1), ' ');
            result.emplace_back(line);
        }
        return result;
    }
};