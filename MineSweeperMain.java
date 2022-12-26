package minesweeper;
import java.awt.*;        // Use AWT's Layout Manager
import java.awt.event.*;  // Use AWT's Event handlers
import javax.swing.*;     // Use Swing's Containers and Components
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.File;

/**
 * The Mine Sweeper Game.
 * Left-click to reveal a cell.
 * Right-click to plant/remove a flag for marking a suspected mine.
 * You win if all the cells not containing mines are revealed.
 * You lose if you reveal a cell containing a mine.
 */
@SuppressWarnings("serial")
public class MineSweeperMain extends JFrame {
   // private variables
   GameBoard board = new GameBoard();
   JButton btnNewGame = new JButton("New Game");
   JButton btnRestart = new JButton("Restart");
   JButton btnMusic = new JButton("Music");
   private boolean isMusic = false;
   private JTextField tfDisplay;
   /*
   private JMenuBar menuBar;
   private JMenu Difficulty;
   private JMenu Options;
   JMenuItem Expert, Intermediate, Easy;
   */
   public static ImageIcon flag = getScaledImage("C:\\picutres\\flag.png");
   public static ImageIcon imageicon1 = getScaledImage("C:\\picutres\\t.png");
   public static ImageIcon num1 = getScaledImage("C:\\picutres\\1.png");
   public static ImageIcon num2 = getScaledImage("C:\\picutres\\2.png");
   public static ImageIcon num3 = getScaledImage("C:\\picutres\\3.png");
   public static ImageIcon num4 = getScaledImage("C:\\picutres\\4.png");
   public static ImageIcon num5 = getScaledImage("C:\\picutres\\5.png");
   public static ImageIcon bomb = getScaledImage("C:\\picutres\\bomb.png");
   

   public static ImageIcon getScaledImage(String imageString) {
       ImageIcon imageIcon = new ImageIcon(imageString);
       Image newimage = imageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
       imageIcon = new ImageIcon(newimage);
       return imageIcon;
    }

   // Constructor to set up all the UI and game components
   public MineSweeperMain() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	   
	   File file = new File("C:\\picutres\\This is Not a Christmas Song - NEFFEX.wav");
	   AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
	   Clip clip = AudioSystem.getClip();
	   clip.open(audioStream);
	      
       Container cp = this.getContentPane();           // JFrame's content-pane
       cp.setLayout(new BorderLayout()); // in 10x10 GridLayout

       cp.add(board, BorderLayout.CENTER);
      
   // Set up the top display panel
      JPanel pnlDisplay = new JPanel(new FlowLayout());
      cp.add(pnlDisplay, BorderLayout.NORTH);

      // Set up the display text field
      tfDisplay = new JTextField(30);
      tfDisplay.setEditable(false);
      tfDisplay.setHorizontalAlignment(JTextField.CENTER);
      pnlDisplay.add(tfDisplay);
      tfDisplay.setText("MineSweeperGO");
      tfDisplay.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
      /*
      menuBar = new JMenuBar();
      Difficulty = new JMenu();
      Options = new JMenu();
      setJMenuBar(menuBar);
      cp.add(menuBar, BorderLayout.WEST);
      menuBar.add(Difficulty);
      menuBar.add(Options);
      menuBar = new JMenuBar();
      Difficulty = new JMenu("Difficulty");
      Options = new JMenu("Options");
      JMenuItem easy = new JMenuItem("Easy");
      JMenuItem medium = new JMenuItem("Medium");


      JMenuItem hard = new JMenuItem("Hard");


      JMenuItem oExit = new JMenuItem("Exit");

      Difficulty.add(easy);
      Difficulty.add(medium);
      Difficulty.add(hard);

      Options.add(oExit);
  
      */
   

      // Add a button to the south to re-start the game
      JPanel pnlButtons = new JPanel(new GridLayout(1, 2, 1, 1));
      cp.add(pnlButtons, BorderLayout.SOUTH);  
      pnlButtons.add(btnRestart);
      btnRestart.setFocusable(false);
      btnRestart.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
      
      btnRestart.addActionListener(new ActionListener() {
    	 
         @Override
         public void actionPerformed(ActionEvent e) {
           
        	 board.init();
         }
      });
      
      pnlButtons.add(btnMusic);
      btnMusic.setFocusable(false);
      btnMusic.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
      
      btnMusic.addActionListener(new ActionListener() {
        
          @Override
          public void actionPerformed(ActionEvent e) {
            if (isMusic) {
                  isMusic = false;
                  btnMusic.setText("Music");
                  clip.stop();
                  
               } else {
                  isMusic = true;
                  btnMusic.setText("Stop Music");
                  // Stop Music()
                  clip.start();
         
                  
               }
            
            
          }
       });
      

      pack();  // Pack the UI components, instead oBorfer setSize()
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // handle window-close button
      setTitle("MinesweeperGO");
      setVisible(true);   // show it
   }

   // The entry main() method
   public static void main(String[] args) {
	   // Recommended to run the GUI construction in
	         //  Event Dispatching thread for thread-safe operations
	         SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	               try {
	           new MineSweeperMain();
	         } catch (UnsupportedAudioFileException | IOException | 	LineUnavailableException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	         } // Let the constructor does the job
	            }
	         });
	       
	    }
}