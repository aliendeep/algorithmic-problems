/*
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
*/
import java.util.*;
public class Twitter {
    // Contains list of all users
    public static final int HEAP_SIZE = 20;
    public static Map<Integer, User> userMapping;
    public static int tweetId;
    
    class Tweet{
        public int id;
        public int msg;
        public Tweet(int msg){
            this.id = tweetId++;
            this.msg = msg;
        }
    }
    
    class User{
        int userId;
        List<Tweet> ownTweets;
        Set<Integer> followsList;
        PriorityQueue<Tweet> minHeap;
        
        public User(int userId){
            this.userId = userId;
            ownTweets = new ArrayList<>();  
            followsList = new HashSet<>(); 
        }
        
        public void postTweet(int tweetId){
            ownTweets.add(new Tweet(tweetId));
        }
        
        public void follows(int id){
            followsList.add(id);        
        }

        public boolean unfollows(int id){
            if(!followsList.contains(id))
                return false;
            followsList.remove(id);
            return true;
        }
    
        public List<Tweet> getTopRecentTweets(){
            List<Tweet> listTweets = new ArrayList<>();
            // 0 contains the most recent 
            int cnt = 0;
            for(int i=ownTweets.size()-1; i>=0 && cnt < HEAP_SIZE; i--){
                listTweets.add(ownTweets.get(i));
                cnt++;
            }
            return listTweets;
        }
        
        public void printTweet(Tweet a){
            System.out.print(" Id " + a.id + " msg " + a.msg);
        }
        public void print(List<Tweet> r){
            for(int i=0; i<r.size(); i++)
                printTweet(r.get(i));
            System.out.println();
        }
        public void printI(List<Integer> r){
            for(int i=0; i<r.size(); i++)
                System.out.print(r.get(i)+ " ");
            System.out.println();
        }
        
        public List<Integer> populateNewsFeed(){
            Comparator comp = new Comparator<Tweet>(){
              @Override
              public int compare(Tweet a, Tweet b){
                  return Integer.compare(a.id, b.id);
              }
            };
            
            minHeap = new PriorityQueue<>(HEAP_SIZE, comp);
            List<Tweet> myTweets = getTopRecentTweets();
            for(int i=0; i<myTweets.size(); i++){
                minHeap.add(myTweets.get(i));
            }
            // All initialized to 0
            Iterator it = followsList.iterator();
            while(it.hasNext()){
                int followId = (Integer)it.next();
                User u = userMapping.get(followId);
                List<Tweet> followerTweets = u.getTopRecentTweets();
                for(int i=0; i<followerTweets.size(); i++){
                    if(minHeap.isEmpty()){
                        minHeap.add(followerTweets.get(i));
                        continue;
                    }
                    Tweet t = minHeap.peek();
                    if(minHeap.size() > HEAP_SIZE && t.id > followerTweets.get(i).id)
                        break;
                    minHeap.add(followerTweets.get(i));
                    // remove the smallest element
                    if(minHeap.size() > HEAP_SIZE){
                        minHeap.remove();
                    }
                }
            }
            if(minHeap.size() == 0)
                return Collections.EMPTY_LIST;
            
            // Remove duplicate elements
            List<Integer> result = new ArrayList<>();
            int i = 0;
            while(!minHeap.isEmpty()){
                Tweet t = minHeap.poll();
                if(i > 0 && t.msg == result.get(result.size()-1)){
                    i++;
                    continue;
                }
                result.add(t.msg);
                i++;
            }
            Collections.reverse(result);
            while(result.size() > 10){
                result.remove(result.size()-1);
            }                
            return result;
        }
    };
    
    /** Initialize your data structure here. */
    public Twitter() {
        userMapping = new HashMap<Integer, User>();
        tweetId = 0;
    }
    
    public void createUser(int userId){
        // If the user is a new user, create user id
        if(!userMapping.containsKey(userId)){        
            userMapping.put(userId, new User(userId));
        }
    }
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        createUser(userId);
        User u = userMapping.get(userId);
        u.postTweet(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        createUser(userId);
        User u = userMapping.get(userId);
        List<Integer> r = u.populateNewsFeed();       
        return r; 
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        createUser(followerId);
        createUser(followeeId);
        User u = userMapping.get(followerId);
        u.follows(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        createUser(followerId);
        createUser(followeeId);
        User u = userMapping.get(followerId);
        u.unfollows(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */