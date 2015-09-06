public class Euler15 {
	public static void main(String[] args) {
		long[][] grid = new long[21][21];
		for (int i = 0; i < grid.length; i++) {
			grid[20][i] = 1;
			grid[i][20] = 1;			
		}
		for (int i = grid.length-2; i >=0 ; i--) {
			for (int j = grid.length-2; j >=0 ; j--) {
				grid[i][j] = grid[i][j+1]+grid[i+1][j]; 
			}
		}
		System.out.println(grid[0][0]);
		
	}
}
