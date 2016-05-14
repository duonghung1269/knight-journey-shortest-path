package sample2;

import java.util.*;

/*
 * http://yanbraslavsky.blogspot.sg/2016/01/chess-knight-shortest-path-problem-java.html
 */
public class Main {

    public static void main(String[] args) {
    	if (args.length != 4) {
    		System.out.println("Number of inputs should be 4 numbers, eg: length width x0 y0");
    		return;
    	}
    	
    	int colSize = 0;
    	int rowSize = 0;
    	int sourceX = 0;
    	int sourceY = 0;
    	    	    	
    	try {
    		colSize = Integer.parseInt(args[0]);
    		rowSize = Integer.parseInt(args[1]);
    		sourceX = Integer.parseInt(args[2]);
    		sourceY = Integer.parseInt(args[3]);
    	} catch (NumberFormatException ex) {
    		System.out.println("Number of inputs should be 4 numbers, eg: length width x0 y0");
    		return;
    	}
    	
    	Board board = new Board(colSize, rowSize);
    	Vertex sourceVertex = board.initChessboardGraph(sourceX, sourceY);
        sourceVertex.setMovesCount(0);
        List<Vertex> solvedVetices = board.solveProblem(sourceVertex);
        
        board.updateBoard(solvedVetices);
        board.printBoard();
    }
}