/* Author: Jack Le
 * The purpose of the program is to solve the project Euler problem number 96
 * The program solves all 50 puzzles given in less than half a second (varies on different computers)
 * The program utilizes 2 solving algorithms: Elimination and guessing(logical brute force)
 * 
 * The algorithm:
 * 	For elimination: The program search for cells where there can only 1 candidate and
 * 	fill the cell with that candidate
 * 	After the elimination is complete if the puzzle is not solved, the program will start
 * 	guessing.
 * 	For guessing: The program recursively call the try number method for the next cell if the
 * 	current cell is legal.
 * 	After guessing for 50ms if the solution is not found, the program will reset the puzzle and
 * 	start guess from the end.
 */
import java.io.*;
import java.util.Scanner;

public class Sudoku {
	static int[][][] puzzle = new int[50][9][9]; // The actual puzzle
	static int[][] copy = new int[9][9]; // The space for the copy
	static int flip; // Move counting variable to know when to flip the guessing direction
	static boolean solved, mode;	// solved boolean is to see if the forward guess solved the puzzle
									// mode boolean is to know which mode the guess in on. False = forward, true = backward
	public static void main(String[] args) throws IOException {
		// Read the puzzle into the array
		BufferedReader read = new BufferedReader(new FileReader("p096_sudoku.txt"));
		String puz = "", line;
		while((line = read.readLine()) != null) puz += line + '\n';
		for(int i = 0; i < puzzle.length;i++){
			for(int j = 0; j < 9;j++){
				for(int k = 0; k < 9;k++){
					puzzle[i][j][k] = Character.getNumericValue(puz.charAt(i*98+10*j+k+8));
				}
			}
		}
		read.close();
		
		//Solve the puzzle
		long total = 0; // Total time variable
		for (int i = 0; i < puzzle.length; i++) { // Iterate every puzzle
			System.out.println("Puzzle " + (i+1));
			int sum3Digits = 0;
			printPuzzle(i);  // Print the unsolved puzzle
			int solveType = 0;
			long start = System.currentTimeMillis(); // Start the clock
			flip = 0;
			solved = false;
			mode = false;
			
			elimination(i); // Solve partially (or completely) by elimination first
			makeACopy(i); // Make a copy of the partially solved puzzle unless it can't be solved within 2000 moves
			if(!solved(i)){
				int[] loc = {0,0};
				tryNum(loc,i); // Try it forward
				if(!solved){ // If it can't solve in 2000 moves then try it backward
					loc[0] = 8;
					loc[1] = 8;
					tryNum(loc,i);
					solveType = 1;
				} else solveType = 2;
			} 
			long stop = System.currentTimeMillis();
			if(solveType == 0) System.out.println("Solved by elimination");
			if(solveType == 1) System.out.println("Solved by guessing backward");
			if(solveType == 2) System.out.println("Solved by guessing forward");
			printPuzzle(i); // Print the solved puzzle
			System.out.println("Time: " + (double)(stop - start)/1000);
			total += stop - start;
			
			sum3Digits = puzzle[i][0][0] + puzzle[i][0][1] + puzzle[i][0][2];
			System.out.println("Sum of 3 top left digits: " + sum3Digits);
		}
		System.out.println("Total Time: " + ((float)total/1000));
	}

	// Method to reset the puzzle
	static void reset(int puz){
		for (int i = 0; i < puzzle[0].length; i++) {
			for (int j = 0; j < puzzle[0][0].length; j++) {
				puzzle[puz][i][j] = copy[i][j];
			}
		}
	}

	// Method to make a copy of the puzzle
	static void makeACopy(int puz){
		for (int i = 0; i < puzzle[0].length; i++) {
			for (int j = 0; j < puzzle[0][0].length; j++) {
				copy[i][j] = puzzle[puz][i][j];
			}
		}
	}

	// Elimination solving method
	static void elimination(int puz){
		// Scan the cells
		for (int i = 0; i < puzzle[0].length; i++) {
			for (int j = 0; j < puzzle[0][0].length; j++) {
				if(puzzle[puz][i][j] == 0){ // If the cell is empty
					int count = 0;
					int val = 0;
					for (int k = 1; k <= 9; k++) {
						puzzle[puz][i][j] = k; // Plug the value in and test it out
						int[] loc = new int[2];
						loc[0] = i;
						loc[1] = j;
						if(legal(loc,puz)){ // If the tested value is legal then count 1 up
							count++;
							if(val == 0) val = k;
						}
					}
					if(count != 1) puzzle[puz][i][j] = 0;
					else{// If the number is unique then set it as that number and go back to the beginning
						puzzle[puz][i][j] = val;
						i=0;
						j=-1;
					}
				}
			}
		}		
	}

	// Method to get the location of the next cell based on the mode
	static int[] nextLoc(int[] location){
		int[] newLoc = new int[2];
		newLoc[0] = location[0];
		newLoc[1] = location[1];
		if(!mode) { // If mode is false then move forward
			if(++newLoc[1] >= 9){
				newLoc[1] = 0;
				newLoc[0]++;
			}
		}else{ // If mode is true then move backward
			if(--newLoc[1] < 0){
				newLoc[1] = 8;
				newLoc[0]--;
			}
		}
		return newLoc;
	}

	// Recursion method to try the number in. If one of the calls returns a true then it will cause a
	// chain true return and eventually escape the recursion stack.
	static boolean tryNum(int[] location, int puz){
		if(!mode && flip++ > 2000){ // If it can't guess after 2000 moves then go the other way
			reset(puz);
			solved = false;
			mode = true;
			return true; // Return true to escape the recursion stack but not solved
		}
		if(puzzle[puz][location[0]][location[1]] != 0){ // If the current cell is not empty
			if((!mode && nextLoc(location)[0] < 9) || 
					(mode && nextLoc(location)[0] >= 0)){ // If the current cell is not the last one
				if(tryNum(nextLoc(location),puz)) return true; // Recursively call the next cell out and only return true when the call return a true
			}else{ // If it is the last cell then the puzzle is solved
				solved = true;
				return true;
			}
		}else{ // If the cell is empty
			for(int i = 1; i <= 9; i++){ // Try all the numbers out
				puzzle[puz][location[0]][location[1]] = i;
				if(legal(location,puz)) // If the number tested is legal
					if((!mode && nextLoc(location)[0] < 9) || 
							(mode && nextLoc(location)[0] >= 0)){ // Same logic as above
						if(tryNum(nextLoc(location),puz)) return true;
					}else{
						solved = true;
						return true;
					}
			}
		}
		puzzle[puz][location[0]][location[1]] = 0; // If there is no more number to test reset the cell to empty 
		return false; // And return false to backtrack to the previous cells
	}

	// Method to check if the cell is legal.
	// Takes in the location of the cell and the number of the puzzle.
	static boolean legal(int[] location, int puz){
		boolean legal = true;
		for(int i = 0; i < 9; i++){ // X check
			if(i != location[1] && puzzle[puz][location[0]][i] == puzzle[puz][location[0]][location[1]]){
				legal = false;
			}
		}
		for(int i = 0; i < 9; i++){ // Y check
			if(i != location[0] && puzzle[puz][i][location[1]] == puzzle[puz][location[0]][location[1]]){
				legal = false;
			}
		}
		// Block check
		int blockX = location[1] / 3;
		int blockY = location[0] / 3;
		for (int i = blockY * 3; i <= blockY * 3 + 2; i++) {
			for (int j = blockX * 3; j <= blockX * 3 + 2; j++) {
				if(i != location[0] && j != location[1] && puzzle[puz][i][j] == puzzle[puz][location[0]][location[1]]){
					legal = false;
				}
			}
		}

		return legal;
	}

	// Method to check if the puzzle is solved
	static boolean solved(int puz){
		for (int i = 0; i < puzzle[0].length; i++) {
			for (int j = 0; j < puzzle[0][0].length; j++) {
				if(puzzle[puz][i][j] == 0) return false;
			}
		}
		return true;
	}

	// Method to print out the puzzle
	static void printPuzzle(int puz){
		String output = "";
		for (int i = 0; i < puzzle[0].length; i++) {
			for (int j = 0; j < puzzle[0][0].length; j++) {
				if(puzzle[puz][i][j] != 0) output += puzzle[puz][i][j]+"|";
				else output += " |";
			}
			output += '\n';
		}
		System.out.print(output);
	}
}
