import java.util.Scanner;

public class Euler6 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCase = Integer.parseInt(scan.nextLine());
		for(int t = 1; t <= testCase; t++){
			long n = Long.parseLong(scan.nextLine());
            long result = (n*(n+1)/2)*(n*(n+1)/2) - n*(n+1)*(2*n+1)/6;
            System.out.println(result);
        }
		scan.close();
	}
}
