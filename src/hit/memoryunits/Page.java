package hit.memoryunits;

public class Page<T> {

	private T content; 
	private java.lang.Long	pageId;
	
	public Page(java.lang.Long id, T content){
		
		this.pageId = id;
		this.content = content;
	}
	
	public T getContent(){
		return this.content;
	}
	
	void setContent(T content){
		this.content = content;
	}
	
	public java.lang.Long getPageId(){
		return this.pageId;
	}
	
	void setPageId(java.lang.Long pageId){
		this.pageId = pageId;
	}
	
	public int hashCode(){
		int result = 0;
		result = (int)content/11;
		result = (int)(result + pageId/11);
		return result;	
	}
	
	public java.lang.String toString(){
		String s = "Page ID is: " + this.pageId + " Page content is: " + this.content;
		return s;
	}
	
	public boolean equals(java.lang.Object obj){
		if ((obj instanceof Page) && (((Page<?>) obj).content == content) && (((Page<?>) obj).pageId == pageId))
			return true;
		else
			return false;
	}
}
