import com.holness.app.graphs.DirectedRouteGraph;
import com.holness.app.edges.Edge;
import com.holness.app.edges.WeightedEdge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class DirectedRouteGraphTest {
	DirectedRouteGraph testGraph;
	Edge testEdge;

	@Before
	public void setup() {
		int vertexCount = 2;
		testGraph = new DirectedRouteGraph(vertexCount);
		testEdge = new WeightedEdge(0, 5, 20);
	}

	@Test
	public void testreturnsVertexSize() {
		int vertexCount = 2;
		assertEquals(vertexCount, testGraph.getVertexCount());
	}

	@Test
	public void testAddsEdgeToGraph() {
		testGraph.addEdge(testEdge);
		ArrayList<Edge>[] edges = testGraph.getEdges();
		int vertex = testEdge.getStartPoint();
		Edge result = edges[vertex].get(0);
		assertTrue(testEdge.equals(result));
	}

	@Test
	public void testReturnsNodeEdgeList() {
		int routeNode = 0;
		testGraph.addEdge(testEdge);
		ArrayList<Edge> expectedList = this.testGraph.getAdjacentList(routeNode);
		assertTrue(expectedList.contains(testEdge));
	}
}
