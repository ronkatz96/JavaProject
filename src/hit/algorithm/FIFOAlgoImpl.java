package hit.algorithm;

import java.util.*;
import java.util.Map.Entry;

public class FIFOAlgoImpl<K,V> extends LinkedHashMap<K,V> implements IAlgoCache<K,V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int capacity;
	private LinkedHashMap<K,V> map;
	
	public FIFOAlgoImpl(int capacity){
		
		this.capacity = capacity;
		map = new LinkedHashMap<K, V>(this.capacity);
		
	}
	@Override
	public V getElement(K key) {
		
		return map.get(key);
	}

	@Override
	public V putElement(K key, V value) {
		
		return map.put(key, value);
	
	}

	@Override
	public void removeElement(K key) {
		
		map.remove(key);
	}
	
	 @Override
	public boolean removeEldestEntry(Entry<K, V> entry) {
	    return size() > this.capacity;
	  }
	
	
}
