import java.math.BigInteger;
public class Euler16_BigInt {
	public static void main(String[] args) {
		long start = System.nanoTime();
		BigInteger product = BigInteger.ONE; 
		for(int i = 1; i<=1000;i++) {
			product = product.multiply(BigInteger.valueOf(2));
		}
		String a = product.toString();
		long sum = 0;
		for (int i = 0; i < a.length(); i++) {
			sum += a.charAt(i) - '0';
		}
		System.out.println(sum);
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");

	}
}
