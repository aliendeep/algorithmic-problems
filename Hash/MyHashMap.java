import java.util.*;

public class MyHashMap<K, V> {
  private final static int prime = 1000003;
  public int hashCodeOfKey(K k){ 
    String s = k.toString();   
    int h = 0;
    for(int i=0; i<s.length(); i++){
      h = (h*256 + s.charAt(i)) % prime; 
    }
    return h;
  }

  class Entry<K, V> {
    K key;
    V value;
    public Entry(K k, V v){
      this.key = k;
      this.value = v;
    }
  }

  LinkedList<Entry<K, V>>[] hashEntries;

  public MyHashMap(){
    hashEntries = new LinkedList[prime];
  }
  
  public int hash(K key){
    return hashCodeOfKey(key);
  }

  boolean containsKey(K key){
    int x = hashCodeOfKey(key);
    if(hashEntries[x] == null)
      return false;
    return true;
  }

  public V get(K key){
    int x = hashCodeOfKey(key);
    if(hashEntries[x] == null)
      return null;

    LinkedList<Entry<K, V>> entries = hashEntries[x];
    for(Entry<K, V> entry : entries){
      if(entry.key == key){
        return entry.value;
      }
    }
    return null;
  }
  public void put(K key, V value){
    int x = hashCodeOfKey(key);
    if(hashEntries[x] == null){
      hashEntries[x] = new LinkedList<Entry<K, V>>();
    }

    // Replace
    LinkedList<Entry<K, V>> collided = hashEntries[x];
    for(Entry<K, V> entry : collided){
      if(entry.key == key){              
        entry.value = value;
        return;
      }
    }
    // Collision, add entry at the end of the list
    Entry<K, V> e = new Entry<K, V>(key, value);
    collided.add(e);
  }

  public static void main(String[] args){
    MyHashMap<String, Integer> h = new MyHashMap();
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