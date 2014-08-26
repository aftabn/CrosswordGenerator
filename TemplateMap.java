public class TemplateMap {
	Square[][] map;
	
	public TemplateMap(int[][] template) {
		int l = template.length;
		map = new Square[l][l];
		
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < l; j++) {
				map[i][j] = new Square();
				
				if (template[i][j] == -1)
					map[i][j].isBlack = true;
				else {
					map[i][j].isBlack = false;
					///////////////////// Handles across
					int countA = 0;
					int col = j-1;
					while (!(col == -1) && !(template[i][col] == -1)) {
						countA++;
						col--;
					}
					map[i][j].posA = countA + 1;
					col = j + 1;
					while (!(col == l) && !(template[i][col] == -1)) {
						countA++;
						col++;
					}
					map[i][j].lengthA = countA + 1;
					
					///////////////////// Handles down
					int countD = 0;
					int row = i-1;
					while (!(row == -1) && !(template[row][j] == -1)) {
						countD++;
						row--;
					}
					map[i][j].posD = countD + 1;
					row = i + 1;
					while (!(row == l) && !(template[row][j] == -1)) {
						countD++;
						row++;
					}
					map[i][j].lengthD = countD + 1;
				}
			}
		}
	}
	
	class Square {
		boolean isBlack;
		int posA, posD;
		int lengthA, lengthD;
		
		public String toString() {
			return ("A: " + posA + ", " + lengthA + "||D: " + posD + ", " + lengthD);
		}
	}
}
