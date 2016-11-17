/*

Problem Statement
    
Bear Limak has recently learned about musical notes. He then listened to a song and noticed that some notes appeared less often than others. In fact, some notes were so rare that they appeared in the song only once!
 
Limak now wants to look for such notes in other songs. Write a program that will look for the rare notes.
 
You are given a int[] notes that describes a song. Each number in notes represents one note of the song. Different numbers represent different notes, equal numbers represent equal notes.
 
Compute and return the number of notes that occur exactly once in the given song.
Definition
    
Class:
BearSong
Method:
countRareNotes
Parameters:
int[]
Returns:
int
Method signature:
int countRareNotes(int[] notes)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Stack limit (MB):
256
Constraints
-
notes will contain between 1 and 50 elements, inclusive.
-
Each element in notes will be between 1 and 1000, inclusive.
Examples
0)

    
{9,10,7,8,9}
Returns: 3
The rare notes are the notes 7, 8, and 10. Each of these notes occurs exactly once in the whole song. The note 9 occurs twice, so it is not a rare note
1)

    
{8,8,7,6,7,3,5,10,9,3}
Returns: 4
Each of the notes 5, 6, 9, and 10 occurs exactly once in this song.
2)

    
{234,462,715,596,906}
Returns: 5

3)

    
{17}
Returns: 1

4)

    
{1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,
1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,
1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000}
Returns: 0

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class BearSong
{
  public int countRareNotes(int[] notes)
  {
    Set<Integer> original = new HashSet<>();
    Set<Integer> result = new HashSet<>();

    for(int note : notes){
      if(!result.contains(note)){
        if(!original.contains(note)){
          result.add(note);
          original.add(note);
        }
      }   
      else{
        result.remove(note);
      } 
    } 
    return result.size(); 
  }  
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!