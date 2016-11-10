/*
https://www.interviewbit.com/problems/allocate-books/
N number of books are given. 
The ith book has Pi number of pages. 
You have to allocate books to M number of students so that maximum number of pages alloted to a student is minimum. A book will be allocated to exactly one student. Each student has to be allocated at least one book. Allotment should be in contiguous order, for example: A student cannot be allocated book 1 and book 3, skipping book 2.

NOTE: Return -1 if a valid assignment is not possible

Input:
 List of Books M number of students 

Your function should return an integer corresponding to the minimum number.

Example:

P : [12, 34, 67, 90]
M : 2

Output : 113

There are 2 number of students. Books can be distributed in following fashion : 
  1) [12] and [34, 67, 90]
      Max number of pages is allocated to student 2 with 34 + 67 + 90 = 191 pages
  2) [12, 34] and [67, 90]
      Max number of pages is allocated to student 2 with 67 + 90 = 157 pages 
  3) [12, 34, 67] and [90]
      Max number of pages is allocated to student 1 with 12 + 34 + 67 = 113 pages

Of the 3 cases, Option 3 has the minimum pages = 113. 
*/
import java.util.*;

public class AllocateBook {
    // page - maximum number of pages that can be allocated to a student
    public int studentNeeded(long page, ArrayList<Integer> books){
        int nstudent = 1;
        // Greedy
        int n = books.size();
        int i = 0;
        long cur = 0;
        while(i < n){
            long t = books.get(i);
            cur = cur + t;
            if(cur > page){
                // Need one more student
                nstudent++;
                // reset cur
                cur = t;
            }
            i++;
        }
        return nstudent;
    }
    
    public long binarySearch(ArrayList<Integer> books, int totalStudent){
        if(books.size() < totalStudent)
            return -1;
        // Find the number of the max pages
        long maxPage = Integer.MIN_VALUE;
        // Sum of length of all boards
        long sum = 0;
        for(int pageNumber : books){
            maxPage = Math.max(maxPage, pageNumber);
            sum += pageNumber;
        }
        // Binary search on the number of pages
        long low = maxPage;
        long high = sum;

        while(low < high){
            long mid = (high - low)/2 + low;
            int nstudent = studentNeeded(mid, books);
            // If number of studetns needed is greater than totalStudent, then we need to 
            // increase page limit
            if(nstudent > totalStudent){
                low = mid + 1;    
            }
            else{
                high = mid;
            }
        }
        return low;
    }
    
    public int books(ArrayList<Integer> a, int b) {
        return (int)binarySearch(a, b);
    }
}

    public static void main(String[] args) {
        ArrayList<Integer> book = new ArrayList<>();
        int[] a = {97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93, 36, 85, 45, 28, 91, 94, 57, 1, 53, 8, 44, 68, 90, 24};
        //int[] a = {1000000, 1000000};
        for(int i=0; i<a.length; ++i)
            book.add(a[i]);
        AllocateBook ob = new AllocateBook();
        System.out.println(ob.studentNeeded(97, book));
        System.out.println(ob.books(book, 26));
    }
}
