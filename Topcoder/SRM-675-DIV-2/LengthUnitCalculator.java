/*

Problem Statement
    
Your task is to write a calculator that will convert between four different length units: inches (in), feet (ft), yards (yd), and miles (mi).  The conversions between these units:
1 ft = 12 in
1 yd = 3 ft
1 mi = 1760 yd
You are given an int amount and Strings fromUnit and toUnit. Compute and return the amount of toUnits that corresponds to amount fromUnits. (For example, if amount=41, fromUnit="mi", and toUnit="in", you are supposed to compute the number of inches in 41 miles.) Note that the result will not necessarily be an integer.
Definition
    
Class:
LengthUnitCalculator
Method:
calc
Parameters:
int, String, String
Returns:
double
Method signature:
double calc(int amount, String fromUnit, String toUnit)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Notes
-
Pay attention to the unusual time limit.
Constraints
-
amount will be between 1 and 1,000, inclusive.
-
fromUnit will be one of {"in", "ft", "yd", "mi"}.
-
toUnit will be one of {"in", "ft", "yd", "mi"}.
Examples
0)

    
1
"mi"
"ft"
Returns: 5280.0
We are asked to convert 1 mile into feet. From the information in the statement we know that 1 mi = 1760 yd = (1760 * 3) ft = 5280 ft.
1)

    
1
"ft"
"mi"
Returns: 1.893939393939394E-4
Here we have 1 ft = 1/5280 mi, which is approximately 0.000189394 miles.
2)

    
123
"ft"
"yd"
Returns: 41.0

3)

    
1000
"mi"
"in"
Returns: 6.336E7

4)

    
1
"in"
"mi"
Returns: 1.5782828282828283E-5

5)

    
47
"mi"
"mi"
Returns: 47.0

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class LengthUnitCalculator
{
  public double calc(int amount, String fromUnit, String toUnit)
  {
    Map<String, Integer> map = new HashMap<>();
    map.put("ft", 0);
    map.put("in", 1);
    map.put("yd", 2);
    map.put("mi", 3);
    
    int n = 4;
    double[][] d = new double[n][n];
    for(double[] t : d){
      Arrays.fill(t, -1);
    }

    // ft to inch
    for(int i=0; i<4; ++i){
      d[i][i] = 1.0;
    }
    d[0][1] = 12;
    d[1][0] = 1.0/12;
    d[2][0] = 3;
    d[0][2] = 1.0/3;
    d[3][2] = 1760;
    d[2][3] = 1/d[3][2];
    
    for(int k=0; k<n; ++k){
      for(int i=0; i<n; ++i){
        for(int j=0; j<n; ++j){
          if(d[i][k] != -1 && d[k][j] != -1)
            d[i][j] = d[i][k] * d[k][j];
        }
      }
    }
    int f = map.get(fromUnit);
    int t = map.get(toUnit);
    return d[f][t]*amount;
  }
  
<%:testing-code%>
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!