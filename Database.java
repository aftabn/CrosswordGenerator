import java.io.*;
import java.util.*;

public class Database
{
	static boolean[][][][][][][][][][][] catalog = new boolean[11][27][][][][][][][][][]; //0 is space, 1 - A... 26 - Z
	static String[] wordlist;
	static String[][] catalogByLength = new String[11][];	
	static TemplateMap Tmap;
	static String[][] crossword;
	private static boolean doneGeneration = false;
	private static boolean reverse = false;
	static int rand;
	
	static final int[][] test = {
		{0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0}};

	static final int[][] ten1 = 
    {{0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
    { 0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
    { 0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
    {-1,-1,-1, 0, 0,-1,-1,-1, 0, 0},
    { 0, 0, 0, 0, 0, 0,-1,-1, 0, 0},
    { 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
    { 0, 0, 0,-1,-1, 0, 0, 0,-1,-1},
    { 0, 0, 0, 0, 0,-1, 0, 0, 0, 0},
    { 0, 0, 0, 0, 0,-1, 0, 0, 0, 0},
    { 0, 0, 0,-1,-1,-1,-1,-1, 0, 0}};
							 
	static final int[][] ten2 = 
    {{0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
    { 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
    { 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
    { 0, 0, 0, 0, 0,-1,-1, 0, 0, 0},
    { 0, 0, 0, 0, 0, 0, 0,-1,-1,-1},
    {-1,-1,-1,-1,-1, 0, 0, 0, 0, 0},
    { 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
    { 0, 0, 0, 0, 0, 0,-1, 0, 0, 0},
    { 0, 0, 0, 0, 0, 0,-1, 0, 0, 0},
    { 0, 0, 0, 0,-1,-1,-1, 0, 0, 0}};
						 
	static final int[][] ten3 = 
					{{0, 0, 0, 0,-1,-1, 0, 0, 0, 0},
					{ 0, 0, 0, 0, 0,-1, 0, 0, 0, 0},
					{ 0, 0, 0, 0, 0,-1, 0, 0, 0, 0},
					{ 0, 0, 0,-1, 0, 0,-1, 0, 0, 0},
					{-1,-1,-1, 0, 0, 0, 0, 0, 0,-1},
					{-1, 0, 0, 0, 0, 0, 0,-1,-1,-1},
					{ 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
					{ 0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
					{ 0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
					{ 0, 0, 0, 0,-1,-1, 0, 0, 0, 0}};
						 
									
	static final int[][] thirteen1 = 
						{{ 0, 0, 0, 0, 0,-1,-1, 0, 0, 0, 0,-1,-1},
						 { 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0,-1},
						 { 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
						 { 0, 0, 0,-1, 0, 0, 0, 0, 0,-1,-1, 0, 0},
						 { 0, 0, 0, 0,-1, 0, 0, 0,-1,-1, 0, 0, 0},
						 { 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
						 {-1,-1,-1,-1, 0, 0, 0, 0, 0, 0,-1,-1,-1},
						 { 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0},
						 { 0, 0, 0, 0,-1, 0, 0, 0,-1,-1, 0, 0, 0},
						 { 0, 0, 0,-1,-1, 0, 0, 0, 0,-1,-1, 0, 0},
						 { 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
						 {-1, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
						 {-1,-1, 0, 0, 0, 0,-1,-1, 0, 0, 0, 0,-1}};	

	static final int[][] thirteen2 = 
						{{ 0, 0, 0,-1, 0, 0, 0,-1,-1, 0, 0, 0, 0},
			 			 { 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0, 0, 0},
			 			 { 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0, 0, 0},
			 			 { 0, 0, 0,-1,-1, 0, 0, 0, 0,-1,-1,-1,-1},
			 			 {-1,-1, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0, 0},
			 			 {-1,-1,-1, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
			 			 { 0, 0, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0, 0},
			 			 { 0, 0, 0, 0, 0, 0,-1, 0, 0, 0,-1,-1,-1},
			 			 { 0, 0, 0,-1,-1, 0, 0, 0,-1, 0, 0, 0,-1},
			 			 {-1,-1,-1,-1, 0, 0, 0, 0,-1,-1, 0, 0, 0},
			 			 { 0, 0, 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0},
			 			 { 0, 0, 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0},
			 			 { 0, 0, 0, 0,-1,-1, 0, 0, 0,-1, 0, 0, 0}};
	
	static final int[][] thirteen3 = {{0, 0, 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0}, 
									 { 0, 0, 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0},
									 { 0, 0, 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0},
									 {-1,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
									 { 0, 0, 0, 0, 0, 0,-1, 0, 0, 0,-1,-1,-1},
									 { 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0, 0,-1},
									 { 0, 0, 0, 0,-1, 0, 0, 0,-1,-1, 0, 0, 0},
									 {-1,-1, 0, 0,-1,-1, 0, 0, 0, 0, 0, 0, 0},
									 {-1,-1,-1, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
									 { 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1,-1},
									 { 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0, 0, 0},
									 { 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0, 0, 0},
									 { 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0, 0, 0}};

	static final int[][] fifteen1 = 
					   {{ 0, 0, 0, 0,-1,-1, 0, 0, 0,-1, 0, 0, 0, 0,-1},
						{ 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
						{ 0, 0, 0, 0, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0, 0},
						{ 0, 0, 0,-1, 0, 0, 0, 0, 0, 0,-1,-1,-1, 0, 0},
						{ 0, 0, 0,-1, 0, 0,-1, 0, 0, 0,-1, 0, 0,-1,-1},
						{-1,-1,-1, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0, 0,-1},
						{ 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
						{ 0, 0, 0, 0,-1,-1, 0, 0, 0,-1,-1, 0, 0, 0, 0},
						{ 0, 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0,-1},
						{-1,-1,-1, 0, 0, 0, 0,-1,-1, 0, 0, 0,-1,-1,-1},
						{-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0},
						{ 0, 0, 0,-1,-1, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0},
						{ 0, 0, 0, 0, 0,-1,-1,-1,-1, 0, 0, 0, 0,-1,-1},
						{ 0, 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0},
						{-1, 0, 0, 0, 0,-1, 0, 0, 0,-1,-1, 0, 0, 0, 0}};

	static final int[][] fifteen2 = 
	  					{{0, 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0},
    					{ 0, 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0},
    					{ 0, 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0},
    					{ 0, 0, 0, 0,-1, 0, 0,-1, 0, 0,-1,-1, 0, 0,-1},
    					{ 0, 0, 0, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0, 0,-1},
    					{-1,-1,-1, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1,-1,-1},
    					{ 0, 0, 0,-1, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0},
    					{ 0, 0, 0, 0, 0,-1,-1, 0, 0,-1, 0, 0, 0, 0, 0},
    					{ 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0,-1,-1,-1,-1},
    					{ 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0},
    					{-1,-1,-1, 0, 0, 0, 0,-1,-1, 0, 0, 0, 0, 0, 0},
    					{ 0, 0, 0, 0, 0,-1, 0, 0, 0, 0,-1,-1,-1,-1,-1},
    					{ 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
    					{ 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
    					{ 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0}};

	static final int[][] fifteen3 = 
						{{0, 0, 0, 0,-1,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0},
			            { 0, 0, 0, 0,-1,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0},
    				    {-1,-1,-1, 0, 0, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0},
					    { 0, 0, 0, 0, 0, 0,-1, 0, 0, 0,-1, 0, 0, 0,-1},
			            { 0, 0, 0, 0, 0,-1,-1, 0, 0, 0, 0, 0,-1,-1,-1},
			            {-1,-1,-1, 0, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0, 0},
			            { 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0},
			            { 0, 0, 0,-1, 0, 0, 0, 0, 0, 0, 0,-1,-1, 0, 0},
			            { 0, 0,-1,-1, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0},
			            { 0, 0, 0, 0, 0,-1,-1,-1, 0, 0, 0, 0, 0,-1,-1},
			            {-1,-1, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0},
			            { 0, 0, 0, 0,-1, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0},
			            { 0, 0, 0, 0,-1, 0, 0,-1, 0, 0, 0, 0,-1,-1,-1},
			            { 0, 0, 0, 0,-1, 0, 0, 0, 0, 0,-1, 0, 0, 0,-1},
			            { 0, 0, 0, 0,-1, 0, 0, 0,-1,-1,-1, 0, 0, 0,-1}};
	
	//////////////////////////////////////////////////
	//Categorizes the wordlist for easier searching///
	//////////////////////////////////////////////////
	public static void createCatalog() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("FinalWordlist.txt"));
		String s = br.readLine();
		String[][] temp = new String[11][50000];
		int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		while (s != null) {
			int l = s.length(); //stores length
			int a, b, c, d, e, f, g, h, i, j; //each one stores a numerical representation of each character; 0 if no character
			a = b = c = d = e = f = g = h = i = j = 0; //initially set at 0
			
			for (int z = 0; z < l; z++) { //assigns values for a - j
				if (z == 0)
					a = (int) (s.charAt(z) - 'A') + 1;
				else if (z == 1)
					b = (int) (s.charAt(z) - 'A') + 1;
				else if (z == 2)
					c = (int) (s.charAt(z) - 'A') + 1;
				else if (z == 3)
					d = (int) (s.charAt(z) - 'A') + 1;
				else if (z == 4)
					e = (int) (s.charAt(z) - 'A') + 1;
				else if (z == 5)
					f = (int) (s.charAt(z) - 'A') + 1;
				else if (z == 6)
					g = (int) (s.charAt(z) - 'A') + 1;
				else if (z == 7)
					h = (int) (s.charAt(z) - 'A') + 1;
				else if (z == 8)
					i = (int) (s.charAt(z) - 'A') + 1;
				else if (z == 9)
					j = (int) (s.charAt(z) - 'A') + 1;
			}

			//procedure based on the fact that if instance exists to one point, anything after that point does not exist
			//also, since list is in order and there are no repeats, the nth letter of a n-length word needs not be checked
			if (l == 2) {
				if (catalog[2][a] == null) {
					catalog[2][a] = new boolean[27][][][][][][][][];
					catalog[2][a][b] = new boolean[1][1][1][1][1][1][1][1];
				}
			}
			else if (l == 3) { //---------------------------------3------------------------------------------
				if (catalog[3][a] == null) {
					catalog[3][a] = new boolean[27][][][][][][][][];
					catalog[3][a][b] = new boolean[27][][][][][][][];
					catalog[3][a][b][c] = new boolean[1][1][1][1][1][1][1];
				}
				else if (catalog[3][a][b] == null) {
					catalog[3][a][b] = new boolean[27][][][][][][][];
					catalog[3][a][b][c] = new boolean[1][1][1][1][1][1][1];
				}
			}
			else if (l == 4) {	 //---------------------------------4------------------------------------------
				if (catalog[4][a] == null) {
					catalog[4][a] = new boolean[27][][][][][][][][];
					catalog[4][a][b] = new boolean[27][][][][][][][];
					catalog[4][a][b][c] = new boolean[27][][][][][][];
					catalog[4][a][b][c][d] = new boolean[1][1][1][1][1][1];
				}
				else if (catalog[4][a][b] == null) {
					catalog[4][a][b] = new boolean[27][][][][][][][];
					catalog[4][a][b][c] = new boolean[27][][][][][][];
					catalog[4][a][b][c][d] = new boolean[1][1][1][1][1][1];
				}
				else if (catalog[4][a][b][c] == null) {
					catalog[4][a][b][c] = new boolean[27][][][][][][];
					catalog[4][a][b][c][d] = new boolean[1][1][1][1][1][1];
				}	
			}
			else if (l == 5) {	 //---------------------------------5------------------------------------------
				if (catalog[5][a] == null) {
					catalog[5][a] = new boolean[27][][][][][][][][];
					catalog[5][a][b] = new boolean[27][][][][][][][];
					catalog[5][a][b][c] = new boolean[27][][][][][][];
					catalog[5][a][b][c][d] = new boolean[27][][][][][];
					catalog[5][a][b][c][d][e] = new boolean[1][1][1][1][1];
				}
				else if (catalog[5][a][b] == null) {
					catalog[5][a][b] = new boolean[27][][][][][][][];
					catalog[5][a][b][c] = new boolean[27][][][][][][];
					catalog[5][a][b][c][d] = new boolean[27][][][][][];
					catalog[5][a][b][c][d][e] = new boolean[1][1][1][1][1];
				}
				else if (catalog[5][a][b][c] == null) {
					catalog[5][a][b][c] = new boolean[27][][][][][][];
					catalog[5][a][b][c][d] = new boolean[27][][][][][];
					catalog[5][a][b][c][d][e] = new boolean[1][1][1][1][1];
				}
				else if (catalog[5][a][b][c][d] == null) {
					catalog[5][a][b][c][d] = new boolean[27][][][][][];
					catalog[5][a][b][c][d][e] = new boolean[1][1][1][1][1];
				}
			}
			else if (l == 6) {	 //---------------------------------6------------------------------------------
				if (catalog[6][a] == null) {
					catalog[6][a] = new boolean[27][][][][][][][][];
					catalog[6][a][b] = new boolean[27][][][][][][][];
					catalog[6][a][b][c] = new boolean[27][][][][][][];
					catalog[6][a][b][c][d] = new boolean[27][][][][][];
					catalog[6][a][b][c][d][e] = new boolean[27][][][][];
					catalog[6][a][b][c][d][e][f] = new boolean[1][1][1][1];
				}
				else if (catalog[6][a][b] == null) {
					catalog[6][a][b] = new boolean[27][][][][][][][];
					catalog[6][a][b][c] = new boolean[27][][][][][][];
					catalog[6][a][b][c][d] = new boolean[27][][][][][];
					catalog[6][a][b][c][d][e] = new boolean[27][][][][];
					catalog[6][a][b][c][d][e][f] = new boolean[1][1][1][1];
				}
				else if (catalog[6][a][b][c] == null) {
					catalog[6][a][b][c] = new boolean[27][][][][][][];
					catalog[6][a][b][c][d] = new boolean[27][][][][][];
					catalog[6][a][b][c][d][e] = new boolean[27][][][][];
					catalog[6][a][b][c][d][e][f] = new boolean[1][1][1][1];
				}
				else if (catalog[6][a][b][c][d] == null) {
					catalog[6][a][b][c][d] = new boolean[27][][][][][];
					catalog[6][a][b][c][d][e] = new boolean[27][][][][];
					catalog[6][a][b][c][d][e][f] = new boolean[1][1][1][1];
				}
				else if (catalog[6][a][b][c][d][e] == null) {
					catalog[6][a][b][c][d][e] = new boolean[27][][][][];
					catalog[6][a][b][c][d][e][f] = new boolean[1][1][1][1];
				}
			}	
			else if (l == 7) {	 //---------------------------------7------------------------------------------
				if (catalog[7][a] == null) {
					catalog[7][a] = new boolean[27][][][][][][][][];
					catalog[7][a][b] = new boolean[27][][][][][][][];
					catalog[7][a][b][c] = new boolean[27][][][][][][];
					catalog[7][a][b][c][d] = new boolean[27][][][][][];
					catalog[7][a][b][c][d][e] = new boolean[27][][][][];
					catalog[7][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[7][a][b][c][d][e][f][g] = new boolean[1][1][1];
				}
				else if (catalog[7][a][b] == null) {
					catalog[7][a][b] = new boolean[27][][][][][][][];
					catalog[7][a][b][c] = new boolean[27][][][][][][];
					catalog[7][a][b][c][d] = new boolean[27][][][][][];
					catalog[7][a][b][c][d][e] = new boolean[27][][][][];
					catalog[7][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[7][a][b][c][d][e][f][g] = new boolean[1][1][1];
				}
				else if (catalog[7][a][b][c] == null) {
					catalog[7][a][b][c] = new boolean[27][][][][][][];
					catalog[7][a][b][c][d] = new boolean[27][][][][][];
					catalog[7][a][b][c][d][e] = new boolean[27][][][][];
					catalog[7][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[7][a][b][c][d][e][f][g] = new boolean[1][1][1];
				}
				else if (catalog[7][a][b][c][d] == null) {
					catalog[7][a][b][c][d] = new boolean[27][][][][][];
					catalog[7][a][b][c][d][e] = new boolean[27][][][][];
					catalog[7][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[7][a][b][c][d][e][f][g] = new boolean[1][1][1];
				}
				else if (catalog[7][a][b][c][d][e] == null) {
					catalog[7][a][b][c][d][e] = new boolean[27][][][][];
					catalog[7][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[7][a][b][c][d][e][f][g] = new boolean[1][1][1];
				}
				else if (catalog[7][a][b][c][d][e][f] == null) {
					catalog[7][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[7][a][b][c][d][e][f][g] = new boolean[1][1][1];
				}
			}
			else if (l == 8) {	 //---------------------------------8------------------------------------------
				if (catalog[8][a] == null) {
					catalog[8][a] = new boolean[27][][][][][][][][];
					catalog[8][a][b] = new boolean[27][][][][][][][];
					catalog[8][a][b][c] = new boolean[27][][][][][][];
					catalog[8][a][b][c][d] = new boolean[27][][][][][];
					catalog[8][a][b][c][d][e] = new boolean[27][][][][];
					catalog[8][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[8][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[8][a][b][c][d][e][f][g][h] = new boolean[1][1];
				}
				else if (catalog[8][a][b] == null) {
					catalog[8][a][b] = new boolean[27][][][][][][][];
					catalog[8][a][b][c] = new boolean[27][][][][][][];
					catalog[8][a][b][c][d] = new boolean[27][][][][][];
					catalog[8][a][b][c][d][e] = new boolean[27][][][][];
					catalog[8][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[8][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[8][a][b][c][d][e][f][g][h] = new boolean[1][1];
				}
				else if (catalog[8][a][b][c] == null) {
					catalog[8][a][b][c] = new boolean[27][][][][][][];
					catalog[8][a][b][c][d] = new boolean[27][][][][][];
					catalog[8][a][b][c][d][e] = new boolean[27][][][][];
					catalog[8][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[8][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[8][a][b][c][d][e][f][g][h] = new boolean[1][1];
				}
				else if (catalog[8][a][b][c][d] == null) {
					catalog[8][a][b][c][d] = new boolean[27][][][][][];
					catalog[8][a][b][c][d][e] = new boolean[27][][][][];
					catalog[8][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[8][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[8][a][b][c][d][e][f][g][h] = new boolean[1][1];
				}
				else if (catalog[8][a][b][c][d][e] == null) {
					catalog[8][a][b][c][d][e] = new boolean[27][][][][];
					catalog[8][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[8][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[8][a][b][c][d][e][f][g][h] = new boolean[1][1];
				}
				else if (catalog[8][a][b][c][d][e][f] == null) {
					catalog[8][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[8][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[8][a][b][c][d][e][f][g][h] = new boolean[1][1];
				}
				else if (catalog[8][a][b][c][d][e][f][g] == null) {
					catalog[8][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[8][a][b][c][d][e][f][g][h] = new boolean[1][1];
				}
			}
			else if (l == 9) {	 //---------------------------------9------------------------------------------
				if (catalog[9][a] == null) {
					catalog[9][a] = new boolean[27][][][][][][][][];
					catalog[9][a][b] = new boolean[27][][][][][][][];
					catalog[9][a][b][c] = new boolean[27][][][][][][];
					catalog[9][a][b][c][d] = new boolean[27][][][][][];
					catalog[9][a][b][c][d][e] = new boolean[27][][][][];
					catalog[9][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[9][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[9][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[9][a][b][c][d][e][f][g][h][i] = new boolean[1];
				}
				else if (catalog[9][a][b] == null) {
					catalog[9][a][b] = new boolean[27][][][][][][][];
					catalog[9][a][b][c] = new boolean[27][][][][][][];
					catalog[9][a][b][c][d] = new boolean[27][][][][][];
					catalog[9][a][b][c][d][e] = new boolean[27][][][][];
					catalog[9][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[9][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[9][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[9][a][b][c][d][e][f][g][h][i] = new boolean[1];
				}
				else if (catalog[9][a][b][c] == null) {
					catalog[9][a][b][c] = new boolean[27][][][][][][];
					catalog[9][a][b][c][d] = new boolean[27][][][][][];
					catalog[9][a][b][c][d][e] = new boolean[27][][][][];
					catalog[9][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[9][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[9][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[9][a][b][c][d][e][f][g][h][i] = new boolean[1];
				}
				else if (catalog[9][a][b][c][d] == null) {
					catalog[9][a][b][c][d] = new boolean[27][][][][][];
					catalog[9][a][b][c][d][e] = new boolean[27][][][][];
					catalog[9][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[9][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[9][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[9][a][b][c][d][e][f][g][h][i] = new boolean[1];
				}
				else if (catalog[9][a][b][c][d][e] == null) {
					catalog[9][a][b][c][d][e] = new boolean[27][][][][];
					catalog[9][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[9][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[9][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[9][a][b][c][d][e][f][g][h][i] = new boolean[1];
				}
				else if (catalog[9][a][b][c][d][e][f] == null) {
					catalog[9][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[9][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[9][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[9][a][b][c][d][e][f][g][h][i] = new boolean[1];
				}
				else if (catalog[9][a][b][c][d][e][f][g] == null) {
					catalog[9][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[9][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[9][a][b][c][d][e][f][g][h][i] = new boolean[1];
				}
				else if (catalog[9][a][b][c][d][e][f][g][h] == null) {
					catalog[9][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[9][a][b][c][d][e][f][g][h][i] = new boolean[1];
				}
			}
			else if (l == 10) {	 //---------------------------------10------------------------------------------
				if (catalog[10][a] == null) {
					catalog[10][a] = new boolean[27][][][][][][][][];
					catalog[10][a][b] = new boolean[27][][][][][][][];
					catalog[10][a][b][c] = new boolean[27][][][][][][];
					catalog[10][a][b][c][d] = new boolean[27][][][][][];
					catalog[10][a][b][c][d][e] = new boolean[27][][][][];
					catalog[10][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[10][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[10][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[10][a][b][c][d][e][f][g][h][i] = new boolean[27];
				}
				else if (catalog[10][a][b] == null) {
					catalog[10][a][b] = new boolean[27][][][][][][][];
					catalog[10][a][b][c] = new boolean[27][][][][][][];
					catalog[10][a][b][c][d] = new boolean[27][][][][][];
					catalog[10][a][b][c][d][e] = new boolean[27][][][][];
					catalog[10][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[10][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[10][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[10][a][b][c][d][e][f][g][h][i] = new boolean[27];
				}
				else if (catalog[10][a][b][c] == null) {
					catalog[10][a][b][c] = new boolean[27][][][][][][];
					catalog[10][a][b][c][d] = new boolean[27][][][][][];
					catalog[10][a][b][c][d][e] = new boolean[27][][][][];
					catalog[10][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[10][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[10][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[10][a][b][c][d][e][f][g][h][i] = new boolean[27];
				}
				else if (catalog[10][a][b][c][d] == null) {
					catalog[10][a][b][c][d] = new boolean[27][][][][][];
					catalog[10][a][b][c][d][e] = new boolean[27][][][][];
					catalog[10][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[10][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[10][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[10][a][b][c][d][e][f][g][h][i] = new boolean[27];
				}
				else if (catalog[10][a][b][c][d][e] == null) {
					catalog[10][a][b][c][d][e] = new boolean[27][][][][];
					catalog[10][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[10][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[10][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[10][a][b][c][d][e][f][g][h][i] = new boolean[27];
				}
				else if (catalog[10][a][b][c][d][e][f] == null) {
					catalog[10][a][b][c][d][e][f] = new boolean[27][][][];
					catalog[10][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[10][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[10][a][b][c][d][e][f][g][h][i] = new boolean[27];
				}
				else if (catalog[10][a][b][c][d][e][f][g] == null) {
					catalog[10][a][b][c][d][e][f][g] = new boolean[27][][];
					catalog[10][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[10][a][b][c][d][e][f][g][h][i] = new boolean[27];
				}
				else if (catalog[10][a][b][c][d][e][f][g][h] == null) {
					catalog[10][a][b][c][d][e][f][g][h] = new boolean[27][];
					catalog[10][a][b][c][d][e][f][g][h][i] = new boolean[27];
				}
				else if (catalog[10][a][b][c][d][e][f][g][h][i] == null) {
					catalog[10][a][b][c][d][e][f][g][h][i] = new boolean[27];
				}
				catalog[10][a][b][c][d][e][f][g][h][i][j] = true;
			}
			temp[s.length()][count[s.length()]] = s;
			count[s.length()]++;
			s = br.readLine();
		}
		
		for (int i = 2; i < 11; i++) {
			int end = 0;
			for (int j = 0; j < 50000; j++)
				if (temp[i][j] == null) {
					end = j;
					break;
				}
			
			catalogByLength[i] = new String[end];
			for (int j = 0; j < end; j++)
				catalogByLength[i][j] = temp[i][j];
		}
	}
	
	public static void createWordlist() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("AggregateCluelist.txt"));
		String[] temp = new String[500000];
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
	
	public static String getRandomClue (String s) {
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
		for (int i = 1; ; i++) {
			String wordDown = wordlist[pos - i].substring(0, wordlist[pos - i].indexOf(" ---"));
			String wordUp = wordlist[pos + i].substring(0, wordlist[pos + i].indexOf(" ---"));
			if (wordDown.equals(s))
				start = pos - i;
			if (wordUp.equals(s))
				end = pos + i;
			if (!wordUp.equals(s) && !wordDown.equals(s))
				break;
		}
		
		int rand = (int)(Math.random()*(end - start + 1)) + start;
		
		return wordlist[rand].substring(wordlist[rand].indexOf("-- ") + 3);
	}
	
	//choose a random template for generateCrossword method
	private static int[][] chooseRandomTemplate(int size) {		
		rand = (int) (Math.random() * 3);
		if (rand == 0) {
			if (size == 10)
				return ten1;
			else if (size == 13)
				return thirteen1;
			else
				return fifteen1;
		}
		else if (rand == 1) {
			if (size == 10)
				return ten2;
			else if (size == 13)
				return thirteen2;
			else
				return fifteen2;
		}
		else if (rand == 2){
			if (size == 10)
				return ten3;
			else if (size == 13)
				return thirteen3;
			else
				return fifteen3;
		}
		return null;
	}
	
	//create a String equivalent of template that algorithm can work on
	private static String[][] createWorkingEquivalent(int[][] template) {
		String[][] result = new String[template.length][template.length];
		
		for (int i = 0; i < template.length; i++)
			for (int j = 0; j < template.length; j++)
				if (Tmap.map[i][j].isBlack)
					result[i][j] = "--";
				else
					result[i][j] = "";
		
		return result;
	}
	
	public static boolean isDone (String[][] s) {
		for (int i = 0; i < s.length; i++)
			for (int j = 0; j < s.length; j++)
				if (!Tmap.map[i][j].isBlack && s[i][j].equals(""))
					return false;
		return true;
	}
	
	public static boolean donePossibilities (boolean[] b) {
		for (int i = 0; i < b.length; i++)
			if (!b[i])
				return false;
		return true;
	}
	
	public static int[] nextSquare (String[][] s, int size) {//[0] = row; [1] = col
		int row = -1, col = -1;
		for (int j = 0; j < size && row == -1; j++) {
			for (int i = 0; i < size && row == -1; i++) {
				if (!Tmap.map[i][j].isBlack && Tmap.map[i][j].posA == 1 && s[i][j].equals("")) {
					row = i;
					col = j;
				}
			}
		}
		int[] result = {row, col};
		return result;		
	}
	
	//Assumes one character
	private static int stringToInt (String s) {
		if (s.equals("-"))
			return -1;
		else
			return (int)(s.charAt(0) - 'A') + 1;
	}
	
	private static String intToString (int i) {
		return "" + (char)('A' + i - 1);
	}
	
	public static boolean isInCatalog (int l, String s) {
		if (s.length() == 0 || s.length() == 1)
			return true;
			
		if (s.indexOf('-') == -1 && l == s.length()) { //binary search to simplify search of words already completed AKA without -
			int top = catalogByLength[l].length - 1;
			int bottom = 0;
			
			while (bottom <= top) {
				int current = (top + bottom)/2;
				String word = catalogByLength[l][current];
				if (word.compareTo(s) == 0)
					return true;
				else if (word.compareTo(s) > 0)
					top = current - 1;
				else
					bottom = current + 1;
			}
			
			return false;
		}
		
		if (s.indexOf('-') == -1 && l > s.length()) { //no dashes in word, but incomplete word. Faster to use catalog
			int[] letters = new int[10];
			for (int i = 0; i < s.length(); i++) {
				letters[i] = stringToInt(s.substring(i, i+1));
			}
			
			if (s.length() == 2) {///////////////////////////////////////////////////////////////////////////////////////// 2
				try {
					if (catalog[l][letters[0]][letters[1]] == null)
						return false;;
				} 
				catch (NullPointerException e) {
					return false;
				}
			}
			else if (s.length() == 3) {///////////////////////////////////////////////////////////////////////////////////////// 3			
				try {
					if (catalog[l][letters[0]][letters[1]][letters[2]] == null)
						return false;
				} 
				catch (NullPointerException e) {
					return false;
				}
			}
			else if (s.length() == 4) {///////////////////////////////////////////////////////////////////////////////////////// 4
				try {
					if (catalog[l][letters[0]][letters[1]][letters[2]][letters[3]] == null)
						return false;;
				} 
				catch (NullPointerException e) {
					return false;
				}		
			}	
			else if (s.length() == 5) {///////////////////////////////////////////////////////////////////////////////////////// 5
				try {
					if (catalog[l][letters[0]][letters[1]][letters[2]][letters[3]][letters[4]] == null)
						return false;;
				} 
				catch (NullPointerException e) {
					return false;
				}
			}
			else if (s.length() == 6) {///////////////////////////////////////////////////////////////////////////////////////// 5
				try {
					if (catalog[l][letters[0]][letters[1]][letters[2]][letters[3]][letters[4]][letters[5]] == null)
						return false;;
				} 
				catch (NullPointerException e) {
					return false;
				}
			}
			else if (s.length() == 7) {///////////////////////////////////////////////////////////////////////////////////////// 5
				try {
					if (catalog[l][letters[0]][letters[1]][letters[2]][letters[3]][letters[4]][letters[5]][letters[6]] == null)
						return false;;
				} 
				catch (NullPointerException e) {
					return false;
				}
			}
			else if (s.length() == 8) {///////////////////////////////////////////////////////////////////////////////////////// 5
				try {
					if (catalog[l][letters[0]][letters[1]][letters[2]][letters[3]][letters[4]][letters[5]][letters[6]]
					                   [letters[7]] == null)
						return false;;
				} 
				catch (NullPointerException e) {
					return false;
				}
			}
			else if (s.length() == 9) {///////////////////////////////////////////////////////////////////////////////////////// 5
				try {
					if (catalog[l][letters[0]][letters[1]][letters[2]][letters[3]][letters[4]][letters[5]][letters[6]]
					                    [letters[7]][letters[8]] == null)
						return false;
				} 
				catch (NullPointerException e) {
					return false;
				}
			}
			else if (s.length() == 10) {///////////////////////////////////////////////////////////////////////////////////////// 5
				try {
					if (catalog[l][letters[0]][letters[1]][letters[2]][letters[3]][letters[4]][letters[5]][letters[6]]
					              [letters[7]][letters[8]][letters[9]] == true)
						return true;;
				} 
				catch (NullPointerException e) {
					return false;
				}
			}
			return true;
		}
		
		for (int i = 0; i < catalogByLength[l].length; i++) { //if string has dashes in it
			boolean isInvalid = false;
					
			for (int j = 0; j < s.length() && !isInvalid; j++) {
				if (s.charAt(j) != '-' && s.charAt(j) != catalogByLength[l][i].charAt(j))
					isInvalid = true;
			}
					
			if (!isInvalid) {
				return true;
			}
		}	
		return false;
	}
	
	public static boolean checkValid (String[][] s, int n) { //n = 0 is check ahead; n = 1 is normal check
		for (int j = 0; j < s.length; j++)
			for (int i = 0; i < s.length; i++)
				if (!Tmap.map[i][j].isBlack && Tmap.map[i][j].posD == 1 && ((!s[i][j].equals("") && n == 1) || n == 0)) { //only checks if square is first letter in down word
					String pre = "";
					for (int m = i; m < i + Tmap.map[i][j].lengthD; m++) //finds whats in the down word so far
						if (s[m][j].equals(""))
							pre += "-";
						else
							pre += s[m][j];
					
					for (int m = pre.length() - 1; m >= 0; m--)
						if (pre.charAt(m) == '-')
							pre = pre.substring(0, m);
						else
							break;
					
					boolean result = isInCatalog(Tmap.map[i][j].lengthD, pre);
					if (!result && n == 0) { //if checking ahead, first letter is blank, and no possible
																	 //remember (i,j) is the start of that down word
						//reverse = true; //need to reverse
						
						//Takes of invalid words
						for (int m = i; m < i + Tmap.map[i][j].lengthD; m++) { //goes through all letters of that down word
							if (!s[m][j].equals("")) { //there is a letter; initiates erasure
								for (int t = j - Tmap.map[m][j].posA + 1; t <= j + Tmap.map[m][j].lengthA - Tmap.map[m][j].posA; t++) {
									s[m][t] = "";
								}
							}
						}			
						
						int[] temp = nextSquare(s, s.length);
						
						try {
							backtrackAlgorithm(s, temp[0], temp[1], s.length, 0);
						}
						catch (StackOverflowError e) {
							reverse = true;
							break;
						}
						
						return false;
					}
					else if (!result)
						return false;
				}
		return true;
	}
	
	
	
	public static String[][] generate (int size) {
		int[][] template = chooseRandomTemplate(size);
		Tmap = new TemplateMap(template);
		String[][] workspace = createWorkingEquivalent(template);
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				workspace[i][j] = "";
		
		int[] temp = nextSquare(workspace, size);
		
		while (!doneGeneration) {
			backtrackAlgorithm(workspace, temp[0], temp[1], size, 0);
			reverse = false;
		}
		
		catalog = null;
		catalogByLength = null;
		
		return crossword;
	}
	
	public static void backtrackAlgorithm(String[][] s, int row, int col, int size, int count) {
		if (isDone(s)) { //checks if done
			crossword = new String[size][size];
			for (int i = 0; i < size; i++)
				for (int j = 0; j < size; j++)
					crossword[i][j] = s[i][j];
			doneGeneration = true;
		}
		else { //first letter in A-word and not black
			int lengthA = Tmap.map[row][col].lengthA;
			boolean[] checkDone = new boolean[catalogByLength[lengthA].length];
			int rand = 0;
			
			boolean noUse = checkValid(s, 0);
			
			while (!donePossibilities(checkDone) && !doneGeneration) { //keep doing this until all the words of that length are tried
				do { //keep getting random number if the word correlated with that number has been used already this call
					rand = (int)(Math.random()*checkDone.length);
				} while (checkDone[rand]);
				checkDone[rand] = true;
				
				String word = catalogByLength[lengthA][rand]; //puts word in
				for (int j = col; j < col + lengthA; j++)
					s[row][j] = word.substring(j-col, j+1-col);
				
				if (checkValid(s, 1)) { //valid word placement, recurse in
					int[] temp = nextSquare(s, size);
					
					backtrackAlgorithm(s, temp[0], temp[1], size, 0);
					
					if (reverse)
						return;
				}
				//not valid, or failed; delete anything progress here
				for (int j = col; j < col + lengthA; j++)
					s[row][j] = "";
				
				count++;
				if (count >= 30000) {
					reverse = true;
					System.out.println(count);
					return;
				}
			}
		}
	}

	
	public static void main(String[] args) throws IOException {
		createCatalog();
		System.out.println(generate(10));
	}
}