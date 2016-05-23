package hit.memoryunits;

import java.util.LinkedHashMap;
import java.util.Map;


public class RamLinkedHashMap<K,V> extends LinkedHashMap<K,V>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private final int MAX_ENTRIES;
    private Map.Entry<K, V> eldestCur;

	public RamLinkedHashMap(int capacity)
	{
		MAX_ENTRIES = capacity;
	}
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) 
    {
    	setEldestCur(eldest);
       return size() > MAX_ENTRIES;
    }
	public Map.Entry<K, V> getEldestCur() {
		return eldestCur;
	}
	public void setEldestCur(Map.Entry<K, V> eldestCur) {
		this.eldestCur = eldestCur;
	}
    
}
