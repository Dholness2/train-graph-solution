package com.holness.app.graphs;

import com.holness.app.edges.Edge;
import java.util.ArrayList;
import java.util.HashMap;

public interface Graph {
	public void addEdge(Edge e);

	public int getVertexCount();

	public ArrayList<Edge>[] getEdges();

	public ArrayList<Edge> getAdjacentList(int node);

	public void setVertexIndexKeys(HashMap<String, Integer> vertexIndexes);

	public HashMap<String, Integer> getIndexKeyMap();

}
