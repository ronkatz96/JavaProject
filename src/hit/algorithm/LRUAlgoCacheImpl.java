package hit.algorithm;

import java.util.*;

public class LRUAlgoCacheImpl<K,V> implements IAlgoCache<K,V>{
	
	private LinkedHashMap<K,V> map;
	java.util.Map.Entry<K,V> eldestCur;
	private int capacity;
	
	public LRUAlgoCacheImpl(int capacity){
		
		map = new LinkedHashMap<>(capacity);
		
		this.capacity = capacity;
		
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
}
