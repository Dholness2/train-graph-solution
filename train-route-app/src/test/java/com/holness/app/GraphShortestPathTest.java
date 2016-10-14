import com.holness.app.GraphShortestPath;
import com.holness.app.graphs.DirectedRouteGraph;
import com.holness.app.edges.WeightedEdge;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class GraphShortestPathTest {

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
  public void testItReturnsShortestPath() {
    String source = "A";
    GraphShortestPath testSP = new GraphShortestPath(testGraph);
    String endNode = "C";
    double length = testSP.ShortestPathfromSourceToNode(source, endNode);
    double expectedLength = 9.0;
    assertTrue(expectedLength == length);
  }

  @Test
  public void testItReturnsShortestPathCycle() {
    String source = "B";
    GraphShortestPath testSP = new GraphShortestPath(testGraph);
    double length = testSP.ShortestPathfromSourceToNodeCycle(source);
    double expectedLength = 9.0;
    assertTrue(expectedLength == length);
  }
}
