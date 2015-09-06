import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Euler23_generator {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		int abundant[] = new int[28123];
		boolean abun[] = new boolean[28123];
		int j = 0;
		for(int i = 12; i <= 28123;i++){
			if(!abun[i-1]){
				int sum = 0;
				for(int k = 1; k <= i / 2; k++){
					if(i%k==0) sum+=k;
				}
				if(sum>i){
					for(int l = i;l <= 28123;l+=i){
						if(!abun[l-1]){
							abundant[j++] = l;
							abun[l-1] = true;
						}
					}
				}
			}
		}
		abundant = Arrays.copyOf(abundant, j);
		Arrays.sort(abundant);
		PrintWriter abu = new PrintWriter("Euler23.txt", "UTF-8");
		for (int i = 0; i < abundant.length; i++) {
			abu.print(abundant[i]+", ");
		}
		abu.close();
	}
}
