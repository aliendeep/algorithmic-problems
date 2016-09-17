
struct TrieNode{
    char val;
    // lowercase letters
    TrieNode* child[26];
    bool isEnd;

    TrieNode(int ch = -1){
        val = ch;
        for(int i=0; i<26; i++)
            child[i] = NULL;
        isEnd = false;
    }
};

class Trie{
  public:
    TrieNode* root;
    // dummy node at the root
    Trie(){
        root = new TrieNode(-1);
    }
    
    void insert(const string& s){
        TrieNode* cur = root;
        int cnt = 0;
        int n = s.size();
        for(const auto& c : s){
            // if node does not exist then create one
            if(cur->child[c-'a'] == NULL){
                cur->child[c-'a'] = new TrieNode(c);
            }
            cur = cur->child[c-'a'];
            cnt++;
            if(cnt == n)
                cur->isEnd = true;
        }
    }
    
    bool search(const string& s, bool fullWord=true){
        TrieNode* cur = root;
        int cnt = 0;
        int n = s.size();
        for(const auto& c : s){
            if(cur->child[c-'a'] == NULL){
                return false;
            }
            
            cur = cur->child[c-'a'];
            
            cnt++;
            if(fullWord && cnt == n){
                return cur->isEnd;
            }
        }        
        return true;
    }
    
    // match prefix
    bool startsWith(const string& s){
        return search(s, false);
    }
};

class Solution {
public:
    int move[4][2] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    void bktk(int r, int c, const vector<vector<char>>& board, vector<vector<int>>& visited, string cur, vector<string>& result, Trie* trie){
        int row = board.size();
        int col = board[0].size();
        if(!(r >= 0 && r < row && c >= 0 && c < col) || visited[r][c]) 
            return;

        cur += board[r][c];
        visited[r][c] = 1;
        
        // insert if it is a solution
        if(trie->startsWith(cur)){
            if(trie->search(cur)){
                result.push_back(cur);
            }
            // for all moves
            for(int i=0; i<4; i++){
                int r1 = r + move[i][0];
                int c1 = c + move[i][1];
                bktk(r1, c1, board, visited, cur, result, trie);
            }
        }
        // untrack
        cur.pop_back();
        visited[r][c] = 0;
    }
    
    // Trie for the dictionary
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        vector<string> r;
        int row = board.size();
        if(row == 0)  return r;
        int col = board[0].size();
        
        Trie* trie = new Trie(); 
        // insert all words in the dictionary
        for(const string& word : words)
            trie->insert(word);
        
        // start from each row, col position
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                vector<vector<int>> visited(row, vector<int>(col, 0));
                string cur;
                bktk(i, j, board, visited, cur, r, trie);
            }
        }
        sort(r.begin(), r.end());

        // remove duplicate entries
        vector<string> result;
        if(r.size() > 0)
            result.push_back(r[0]);

        for(int i=1; i<r.size(); i++){
            if(r[i] != r[i-1])
                result.push_back(r[i]);
        }
                
        return result;
    }
};