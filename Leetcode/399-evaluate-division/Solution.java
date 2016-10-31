/*
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/
public class Solution {
    // Floyd–Warshall algorithm
    // O(n^3)
    // An equation A/B=k implies A->B (directed edge from A to B), and (A/B)*(B/C)*(C/D) represent the path A->B->C->D
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // Count number of nodes
        Map<String, Integer> counter = new HashMap<String, Integer>();
        int nodeIndex = 0;
        // Convert strings to node id
        for(int i=0; i<equations.length; i++){
            String num = equations[i][0];
            String den = equations[i][1];
            if(!counter.containsKey(num))
                counter.put(num, nodeIndex++);
            if(!counter.containsKey(den))
                counter.put(den, nodeIndex++);
        }    
        // number of nodes
        int n = counter.size();
        // -1 initialization
        double[][] d = new double[n][n];
        for(int i=0; i<n; i++)
            Arrays.fill(d[i], -1);

        // initialization
        for(int i=0; i<equations.length; i++){
            String num = equations[i][0];
            String den = equations[i][1];
            d[counter.get(num)][counter.get(num)] = 1.0;
            d[counter.get(den)][counter.get(den)] = 1.0;
            d[counter.get(num)][counter.get(den)] = values[i];
            d[counter.get(den)][counter.get(num)] = 1.0/values[i];
        }    
        
        // Algorithm        
        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    // if node exists
                    if(d[i][k] != -1 && d[k][j] != -1)
                        d[i][j] = d[i][k] * d[k][j];    
                }
            }
        }
        
        // Answer Prep
        double[] result = new double[queries.length];
        Arrays.fill(result, -1);
        for(int i=0; i<queries.length; i++){
            String num = queries[i][0];
            String den = queries[i][1];
            if(counter.containsKey(num) && counter.containsKey(den)) 
                result[i] = d[counter.get(num)][counter.get(den)];
        }
        return result;
    }
}