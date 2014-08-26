import java.awt.Graphics;
import javax.swing.*;

public class ReadMe{
	public ReadMe () {
		JFrame frame = new JFrame("ReadMe");
		Drawing draw = new Drawing();
		frame.add(draw);
		frame.setSize(416, 338);
		frame.setVisible(true);
	}
	class Drawing extends JComponent{
		public void paint (Graphics g){
			g.drawImage(new ImageIcon("files/readme.jpg").getImage(), 0, 0, null);
		}}

}
