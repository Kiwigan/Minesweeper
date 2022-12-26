package minesweeper;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
/**
 * Customize (subclass) the JButton to include
 * row/column numbers and status of each cell.
 */
@SuppressWarnings("serial")
public class Cell extends JButton {
   // Name-constants for JButton's colors and fonts
   public static final Color BG_NOT_REVEALED = Color.GREEN;
   public static final Color FG_NOT_REVEALED = Color.RED;    // flag, mines
   public static final Color BG_REVEALED = Color.LIGHT_GRAY;
   public static final Color FG_REVEALED = Color.YELLOW; // number of mines
   public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);
   

   // All variables have package access
   int row, col;        // The row and column number of the cell
   boolean isRevealed;
   boolean isMined;
   boolean isFlagged;
   boolean winCon;
   ImageIcon flag, imageicon1, bomb, num1, num2, num3, num4, num5;

   // Constructor
   public Cell(int row, int col) {
	  super();// JTextField
      this.row = row;
      this.col = col;
      // Set JButton's default display properties
      super.setFont(FONT_NUMBERS);
      bomb = MineSweeperMain.bomb;
      imageicon1 = MineSweeperMain.imageicon1;
      flag = MineSweeperMain.flag;
      num1 = MineSweeperMain.num1;
      num2 = MineSweeperMain.num2;
      num3 = MineSweeperMain.num3;
      num4 = MineSweeperMain.num4;
      num5 = MineSweeperMain.num5;
      
   }	
   
   

   public void init(boolean isMined) {
      isRevealed = false;
      isFlagged = false;
      this.isMined = isMined;
      winCon = isMined;
      super.setEnabled(true);  // enable button
      super.setForeground(FG_REVEALED);
      super.setBackground(BG_NOT_REVEALED);
      super.setIcon(imageicon1);    
      super.setText("");       // display blank
      super.setBorder(null);
   }
   
   
   public void paintNum(int num) {
	   switch(num){
	   case(0): break;
	   case(1): super.setIcon(num1); break;
	   case(2): super.setIcon(num2); break;
	   case(3): super.setIcon(num3); break;
	   case(4): super.setIcon(num4); break;
	   case(5): super.setIcon(num5); break;
	   }
   }
   
   public void paintLose() {
	   super.setBackground(FG_NOT_REVEALED);
	   super.setIcon(bomb);
   }

   // Paint itself based on its status
   public void paint() { 
	   if(isMined && isRevealed) {
		   super.setBackground(BG_REVEALED);
		   super.setIcon(bomb);
	   }
	   else if(isRevealed) {
		   super.setBackground(BG_REVEALED);
		   super.setIcon(null);
	   }
	   else if(isFlagged) {
		   super.setIcon(flag);
	   }
	   else if(!isFlagged) {
		   super.setIcon(imageicon1);
	   }
	   else {
		   super.setBackground(BG_NOT_REVEALED);
	   }
   }
}