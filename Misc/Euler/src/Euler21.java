
public class Euler21 {
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		int sumz = 0;
		boolean amicabulz[] = new boolean[10000]; // List to keep track of amicable pairs.
		for(int i = 1; i <= 10000; i++){
			if(amicabulz[i-1]) continue; // If the number is already amicable, skip it.
			int theSum = sum(i);
			if(i!=theSum && i == sum(theSum)){ // If the number is different from the sum and equals to the sum of its sum
				sumz += i + theSum;
				amicabulz[theSum-1] = true;
			}
		}
		long stop = System.currentTimeMillis(); // This program has a built in timer.
		System.out.println(sumz + "\nTime elapsed: " + (stop-start) + "ms");
	}

	// Method to find sum of all proper devisors.
	static int sum(int daNumba){
		int sum = 0;
		for(int i = 1; i <= daNumba/2; i++){ // After a half of that number, there wont be anymore devisors.
			if(daNumba % i == 0) sum += i;
		}
		return sum;
	}
}
