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
public class Solution {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        List<String> l = new ArrayList<>();
        for(String s : paths){
            // Example: "/home//foo/"
            if(s.length() == 0 || s.compareTo(".") == 0)
                continue;
            if(s.compareTo("..") == 0){
                // Example: "/.."
                if(l.size() > 0)
                    l.remove(l.size()-1);
            }
            else{
                l.add(s);
            }
        }
        // Content of the list is the result
        StringBuilder r = new StringBuilder();
        for(String s : l){
            r.append("/");
            r.append(s);
        }
        
        // Example: "/../"
        if(r.length() == 0)
            return "/";
            
        return r.toString();
        
    }
}

// Stack
class Solution {
    public String simplifyPath(String path) {
        String[] tokens = path.split("/");
        int n = tokens.length;

        Deque<String> stk = new LinkedList<>();
        for(int i=0; i<n; ++i){
            if(tokens[i].length() == 0)
                continue;
            if(tokens[i].equals("..")){
                if(!stk.isEmpty())
                    stk.pop();
            }    
            else if(!tokens[i].equals(".")){
                stk.push(tokens[i]);
            }
        }
        StringBuilder r = new StringBuilder();
        while(!stk.isEmpty()){
            r.append("/");
            r.append(stk.pollLast());
        }
        return r.length() == 0 ? "/" : r.toString();
    }
}
