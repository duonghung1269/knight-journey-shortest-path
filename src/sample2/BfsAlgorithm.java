package sample2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by yan.braslavski on 1/24/16.
 *
 * Algorithm encapsulated into a class , for easier interchangeability.
 */
public class BfsAlgorithm {

    /**
     * Running multi purpose bfs algorithm
     * @param sourceVertex root node
     * @param action defines actions to take on each node traversal
     */
    public List<Vertex> run(Vertex sourceVertex, BfsAction action) {
        List<Vertex> trackVisitedList = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(sourceVertex);
        sourceVertex.setVisited(true);
        trackVisitedList.add(sourceVertex);
        while (!queue.isEmpty()) {
            Vertex polledVertex = (Vertex) queue.poll();
            int parentCount = polledVertex.getMovesCount();
            //we are breaking if needed
            if (action.onVisitParent(polledVertex))
                break;

            for (Vertex adj : polledVertex.getAdjVertices()) {
                if (adj.isVisited()) {
                    continue;
                }
                
                adj.setVisited(true);
                adj.setMovesCount(parentCount + 1);
                trackVisitedList.add(adj);
                queue.add(adj);

                //we are breaking if needed
//                if (action.onVisitChild(adj)) {
//                    //we are emptying the quee to break from the loop
//                    queue = new LinkedList<>();
//                    break;
//                }
            }
        }

        //reset vertexes state
//        for (Vertex v : trackVisitedList) {
//            v.setVisited(false);
//        }
        
        return trackVisitedList;
    }

    /**
     * That interface will help us to generify BFS Algorithm usage
     * for different purposes
     */
    public interface BfsAction {
        /**
         * @return true if want to interrupt traversal
         */
        boolean onVisitParent(Vertex vertex);

        /**
         * @return true if want to interrupt traversal
         */
        boolean onVisitChild(Vertex vertex);
    }
}