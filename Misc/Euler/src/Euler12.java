public class Euler12 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		int numOfPrimes = 100;
		boolean prime[] = new boolean[numOfPrimes];
		long primeList[] = new long[numOfPrimes];
		int primeI = 0;
		for(long i = 2; i <= numOfPrimes; i++){
			if(!prime[(int) (i-1)]){
				primeList[primeI++] = i;
				for(long j = i*i; j <= numOfPrimes;j+=i){
					prime[(int) (j-1)] = true;
				}
			}
		}
		
		int i = 2;
		while(true){
			primeI = 0;
			int countFactor = 0;
			int count = 1;
			int num = i*(i+1)/2;
			while(num > 0){
				if(num % primeList[primeI] != 0){
					if(primeList[++primeI] == 0) break;
					if(count == 1 && primeList[primeI] > Math.sqrt(i*(i+1)/2))	break;
					count *= (countFactor+1);
					countFactor = 0;
					continue;
				}
				num /= primeList[primeI];
				countFactor++;
			}
			if(count >= 500){
				System.out.println(i + " " + (i*(i+1)/2) + " " + count);
				break;
			}
			i++;
		}
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");

	}
}
