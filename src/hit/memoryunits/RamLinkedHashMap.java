package hit.memoryunits;

import java.util.LinkedHashMap;
import java.util.Map;


public class RamLinkedHashMap<K,V> extends LinkedHashMap<K,V>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private final int MAX_ENTRIES;

	public RamLinkedHashMap(int capacity)
	{
		MAX_ENTRIES = capacity;
	}
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
       return size() > MAX_ENTRIES;
    }
}
