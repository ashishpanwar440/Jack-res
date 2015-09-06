import java.io.*;
import java.util.*;

public class Euler72 {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int testCase = Integer.parseInt(scan.nextLine());
        for(int t = 1; t <= testCase; t++){
            long n = Long.parseLong(scan.nextLine());
            long count = 0;
            boolean[] prime = new boolean[(int)n];
            int[] primeList = new int[(int)n];
            int primei = 0;
            for(long i = 2;i <= n;i++){
                if(!prime[(int) i-1]){
					primeList[primei++] = (int)i;
    				for(long j = i*i; j <= n; j +=i){
    					prime[(int)j-1] = true;
    				}
    			}
            }
            
            count = (n-1)*n/2;
            for(int i = 2;i <= n; i++){
            	if(prime[i-1]){
//                	System.out.println("Current i:"+i+" "+count);
            		int m = i;
            		primei = 0;
            		while(primeList[primei]!= 0 && m >=primeList[primei]){
            			if(m % primeList[primei] != 0)
            				primei++;
            			else{
//            				System.out.print(m+ " / "+primeList[primei]);
            				m /= primeList[primei];
            				count -= (i-1)/primeList[primei];
//            				System.out.println(" " + count);
            				primei++;
            			}
            		}
//            		System.out.println("Done with " + i);
            	}
//            	System.out.println("Current i:"+i+" "+count);
            }
            System.out.println(count);
        }
    }
}