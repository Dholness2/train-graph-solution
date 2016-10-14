import com.holness.app.GraphParser;
import com.holness.app.graphs.DirectedRouteGraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class GraphParserTest {

	private GraphParser testParser;
	
	@Before
	public void setUp() throws Exception {
   String path = "src/main/java/com/holness/app/SampleData.txt"; 
   testParser  = new GraphParser(path);
	}

	@Test
    public void testBuildsGraphBasedOnSampleFile() throws Exception{
      DirectedRouteGraph testGraph = testParser.getGraph();
      int vertexCount =  5;  // sample data graph AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
      assertEquals(vertexCount, testGraph.getVertexCount());
	}


	@Test
    public void testAddsAllVertexs() throws Exception{
      DirectedRouteGraph testGraph = testParser.getGraph();
      String [] expectedVertices = { "A","B","C","D","E","E"};
      HashMap <String,Integer> vertices = testGraph.getIndexKeyMap();
      for(String vertex: expectedVertices) {
        assertTrue(vertices.containsKey(vertex));
      }
    }
}
