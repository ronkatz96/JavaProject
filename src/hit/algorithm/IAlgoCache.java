package hit.algorithm;

public interface IAlgoCache<K,V> {
	
	V getElement(K key);
	V putElement(K key, V value);
	void removeElement(K key);
}
