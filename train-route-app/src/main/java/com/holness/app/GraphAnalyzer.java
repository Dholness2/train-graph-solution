
package com.holness.app;

import com.holness.app.graphs.Graph;
import com.holness.app.edges.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GraphAnalyzer {

	private Graph graph;
	private int count;
	private HashMap<String, Integer> keyMap;

	public GraphAnalyzer(Graph graph) {
		this.graph = graph;
		this.keyMap = graph.getIndexKeyMap();
	}

	public int pathWeight(String path) {
		String [] stops = path.split("-");
		int pathWeight = 0;
		for (int i = 0; i < stops.length-1; i++) {
			Edge currentEdge = findEdge(keyMap.get(stops[i]), keyMap.get(stops[i + 1]));
			if(currentEdge != null) { 
			    pathWeight = pathWeight + currentEdge.getWeight();
			}else {
				pathWeight = -1;
			}
		}
		return pathWeight;

	}

	public int pathCount(String startVertex, String endvertex, int stopLimit) {
		resetCount();
		dfs(keyMap.get(startVertex), 0, keyMap.get(endvertex), stopLimit);
		return this.count;
	}

	public int pathCountExact(String startVertex, String endvertex, int length) {
		resetCount();
		dfsExact(keyMap.get(startVertex), 1, keyMap.get(endvertex), length);
		return this.count;
	}
	
	public int pathCountWeightLimit(String startVertex, String endvertex, int weightLimit) {
		resetCount();
    dfsExactWeight(keyMap.get(startVertex), keyMap.get(endvertex), weightLimit, 0);
		return this.count;
	}
	
	private Edge findEdge(int startPoint, int endPoint) {
		Edge result = null;
		for (Edge edge : this.graph.getAdjacentList(startPoint)) {
			if (edge.getEndPoint() == endPoint) {
				result = edge;
				break;
			}
		}
		return result;
	}

	private void resetCount() {
		this.count = 0;
	}

	private void dfs(int vertexIndex, int depth, int search, int limit) {
		if (depth == limit) {
			return;
		}
		LinkedList<ArrayList<Edge>> queue = new LinkedList<ArrayList<Edge>>();
		queue.add(graph.getAdjacentList(vertexIndex));
		while (!queue.isEmpty()) {
			for (Edge edge : queue.poll()) {
				if (containsVertex(edge, search)) {
					this.count++;
				}
				dfs(edge.getEndPoint(), depth + 1, search, limit);
			}
		}
	}

	private void dfsExact(int vertexIndex, int depth, int search, int length) {
		if (depth == length) {
			return;
		}
		LinkedList<ArrayList<Edge>> queue = new LinkedList<ArrayList<Edge>>();
		queue.add(graph.getAdjacentList(vertexIndex));
		while (!queue.isEmpty()) {
			for (Edge edge : queue.poll()) {
				if (containsVertex(edge, search) && (depth == length)) {
					this.count++;
				}
				dfs(edge.getEndPoint(), depth + 1, search, length);
			}
		}
	}

	private void dfsExactWeight(int vertexIndex, int search, int weightLimit, int pathWeight) {
		if (pathWeight >= weightLimit) {
			return;
		}
		LinkedList<ArrayList<Edge>> queue = new LinkedList<ArrayList<Edge>>();
		queue.add(graph.getAdjacentList(vertexIndex));
		while (!queue.isEmpty()) {
			for (Edge edge : queue.poll()) {
				int currentWeight = pathWeight + edge.getWeight();
				if (containsVertex(edge, search) && (currentWeight < weightLimit)) {
					this.count++;
				}
				dfsExactWeight(edge.getEndPoint(), search, weightLimit, currentWeight);
			}
		}
	}

	private boolean containsVertex(Edge edge, int vertexIndex) {
		return edge.getEndPoint() == vertexIndex;
	}

}
