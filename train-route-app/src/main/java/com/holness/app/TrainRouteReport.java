package com.holness.app;

import com.holness.app.displays.ReportDisplay;
import com.holness.app.GraphAnalyzer;
import com.holness.app.GraphShortestPath;

import java.util.Map;

public class TrainRouteReport {

  private static final String SP = "shortestPaths";
  private static final String SP_CYCLES = "shortestPathCycles";
  private static final String ROUTES_DISTANCE = "routeDistances";
  private static final String ROUTES_STOPS_EXACT = "routeCountExactStops";
  private static final String ROUTES_STOPS_MAX = "routeCountDistancesMax";
  private static final int START_INDEX = 0;
  private static final int END_INDEX = 1;
  private static final int DISTANCE_INDEX = 2;

  private GraphAnalyzer analyzer;
  private GraphShortestPath sp;
	private Map<String,String[]> config;
  private ReportDisplay display;
	private int outputCount;
	
	public TrainRouteReport (ReportDisplay display, GraphAnalyzer analyzer, GraphShortestPath sp, Map<String,String[]> config)  {
    this.config = config;
    this.outputCount = 0;
	  this.analyzer = analyzer;
	  this.display = display;
	  this.sp =sp;
	}
	
  public void run() {
    reportRouteDistances();
    reportRouteStopCountMax();
    reportRouteCountDistanceExact();
    reportShortestPathExact();
    reportShortestPathCycles();
  }

private void reportShortestPathExact() {
  for (String path : config.get(SP)) {
		this.outputCount++;
	  this.display.writeOutput(this.outputCount+"."+"Output: "+ getSP(path));
	}
}

 private double getSP (String path) {
 	String start =  path.substring(START_INDEX, END_INDEX);
 	String end = path.substring(END_INDEX);
  return sp.ShortestPathfromSourceToNode(start, end);
 }

 private void reportShortestPathCycles() {
   for (String path : config.get(SP_CYCLES)) {
     this.outputCount++;
     this.display.writeOutput(this.outputCount+"."+"Output: "+ getSPCycles(path));
   }
 }

private double getSPCycles (String path) {
 	String start =  path.substring(START_INDEX, END_INDEX);
 	String end = path.substring(END_INDEX);
  return sp.ShortestPathfromSourceToNodeCycle(start);
}

private void reportRouteStopCountMax() {
	for (String path : config.get(ROUTES_STOPS_MAX)) {
		this.outputCount++;
		this.display.writeOutput(this.outputCount+"."+"Output: "+ getCountStopsMax(path));
	}
}

private void reportRouteCountDistanceExact() {
	for (String path : config.get(ROUTES_STOPS_MAX)) {
		this.outputCount++;
		this.display.writeOutput(this.outputCount+"."+"Output: "+ getCountDistanceExact(path));
	}
}

private int getCountDistanceExact(String path) {
  String start = path.substring(START_INDEX, END_INDEX);
 	String end = path.substring(END_INDEX, DISTANCE_INDEX);
  int weight = Character.getNumericValue(path.charAt(DISTANCE_INDEX));
  return analyzer.pathCountExact(start,end, weight);
}

private int getCountStopsMax(String path) {
  String start = path.substring(START_INDEX, END_INDEX);
 	String end = path.substring(END_INDEX, DISTANCE_INDEX);
  int stopLimit = Character.getNumericValue(path.charAt(DISTANCE_INDEX));
  return analyzer.pathCount(start,end, stopLimit);
}

private void reportRouteDistances() {
	for (String path : config.get(ROUTES_DISTANCE)) {
	  this.outputCount++;
    this.display.writeOutput(this.outputCount+ "."+ getRouteDistance(path));
	}
}

private String getRouteDistance(String path ) {
	int weight = analyzer.pathWeight(path);
	String result;
	if (weight == -1) {
		result = "OutPut: NO SUCH ROUTE";
	}else {
		result = "OutPut:" + weight;
	}
	return result;
}

}
