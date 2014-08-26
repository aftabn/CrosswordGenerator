import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Clues
{
	static JPanel panelA = new JPanel();
	static JPanel panelD = new JPanel();
	static JPanel mainPanel = new JPanel(new GridLayout(2,0));
	static JScrollPane scrollPaneA = new JScrollPane(panelA);
	static JScrollPane scrollPaneD = new JScrollPane(panelD);
	static final String newline = "\n\n";
	static String text = "";
	static String word = "";
	static String clue = "";
	static int count = 0;
	static String[] wordlist = new String[250000];
	static Box[] clueBox = new Box[200];
	static JLabel across = new JLabel("ACROSS");
	static JLabel down = new JLabel("DOWN");
	static int[][] raw = null;


	
	public Clues(int[][] xword) throws IOException
	{	
		raw = xword;
		panelA.setPreferredSize(new Dimension(270, 2500));
		panelD.setPreferredSize(new Dimension(270, 2500));
		scrollPaneA.setPreferredSize(new Dimension(300, 500));
		scrollPaneD.setPreferredSize(new Dimension(300, 500));
		mainPanel.add(scrollPaneA);
		mainPanel.add(scrollPaneD);
		panelA.add(across, "North");
		panelD.add(down, "North");
		displayClue(numberedCrossword(raw));
		mainPanel.setPreferredSize(new Dimension (330, 800));
	}

	public int[][] numberedCrossword (int[][] layout) 
	{
		int count =	1;
		int[][] result	= new int[layout.length][layout.length];
		
		for (int	i = 0; i	< layout.length; i++)
			for (int	j = 0; j	< layout.length; j++)
			{
				if (layout[i][j] == -1)
					result[i][j] = -1;
				else if	(layout[i][j] != -1 && (i == 0 || j	==	0 || layout[i-1][j] == -1 || layout[i][j-1] == -1)) 
				{
					result[i][j] =	count;
					count++;
				}
			}
		return result;
	}
	
	public String getRandomClue (String s) 
	{
		int bottom = 0, top = wordlist.length - 1;
		int pos = -1;
		
		while (bottom <= top && pos == -1) {
			int current = (top + bottom)/2;
			String word = wordlist[current].substring(0, wordlist[current].indexOf(" ---"));
			if (word.compareTo(s) == 0)
				pos = current;
			else if (word.compareTo(s) > 0)
				top = current - 1;
			else
				bottom = current + 1;
		}
		
		int start = pos, end = pos;
		
		for (int i = pos - 1; i >= 0; i--) {
			String wordDown = wordlist[i].substring(0, wordlist[i].indexOf(" ---"));
			if (wordDown.equals(s))
				start = i;
			else
				break;
		}
		
		for (int i = pos + 1; i < wordlist.length; i++) {
			String wordUp = wordlist[i].substring(0, wordlist[i].indexOf(" ---"));
			if (wordUp.equals(s))
				end = i;
			else
				break;
		}
		
		int rand = (int)(Math.random()*(end - start + 1)) + start;
		
		return wordlist[rand].substring(wordlist[rand].indexOf("-- ") + 3);
	}
	
	public static void createWordlist() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("AggregateCluelist.txt"));
		String[] temp = new String[250000];
		int count = 0;
		String s = br.readLine();
		
		while (s != null) {
			temp[count++] = s;
			s = br.readLine();
		}
		
		br.close();
		
		wordlist = new String[count];
		for (int i = 0; i < count; i++)
			wordlist[i] = temp[i];
	}
	
	public String getAcross(int[][] answer, int row, int col) throws IOException
	{
		for (int j = col; j < answer.length && answer[row][j] > 0; j++)
				text += (char)(answer[row][j] + 64);
		return getRandomClue(text);
	}
					
	public String getDown(int[][] answer, int row, int col) throws IOException
	{
		for (int i = row; i < answer.length && answer[i][col] > 0; i++)
				text += (char)(answer[i][col] + 64);
		return getRandomClue(text);		
	}

	public void displayClue(int[][] layout) throws IOException
	{
		for (int i = 0; i < layout.length; i++)
		{
			for (int j = 0; j < layout.length; j++)
			{
				if (layout[i][j] > 0 && ((i == 0 || layout[i-1][j] == -1) && (j == 0 || layout[i][j-1] == -1)))
				{
					clueBox[count] = new Box(layout[i][j], getAcross(raw, i, j));
					text = "";
					panelA.add(clueBox[count].panel);
					
					count++;
					
					clueBox[count] = new Box(layout[i][j], getDown(raw, i, j));
					text = "";
					panelD.add(clueBox[count].panel);
					
					count++;
				}
				
				else if (layout[i][j] > 0 && (i == 0 || layout[i-1][j] == -1))
				{
					clueBox[count] = new Box(layout[i][j], getDown(raw, i, j));
					text = "";
					panelD.add(clueBox[count].panel);
					count++; 
				}
				
				else if (layout[i][j] > 0 && (j == 0 || layout[i][j-1] == -1))
				{
					clueBox[count] = new Box(layout[i][j], getAcross(raw, i, j));
					text = "";
					panelA.add(clueBox[count].panel);
					count++;
				}
			}
		}
	}
}

		