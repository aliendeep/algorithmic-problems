import java.util.*;

// Two Hash : To reduce checking
public class Hash<K, V> {
  private final static int prime1 = 1000003;
  private final static int prime2 = 1000033;

  public int hashCodeOfKey(K k, int prime){ 
    String s = k.toString();   
    int h = 0;
    for(int i=0; i<s.length(); i++){
      h = (h*256 + s.charAt(i)) % prime; 
    }
    return h;
  }

  class Entry<K, V> {
    int hash2Value;
    K key;
    V value;
    public Entry(K k, V v, int h2){
      this.key = k;
      this.value = v;
      hash2Value = h2;
    }
  }

  LinkedList<Entry<K, V>>[] hashEntries;
  boolean[] hash2Entries;

  public Hash(){
    hashEntries = new LinkedList[prime1];
    hash2Entries = new boolean[prime2];
  }
  
  public int hash2(K key){
    return hashCodeOfKey(key, prime2);
  }
  
  public int hash1(K key){
    return hashCodeOfKey(key, prime1);
  }

  boolean containsKey(K key){
    int h2 = hash2(key); 
    if(hash2Entries[h2] == false)
      return false;
    int h1 = hash1(key);;
    LinkedList<Entry<K, V>> entries = hashEntries[h1];
    for(Entry<K, V> entry : entries){
      if(entry.hash2Value == h2 && entry.key == key)
        return true;
    }
    return true;
  }

  public V get(K key){
    int h2 = hash2(key); 
    if(hash2Entries[h2] == false)
      return null;

    int h1 = hash1(key);;
    LinkedList<Entry<K, V>> entries = hashEntries[h1];
    for(Entry<K, V> entry : entries){
      if(entry.hash2Value == h2 && entry.key == key){
        return entry.value;
      }
    }
    // return null for non existent key
    return null;
  }

  public void put(K key, V value){
    int h2 = hash2(key); 
    int h1 = hash1(key);
    if(hash2Entries[h2] == false){
      hash2Entries[h2] = true;
      hashEntries[h1] = new LinkedList<Entry<K, V>>();      
    }

    // Replace
    LinkedList<Entry<K, V>> collided = hashEntries[h1];
    for(Entry<K, V> entry : collided){
      if(entry.hash2Value == h2 && entry.key == key){              
        entry.value = value;
        return;
      }
    }
    // Collision, add entry at the end of the list
    Entry<K, V> e = new Entry<K, V>(key, value, h2);
    collided.add(e);
  }

  public static void main(String[] args){
    Hash<String, Integer> h = new Hash();
    System.out.println(h.containsKey("shuvra"));
    System.out.println(h.containsKey("test"));
    // default value for not existent key is null
    System.out.println(h.get("test"));
    h.put("test", 100);
    System.out.println(h.containsKey("test"));
    System.out.println(h.get("test"));
    h.put("test", 150);
    System.out.println(h.containsKey("test"));
    System.out.println(h.get("test"));
  }
}