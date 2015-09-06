public class Euler16 {

	public static void main(String[] args) {
		long start = System.nanoTime();
		String product = "1"; 
		for(int i = 1; i<=1000;i++) {
			product = add(product,product);
		}
		String a = product.toString();
		long sum = 0;
		for (int i = 0; i < a.length(); i++) {
			sum += a.charAt(i) - '0';
		}
		System.out.println(sum);
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");
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
