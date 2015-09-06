import java.util.Scanner;


public class Test {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		long testCase = Long.parseLong(scan.nextLine());
		for(long t = 1; t <= testCase; t++){
			long n = Long.parseLong(scan.nextLine());
			boolean prime[];
			if(n > 941) prime = new boolean[(int)n/941];
			else prime = new boolean[(int)n/2];
			System.out.println((int)Math.sqrt(n));
			long thePrime = 0;
			for(long i = 2; i <= prime.length; i++){
				if(!prime[(int)i-1]){
					if(n % i == 0)
						thePrime = i;
					for(long j = i*i; j <= prime.length; j+=i){
						prime[(int)j-1] = true;
					}
				}
			}
			if(thePrime == 0 && n > 1) thePrime = n;
			System.out.println(thePrime);
		}
	}
}
