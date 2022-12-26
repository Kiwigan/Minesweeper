package minesweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {
   // Name-constants for the game properties
   public static final int ROWS = 10;      
   public static final int COLS = 10;
   public static final int numMines = 10;

   // Name-constants for UI sizes
   public static final int CELL_SIZE = 60;  // Cell width and height, in pixels
   public static final int CANVAS_WIDTH = CELL_SIZE * COLS; // Game board width/height
   public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;

   
   
   
   Cell[][] cells = new Cell[ROWS][COLS];
   MineMap mines = new MineMap();
   int flagcount;
   CellMouseListener lis = new CellMouseListener();
   

   // Constructor
   public GameBoard() {
      super.setLayout(new GridLayout(ROWS, COLS, 2, 2));// JPanel
      super.setBackground(Color.DARK_GRAY);
      // Allocate the 2D array of Cell, and added into content-pane.
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            cells[row][col] = new Cell(row, col);
            super.add(cells[row][col]);
            
         }
      }

      // [TODO 3] Allocate a common listener as the MouseEvent listener for all the
      //  Cells (JButtons)
      // [TODO 4] Every cell adds this common listener

      // ......

      // Initialize for a new game
      init();
      // Set the size of the content-pane and pack all the components
      //  under this container.
      super.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
   }

   // Initialize and re-initialize a new game
   public void init() {
      // Get a new mine map
      mines.newMineMap(numMines);
      flagcount = 0;
      // Reset cells, mines, and flags
      for (int row = 0; row < ROWS; row++) {
         for (int col = 0; col < COLS; col++) {
        	 cells[row][col].addMouseListener(lis);
             // Initialize each cell with/without mine
             cells[row][col].init(mines.isMined[row][col]);
         }
      }
   }

   // Return the number of mines (0 - 8) in the 8 surrounding cells of the given cell.
   private int getSurroundingMines(Cell cell) {
      int numMines = 0;
      for (int row = cell.row - 1; row <= cell.row + 1; row++) {
         for (int col = cell.col - 1; col <= cell.col + 1; col++) {
        	 if (row >= 0 && row < ROWS && col >= 0 && col < COLS && mines.isMined[row][col]) {
        		 numMines++;
        		 }
            }
         }
      return numMines;
   }

   // This cell has 0 surrounding mine. Reveal the 8 surrounding cells recursively
   private void revealSurrounding(Cell cell) {
	   for(int i = cell.row - 1; i <= cell.row + 1; i++) {
		   for(int j = cell.col - 1; j <= cell.col + 1; j++) {
			   if(i<ROWS && i>=0 && j<COLS && j>=0 && cells[i][j].isRevealed == false) {
				   cells[i][j].isRevealed = true;
				   cells[i][j].winCon = true;
				   cells[i][j].removeMouseListener(lis);
				   if(getSurroundingMines(cells[i][j]) == 0){
					   cells[i][j].paint();
					   revealSurrounding(cells[i][j]);
					   }
				   else {
					   //cells[i][j].setText(Integer.toString(getSurroundingMines(cells[i][j])));
					   cells[i][j].paintNum(getSurroundingMines(cells[i][j]));
					   //cells[i][j].paint();
					   }
			   	}
		   }
	   }
   }

   // Return true if the player has won (all cells have been revealed or were mined)
   public boolean hasWon() {
	   for (int row = 0; row < ROWS; row++) {
           for (int col = 0; col < COLS; col++) {
         	  if (cells[row][col].winCon == false) {
         		  return false;
         	  }
           }
       }
       return true;
   	}
   
   public void revealEverything() {
	   for(int i = 0; i < ROWS; i++) {
		   for(int j = 0; j < COLS ; j++) {
			   cells[i][j].removeMouseListener(lis);
			   if(cells[i][j].isRevealed == false) {
				   if(cells[i][j].isMined) {
					   cells[i][j].isFlagged = false;
					   cells[i][j].isRevealed = true;
					   cells[i][j].paint();
				   }
				   else {
					   cells[i][j].isFlagged = false;
					   cells[i][j].setText("");
					   cells[i][j].isRevealed = true;
					   cells[i][j].paint();
					   //if(getSurroundingMines(cells[i][j]) != 0) {
					   cells[i][j].paintNum(getSurroundingMines(cells[i][j]));
						   //cells[i][j].setText(Integer.toString(getSurroundingMines(cells[i][j])));
					   //}
				   }
			   }
		   }
	   }
   }
   
   // [TODO 2] Define a Listener Inner Class
   private class CellMouseListener extends MouseAdapter {
	   	@Override
	      public void mouseClicked(MouseEvent e) {         // Get the source object that fired the Event
	          Cell sourceCell = (Cell)e.getSource();
	          // For debugging
	          System.out.println("You clicked on (" + sourceCell.row + "," + sourceCell.col + ")");

	          // Left-click to reveal a cell; Right-click to plant/remove the flag.
	          if (e.getButton() == MouseEvent.BUTTON1) {  // Left-button clicked
	             // [TODO 5] If you hit a mine, game over
	        	  if(sourceCell.isMined && sourceCell.isFlagged == false) {
	        		  System.out.println("U Lose, try again next time!");
	        		  sourceCell.paintLose();
	        		  sourceCell.isRevealed = true;
	        		  revealEverything();
	        		  JOptionPane.showMessageDialog(null, "Game Over! Try again next time!");
	        		  return;
	        	  }
	        	  
	        	  else if(sourceCell.isFlagged == false){
	        		  if(getSurroundingMines(sourceCell) == 0) {
	        			  revealSurrounding(sourceCell);
	        		  }
	        		  else {
	        			  sourceCell.isRevealed = true;
	        			  sourceCell.winCon = true;
	        			  sourceCell.paintNum(getSurroundingMines(sourceCell));
	        			  //sourceCell.setText(Integer.toString(getSurroundingMines(sourceCell)));
	        			  //sourceCell.paint();
	        		  }
	        	  }
	             // Otherwise, reveal the cell and display the number of surrounding mines
	             //  ......

	          }
	          else if (e.getButton() == MouseEvent.BUTTON3) { // right-button clicked
	        	  if(sourceCell.isFlagged == true) {
	        		  sourceCell.isFlagged = false;
	        		  flagcount--;
	        		  sourceCell.paint();
	        		  sourceCell.setText("");
	        	  }
	        	  else {
	        		  sourceCell.isFlagged = true;
	        		  flagcount++;
	        		  sourceCell.paint();
	        		  //sourceCell.setText(">");
	        		  sourceCell.winCon = true;
	        	  }
	        	  
	        	
	        	  // [TODO 6] If the location is flagged, remove the flag
	             // Otherwise, plant a flag.
	             // ......
	          }
	          System.out.println(flagcount);
	          if(hasWon()) {
	        	  System.out.println("U Won!, Congrats!");
	        	  revealEverything();
	        	  JOptionPane.showMessageDialog(null, "U Won!, Congrats!");
	          }
	          // [TODO 7] Check if the player has won, after revealing this cell
	          // ......
	       }
	    }
}