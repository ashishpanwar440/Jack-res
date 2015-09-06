public class Euler24_com {
	public static void main(String[] args) {
		long start = System.nanoTime();
		long answer = 0;
		int distance = 1000000-1;
		java.util.ArrayList<Integer> list = new java.util.ArrayList<Integer>();
		for(int i = 0; i <= 9;i++) list.add(i);
		for(int i = 0; i <= 9;i++){
			long digit = distance/factorial(9-i);
			answer = answer*10+list.get((int) digit);
			list.remove((int) digit);
			distance -= digit*factorial(9-i);
			if(distance==0){
				for (int j = 0; j < list.size(); j++) {
					answer = answer*10+list.get(j);
				}
				break;
			}
		}
		System.out.println(answer);
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
	}
	
	public static long factorial(int num){
		long result = 1;
		for(int i = 2; i <= num;i++) result *= i;
		return result;
	}
}
