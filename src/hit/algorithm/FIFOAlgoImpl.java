package hit.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class FIFOAlgoImpl<K,V> implements IAlgoCache<K,V>{
	private java.util.Map <K,V> pages;
	private ArrayList<K> putOrder;
	private int maxIndex;
	private int initialCapacity;

	public FIFOAlgoImpl(int initialCapacity){
		pages=new HashMap<K,V>();
		putOrder=new ArrayList<K>();
		maxIndex=0;
		this.initialCapacity=initialCapacity;
	}
	@Override
	public V getElement (K key){
		return pages.get(key);
	}
	@Override
	public V putElement (K key, V value){
		V curValue=null;
		if (pages.size()==initialCapacity){
			curValue=pages.get(putOrder.get(0));
			removeElement (putOrder.get(0));
		}
		putOrder.add(maxIndex, key);
		maxIndex++;
		pages.put(key,value);
		return curValue;
	}
	@Override
	public void removeElement(K key){
		if(putOrder.remove(key)){
			maxIndex--;
			pages.remove(key);
		}
	}
	@Override
	public String toString(){
		return putOrder.toString();
	}
}
