public class Euler4 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		int pal = 999999;
		while(!isPalindrome(--pal));
		int leftSide = pal / 1000;
		for(;leftSide>101;leftSide--){
			int newPal = leftSide * 1000 + 100 * (leftSide % 10) + 
					10 * (leftSide % 100 /10) + leftSide / 100; 
			boolean found = false;
			for(int i = 100; i <= 999; i++){
				if(newPal % i == 0 && (int)Math.log10(newPal/i) == 2){
					System.out.println(newPal);
					System.out.println(i + "\n" + (newPal/i));
					found = true;
					break;
				}
			}
			if(found) break;
		}
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
	}
	public static boolean isPalindrome(int num){
		int rightSide = num % 1000 / 100 + 10 * (num % 100 /10) + 100*(num%10);  
		return rightSide == num / 1000;
	}
}
