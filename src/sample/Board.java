package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Chess board.
 * 
 * @author duonghung1269
 * 
 */
public class Board {

	private static final int KNIGHT_FARMOST_MOVE = 2;
	private static final int KNIGHT_NEAREST_MOVE = 1;
	private int[][] boardArray;
	private final int rowSize;
	private final int colSize;
	private final int nearestMove;
	private final int fareMostMove;

	/**
	 * When Knight moves , he moves 2 squares and then 1 square perpendicularly.
	 * 
	 * @param colSize
	 *            length of the board.
	 * @param rowSize
	 *            width of the board.
	 */
	public Board(int colSize, int rowSize) {
		if (colSize <= 0) {
			throw new IllegalArgumentException(
					"Board length should be greater than 0!");
		}

		if (rowSize <= 0) {
			throw new IllegalArgumentException(
					"Board width should be greater than 0!");
		}

		this.colSize = colSize;
		this.rowSize = rowSize;
		this.nearestMove = KNIGHT_NEAREST_MOVE;
		this.fareMostMove = KNIGHT_FARMOST_MOVE;

		initBoard();
	}

	private void initBoard() {
		boardArray = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				boardArray[row][col] = -1;
			}
		}
	}

	/**
	 * Creates chess board as a graph represented as adjacency list.
	 * 
	 * @param sourceX
	 *            starting x coordinate
	 * @param sourceY
	 *            starting y coordinate
	 * @return the source node as it will represent the entire graph
	 */
	public Vertex initChessboardGraph(int sourceX, int sourceY) {
		if (sourceX >= colSize) {
			throw new IllegalArgumentException(
					"x coordiante should be less than board length: " + colSize);
		}

		if (sourceY >= rowSize) {
			throw new IllegalArgumentException(
					"y coordiante should be less than board width: " + rowSize);
		}

		final ArrayList<Vertex> vertexes = createVertices();
		addAdjaccentNodes(vertexes);
		Vertex sourceNode = getSourceNode(sourceX, sourceY, vertexes);
		sourceNode.setMovesCount(0);
		return sourceNode;
	}

	private Vertex getSourceNode(int sourceX, int sourceY,
			final ArrayList<Vertex> vertexes) {
		Vertex source = new Vertex(sourceX, sourceY);
		int indexOfSourceVertex = vertexes.indexOf(source);
		return vertexes.get(indexOfSourceVertex);
	}

	/**
	 * Associating adjacent nodes to each created node.
	 * 
	 * @param vertices
	 *            Vertices of chess board need to add adjacent
	 */
	private void addAdjaccentNodes(final ArrayList<Vertex> vertices) {
		for (Vertex vertex : vertices) {
			if (vertex.getAdjVertices().isEmpty())
				createAdjacentListForVertex(vertex, vertices);
		}
	}

	/**
	 * Create all vertices of chess board.
	 * 
	 * @return All vertices of chess board.
	 */
	private ArrayList<Vertex> createVertices() {
		final ArrayList<Vertex> vertexes = new ArrayList<>();
		for (int row = 0; row <= rowSize; row++) {
			for (int col = 0; col <= colSize; col++) {
				vertexes.add(new Vertex(col, row));
			}
		}
		return vertexes;
	}

	/**
	 * Creates adjacency list for provided node , according to problem
	 * definition
	 * 
	 * @param vertexes
	 *            are used to define connections between one another
	 */
	private void createAdjacentListForVertex(Vertex vertex,
			ArrayList<Vertex> vertexes) {
		// Here we are just trying to create adjacent nodes in all possible
		// directions

		int xRightFull = vertex.getX() + fareMostMove;
		int xRightHalf = vertex.getX() + nearestMove;
		int xLeftFull = vertex.getX() + -fareMostMove;
		int xLeftHalf = vertex.getX() + -nearestMove;

		int yUpFull = vertex.getY() + fareMostMove;
		int yUpHalf = vertex.getY() + nearestMove;
		int yDownFull = vertex.getY() + -fareMostMove;
		int yDownHalf = vertex.getY() + -nearestMove;

		// full right half up
		createAdjVertexIfInBounds(vertex, xRightFull, yUpHalf, vertexes);
		// half right Full up
		createAdjVertexIfInBounds(vertex, xRightHalf, yUpFull, vertexes);
		// half left Full up
		createAdjVertexIfInBounds(vertex, xLeftHalf, yUpFull, vertexes);
		// full left half up
		createAdjVertexIfInBounds(vertex, xLeftFull, yUpHalf, vertexes);
		// full left half down
		createAdjVertexIfInBounds(vertex, xLeftFull, yDownHalf, vertexes);
		// half left full down
		createAdjVertexIfInBounds(vertex, xLeftHalf, yDownFull, vertexes);
		// half right full down
		createAdjVertexIfInBounds(vertex, xRightHalf, yDownFull, vertexes);
		// full right half down
		createAdjVertexIfInBounds(vertex, xRightFull, yDownHalf, vertexes);
	}

	/**
	 * Adds adjacent nodes according to constrains in problem definition
	 * 
	 * @param vertices
	 *            will be used to define connections between one another.
	 */
	private void createAdjVertexIfInBounds(Vertex vertex, int x, int y,
			ArrayList<Vertex> vertices) {
		// check problemConditions
		if (!isPositionOnChessBoard(x, y)) {
			return;
		}

		final int indexOf = vertices.indexOf(new Vertex(x, y));
		final Vertex reusedVertex = vertices.get(indexOf);
		vertex.addAdjVertex(reusedVertex);
	}

	/**
	 * Using Bread First Search Algorithm to solve problem.
	 * 
	 * @param sourceNode
	 *            root node.
	 */
	public void movesKnight(final Vertex sourceNode) {
		BfsAlgorithm bfs = new BfsAlgorithm();
		List<Vertex> visitedVertices = bfs.run(sourceNode);
		updateBoardMovement(visitedVertices);
	}

	/**
	 * Check if the position within bounds of chess board.
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @return true if position within bounds of chess board, false otherwise.
	 */
	private boolean isPositionOnChessBoard(int x, int y) {
		return (x < colSize && y < rowSize && x >= 0 && y >= 0);
	}

	private void updateBoardMovement(List<Vertex> solvedVetices) {
		for (Vertex vertex : solvedVetices) {
			boardArray[vertex.getY()][vertex.getX()] = vertex.getMovesCount();
		}
	}

	public void printBoard() {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				System.out.format("%3d ", boardArray[row][col]);
			}
			System.out.println();
		}
	}

	public int getFareMostMove() {
		return fareMostMove;
	}

	public int getNearestMove() {
		return nearestMove;
	}

	public int getRowSize() {
		return rowSize;
	}

	public int getColSize() {
		return colSize;
	}

	public int[][] getBoardArray() {
		return boardArray;
	}
	
	public void setBoardArray(int[][] boardArray) {
		this.boardArray = boardArray;
	}

}
