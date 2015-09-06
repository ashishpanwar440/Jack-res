import java.util.Scanner;


public class Euler7 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCase = Integer.parseInt(scan.nextLine());
		for(int t = 1; t <= testCase; t++){
			long n = Long.parseLong(scan.nextLine());
			long start = System.nanoTime();
			boolean prime[] = new boolean[114729];
			int d = 1;
			for(long i = 2; i <= prime.length; i++){
				if(!prime[(int)i-1]){
					if(d++ == n){
						System.out.println(i);
						break;
					}
					for(long k = i*i; k <= prime.length; k+=i){
						prime[(int)k-1] = true;
					}
				} 
			}
			long stop = System.nanoTime();
			long elapse = stop - start;
			System.out.println(elapse + " nanoseconds");
		}
	}

}
