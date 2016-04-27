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

	public synchronized java.util.List<byte[]> getData() 
	{
		return data;
	}

	public synchronized void setData(java.util.List<byte[]> data) {
		this.data = data;
	}

	public synchronized java.util.List<java.lang.Long> getPages() 
	{
		return pages;
	}

	public synchronized void setPages(java.util.List<java.lang.Long> pages) {
		this.pages = pages;
	}

	public synchronized int getSleepMs() {
		return sleepMs;
	}

	public synchronized void setSleepMs(int sleepMs) {
		this.sleepMs = sleepMs;
	}
	
	public synchronized java.lang.String toString()
	{
		
		return pages.toString();
	}
	
}
