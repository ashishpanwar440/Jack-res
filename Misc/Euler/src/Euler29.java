import java.math.BigInteger;
import java.util.*;

public class Euler29 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		int b = 100, a = 100;
		LinkedList<BigInteger> array = new LinkedList<BigInteger>();
		int k = 0;
		for(long i = 2;i<=a;i++){
			for(long j = 2;j<=b;j++){
				BigInteger t = BigInteger.valueOf(i);
				t = pow(t,j);
				array.add(t);
			}
		}
		System.out.println(array.size());
		array.sort(null);
		for (int i = 0; i < array.size()-1; i++) {
			if(array.get(i).equals(array.get(i+1))){
				array.remove(i);
				i--;
			}
		}
		array.sort(null);
		System.out.println(array.size());
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");

	}
	
	public static BigInteger pow(BigInteger base, long pow){
		BigInteger i = BigInteger.ONE;
		for (int j = 1; j <= pow; j++) {
			i = i.multiply(base);
		}
		return i;
	}
}
