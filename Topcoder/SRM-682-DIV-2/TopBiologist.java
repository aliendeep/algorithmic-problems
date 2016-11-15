/*

Problem Statement
    
For the purposes of this problem, a DNA sequence is a string consisting of the letters A, C, G, and T.  You have been hired to help researchers at TopBiologist uncover the secrets of the human genome. The researchers have identified a DNA sequence, which is given to you in the String sequence. They have asked you to write a program which finds the shortest DNA sequence that is not contained in sequence.  For example, if sequence is the string "AGGTCTA", then one possible answer would be the string "AC". Other solutions, such as "CC", would also be accepted. "AG" would be an invalid answer because "AG" is contained in sequence. "AAA" would also be invalid: although it isn't contained in sequence, it is 3 characters long, and there are better answers which are only 2 characters long.  You are not really sure how this could possibly help anyone's research, but it's not your job to question TopBiologist. Find and return the shortest DNA sequence which is not contained in sequence. If there are multiple possible answers, you may return any of them.
Definition
    
Class:
TopBiologist
Method:
findShortestNewSequence
Parameters:
String
Returns:
String
Method signature:
String findShortestNewSequence(String sequence)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Stack limit (MB):
256
Notes
-
Formally, we say that sequence X is contained in sequence Y if we can obtain X by erasing some (possibly none) elements from the beginning and from the end of Y. For example, "AB" is contained in "ABC", but "AC" is not contained in "ABC".
Constraints
-
sequence will contain between 1 and 2000 characters, inclusive.
-
Each character of sequence will be A, C, G, or T.
Examples
0)

    
"AGGTCTA"
Returns: "AC"
This is the example from the problem statement. Any of the following answers will be accepted: "AA", "AC", "AT", "CA", "CC", "CG", "GA", "GC", "TG", and "TT".
1)

    
"AGACGACGGAGAACGA"
Returns: "T"

2)

    
"A"
Returns: "C"

3)

    
"AAGATACACCGGCTTCGTG"
Returns: "CAT"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

class TrieNode{
  public int val;
  public TrieNode[] child;
  public TrieNode parent;

  public TrieNode(int ch){
    val = ch;
    child = new TrieNode[4];
    for(int i=0; i<4; ++i){
      child[i] = null;
    }
  }
}

class Trie{
  public TrieNode root;
  TrieNode last;

  public Trie(){
    root = new TrieNode(-1);
    root.parent = root;
    last = null;
  }
  
  public int getVal(char ch){
    if(ch == 'A')   return 0;
    if(ch == 'C')   return 1;
    if(ch == 'G')   return 2;
    if(ch == 'T')   return 3;
    return -1;
  }

  public char getChar(int n){
    if(n == 0)    return 'A';
    if(n == 1)    return 'C';
    if(n == 2)    return 'G';
    if(n == 3)    return 'T';
    return '0';
  }
    
  public void insert(String s){
    int n = s.length();
    TrieNode cur = root;
    for(int i=0; i<n; ++i){
      int val = getVal(s.charAt(i));
      if(cur.child[val] == null){
        cur.child[val] = new TrieNode(val);
      }
      cur.child[val].parent = cur;
      cur = cur.child[val];
      if(i == n-1)
          last = cur;
    }
  }
  
  public void getPath(TrieNode node, StringBuilder r){
    if(node.parent == node){
      //System.out.println("Returning!");
      return;    
    }
    //System.out.println("Adding "+node.val);
    r.append(getChar(node.val));
    getPath(node.parent, r);
  }
  
  // get longest path + 1  
  public String getLongestPath(){
    StringBuilder r = new StringBuilder();    
    getPath(last, r);  
    r.reverse();
    r.append('A');
    return r.toString();
  }
  public String shortestNewDNASequence(){
    Queue<TrieNode> Q = new LinkedList<>();
    Q.add(root);
    TrieNode lastNode = null;

    while(!Q.isEmpty()){
      TrieNode t = Q.remove();
      lastNode = t;
      TrieNode cur = t;      
      for(int i=0; i<4; ++i){
        if(t.child[i] == null){
          // found
          //System.out.print(" ! " + getChar(i) + " ");
          StringBuilder r = new StringBuilder();
          getPath(t, r);  
          r.reverse();
          r.append(getChar(i));
          return r.toString();        
        } 
        //System.out.print(cur.child[i].val+ " ");        
        // node, parent
        Q.add(cur.child[i]); 
      }
      //System.out.println();
    }
    // Get any path + 1 extra character
    StringBuilder r = new StringBuilder();
    getPath(lastNode, r);  
    r.reverse();
    r.append('A');
    return r.toString();
  }
}

public class TopBiologist
{ 
  // Trie 
  public String findShortestNewSequence(String sequence){
    Trie trie = new Trie();
    int n = sequence.length();
    // insert all subsequence in the trie
    for(int len=1; len<=n; ++len){
      int start = 0;
      while(start <= n - len){
        String x = sequence.substring(start, start+len);
        trie.insert(x);
        start++;
      }
      // Prune
      String candidate = trie.shortestNewDNASequence();
      if(candidate.length() == len)
          return candidate;
    }
    return trie.getLongestPath();
  }  
  /*
  ACTACGCCGGCTCCCGATCAGCTTGCAGGGGACGGCCCTAGTGCCTTGTCACGCAATGCGGCGTGGTTGAGGTTGCCCCCATGTCTCCGTTTTGGTGGGGCTGCCATGTGCGTGCAAGCCGAGAGTCCCATTGTCTGTGCTATTATTTGCGAGTCGCATAGCTTTTTGATCGGCAGGTCTAGTACTCATTCACCGGGAATAGGCCCTTTCGACCCCCCGGGTAACGTAGCCACTACACTAGGCTTAAAATAGTTGTCTAGGACCACGAGTCACAGCCATTGCGGGCCGGTGTTGGTAAACGAGTCGGGGAGTAGAACGACGGAGACGTAACGGAATCTCGTGCAGGACGTACGACGCTTACTGTGGCTCCAAATGATTTTCCCTATTTTAGACTTGCCCTTGCCATCGAATCCTTTTTACGTTTGCTTGGAGCTGTTAATACTAGTTCTGCCATCCATGTCAGAAGCATACTAATTATGCATTACTTGTTGGGGCCAGCCGCCATTAGTATTGACTCCCAGGCATCGGTCAAACTTTAGAGGATAGGAATTTAGATACCGCATCGTATGAGTCCATTGATCGTTGCCGTCTGCAAACTATCATCCGTCGGAAGCGATGCAGGTAAGTAGAATGACTTATTCCCCAGGGCTGTATAGACATCAAGTGTAACGTTACCGTCCACAACATTTGGCATGATTTAGCCTTTTTCAGGAAATATGAGACTAATATAAGCTATGTACGGTGCATTTTTGCCCACCTCTGTCGCGGAAAATCGCCCGTCCCCCTTATACGACCTGGACCGGACTTCGTTTTCAGCTTACTCGAATTTTAATCTTGATGTCCACCCATCTATTATCTGAACCTTTATCCCAGATCGGATGTGACGAGACTTTAGAGATCAGCAAGTATGAGTGCTGAGACTTTTTAGGGTTGTACATACCCTTCCATTAGACTATTTCGTACACGAGGAATCAAGAAAAACCCACCAAATTGTGTAGAATTATAGAGGCGCGGACACTAGAACCTAATATCTCGTCTCAACGCCGGTCCAATCATTTTTCCGTGTAGCAACAGGAGTTTAGAACGGTGTGGGCCGTTCCCGCCCACTTGACTCGCTGCGTATCGTCCTGTGATCGAGTGATGAGCAAGAATCGTCCCTATATATACTTGGGGAGAAACAGACGATACATTTGTCAGACCATAGTGATCGATGTCATACTGACGACTCGAAGCCATCCAGGACAAGTGACGTCTGGAGCTGAAGTTTTGTGGTTACTGAATTTCTACATGGGCATAACCGGAGTGAGCTGTCCGTTTAAGAAACTTAGATCAAGGGCCGTTTCTTCAAAAACTCACGCGCGCAAACACGAGTTG
  Expected:
  "AAAG"
  */
  public static void main(String[] args){
    TopBiologist ob = new TopBiologist();
    System.out.println("Result "+ob.findShortestNewSequence("ACTACGCCGGCTCCCGATCAGCTTGCAGGGGACGGCCCTAGTGCCTTGTCACGCAATGCGGCGTGGTTGAGGTTGCCCCCATGTCTCCGTTTTGGTGGGGCTGCCATGTGCGTGCAAGCCGAGAGTCCCATTGTCTGTGCTATTATTTGCGAGTCGCATAGCTTTTTGATCGGCAGGTCTAGTACTCATTCACCGGGAATAGGCCCTTTCGACCCCCCGGGTAACGTAGCCACTACACTAGGCTTAAAATAGTTGTCTAGGACCACGAGTCACAGCCATTGCGGGCCGGTGTTGGTAAACGAGTCGGGGAGTAGAACGACGGAGACGTAACGGAATCTCGTGCAGGACGTACGACGCTTACTGTGGCTCCAAATGATTTTCCCTATTTTAGACTTGCCCTTGCCATCGAATCCTTTTTACGTTTGCTTGGAGCTGTTAATACTAGTTCTGCCATCCATGTCAGAAGCATACTAATTATGCATTACTTGTTGGGGCCAGCCGCCATTAGTATTGACTCCCAGGCATCGGTCAAACTTTAGAGGATAGGAATTTAGATACCGCATCGTATGAGTCCATTGATCGTTGCCGTCTGCAAACTATCATCCGTCGGAAGCGATGCAGGTAAGTAGAATGACTTATTCCCCAGGGCTGTATAGACATCAAGTGTAACGTTACCGTCCACAACATTTGGCATGATTTAGCCTTTTTCAGGAAATATGAGACTAATATAAGCTATGTACGGTGCATTTTTGCCCACCTCTGTCGCGGAAAATCGCCCGTCCCCCTTATACGACCTGGACCGGACTTCGTTTTCAGCTTACTCGAATTTTAATCTTGATGTCCACCCATCTATTATCTGAACCTTTATCCCAGATCGGATGTGACGAGACTTTAGAGATCAGCAAGTATGAGTGCTGAGACTTTTTAGGGTTGTACATACCCTTCCATTAGACTATTTCGTACACGAGGAATCAAGAAAAACCCACCAAATTGTGTAGAATTATAGAGGCGCGGACACTAGAACCTAATATCTCGTCTCAACGCCGGTCCAATCATTTTTCCGTGTAGCAACAGGAGTTTAGAACGGTGTGGGCCGTTCCCGCCCACTTGACTCGCTGCGTATCGTCCTGTGATCGAGTGATGAGCAAGAATCGTCCCTATATATACTTGGGGAGAAACAGACGATACATTTGTCAGACCATAGTGATCGATGTCATACTGACGACTCGAAGCCATCCAGGACAAGTGACGTCTGGAGCTGAAGTTTTGTGGTTACTGAATTTCTACATGGGCATAACCGGAGTGAGCTGTCCGTTTAAGAAACTTAGATCAAGGGCCGTTTCTTCAAAAACTCACGCGCGCAAACACGAGTTG"));
    //System.out.println("Result "+ob.findShortestNewSequence("ACGT"));
    // T
    System.out.println(ob.findShortestNewSequence("AGACGACGGAGAACGA"));
    // C
    System.out.println(ob.findShortestNewSequence("A"));
    // CAT
    System.out.println(ob.findShortestNewSequence("AAGATACACCGGCTTCGTG"));
    // AC
    System.out.println(ob.findShortestNewSequence("AGGTCTA"));
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!