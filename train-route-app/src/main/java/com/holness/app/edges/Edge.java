package com.holness.app.edges;

public interface Edge {
	public int getStartPoint();

	public int getEndPoint();

	public boolean isWeighted();
	
	public int getWeight();
}
