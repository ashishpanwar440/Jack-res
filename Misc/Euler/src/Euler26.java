import java.util.ArrayList;
public class Euler26 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		int max = 0;
		int maxDiv = 0;
		for (int i = 1; i < 1000; i++) {
			int a = divide(i);
			if(a>max){
				max = a;
				maxDiv = i;
			}
		}
		System.out.println(maxDiv + " " + max);
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
	}
	
	public static int divide(int d){
		ArrayList<Integer> remainders = new ArrayList<Integer>();
		int currentDividend = 1;
		int cycle = 0;
		while(true){
			int zer = 0;
			cycle++;
			boolean recur = false;
			while(currentDividend<d){
				currentDividend*=10;
				if(!remainders.contains(currentDividend))
					remainders.add(currentDividend);
				else{
					cycle-=remainders.indexOf(currentDividend)+1;
					recur = true;
					break;
				}
				if(zer++>0){
					cycle++;
				}
			}
			if(recur) break;
			int digit = currentDividend/d;
			currentDividend-=digit*d;
			if(currentDividend==0) break;
		}
		return cycle;
	}
}
