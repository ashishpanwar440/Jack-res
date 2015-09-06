
public class ToBraille {

	public static void main(String[] args) {
		String test = "This, is a. TEST but it also contains numbers 40 and another one 56213";
		// For the designs of the letters
		boolean letterDesign[][][] = new boolean[30][3][2]; 
		// A
		letterDesign[0][0][0] = true;
		// B
		letterDesign[1][0][0] = true;
		letterDesign[1][1][0] = true;
		// C
		letterDesign[2][0][0] = true;
		letterDesign[2][0][1] = true;
		// D
		letterDesign[3][0][0] = true;
		letterDesign[3][0][1] = true;
		letterDesign[3][1][1] = true;
		// E
		letterDesign[4][0][0] = true;
		letterDesign[4][1][1] = true;
		// F
		letterDesign[5][0][0] = true;
		letterDesign[5][0][1] = true;
		letterDesign[5][1][0] = true;
		// G
		letterDesign[6][0][0] = true;
		letterDesign[6][0][1] = true;
		letterDesign[6][1][0] = true;
		letterDesign[6][1][1] = true;
		// H
		letterDesign[7][0][0] = true;
		letterDesign[7][1][0] = true;
		letterDesign[7][1][1] = true;
		// I
		letterDesign[8][0][1] = true;
		letterDesign[8][1][0] = true;
		// J
		letterDesign[9][0][1] = true;
		letterDesign[9][1][0] = true;
		letterDesign[9][1][1] = true;
		// K
		letterDesign[10][0][0] = true;
		letterDesign[10][2][0] = true;
		// L
		letterDesign[11][0][0] = true;
		letterDesign[11][1][0] = true;
		letterDesign[11][2][0] = true;
		// M
		letterDesign[12][0][0] = true;
		letterDesign[12][0][1] = true;
		letterDesign[12][2][0] = true;
		// N
		letterDesign[13][0][0] = true;
		letterDesign[13][0][1] = true;
		letterDesign[13][1][1] = true;
		letterDesign[13][2][0] = true;
		// O
		letterDesign[14][0][0] = true;
		letterDesign[14][1][1] = true;
		letterDesign[14][2][0] = true;
		// P
		letterDesign[15][0][0] = true;
		letterDesign[15][0][1] = true;
		letterDesign[15][1][0] = true;
		letterDesign[15][2][0] = true;
		// Q
		letterDesign[16][0][0] = true;
		letterDesign[16][0][1] = true;
		letterDesign[16][1][0] = true;
		letterDesign[16][1][1] = true;
		letterDesign[16][2][0] = true;
		// R
		letterDesign[17][0][0] = true;
		letterDesign[17][1][0] = true;
		letterDesign[17][1][1] = true;
		letterDesign[17][2][0] = true;
		// S
		letterDesign[18][0][1] = true;
		letterDesign[18][1][0] = true;
		letterDesign[18][2][0] = true;
		// T
		letterDesign[19][0][1] = true;
		letterDesign[19][1][0] = true;
		letterDesign[19][1][1] = true;
		letterDesign[19][2][0] = true;
		// U
		letterDesign[20][0][0] = true;
		letterDesign[20][2][0] = true;
		letterDesign[20][2][1] = true;
		// V
		letterDesign[21][0][0] = true;
		letterDesign[21][1][0] = true;
		letterDesign[21][2][0] = true;
		letterDesign[21][2][1] = true;
		// W
		letterDesign[22][0][1] = true;
		letterDesign[22][1][0] = true;
		letterDesign[22][1][1] = true;
		letterDesign[22][2][1] = true;
		// X
		letterDesign[23][0][0] = true;
		letterDesign[23][0][1] = true;
		letterDesign[23][2][0] = true;
		letterDesign[23][2][1] = true;
		// Y
		letterDesign[24][0][0] = true;
		letterDesign[24][0][1] = true;
		letterDesign[24][1][1] = true;
		letterDesign[24][2][0] = true;
		letterDesign[24][2][1] = true;
		// Z
		letterDesign[25][0][0] = true;
		letterDesign[25][1][1] = true;
		letterDesign[25][2][0] = true;
		letterDesign[25][2][1] = true;
		// Capital sign
		letterDesign[26][2][1] = true;
		// Number sign
		letterDesign[27][0][1] = true;
		letterDesign[27][1][1] = true;
		letterDesign[27][2][1] = true;
		letterDesign[27][2][0] = true;
		// Period
		letterDesign[28][1][0] = true;
		letterDesign[28][1][1] = true;
		letterDesign[28][2][1] = true;
		// Comma
		letterDesign[29][1][1] = true;
		
		// Check the text for special characters
		for (int i = 0; i < test.length(); i++) {
			if(Character.isUpperCase(test.charAt(i))){ // If it's an uppercase
				// Add an * in front of it so we will print a uppercase sign there
				test = test.substring(0, i) + "*" + test.substring(i);
				i++;
			}
			if(Character.isDigit(test.charAt(i))){ // If it is a digit
				if(test.charAt(i)!='0'){
					// Put a / in front and turn the number into the coresponding letter 1->a 2->b etc.
					test = test.substring(0, i) + "/" + (char)(test.charAt(i)-'1'+'a') + test.substring(i+1);
				}else test = test.substring(0, i) + "/" + "j" + test.substring(i+1); // 0 is a special case because it's supposed to be j
				i++;
			}
		}
		test = test.toLowerCase(); // Turn them all into lower case
		// Calculate the size of the matrix to fit the string
		boolean matrix[][] = new boolean[(test.length()/40+1)*4][80];
		for (int i = 0; i < test.length(); i++) {
			// The location of the letter on the matrix
			int x = (i % 40) * 2;
			int y = (i / 40) * 4;// Times 4 so there will be a space in the middle of each line
			for (int j = 0; j <= 2; j++) {
				for (int k = 0; k <= 1; k++) {
					// If it is a letter then just subtract it by the letter 'a' to get the index of the design
					if(test.charAt(i) - 'a' >= 0 && test.charAt(i) - 'a' <=25)
						matrix[y+j][x+k] = letterDesign[test.charAt(i) - 'a'][j][k];
					// Individual if statements for special cases
					if(test.charAt(i) == '*')
						matrix[y+j][x+k] = letterDesign[26][j][k];
					if(test.charAt(i) == '/')
						matrix[y+j][x+k] = letterDesign[27][j][k];
					if(test.charAt(i) == '.')
						matrix[y+j][x+k] = letterDesign[28][j][k];
					if(test.charAt(i) == ',')
						matrix[y+j][x+k] = letterDesign[29][j][k];
				}
			}
		}
		
		// Print out the matrix
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j]) System.out.print(". ");
				else System.out.print("  "); // Add a space so that they won't stick together
			}
			System.out.println();
		}
	}
}
