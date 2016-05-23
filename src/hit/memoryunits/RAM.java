package hit.memoryunits;


import java.util.logging.Level;

import hit.util.MMULogger;
import hit.util.RamLinkedHashMap;

public class RAM {

	private int	initialCapacity; 
	private RamLinkedHashMap<java.lang.Long,Page<byte[]>> pages ;
	
	public RAM(int initialCapacity){
		this.setInitialCapacity(initialCapacity);
		pages = new RamLinkedHashMap<java.lang.Long,Page<byte[]>>(initialCapacity);
		MMULogger.getInstance().write(String.format("RC %s",Integer.toString(initialCapacity)), Level.INFO);
	}
	
	public int getInitialCapacity() {
		return initialCapacity;
	}
	
	public RamLinkedHashMap<java.lang.Long,Page<byte[]>> getPages()
	{
		return pages;
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
	
	public synchronized Page<byte[]> getPage(Long pageId){
		return this.pages.get(pageId);
	}
	
	public synchronized void addPage(Page<byte[]> addPage){
		this.pages.put(addPage.getPageId(), addPage);
	}
	
	public synchronized void removePage(Page<byte[]> removePage){
		this.pages.remove(removePage);
	}
	
	public synchronized Page<byte[]>[] getPages(Long[] pageIds){
		@SuppressWarnings("unchecked")
		Page<byte[]>[] arr = new Page[pageIds.length];
		for (int i=0;i<pageIds.length;i++){
			arr[i] = this.pages.get(pageIds[i]);
		}
		return arr;
	}
	
	public synchronized void addPages(Page<byte[]>[] addPages){
		for (int i=0;i<addPages.length;i++){
			this.pages.put(addPages[i].getPageId(), addPages[i]);
		}
	}
	
	public synchronized void removePages(Page<byte[]>[] removePages){
		for (int i=0;i<removePages.length;i++){
			this.pages.remove(removePages[i]);
		}
	}
	
	public synchronized boolean isRamFull()
	{
		return (initialCapacity == pages.size());
	}
	
	public synchronized int pagesSize()
	{
		return pages.size();
	}
}
