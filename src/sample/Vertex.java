package sample;

import java.util.LinkedList;

/**
 * Represents a graph node.
 * 
 * @author duonghung1269
 * 
 */
public class Vertex {
	private static final int INVALID_MOVE = -1;
	private final int x;
	private final int y;
	private int movesCount;
	private boolean visited;
	private LinkedList<Vertex> adjVertices = new LinkedList<>();

	public Vertex(int x, int y) {
		this.x = x;
		this.y = y;
		movesCount = INVALID_MOVE;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Vertex vertex = (Vertex) o;

		if (x != vertex.x)
			return false;
		return y == vertex.y;

	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

	public LinkedList<Vertex> getAdjVertices() {
		return adjVertices;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void addAdjVertex(Vertex v) {
		adjVertices.add(v);
	}

	public int getMovesCount() {
		return movesCount;
	}

	public void setMovesCount(int movesCount) {
		this.movesCount = movesCount;
	}

}
