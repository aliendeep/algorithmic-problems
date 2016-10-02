/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
*/

public class Solution {
    /*
        Observation
        1) if the sum of gas >= the sum of cost, then the circle can be completed.
        2) if A can not reach C in a the sequence of A-->B-->C, then B can not make it either.
        
        Case 1: If gas[A] < cost[A], then A can not even reach B. 
            So to reach C from A, gas[A] must >= cost[A]. 
        Case 2: gas[A] >= cost[A].
            Given that A can not reach C, we have gas[A] + gas[B] < cost[A] + cost[B],
            and gas[A] >= cost[A],
            Therefore, gas[B] < cost[B], i.e., B can not reach C. 
    */    
    // Greedy
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int sum = 0;
        int tot = 0;
        for(int i=0; i<gas.length; i++){
            int rem = gas[i] - cost[i];
            sum += rem;
            tot += rem;
            if(sum < 0){
                start = i+1;
                sum = 0;
            }
        }
        if(tot >= 0)
            return start;
        return -1;
    }
}