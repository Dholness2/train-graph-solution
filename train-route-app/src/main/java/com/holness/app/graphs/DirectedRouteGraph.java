package com.holness.app.graphs;

import com.holness.app.graphs.Graph;
import com.holness.app.edges.Edge;
import java.util.ArrayList;
import java.util.HashMap;

public class DirectedRouteGraph implements Graph {
	private ArrayList<Edge>[] edges;
	private int vertexCount;
	private int edgeCount;
	private HashMap<String, Integer> indexKeys;

	public DirectedRouteGraph(int vertexCount) {
		this.edgeCount = 0;
		this.vertexCount = vertexCount;
		this.edges = (ArrayList<Edge>[]) new ArrayList[vertexCount];
		buildLists(vertexCount);
	}

	public int getVertexCount() {
		return this.vertexCount;
	}

	private void buildLists(int vertexCount) {
		for (int v = 0; v < vertexCount; v++) {
			this.edges[v] = new ArrayList<Edge>();
		}
	}

	public void addEdge(Edge edge) {
		this.edgeCount++;
		edges[edge.getStartPoint()].add(edge);
	}

	public ArrayList<Edge> getAdjacentList(int node) {
		return this.edges[node];
	}

	public ArrayList<Edge>[] getEdges() {
		return this.edges;
	}

	public void setVertexIndexKeys(HashMap<String, Integer> vertexIndexes) {
		this.indexKeys = vertexIndexes;
	}

	public HashMap<String, Integer> getIndexKeyMap() {
		return this.indexKeys;
	}
}
