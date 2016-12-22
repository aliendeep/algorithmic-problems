/*
We're going to make our own Contacts application! The application must perform two types of operations:

add name, where  is a string denoting a contact name. This must store  as a new contact in the application.
find partial, where  is a string denoting a partial name to search the application for. It must count the number of contacts starting with  and print the count on a new line.
Given  sequential add and find operations, perform each operation in order.

Input Format

The first line contains a single integer, , denoting the number of operations to perform. 
Each line  of the  subsequent lines contains an operation in one of the two forms defined above.

Constraints

It is guaranteed that  and  contain lowercase English letters only.
The input doesn't have any duplicate  for the  operation.
Output Format

For each find partial operation, print the number of contact names starting with  on a new line.

Sample Input

4
add hack
add hackerrank
find hac
find hak
Sample Output

2
0
Explanation

We perform the following sequence of operations:

Add a contact named hack.
Add a contact named hackerrank.
Find and print the number of contact names beginning with hac. There are currently two contact names in the application and both of them start with hac, so we print  on a new line.
Find and print the number of contact names beginning with hak. There are currently two contact names in the application but neither of them start with hak, so we print  on a new line.
*/
// https://www.hackerrank.com/challenges/ctci-contacts
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class TrieNode {
    // Initialize your data structure here.
    public char val;
    public TrieNode[] child;
    public boolean isEnd;
    public int wordCount;
    
    public TrieNode(char v){
        this.val = v;
        child = new TrieNode[26];
        for(int i=0; i<26; i++){
            child[i] = null;
        }
        isEnd = false;
        wordCount = 0;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode('0');
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        int n = word.length();
        TrieNode cur = root;        
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c -'a';
            if(cur.child[index] == null)
                cur.child[index] = new TrieNode(c);            
            cur = cur.child[index];
            cur.wordCount = cur.wordCount + 1;
            if(i == n-1)
                cur.isEnd = true;
        }
    }
  
    // Count the number of contacts starting with the prefix
    public void countWordsWithPrefix(String prefix){
        int n = prefix.length();
        TrieNode cur = root;
        int i = 0;
        for(i=0; i<n; ++i){
            char c = prefix.charAt(i);
            int index = c -'a';
            if(cur.child[index] == null)
                break;
            cur = cur.child[index];
        }
        if(i < n){
            System.out.println("0");
            return;
        }
        System.out.println(cur.wordCount);        
    }
}
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Trie trie = new Trie();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            if(op.equals("add")){
                trie.insert(contact);
            }
            else{
                trie.countWordsWithPrefix(contact);
            }
        }
    }
}
