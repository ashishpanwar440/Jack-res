import java.util.*;

public class Euler5 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCase = Integer.parseInt(scan.nextLine());
		for(int t = 1; t <= testCase; t++){
			int n = Integer.parseInt(scan.nextLine());
			long start = System.nanoTime();
			boolean prime[] = new boolean[n];
			long product = 1;
			for(int i = 2; i <= n; i++){
				if(!prime[i-1]){
					product *= (long)i;
					for(int j = i*i; j <= n; j+=i){
						prime[j-1] = true;
						if(Math.log(j) / Math.log(i)-(int)(Math.log(j) / Math.log(i))== 0){
							product *= i;
						}
					}
				}
			}
			long stop = System.nanoTime();
			long elapse = stop - start;
			System.out.println(product + " took " + elapse + " nanoseconds");
		}
		scan.close();
	}
}
