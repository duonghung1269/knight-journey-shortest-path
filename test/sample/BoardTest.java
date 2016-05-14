package sample;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BoardTest {

	private int boardLength;
	private int boardWidth;
	private int sourceX;
	private int sourceY;
	private int[][] expectedBoardArray;
	
	@Parameters
    public static Collection init() {
        return Arrays.asList(new Object[][] {
           { 3, 4, 0, 0, new int[][] {
		        		   {0, 3, 2},
		        		   {3, 4, 1},
		        		   {2, 1, 4},
		        		   {5, 2, 3}
           				} 
           }, 
           { 3, 3, 0, 2, new int[][] {
        		   {2,  1, 4},
        		   {3, -1, 1},
        		   {0,  3, 2}
   				} 
           },
           { 3, 3, 1, 1, new int[][] {
        		   {-1, -1, -1},
        		   {-1,  0, -1},
        		   {-1, -1, -1}
   				} 
           },
        });
    }		
	
	public BoardTest(int boardLength, int boardWidth, int sourceX, int sourceY,
			int[][] boardArra) {
		super();
		this.boardLength = boardLength;
		this.boardWidth = boardWidth;
		this.sourceX = sourceX;
		this.sourceY = sourceY;
		this.expectedBoardArray = boardArra;
	}




	@Test(expected=IllegalArgumentException.class)
	public void testInvalidBoard() {
			new Board(0, 4);
	}

	@Test
	public void testXX() {
		Board board = new Board(boardLength, boardWidth);
		Vertex sourceVertex = board.initChessboardGraph(sourceX, sourceY);
		board.movesKnight(sourceVertex);
		Assert.assertEquals(Arrays.deepEquals(this.expectedBoardArray, board.getBoardArray()), true);
	}
}
