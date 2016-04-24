package hit.algorithm;

public class IAlgoCacheTest {

	public static void main(String[] args)
	{
		test();
		test1();
		/* Output of this method:
		 * 	class hit.algorithm.FIFOAlgoImpl
			class hit.algorithm.LFUAlgoCacheImpl
			class hit.algorithm.LRUAlgoCacheImpl
		 * 
		 */
	}
	public static void test() 
	{
		IAlgoCache cache1 = new FIFOAlgoImpl(5);
		IAlgoCache cache2 = new FIFOAlgoImpl(6);
		IAlgoCache cache3 = new LFUAlgoCacheImpl(5);
		IAlgoCache cache4 = new LFUAlgoCacheImpl(6);
		IAlgoCache cache5 = new LRUAlgoCacheImpl(5);
		IAlgoCache cache6 = new LRUAlgoCacheImpl(6);
		
	}
	
	public static void test1() 
	{
		IAlgoCache test;
		FIFOAlgoImpl cache1 = new FIFOAlgoImpl(5);
		LFUAlgoCacheImpl cache2 = new LFUAlgoCacheImpl(5);
		LRUAlgoCacheImpl cache3 = new LRUAlgoCacheImpl(5);
		
		test = cache1;
		System.out.println(test.getClass());
		test = cache2;
		System.out.println(test.getClass());
		test = cache3;
		System.out.println(test.getClass());
		

	}

}
