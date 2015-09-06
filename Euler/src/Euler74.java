import java.util.Scanner;
import java.util.Vector;

public class Euler74 {
	static int d = 0;
	static int[] cache = new int[1000000];
	
	public static void main(String[] args) {
		cache[169] = 3;
		cache[363601] = 3;
		cache[1454] = 3;
		cache[871] = 2;
		cache[45361] = 2;
		cache[872] = 2;
		cache[45362] = 2;
		Scanner scan = new Scanner(System.in);
		int testCase = Integer.parseInt(scan.nextLine());
		for(int t = 1; t <= testCase; t++){
			String input = scan.nextLine();
			long n = Long.parseLong(input.substring(0,input.indexOf(' ')));
			int l = Integer.parseInt(input.substring(input.indexOf(' ')+1));
			boolean found = false;
			for(int i = 0;i <= n;i++){
				d=-1;
				if(chain(i,l, new Vector())) {
					if(found)System.out.print(" ");
					System.out.print(i);
					found = true;
				}
			}
			if(!found) System.out.println(-1);
			else System.out.println();
			
		}
	}

	public static boolean chain(int n, int length, Vector<Integer> numList){
		if(d++ > length) return false;

		if(n < 1000000 && cache[n] != 0){
			if(d+cache[n] == length) return true;
			else return false;
		}
		if(numList.size()>0){
			for (int i = 0; i < numList.size();i++) {
				if(numList.get(i) == n){
					Integer[] builder = new Integer[2];
					if(d == length) return true;
					else return false;
				}
			}
			numList.add(n);
		}else numList.add(n);
		int newNum = 0;
		if(n>0){
			while(n>0){
				int digit = 1;
				if(n % 10 > 1){
					for(int i = 1;i <= n % 10;i++){
						digit *= i;
					}
				} else digit = 1;
				newNum += digit;
				n /= 10;
			}
			return chain(newNum, length, numList);
		} else return chain(1, length, numList);
	}
}
