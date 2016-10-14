import com.holness.app.TrainRouteReport;
import com.holness.app.graphs.Graph;
import com.holness.app.graphs.DirectedRouteGraph;
import com.holness.app.edges.WeightedEdge;
import com.holness.app.GraphParser;
import com.holness.app.GraphAnalyzer;
import com.holness.app.GraphShortestPath;
import com.holness.app.displays.ReportDisplay;

import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TrainRouteReportTest {
	
  private TrainRouteReport testReport;
	private DisplayMock displayMock;
  private HashMap<String,Integer> vertexIndexes;
 
  private Map getConfig() {
    String routeDistances [] = {"A-B-C","A-D","A-D-C","A-E-B-C-D","A-E-D"};
    String [] routeCountDistancesMax = {"CC3"};
    String [] routeCountExactStops  = {"CC4"};
    String [] shortestPaths = {"AC"};
    String [] shortestPathCycles ={"BB"};

    HashMap <String, String[]> reportConfig = new HashMap<String,String[]>();
    reportConfig.put("routeDistances", routeDistances);
    reportConfig.put("routeCountDistancesMax", routeCountDistancesMax);
    reportConfig.put("routeCountExactStops", routeCountExactStops);
    reportConfig.put("shortestPaths",shortestPaths);
    reportConfig.put("shortestPathCycles", shortestPathCycles);
    return reportConfig;
  }

  @Before
	public void setUp() throws Exception {
    displayMock  =  new DisplayMock();
    DirectedRouteGraph  testGraph = getGraph();
    GraphAnalyzer testAnalyzer = new GraphAnalyzer(testGraph);
    GraphShortestPath testSp =  new GraphShortestPath(testGraph);
    testReport = new TrainRouteReport(displayMock, testAnalyzer, testSp, getConfig());
 	}

	@Test 
	public void testFindsPathDistanceForSet() {
	 testReport.run();
   String result = displayMock.getOutput();
   String [] expectedResults= { "1.OutPut:9","2.OutPut:5","3.OutPut:13","4.OutPut:22",
       "5.OutPut: NO SUCH ROUTE","6.Output: 2","7.Output: 1","8.Output: 9.0","9.Output: 9.0" };
	 for(String expected: expectedResults) {
     assertTrue(result.contains(expected));
	 }
 }

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

	private DirectedRouteGraph  getGraph() {
		String edges[] = { "AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7" };
		vertexIndexes = new HashMap<String, Integer>();
		DirectedRouteGraph testGraph = new DirectedRouteGraph(5);
		for (int i = 0; i < edges.length; i++) {
			String first = String.valueOf(edges[i].charAt(0));
			String second = String.valueOf(edges[i].charAt(1));
			addKeys(vertexIndexes, first, second, i);
			int wieght = Character.getNumericValue(edges[i].charAt(2));
			WeightedEdge wEdge = new WeightedEdge(vertexIndexes.get(first), vertexIndexes.get(second), wieght);
			testGraph.addEdge(wEdge);
		}
		testGraph.setVertexIndexKeys(vertexIndexes);
    return testGraph;
	}

  private class DisplayMock implements ReportDisplay {

	private StringBuilder sb;

	public DisplayMock() {
		sb = new StringBuilder();
	}

	public void writeOutput(String outPut) {
		sb.append(outPut);
	}

	public String getOutput() {
		return sb.toString();
	}

 }
}
