package hit.algorithm;

import java.util.*;
import java.util.Map.Entry;

public class LRUAlgoCacheImpl<K,V> implements IAlgoCache<K,V>{
	
	private Map<K,V> map;
	Map.Entry<K,V> eldestCur;
	private int capacity;
	
	public LRUAlgoCacheImpl(int capacity){
		
		this.capacity = capacity;
		map = new LinkedHashMap<>(this.capacity);
	}
	
	@Override
	public synchronized V getElement(K key) {
		
		if(map.containsKey(key))
			return map.get(key);
		return null;
	}

	@Override
	public V putElement(K key, V value) {
		
		V LRUEntry = null;
		for(K index : map.keySet())
		{
			LRUEntry = map.get(index);
			
		}
		map.put(key, value);
		return LRUEntry;
	}

	@Override
	public void removeElement(K key) {
		
		if(map.containsKey(key))
			map.remove(key);
	}
	
}
