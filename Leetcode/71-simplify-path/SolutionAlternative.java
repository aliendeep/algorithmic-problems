/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/
// Using Stack
public class Solution {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");

        Deque<String> stk = new LinkedList<>();
        for(String s : paths){
            // Example: "/home//foo/"
            if(s.length() == 0 || s.compareTo(".") == 0)
                continue;
            if(s.compareTo("..") == 0){
                // Example: "/.."
                if(!stk.isEmpty())
                    stk.removeFirst();
            }
            else{
                stk.addFirst(s);
            }
        }
        // Content of the list is the result
        StringBuilder r = new StringBuilder();
        while(!stk.isEmpty()){
            r.append("/");
            r.append(stk.removeFirst());
        }
        
        // Example: "/../"
        if(r.length() == 0)
            return "/";
        
        String[] t = r.toString().split("/");
        StringBuilder f = new StringBuilder();
        for(int i=t.length-1; i>=0; i--){
            // "/home//foo/"
            if(t[i].length() == 0)
                continue;
            f.append("/");
            f.append(t[i]);
        }
        return f.toString();
        
    }
}