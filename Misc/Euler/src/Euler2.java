import java.util.Scanner;

public class Euler2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testCase = Integer.parseInt(scan.nextLine());
		for(int t = 1; t <= testCase; t++){
            long n = Long.parseLong(scan.nextLine());
            long sum = 0;
            long previous = 1;
            long current = 1;
            while(current <= n){
                if(current % 2 == 0) sum += current;
                long temp = current;
                current += previous;
                previous = temp;
            }
            System.out.println(sum);
        }
		scan.close();
	}
}
