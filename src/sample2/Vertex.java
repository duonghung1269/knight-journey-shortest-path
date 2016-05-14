package sample2;

import java.util.LinkedList;

/**
 * Created by yan.braslavski on 1/24/16.
 *
 * Represents a graph node
 */
public class Vertex {
    private final int mX;
    private final int mY;
    private int movesCount;
    private String mName;
    private boolean visited;
    private LinkedList<Vertex> mAdjVertices = new LinkedList<>();

    public Vertex(int x, int y) {
        mName = "(" + x + "," + y + ")";
        mX = x;
        mY = y;
        movesCount = -1;
    }

    @Override
    public String toString() {
        return mName;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (mX != vertex.mX) return false;
        return mY == vertex.mY;

    }

    @Override
    public int hashCode() {
        int result = mX;
        result = 31 * result + mY;
        return result;
    }

    public LinkedList<Vertex> getAdjVertices() {
        return mAdjVertices;
    }

    public String getName() {
        return mName;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addAdjVertex(Vertex v) {
        mAdjVertices.add(v);
    }

	public int getMovesCount() {
		return movesCount;
	}

	public void setMovesCount(int movesCount) {
		this.movesCount = movesCount;
	}
        
}
