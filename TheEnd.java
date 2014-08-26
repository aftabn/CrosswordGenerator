import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class TheEnd{
	public TheEnd () {
		JFrame frame = new JFrame("TheEnd");
		Drawing draw = new Drawing();
		frame.add(draw);
		frame.setSize(416, 338);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class Drawing extends JComponent{
		public void paint (Graphics g){
			g.drawImage(new ImageIcon("files/theend.jpg").getImage(), 0, 0, null);
		}
	}
	
}
