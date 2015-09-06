
public class Euler9 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		boolean found = false;
		for(int b = 1; b <= 1000;b++){
			for(int a = 1; a < b;a++){
				int c = (int)Math.sqrt(a*a+b*b);
				if(a + b + c == 1000 && Math.sqrt(a*a+b*b) - c == 0){
					System.out.println("a = " + a + ", b = " + b + ", c = " + c);
					System.out.println("Product = " + (a*b*c));
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
}
