import java.util.ArrayList;;

public class Euler50 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int MAX = 2000000;
		// Create the prime list and a prime boolean array to check if a number is prime
		boolean prime[] = new boolean[MAX];
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		for (int i = 2; i <= MAX; i++) {
			if(!prime[i-1]){
				primeList.add(i);
				for (int j = i * i; j <= MAX && j > 0; j += i) {
					prime[j-1] = true;
				}
			}
		}
		// Actually starts to find the max chain length
		int maxChainLength = 0;
		long thePrimeWereLookingFor = 2;
		int startingPrime = 2;
		for (int i = 0; i < primeList.size(); i++) { // On each prime number in the list
			int sum = primeList.get(i);
			int chainLength = 1;
			// Find the longest chain that has a prime sum 
			for (int j = i+1; j < primeList.size() && sum + primeList.get(j) < MAX; j++) {
				chainLength++;
				sum += primeList.get(j);
				if(!prime[(sum-1)]){ // If the prime sum is found.
					if(chainLength > maxChainLength){
						thePrimeWereLookingFor = sum;
						maxChainLength = chainLength;
						startingPrime = primeList.get(i);
					}
				}
			}
			// If we have less than the max chain left then end the search
			if (primeList.size()-i < maxChainLength) break;
		}
		long stop = System.currentTimeMillis();
		System.out.println("The prime we're looking for: " + thePrimeWereLookingFor);
		System.out.println("The chain it creates: " + maxChainLength);
		System.out.println("The chain starts with: " + startingPrime);
		System.out.println("Time elapsed: " + (stop - start) + " ms");
	}
}
