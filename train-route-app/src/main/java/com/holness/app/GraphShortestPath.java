package com.holness.app;

import com.holness.app.graphs.DirectedRouteGraph;
import com.holness.app.edges.Edge;
import com.holness.app.edges.WeightedEdge;
import java.util.HashMap;

public class GraphShortestPath {

  private int [] disTo;
  private Edge [] edgeTo;
  private IndexMinPriorityQueue<Integer> pq;
  private DirectedRouteGraph graph;
  private int source;
  private HashMap<String, Integer> indexKeys;

  public GraphShortestPath(DirectedRouteGraph graph) {
    this.graph = graph;
    this.indexKeys = graph.getIndexKeyMap();
    this.disTo = new int [graph.getVertexCount()];
    this.edgeTo = new WeightedEdge[graph.getVertexCount()];
  }

  public int ShortestPathFromSourceToNode(String start, String end) {
    this.source = indexKeys.get(start);
    buildDistanceQueue();
    return this.disTo[indexKeys.get(end)];
  }

  public int ShortestPathFromSourceToNodeCycle(String start) {
    int nodeIndex = this.indexKeys.get(start);
    return getDisToCycle(nodeIndex,nodeIndex);
  }

  public int getDisTo(int start, int end) {
    this.source = start;
    buildDistanceQueue();
    return this.disTo[end];
  }

  public int getDisToCycle(int start, int end) {
    this.source = start;
    buildDistanceQueueCycle();
    return this.disTo[end];
  }

  private void buildDistanceQueue() {
    setDefaultDisVals();
    disTo[this.source] = 0;
    this.pq = new IndexMinPriorityQueue<Integer>(this.graph.getVertexCount());
    this.pq.insert(this.source, disTo[this.source]);
    loadEdgesToQueue();
  }


  private void buildDistanceQueueCycle() {
    setDefaultDisVals();
    this.pq = new IndexMinPriorityQueue<Integer>(this.graph.getVertexCount());
    this.pq.insert(this.source, disTo[this.source]);
    loadEdgesToQueue();
  }

  private void loadEdgesToQueue() {
    while (!this.pq.isEmpty()) {
      int vertex = this.pq.delMin();
      for (Edge e : this.graph.getAdjacentList(vertex)) {
        relaxEdges(e);
      }
    }
  }

  private void setDefaultDisVals() {
    for (int v = 0; v < graph.getVertexCount(); v++) {
      disTo[v] =  Integer.MAX_VALUE;
    }
  }

  private void relaxEdges(Edge edge) {
    int start = edge.getStartPoint();
    int end = edge.getEndPoint();
    if (disTo[end] > (disTo[start] + edge.getWeight())) {
      updateDistance(start, end, edge);
      edgeTo[end] = edge;
      updateQueue(end);
    }
  }

  private void updateDistance(int start,int end,  Edge edge) {
    if(disTo[start] == Integer.MAX_VALUE) {
        disTo[end] = edge.getWeight();
      } else {
        disTo[end] = disTo[start] + edge.getWeight();
      }
  }

  private void updateQueue(int end) {
    if (pq.contains(end)) {
        pq.decreaseKey(end, disTo[end]);
      } else {
        pq.insert(end, disTo[end]);
      }
  }
}
