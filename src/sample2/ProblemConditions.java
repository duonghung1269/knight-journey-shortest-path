package sample2;

/**
 * Created by yan.braslavski on 1/24/16.
 *
 * To have some degree of freedom I've encapsulated some problem related
 * conditions in order to scale the problem with ease.
 */
public class ProblemConditions {
    private final int rowSize;
    private final int colSize;
    private final int mNearestMove;
    private final int mFareMostMove;
    private int[][] board;
    
    /**
     * When Knight moves , he moves 2 squares and then 1 square perpendicularly.
     * To generify this , I am saying it moves a "big" distance and then smaller distance perpendicularly.
     * @param sizeOfBoardNxN standard board is 8x8 , but yours can be different
     * @param nearestMove the smaller part of knight step
     * @param fareMostMove the bigger part of knight step
     */
    public ProblemConditions(int rowSize, int colSize, int nearestMove, int fareMostMove) {
        this.rowSize = rowSize;
        this.colSize = colSize;        
        mNearestMove = nearestMove;
        mFareMostMove = fareMostMove;
                
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
     * @return true if the position within bounds of chessboard
     */
    public boolean isPositionOnChessBoard(int x,int y) {
        return (x < colSize && y < rowSize
                && x >= 0 && y >= 0);
    }

    public void printBoard() {
    	for (int row = 0; row < rowSize; row++) {
    		for (int col = 0; col < colSize; col++) {
    			System.out.print(board[row][col] + " ");
    		}
    		System.out.println();
    	}
    }
    
    public int getFareMostMove() {
        return mFareMostMove;
    }

    public int getNearestMove() {
        return mNearestMove;
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