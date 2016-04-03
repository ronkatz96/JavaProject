package hit.algorithm;

import java.util.*;

public class LRUAlgoCacheImpl<K,V> implements IAlgoCache<K,V>{
	
	private LinkedHashMap<K,V> map;
	Map.Entry<K,V> eldestCur;
	private int capacity;
	
	public LRUAlgoCacheImpl(int capacity){
		
		this.capacity = capacity;
		map = new LinkedHashMap<>(this.capacity);
	}
	
	@Override
	public V getElement(K key) {
		
		if(map.containsKey(key))
			return map.get(key);
		return null;
	}

	@Override
	public V putElement(K key, V value) {
		
		V curValue = null;
		if(capacity == map.size()){
			Map.Entry<K,V> leastKey = this.eldestCur;
			curValue = leastKey.getValue();
			removeElement(leastKey.getKey());
		}
		map.put(key, value);
		return curValue;
	}

	@Override
	public void removeElement(K key) {
		
		if(map.containsKey(key))
			map.remove(key);
	}
	
}
