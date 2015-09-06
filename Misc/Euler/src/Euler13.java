import java.io.*;

public class Euler13 {
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("Euler13.txt"));
		String theString = "", line;
		while((line = read.readLine()) != null) theString += line + '\n';
		long start = System.nanoTime();
		String[] nums = theString.split("\n");
		String out = nums[0];
		for (int i = 1; i < nums.length; i++) {
			out = add(out,nums[i]);
		}
		System.out.println(out.substring(0,10));
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
		read.close();
	}

	public static String add(String a, String b){
		String result = new String(a);
		for(int i = 0;i < b.length();i++){
			result = increment(result,b.charAt(b.length()-1-i)-'0',result.length()-1-i);
		}
		return result;
	}

	public static String increment(String a, int b, int position){
		int c = a.charAt(position) - '0' + b;
		if(c <= 9){
			return a.substring(0,position) + (c%10) + a.substring(position+1);
		}else{
			if(position - 1 < 0)
				return "1" + (c%10) + a.substring(position+1);
			else{
				String rep = a.substring(0,position) + (c%10) + a.substring(position+1);
				return increment(rep,1,position - 1);
			}
		}
	}
}
