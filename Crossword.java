import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class Crossword implements KeyListener {
	static JFrame frame = new JFrame ("Crossword");
	static JButton check = new JButton("Check");
	static JButton complete = new JButton ("Complete");
	Drawing draw = new Drawing();
	static int[][] board;
	static int[][] original;
	static boolean[][] incorrect = new boolean[15][15];
	static boolean[][] boardSelect = new boolean[15][15];
	static int boxX, boxY = 0;
	static int prevboxX, prevboxY = 0;
	static JPanel cluesPanel = new JPanel();
	static JPanel buttonPanel = new JPanel();
	static boolean done;
	
	//crossword
	class Drawing extends JComponent{
		public void paint (Graphics g){
			g.drawImage(new ImageIcon("files/" + (TitleScreen.dimension*10 + Database.rand+1 )+ ".jpg").getImage(), 0, 0, TitleScreen.dimension*30+1, TitleScreen.dimension*30+1, this);
			for (int i = 0; i < TitleScreen.dimension; i++)
				for (int j = 0; j < TitleScreen.dimension; j++){
					int n = board[i][j];
					boolean sel = boardSelect[i][j];
					boolean wrong = incorrect[i][j];
					if (sel == true && n != -1)
		            	g.drawImage(Letters.l[27].getImage(), j * 30 + 2, i * 30 + 2, 28, 28, this);
					if (wrong == true)
						g.drawImage(Letters.l[28].getImage(), j * 30 + 2, i * 30 + 2, 28, 28, this);
					if (n > 0)
		            g.drawImage(Letters.l[n].getImage(), j * 30 + 2, i * 30 + 2, 28, 28, this);
					draw.setFocusable(true);
					draw.requestFocusInWindow();
				}
		}


	}
	
	//selecting squares
	 class MouseListen extends MouseAdapter
	 {
		 public void mouseClicked(MouseEvent e)
		 {
			 if (!done){
			 if (boxX < TitleScreen.dimension && boxY < TitleScreen.dimension) {
			 prevboxX = boxX;
			 prevboxY = boxY;
			 }
	         boxX = e.getX() / 30;
	         boxY = e.getY() / 30;
	         if (boxX > TitleScreen.dimension-1 || boxY > TitleScreen.dimension-1){
	        	 return;
	         }
	    	 boardSelect[prevboxY][prevboxX] = false;
	         if (boardSelect[boxY][boxX] == false)
	        	 boardSelect[boxY][boxX] = true;
	         else
	        	 boardSelect[boxY][boxX] = false;
	         
	 		incorrect[boxY][boxX] = false;
	        draw.repaint();
			 }
	      }   
	   }
	
	 public class ActionAdapter extends Object implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	if (!done){
	        	if (e.getSource() == complete){
	        		board = original;
	        		for (int i = 0; i < TitleScreen.dimension; i++)
	        			for (int j = 0; j < TitleScreen.dimension; j++)
	        				incorrect[i][j] = false;
	        		done = true;
	        	}
	        	else if (e.getSource() == check)
	        		Crossword.checkCrossword();
	        	draw.repaint();
	        }
	        }

	    }

	 //adding components
	public Crossword() throws IOException {
		new Letters();
		original = stringToInt(Database.generate(TitleScreen.dimension), TitleScreen.dimension);
		Clues cluebox = new Clues(original);
		cluesPanel = cluebox.mainPanel;
		buttonPanel.add(check);
		buttonPanel.add(complete);
		check.addActionListener(new ActionAdapter());
		complete.addActionListener(new ActionAdapter());
		getTemplate();
		draw.addMouseListener(new MouseListen());
		frame.setSize(TitleScreen.dimension*30 + 350, TitleScreen.dimension*30+77);
		frame.add(cluesPanel, "East");
		frame.add(draw);
		frame.add(buttonPanel, "South");
		draw.addKeyListener(this);	
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void checkCrossword() {
		if (!done){
		for (int i = 0; i < TitleScreen.dimension; i++)
			for (int j = 0; j < TitleScreen.dimension; j++){
				if (board[i][j] == 0)
					return;
			}
		for (int i = 0; i < TitleScreen.dimension; i++)
			for (int j = 0; j < TitleScreen.dimension; j++){
				if (board[i][j] != original[i][j])
					incorrect[i][j] = true;
				else
					incorrect[i][j] = false;
			}
		checkTheEnd();
		}
		
	}

	private static void checkTheEnd() {
			for (int i = 0; i < TitleScreen.dimension; i++)
    			for (int j = 0; j < TitleScreen.dimension; j++)
    				if (incorrect[i][j] == true)
    					return;
    		new TheEnd();
    		done = true;
	}

	//choosing template
	private void getTemplate() {
		if (Database.rand == 0 && TitleScreen.dimension == 10)
			board = Database.ten1;
		else if (Database.rand == 1 && TitleScreen.dimension == 10)
			board = Database.ten2;
		else if (Database.rand == 2 && TitleScreen.dimension == 10)
			board = Database.ten3;
		else if (Database.rand == 0 && TitleScreen.dimension == 13)
			board = Database.thirteen1;
		else if (Database.rand == 1 && TitleScreen.dimension == 13)
			board = Database.thirteen2;
		else if (Database.rand == 2 && TitleScreen.dimension == 13)
			board = Database.thirteen3;
		else if (Database.rand == 0 && TitleScreen.dimension == 15)
			board = Database.fifteen1;
		else if (Database.rand == 1 && TitleScreen.dimension == 15)
			board = Database.fifteen2;
		else if (Database.rand == 2 && TitleScreen.dimension == 15)
			board = Database.fifteen3;
		else
			;
		
	}
	
	//string crossword to int crossword
	public int[][] stringToInt(String[][] generated, int size)
	{
		int[][] temp = new int[size][size];
		for(int i = 0; i < generated.length; i++)
			for (int j = 0; j < generated.length; j++)
				if (generated[i][j].equals(""))
					temp[i][j] = -1;
				else
					temp[i][j] = (int)(generated[i][j].charAt(0) - 'A') + 1;
		return temp;
	}
	
	//keylistener
	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {
		if (!done){
		if (boardSelect[boxY][boxX] == true){
			String s = Character.toString(e.getKeyChar());
			char c = s.toUpperCase().charAt(0);
			if ((c >= 'A' && c <= 'Z') && board[boxY][boxX] != -1)
				board[boxY][boxX] = c - 64;
			else if ((int)c == 127 || (int)c == 8)
				board[boxY][boxX] = 0;

		}
		boardSelect[boxY][boxX] = false;

		draw.repaint();
		}
	}
}
