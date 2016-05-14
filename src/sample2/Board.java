package sample2;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private int[][] board;
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
		this.colSize = colSize;
		this.rowSize = rowSize;
		this.nearestMove = 1;
		this.fareMostMove = 2;

		initBoard();
	}

	private void initBoard() {
		board = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				board[row][col] = -1;
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
		final ArrayList<Vertex> vertexes = createVertices();
		addAdjaccentNodes(vertexes);
		return getSourceNode(sourceX, sourceY, vertexes);
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
	 * adds adjacent nodes according to constrains in problem definition
	 * 
	 * @param vertexes
	 *            will be used to define connections between one another
	 */
	private void createAdjVertexIfInBounds(Vertex vertex, int x, int y,
			ArrayList<Vertex> vertexes) {
		// check problemConditions
		if (!isPositionOnChessBoard(x, y)) {
			return;
		}

		// we don't want to assign new instances of Vertexes
		// as adjacent nodes , that will not work.
		// we need to reuse the same instances and assign
		// connections between them
		final int indexOf = vertexes.indexOf(new Vertex(x, y));
		final Vertex reusedVertex = vertexes.get(indexOf);
		vertex.addAdjVertex(reusedVertex);
	}

	public List<Vertex> solveProblem(final Vertex from) {
		BfsAlgorithm bfs = new BfsAlgorithm();
		return bfs.run(from, new BfsAlgorithm.BfsAction() {

			@Override
			public boolean onVisitParent(Vertex parent) {
				return false;
			}

			@Override
			public boolean onVisitChild(Vertex vertex) {
				return false;
			}
		});

	}

	/**
	 * @return true if the position within bounds of chessboard
	 */
	private boolean isPositionOnChessBoard(int x, int y) {
		return (x < colSize && y < rowSize && x >= 0 && y >= 0);
	}

	public void updateBoard(List<Vertex> solvedVetices) {
		for (Vertex vertex : solvedVetices) {
			board[vertex.getY()][vertex.getX()] = vertex.getMovesCount();
		}
	}

	public void printBoard() {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				System.out.format("%3d ", board[row][col]);
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

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
}
