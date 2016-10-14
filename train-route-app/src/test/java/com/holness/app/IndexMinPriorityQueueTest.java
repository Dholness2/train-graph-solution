import com.holness.app.IndexMinPriorityQueue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IndexMinPriorityQueueTest {

	IndexMinPriorityQueue<Double> testPQ;
	
	@Before
	public void setUp() throws Exception {
      int maxElementCount = 20;
	  testPQ = new IndexMinPriorityQueue<Double>(maxElementCount);  	
	}

	@Test
	public void testInitalStateIsEmpty() {
		Boolean result =  testPQ.isEmpty();
		assertTrue(result);
	}
	
	@Test
	public void testIdentifyELementbyIndex() {
      Double testKey = 7.0;
	  int index = 0;
	  testPQ.insert(index, testKey);
	  assertTrue(testPQ.contains(index));
    }
	
	@Test
	public void testAddsElementToQueue() {
	   Double testKey = 5.0;
	   int index = 0;
	   testPQ.insert(index, testKey);
	   assertTrue(testPQ.contains(index));
	}
	
	@Test
	public void testSortElementsFromLowestoHigest() {
	   Double minKey = 5.0;
	   int minIndex = 0;
	   testPQ.insert(minIndex, minKey);
	   testPQ.insert(1, 7.0);
	   testPQ.insert(2,10.0);
	   Double resultKey = testPQ.minKey();
	   int resultIndex = testPQ.minIndex();
	   assertTrue(minKey== resultKey);
	   assertTrue(minIndex== resultIndex);
	}
	
	@Test
	public void testReturnsAssociatedKeyValueBasedOnIndex(){
      Double testKey = 9.0;
      int index= 0;
      testPQ.insert(index,testKey);
      Double result = testPQ.keyOf(index);
      assertEquals(testKey,result);
	}
	
	
	@Test
	public void testItdecreasesKeyValuesBasedOnIndex() {
	  Double currentKey = 6.0;
	  int index = 0;
	  Double lowerKey = 4.0;
	  testPQ.insert(index, currentKey);
	  testPQ.decreaseKey(index,lowerKey);
	  Double result = testPQ.keyOf(index);
	  assertEquals(lowerKey,result);
	}
	
	@Test
	public void testDeletedMinElememnt() {
		 Double minKey = 5.0;
		 int minIndex = 1;
		 testPQ.insert(minIndex, minKey);
		 testPQ.insert(2, 7.0);
		 testPQ.insert(3,10.0);
		 int result = testPQ.delMin();
		 assertEquals(minIndex, result);
	}
		
	

}
