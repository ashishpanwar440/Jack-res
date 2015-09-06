public class Euler28 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		int[][] matrix = new int[1001][1001];
		int[] location = new int[2];
		location[0] = matrix.length/2;
		location[1] = matrix[0].length/2;
		char direction = 0;
		int steps = 1, numOfSteps = 1, i = 1;
		
		while(i<=matrix.length*matrix[0].length){
			matrix[location[0]][location[1]] = i++;
			steps--;
			switch(direction){
			case 0:
				location[1]++;
				if(steps==0){
					steps = numOfSteps;
					direction = 1;
				}
				break;
			case 1:
				location[0]++;
				if(steps==0){
					steps = ++numOfSteps;
					direction = 2;
				}
				break;
			case 2:
				location[1]--;
				if(steps==0){
					steps = numOfSteps;
					direction = 3;
				}
				break;
			case 3:
				location[0]--;
				if(steps==0){
					steps = ++numOfSteps;
					direction = 0;
				}
				break;
			}
		}
		for (int j = 0; j < matrix.length; j++) {
			for (int k = 0; k < matrix[0].length; k++) {
				System.out.print(matrix[j][k]+"\t");
			}
			System.out.println();
		}

		long sum = 0;
		for (int j = 0; j < matrix.length; j++) {
			sum += matrix[j][j] + matrix[matrix.length-1-j][j];
		}
		sum -= matrix[matrix.length/2][matrix.length/2];
		System.out.println(sum);
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
	}
}
