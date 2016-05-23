package hit.algorithm;

import java.util.*;

import hit.util.RamLinkedHashMap;

public class LRUAlgoCacheImpl<K,V> implements IAlgoCache<K,V>{
	
	private Map<K,V> map;
	Map.Entry<K,V> eldestCur;
	private int capacity;
	
	public LRUAlgoCacheImpl(int capacity){
		
		this.capacity = capacity;
		map = new RamLinkedHashMap<>(this.capacity);
	}
	
	@Override
	public synchronized V getElement(K key) {
		
		if(map.containsKey(key))
			return map.get(key);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V putElement(K key, V value) 
	{
			map.put(key, value);
			@SuppressWarnings("rawtypes")
			RamLinkedHashMap currentMap = (RamLinkedHashMap) this.map;
			return (V) currentMap.getEldestCur().getValue();
		
	}

	@Override
	public void removeElement(K key) {
		
		if(map.containsKey(key))
			map.remove(key);
	}
	
}
