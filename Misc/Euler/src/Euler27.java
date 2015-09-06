public class Euler27 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		boolean[] prime = new boolean[20000];
		for (long i = 2; i <= prime.length; i++) {
			if(!prime[(int) i-1]){
				for(long j = i*i; j <= prime.length;j+=i){
					prime[(int) j-1] = true;
				}
			}
		}
		long maxN = 0, maxA = 0, maxB = 0;
		for(long a = -999; a < 1000;a++){
			for(long b = -999; b < 1000;b++){
				long n = 0;
				while(n*n+a*n+b>0 && !prime[(int)(n*n+a*n+b)-1]) n++;
				if(maxN < n){
					maxN = n;
					maxA = a;
					maxB = b;
				}
			}
		}
		System.out.println(maxA + ", " + maxB + ", " + maxN);
		System.out.println(maxA*maxB);
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
	}
}
