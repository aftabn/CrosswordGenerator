import java.awt.event.*;
import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class TitleScreen implements MouseListener{
	JFrame frame = new JFrame("Crossword");
	static int dimension = 15;
	Drawing draw = new Drawing();
	
	public TitleScreen() throws IOException {
		Database.createCatalog();
		Clues.createWordlist();
		frame.add(draw);
		draw.addMouseListener(this);
		frame.setSize(416, 338);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	class Drawing extends JComponent{
		public void paint (Graphics g){
			g.drawImage(new ImageIcon("files/background.jpg").getImage(), 0, 0, null);
		}}


	
	public static void main (String[] args) throws IOException{
		new TitleScreen();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getX() > 120 && e.getX() < 275 && e.getY() > 115 && e.getY() < 150){
			dimension = 10;
			frame.setVisible(false);
			try {
				new Crossword();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getX() > 120 && e.getX() < 275 && e.getY() > 170 && e.getY() < 205){
			dimension = 13;
			frame.setVisible(false);
			try {
				new Crossword();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getX() > 120 && e.getX() < 275 && e.getY() > 215 && e.getY() < 255){
			dimension = 15;
			frame.setVisible(false);
			try {
				new Crossword();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getX() > 370 && e.getX() < 395 && e.getY() > 5 && e.getY() < 24){
			System.out.print("hi");
			new ReadMe();
		}
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}

}
