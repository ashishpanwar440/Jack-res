import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Euler18 {
	static long max = 0;
	
	public static void main(String[] args) throws IOException {
		int[][] num = new int[15][15];
		BufferedReader read = new BufferedReader(new FileReader("Euler18.txt"));
		String line;
		int i = 0;
		while((line = read.readLine()) != null){
			int j = 0;
			while(true){
				line = line.trim();
				num[i][j++] = Integer.parseInt(line.substring(0, 2));
				if(line.length()==2) break;
				line = line.substring(2);
				
			}
			i++;
		}
		long start = System.nanoTime();
		sumPath(num,0,0,0);
		System.out.println(max);
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
	}
	
	public static void sumPath(int[][] list, int i, int j, long sum){
		if(i==list.length-1){
			if(max < sum + list[i][j]) max = sum + list[i][j];
			return;
		}else{
			sumPath(list,i+1,j,sum + list[i][j]);
			sumPath(list,i+1,j+1,sum + list[i][j]);
		}
	}
}
