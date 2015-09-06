import java.util.*;

public class Euler3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCase = Integer.parseInt(scan.nextLine());
		for(int t = 1; t <= testCase; t++){
			long j = Long.parseLong(scan.nextLine());
			boolean prime[] = new boolean[(int)(Math.sqrt(j))];
			boolean found = false;
			for(long i = 2; i <= prime.length; i++){
				if(!prime[(int)i-1]){
					while(j % i == 0) {
						j /= i;
					}
					if(j<i){
						System.out.println(i);
						found = true;
						break;
					}
					for(long k = i*i; k <= prime.length; k+=i){
						prime[(int)k-1] = true;
					}
				} 
			}
			if(!found) System.out.println(j);
		}
		scan.close();
	}
}
