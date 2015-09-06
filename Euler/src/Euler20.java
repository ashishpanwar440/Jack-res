import java.math.BigInteger;

public class Euler20 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		BigInteger theInt = BigInteger.ONE;
		for(int i = 2; i <= 100;i++){
			theInt = theInt.multiply(BigInteger.valueOf(i));
		}
		String result = theInt.toString();
		long sum = 0;
		for (int i = 0; i < result.length(); i++) {
			sum+=result.charAt(i) - '0';
		}
		System.out.println(theInt + "\n" + sum);
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
	}
}
