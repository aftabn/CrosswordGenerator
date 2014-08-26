import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Box implements ActionListener{
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea textArea = new JTextArea();
		JButton button = new JButton();
		boolean select = false;
		
		public Box (int num, String clue) {
			button.setText("" + num);
			button.setBackground(new Color(191, 191, 191));
			button.addActionListener(this);
			textArea.setText(clue);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setEditable(false);
			textArea.setSize(250, 1);
			panel.add(button, "West");
			panel.add(textArea, "Center");
		}

		public void actionPerformed(ActionEvent e) {
			if (!Crossword.done){
			if (e.getSource() == button){
				if (select == false){
					button.setBackground(new Color(119, 253, 169));
					select = true;
				}
				else {
					button.setBackground(new Color(191, 191, 191));
					select = false;
				}
				//Crossword.draw.setFocusable(true);
				//Crossword.draw.requestFocusInWindow();
			}
			}
		}
	}