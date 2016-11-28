class TrieNode {
    // Initialize your data structure here.
    public char val;
    public TrieNode[] children;
    public boolean isEnd;
    public TrieNode(char v){
        this.val = v;
        children = new TrieNode[26];
        for(int i=0; i<26; i++){
            children[i] = null;
        }
        isEnd = false;
    }
}

public class WordDictionary {
    private TrieNode root;

    public WordDictionary(){
        root = new TrieNode('0');
    }
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode cur = root;
        for(int i=0; i<word.length(); i++){
            int index = word.charAt(i)-'a';
            if(cur.children[index] == null)
                cur.children[index] = new TrieNode(word.charAt(i));
            cur = cur.children[index];
            if(i == word.length()-1)
                cur.isEnd = true;
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word, TrieNode cur) {
        if(cur == null)
            return false;
        if(word.length() == 0)
            return (cur.isEnd == true);
        
        if(word.charAt(0) == '.'){
            for(int i=0; i<26; i++){
                if(search(word.substring(1), cur.children[i]))
                    return true;
            }
        }
        else{
            int index = word.charAt(0) - 'a';
            if(cur.children[index] == null)
                return false;
            cur = cur.children[index];
            return search(word.substring(1), cur);
        }
        return false;
    }

    public boolean search(String word){
        return search(word, root);
    }

    public static void main(String[] args){
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));        
        System.out.println(wordDictionary.search("bad"));        
        System.out.println(wordDictionary.search("b.."));        
        System.out.println(wordDictionary.search(".ad"));       
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");