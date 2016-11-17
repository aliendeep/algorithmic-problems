/*

Problem Statement
    
You have two favorite music bands. Each of them has just recorded a new album. You have bought both albums. You know the durations (in seconds) of songs on each of the album. You are given these duration in int[]s durations1 and durations2. Elements of durations1 are the durations of songs on one of the album, elements of durations2 correspond to the songs of the other album. You only have a limited amount of time before you have to leave for work. This amount of time is given in the int minutes. (Note that the durations are given in seconds while this time is given in minutes.) Given this time, you want to listen to as many different songs as possible. However, there is a constraint: as you are a fan of both bands, you have to listen to at least T different songs from each album. Each song only counts if you listened to it from its beginning to its end. You can play the songs in any order you like. Selecting the next song to play and switching between albums takes zero time. If the input data is such that it is impossible to listen to T different songs from each album in the time you have, return -1. Otherwise, return the largest number of different songs you can listen to.
Definition
    
Class:
ListeningSongs
Method:
listen
Parameters:
int[], int[], int, int
Returns:
int
Method signature:
int listen(int[] durations1, int[] durations2, int minutes, int T)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Constraints
-
durations1 will contain between 1 and 100 elements inclusive.
-
durations2 will contain between 1 and 100 elements inclusive.
-
Each element of durations1 will be between 60 and 3,600 inclusive.
-
Each element of durations2 will be between 60 and 3,600 inclusive.
-
minutes will be between 1 and 12,000 inclusive.
-
T will be between 1 and 100 inclusive.
Examples
0)

    
{300,200,100}
{400,500,600}
17
1
Returns: 4
You have 17 minutes = 17*60 seconds = 1020 seconds before you have to leave for work. In this time you can listen to at most 4 songs. The only optimal solution is to listen to the entire first album and to the first song of the second album. This takes 300 + 200 + 100 + 400 = 1000 seconds, which does not exceed 1020.
1)

    
{300,200,100}
{400,500,600}
10
1
Returns: 2
In this case you could have listened to all the songs from the first album, but you need to listen to at least one song form the second album So you have three choices:
You can listen to the second song of the first album and the first song of the second album.
You can listen to the third song of the first album and the first song of the second album.
You can listen to the third song of the first album and the second song of the second album.
In all these cases, you can never listen to more than 2 songs.
2)

    
{60,60,60}
{60,60,60}
5
2
Returns: 5
Any five of these songs take exactly 5 minutes, which is precisely equal to the time you have left.
3)

    
{120,120,120,120,120}
{60,60,60,60,60,60}
10
3
Returns: 7

4)

    
{196,147,201,106,239,332,165,130,205,221,248,108,60}
{280,164,206,95,81,383,96,255,260,244,60,313,101}
60
3
Returns: 22
These are the song durations for discs 1 and 2 of The Wall, by Pink Floyd (with durations changed to 60 for songs under 60 seconds). You want to listen to at least 3 songs from each disc in one hour.
5)

    
{100,200,300}
{100,200,300}
2
1
Returns: -1
You don't have enough time to listen to a song from the first album and a song from the second album.
6)

    
{100,200,300,400,500,600}
{100,200}
1000
3
Returns: -1
There is not a sufficient number of different songs on the second album: you want to listen to at least three of them but the album contains only two songs.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class ListeningSongs
{
  public int listen(int[] durations1, int[] durations2, int minutes, int T)
  {
    if(durations1.length < T || durations2.length < T)
      return -1;

    int time = minutes*60;
    Arrays.sort(durations1);  
    Arrays.sort(durations2);
    int i = 0, j = 0;
    int cnt = 0;
    int total = 0;
    while(i < T){
      total += durations1[i];
      if(total > time)
        return -1;
      ++i;
      cnt++;
    }     
    while(j < T){
      total += durations2[j];
      if(total > time)
        return -1;
      ++j;
      cnt++;
    } 
    
    while(i < durations1.length && j < durations2.length){
      if(durations1[i] < durations2[j]){
        total += durations1[i];
        if(total > time)
          break;
        ++i;
      }
      else{
        total += durations2[j];
        if(total > time)
          break;
        ++j;
      }
      cnt++;
    }   
    while(i < durations1.length){
      total += durations1[i];
      if(total > time)
        break;
      ++i;
      cnt++;
    }
    while(j < durations2.length){
      total += durations2[j];
      if(total > time)
        break;
      ++j;
      cnt++;
    }
    
    return cnt;
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!