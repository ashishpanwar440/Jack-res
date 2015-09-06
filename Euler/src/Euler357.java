public class Euler357 {
	static int LIMIT = 100000000;
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		boolean[] notPrime = new boolean[LIMIT+1];
		for (long i = 2; i <= notPrime.length; i++) {
			if(!notPrime[(int) (i-1)]){
				for (long j = i*i; j <= notPrime.length && j > 0; j+=i) {
					notPrime[(int) (j-1)] = true;
				}
			}
		}
		
		System.out.println("done with prime");
		long sum = 0;
		for (long i = 1; i <= LIMIT; i++) {
			boolean valid = true;
			for(long j = 1; j <= (int) Math.sqrt(i);j++){
				if(i % j == 0 && notPrime[(int) (j+i/j-1)]){
					valid = false;
					break;
				}
			}
			if(valid){
				sum += i;
			}
		}
		
		System.out.println(sum);
		long stop = System.currentTimeMillis();
		System.out.println("Time elapsed: " + (stop - start) + " ms");
	}
}
