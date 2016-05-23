package hit.algorithm;

import java.util.*;
import java.util.Map.Entry;

import hit.util.RamLinkedHashMap;

public class LFUAlgoCacheImpl<K,V> implements IAlgoCache<K,V> {

	private Map<K, V> cache;
	private Map<K, Integer> cacheCounter;
	private int capacity;
	
	public LFUAlgoCacheImpl(int capacity){
		
		this.capacity = capacity;
		cache = new RamLinkedHashMap<K,V>(this.capacity);
		cacheCounter = new RamLinkedHashMap<K,Integer>(this.capacity);	
	}
	
	@Override
	public V getElement(K key) {
		
		if(cacheCounter.containsKey(key)){
			Integer count = cacheCounter.get(key);
			count++;
			cacheCounter.put(key, count);
			return cache.get(key);
		}
		return null;
	}

	@Override
	public synchronized V putElement(K key, V value) {
		
		V curValue = null;
		if(capacity == cache.size()){
			K minKey = findMin();
			curValue = getElement(minKey);
			removeElement(minKey);
		}
		cache.put(key, value);
		cacheCounter.put(key, 1);
		return curValue;
	}

	@Override
	public void removeElement(K key) {
		
		if(cache.containsKey(key)){
			cache.remove(key);
			cacheCounter.remove(key);
		}
	}
	
	private K findMin() {
	
		Integer minValueInMap = (Collections.min(cacheCounter.values()));  // This will return minimum value in the Hash map
		for (Entry <K,Integer> entry : cacheCounter.entrySet()) {  // Iterate through Hash map
            if ((entry.getValue() == minValueInMap) || (cache.get(entry.getKey()) != null)) {                
                return entry.getKey();// return the key with minimum value
            }
        }
        return null;
	}
}
