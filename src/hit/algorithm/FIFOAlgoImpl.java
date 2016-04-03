package hit.algorithm;

import java.util.*;

public class FIFOAlgoImpl<K,V> implements IAlgoCache<K,V> {

	private int capacity;
	
	public FIFOAlgoImpl(int capacity){
		
		this.capacity = capacity;
		
	}
	@Override
	public V getElement(K key) {
		
		return getElement(key);
	}

	@Override
	public V putElement(K key, V value) {
		
		return putElement(key, value);
	}

	@Override
	public void removeElement(K key) {
		
		removeElement(key);
	}
	
	
}
