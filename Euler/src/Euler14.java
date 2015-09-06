public class Euler14 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		long maxNum = 0;
		long max = 0;
		for(long i = 1;i <= 1000000;i++){
			int count = 1;
			long j = i;
			while(j>1){
				if(j%2==0) j /= 2;
				else j = 3*j+1;
				count++;
			}
			if(count>max){
				max = count;
				maxNum = i;
			}
		}
		System.out.println(maxNum + " contains " + max + " terms");
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
	}
}
