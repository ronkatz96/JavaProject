package hit.processes;

public class ProcessCycle 
{
	private java.util.List<byte[]> data;
	private java.util.List<java.lang.Long> pages;
	private int sleepMs;
	
	public ProcessCycle(java.util.List<java.lang.Long> pages, int sleepMs, java.util.List<byte[]> data)
	{
		this.setData(data);
		this.setPages(pages);
		this.setSleepMs(sleepMs);
	}

	public java.util.List<byte[]> getData() {
		return data;
	}

	public void setData(java.util.List<byte[]> data) {
		this.data = data;
	}

	public java.util.List<java.lang.Long> getPages() {
		return pages;
	}

	public void setPages(java.util.List<java.lang.Long> pages) {
		this.pages = pages;
	}

	public int getSleepMs() {
		return sleepMs;
	}

	public void setSleepMs(int sleepMs) {
		this.sleepMs = sleepMs;
	}
	
	public java.lang.String toString()
	{
		//TODO what should the overridden toString return?
		return "should be replaced with actual content";
	}
	
}
