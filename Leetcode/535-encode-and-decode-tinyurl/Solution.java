/* 
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl 
and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction 
on how your encode/decode algorithm should work. You just need to ensure that a 
URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
*/

public class Codec {
    Map<String, String> map;
    Random rand;
    
    public Codec() {
        map = new HashMap<>();
        rand = new Random();
    }
    
    String genHashCode(String url) {
        long val = 0;
        for(int i=0; i<url.length(); ++i) {
            val = val*10 + url.charAt(i)*rand.nextInt();
        }  
        return Long.toString(val);
    }
    
    public String hashCode(String url) {
        String r = new String();
        while(true) {
            r = genHashCode(url);
            if(!map.containsKey(r))
                break;
        }
        map.put(r, url);
        return r;        
    }
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        return hashCode(longUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);    
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
