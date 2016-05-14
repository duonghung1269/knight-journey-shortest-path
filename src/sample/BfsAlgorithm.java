package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Bread First Search Algorithm encapsulated into a class , for easier
 * interchangeability.
 * 
 * @author duonghung1269
 * 
 */
public class BfsAlgorithm {

	/**
	 * Running multi purpose bfs algorithm.
	 * 
	 * @param sourceVertex
	 *            source node.
	 * @return List of vertices that knight visited.
	 */
	public List<Vertex> run(Vertex sourceVertex) {
		List<Vertex> visitedList = new ArrayList<>();
		Queue<Vertex> queue = new LinkedList<>();
		queue.add(sourceVertex);
		sourceVertex.setVisited(true);
		sourceVertex.setMovesCount(0);
		visitedList.add(sourceVertex);
		
		while (!queue.isEmpty()) {
			Vertex polledVertex = (Vertex) queue.poll();
			int parentCount = polledVertex.getMovesCount();

			for (Vertex adj : polledVertex.getAdjVertices()) {
				if (adj.isVisited()) {
					continue;
				}

				adj.setVisited(true);
				adj.setMovesCount(parentCount + 1);
				visitedList.add(adj);
				queue.add(adj);

			}
		}

		return visitedList;
	}

}