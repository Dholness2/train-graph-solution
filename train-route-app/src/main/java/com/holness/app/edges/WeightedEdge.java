package com.holness.app.edges;

public class WeightedEdge implements Edge {
	private int weight;
	private int start;
	private int end;

	public WeightedEdge(int startPoint, int endPoint, int edgeWeight) {
		weight = edgeWeight;
		start = startPoint;
		end = endPoint;
	}

	public int getStartPoint() {
		return this.start;
	}

	public int getEndPoint() {
		return this.end;
	}

	public int getWeight() {
		return this.weight;
	}

	public boolean isWeighted() {
      return true;
	}
	
	public int compareTo(WeightedEdge edge) {
		return Integer.compare(this.weight, edge.getWeight());
	}
}
