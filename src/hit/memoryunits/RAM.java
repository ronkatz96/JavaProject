package hit.memoryunits;

public class RAM {

	private int	initialCapacity; 
	private java.util.Map<java.lang.Long,Page<byte[]>>	pages ;
	
	public RAM(int initialCapacity){
		this.setInitialCapacity(initialCapacity);
	}
	
	public int getInitialCapacity() {
		return initialCapacity;
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
	
	public Page<byte[]> getPage(int pageId){
		return this.pages.get(pageId);
	}
	
	public void addPage(Page<byte[]> addPage){
		this.pages.put(addPage.getPageId(), addPage);
	}
	
	public void removePage(Page<byte[]> removePage){
		this.pages.remove(removePage);
	}
	
	public Page<byte[]>[] getPages(java.lang.Long[] pageIds){
		Page<byte[]>[] arr = new Page[pageIds.length];
		for (int i=0;i<pageIds.length;i++){
			arr[i] = this.pages.get(pageIds[i]);
		}
		return arr;
	}
	
	public void addPages(Page<byte[]>[] addPages){
		for (int i=0;i<addPages.length;i++){
			this.pages.put(addPages[i].getPageId(), addPages[i]);
		}
	}
	
	public void removePages(Page<byte[]>[] removePages){
		for (int i=0;i<removePages.length;i++){
			this.pages.remove(removePages[i]);
		}
	}
}
