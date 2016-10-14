package com.holness.app;

import com.holness.app.edges.WeightedEdge;
import com.holness.app.graphs.DirectedRouteGraph;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphParser {
	private static final int WEIGHT = 2;
	private static final int END_POINT = 1;
	private static final int START_POINT = 0;
	private String path;
	private HashMap<String, Integer> vertexIndexes;
	private String[] edges;
	private ArrayList<WeightedEdge> weightedEdges;
	private DirectedRouteGraph graph;

	public GraphParser(String path) throws Exception {
		this.path = path;
		this.vertexIndexes = new HashMap<String, Integer>();
		this.weightedEdges = new ArrayList<WeightedEdge>();
		buildGraph();
	}

	public DirectedRouteGraph getGraph() {
		return this.graph;
	}

	private void buildGraph() throws Exception {
		parseEdges();
		this.graph = new DirectedRouteGraph(vertexCount());
		addEdges();
		this.graph.setVertexIndexKeys(this.vertexIndexes);
	}

	private void parseEdges() throws Exception {
    this.edges = parseFileToString().split(",");
		for (int index = 0; index < edges.length; index++) {
			String first = String.valueOf(edges[index].charAt(START_POINT));
			String second = String.valueOf(edges[index].charAt(END_POINT));
			int weight = Character.getNumericValue(edges[index].charAt(WEIGHT));
			addKeys(vertexIndexes, first, second, index);
			buildEdge(vertexIndexes.get(first), vertexIndexes.get(second), weight);
		}
	}

	private void buildEdge(int first, int second, int weight) {
		this.weightedEdges.add(new WeightedEdge(first, second, weight));
	}

	private int vertexCount() {
		return this.vertexIndexes.size();
	}

	private void addEdges() {
		for (WeightedEdge edge : this.weightedEdges) {
			graph.addEdge(edge);
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

	private String parseFileToString() throws Exception {
    List<String> lines = getFile();
    StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line.trim());
		}
		return sb.toString();
	}

  private List<String> getFile() {
    Path filePath = null;
    List<String> result = null;
    try {
      filePath =  Paths.get(path);
      result =  Files.readAllLines(filePath);
    } catch (Exception e) {
      System.out.println("File path error" + e);
    }
    return result;
  }
}
