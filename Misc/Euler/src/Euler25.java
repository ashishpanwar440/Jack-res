import java.math.BigInteger;

public class Euler25 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		BigInteger previous = BigInteger.ONE;
		BigInteger current = BigInteger.ONE;
		int index = 2;
		while(current.toString().length()<1000){
			BigInteger temp = current.add(BigInteger.ZERO);
			current = current.add(previous);
			previous = temp.add(BigInteger.ZERO);
			index++;
		}
		System.out.println(index);
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");

	}
}
