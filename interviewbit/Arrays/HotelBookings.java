/*
A hotel manager has to process N advance bookings of rooms for the next season. His hotel has K rooms. Bookings contain an arrival date and a departure date. He wants to find out whether there are enough rooms in the hotel to satisfy the demand. Write a program that solves this problem in time O(N log N) .

Input:


First list for arrival time of booking.
Second list for departure time of booking.
Third is K which denotes count of rooms.

Output:

A boolean which tells whether its possible to make a booking. 
Return 0/1 for C programs.
O -> No there are not enough rooms for N booking.
1 -> Yes there are enough rooms for N booking.
Example :

Input : 
        Arrivals :   [1 3 5]
        Departures : [2 6 8]
        K : 1

Return : False / 0 

At day = 5, there are 2 guests in the hotel. But I have only one room. 
*/
public class HotelBookings {
    class Interval{
        int time;
        // 0 start, 1 end
        int isEnd;
        public Interval(int t, int b){
            time = t;
            isEnd = b; 
        }
    }
    public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int k) {
        int n = arrive.size();
        // 0 bookings
        if(n == 0)      return true;
        if(k == 0)      return false;

        Interval[] a = new Interval[2*n];
        int c = 0;
        for(int i=0; i<n; i++){
            a[c++] = new Interval(arrive.get(i), 0); 
        }
        
        for(int i=0; i<n; i++){
            a[c++] = new Interval(depart.get(i), 1); 
        }
        Arrays.sort(a, new Comparator<Interval>(){
            @Override
            // Sort by start time (end comes first)
            public int compare(Interval a, Interval b){
                if(a.time == b.time)
                    return Integer.compare(b.isEnd, a.isEnd);
                return Integer.compare(a.time, b.time);
            }
        });
        
        // max room needed
        int max = 0;
        int cnt = 0;
        for(int i=0; i<a.length; ++i){
            if(a[i].isEnd == 0){
                cnt++;
                max = Math.max(max, cnt);
            }
            else{
                cnt--;
            }
        }
        return max <= k ? true : false;
    }
}
