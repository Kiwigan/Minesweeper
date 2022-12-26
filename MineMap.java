package minesweeper;
import java.util.Random;
/**
 * Locations of Mines
 */
public class MineMap {
	
	public static final int noOfMines = GameBoard.numMines;
	
	int numMines;
	public boolean[][] isMined;
	public MineMap() {
	      isMined = new boolean[GameBoard.ROWS][GameBoard.COLS];
	   }
	
	public void newMineMap(int numMines) {
		this.numMines = numMines;
		int x, y;	
		Random rand = new Random();
		
		isMined = new boolean[GameBoard.ROWS][GameBoard.COLS];
		for(int i = 0; i < numMines; i++) {
			for(int j = 0; j < numMines; j++) {
				isMined[i][j] = false;
			}
		}
		
		int k = 0;
		while(k < noOfMines) {
			x = rand.nextInt(noOfMines);
			y = rand.nextInt(noOfMines);
			if(isMined[x][y] == false) {
				isMined[x][y] = true;
				k++;
			}
		}
	}
	
		
		
	/*
   // package access
   int numMines;
   boolean[][] isMined;

   // Constructor
   public MineMap() {
      isMined = new boolean[GameBoard.ROWS][GameBoard.COLS];
   }

   // Allow user to change the rows and cols
   public void newMineMap(int numMines) {
      this.numMines = numMines;
      // Hardcoded for illustration, assume numMines=10
      isMined[0][0] = true;
      isMined[5][2] = true;
      isMined[9][5] = true;
      isMined[6][7] = true;
      isMined[8][2] = true;
      isMined[2][4] = true;
      isMined[5][7] = true;
      isMined[7][7] = true;
      isMined[6][8] = true;
      isMined[4][8] = true;
   }
   */
}