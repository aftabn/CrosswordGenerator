import javax.swing.*;


public class Letters {
	static ImageIcon[] l = new ImageIcon[29];
	
	public Letters() {
		l[27] = new ImageIcon ("files/27.jpg");
		l[28] = new ImageIcon ("files/28.jpg");
		for (int i = 1; i < 27; i++)
			l[i] = new ImageIcon ("files/" + i + ".gif");

	}
}
