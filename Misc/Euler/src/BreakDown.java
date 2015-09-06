
public class BreakDown {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long j = Long.parseLong(args[0]);
		boolean prime[] = new boolean[100000];
		int d = 0;
		for(long i = 2; i < prime.length; i++){
			if(!prime[(int)i-1]){
				while(j % i == 0) {
					j /= i;
					d++;
				}
				System.out.println(i + ": " + d);
				if(j<i) break;
				d=0;
				for(long k = i*i; k < prime.length; k+=i){
					prime[(int)k-1] = true;
				}
			} 
		}
	}
}
