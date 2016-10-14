import com.holness.app.GraphAnalyzer;
import com.holness.app.graphs.DirectedRouteGraph;
import com.holness.app.edges.WeightedEdge;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class GraphAnalyzerTest {

  HashMap<String, Integer> vertexIndexes;
	DirectedRouteGraph testGraph;

	private void addKeys(Map<String, Integer> vertexIndexes, String first, String second, int count) {
		int currentCount = vertexIndexes.size();
		if (!vertexIndexes.containsKey(first)) {
			vertexIndexes.put(first, currentCount);
			currentCount++;
		}
		if (!vertexIndexes.containsKey(second)) {
			vertexIndexes.put(second, currentCount);
		}
	}

	@Before
	public void setUp() throws Exception {
		String edges[] = { "AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7" };
		vertexIndexes = new HashMap<String, Integer>();
		testGraph = new DirectedRouteGraph(5);
		for (int i = 0; i < edges.length; i++) {
			String first = String.valueOf(edges[i].charAt(0));
			String second = String.valueOf(edges[i].charAt(1));
			addKeys(vertexIndexes, first, second, i);
			int wieght = Character.getNumericValue(edges[i].charAt(2));
			WeightedEdge wEdge = new WeightedEdge(vertexIndexes.get(first), vertexIndexes.get(second), wieght);
			testGraph.addEdge(wEdge);
		}
		testGraph.setVertexIndexKeys(vertexIndexes);
	}
    
	
	@Test
	public void testFindsPathsWeightOddStopCount() {
		GraphAnalyzer testAnalyzer = new GraphAnalyzer(testGraph);
		String pathRoute =  "A-B-C"; 
		double testPathWeight = testAnalyzer.pathWeight(pathRoute);
		double expectedWeight = 9.0;
		assertTrue(expectedWeight == testPathWeight);
	}
	
	@Test
	public void testFindsPathsWeightEvenStopCount() {
		GraphAnalyzer testAnalyzer = new GraphAnalyzer(testGraph);
		String path = "A-E-B-C-D";
		double testPathWeight = testAnalyzer.pathWeight(path);
		double expectedWeight = 22;
		assertTrue(expectedWeight == testPathWeight);
    }
	
	
	@Test
	public void testFindsPathCountBasedOnStopsLimit() {
		GraphAnalyzer testAnalyzer = new GraphAnalyzer(testGraph);
		int stopLimit = 3;
		String startVertex = "C";
		String endvertex = "C";
		int resultCount =  testAnalyzer.pathCount(startVertex, endvertex, stopLimit);
		int expectedCount = 2;
		assertEquals(expectedCount, resultCount);
	}

	@Test
	public void testFindsPathCountBasedOnStopsLimitLength() {
		GraphAnalyzer testAnalyzer = new GraphAnalyzer(testGraph);
		int length = 4;
		String startVertex = "A";
		String endvertex = "C";
		int resultCount = testAnalyzer.pathCountExact(startVertex, endvertex, length);
		int expectedCount = 3;
		assertEquals(expectedCount, resultCount);
	}
	
	@Test
	public void testFindsPathCountBasedOnWeightLimit() {
		GraphAnalyzer testAnalyzer = new GraphAnalyzer(testGraph);
		int pathWeightLimit = 30;
	    String startVertex = "C";
		String endvertex = "C";
		int resultCount = testAnalyzer.pathCountWeightLimit(startVertex, endvertex, pathWeightLimit);
		int expectedCount = 7;
		assertEquals(expectedCount, resultCount);
	}
}
