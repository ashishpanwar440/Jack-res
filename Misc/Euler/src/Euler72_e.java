import java.io.*;
import java.util.*;

public class Euler72_e {

	public static void main(String[] args) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		Scanner scan = new Scanner(System.in);
		int testCase = Integer.parseInt(scan.nextLine());
		for(int t = 1; t <= testCase; t++){
			long n = Long.parseLong(scan.nextLine());
			long count = (n-1)*n/2;
			boolean[] prime = new boolean[(int)n];
			for(long i = 2;i <= n;i++){
				if(!prime[(int) i-1]){
					for(long j = i*2; j <= n; j +=i){
						prime[(int)j-1] = true;
						count -= (j-i)/i;
					}
				}
			}
			System.out.println(count);
		}
	}
}