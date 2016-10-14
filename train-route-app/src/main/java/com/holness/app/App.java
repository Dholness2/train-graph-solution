package com.holness.app;

import com.holness.app.GraphParser;
import com.holness.app.GraphAnalyzer;
import com.holness.app.GraphShortestPath;
import com.holness.app.displays.ReportDisplay;
import com.holness.app.displays.CommandLineDisplay;
import com.holness.app.TrainRouteReport;

import java.util.HashMap;

public class App {
  
  public static final String DEFAULT_PATH  = "src/main/java/com/holness/app/SampleData.txt";
	public static String routeDistances [] = {"A-B-C","A-D","A-D-C","A-E-B-C-D","A-E-D"};
	public static String [] routeCountDistancesMax = {"CC3"};
	public static String [] routeCountExactStops = {"AC4"};
	public static String [] shortestPaths = {"AC"};
	public static String [] shortestPathCycles ={"BB"};
  public static String [] routeCountDistancesMaxCycle = {"CC30"};
  public static void main( String[] args ) throws Exception {

    HashMap <String, String[]> reportConfig = new HashMap<String,String[]>();
    reportConfig.put("routeDistances", routeDistances);
    reportConfig.put("routeCountDistancesMax", routeCountDistancesMax);
    reportConfig.put("routeCountExactStops", routeCountExactStops);
    reportConfig.put("shortestPaths",shortestPaths);
    reportConfig.put("shortestPathCycles", shortestPathCycles);
    reportConfig.put("routeCountDistancesMaxCycle", routeCountDistancesMaxCycle);

    String path = DEFAULT_PATH;
    GraphParser parser = new GraphParser(path);
    GraphAnalyzer analyzer = new GraphAnalyzer(parser.getGraph());
    GraphShortestPath sp = new GraphShortestPath(parser.getGraph());
    ReportDisplay display = new CommandLineDisplay();
    TrainRouteReport  report = new TrainRouteReport(display,analyzer,sp,reportConfig);
    report.run();
  }
}
