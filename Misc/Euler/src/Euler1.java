import java.util.Scanner;

public class Euler1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCase = Integer.parseInt(scan.nextLine());
		for(int t = 1; t <= testCase; t++){
			long n = Long.parseLong(scan.nextLine());
			n--;
			long sum = 3*((n/3)*((n/3)+1)/2)+5*((n/5)*((n/5)+1)/2)-15*((n/15)*((n/15)+1)/2);
			System.out.println(sum);
		}
		scan.close();
	}
}
