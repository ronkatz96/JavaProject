package hit.memoryunits;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Page<T> implements Serializable{

	private T content; 
	private Long pageId;
	
	public Page(Long id, T content){
		
		this.pageId = id;
		this.content = content;
	}
	
	public T getContent(){
		return this.content;
	}
	
	public void setContent(T content){
		this.content = content;
	}
	
	public Long getPageId(){
		return this.pageId;
	}
	
	void setPageId(Long pageId){
		this.pageId = pageId;
	}
	
	public int hashCode(){
//		int result = 0;
//		result = (int)content/11;
//		result = (int)(result + pageId/11);
		return content.hashCode();	
	}
	
	@Override
	public String toString(){
		String s = "Page ID is: " + this.pageId + " Page content is: " + this.content;
		return s;
	}
	
	public boolean equals(Object obj){
		if ((obj instanceof Page) && (((Page<?>) obj).content == content) && (((Page<?>) obj).pageId == pageId))
			return true;
		else
			return false;
	}
}
