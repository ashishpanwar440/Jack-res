import java.util.*;
public class LookAndSay {

	public static void main(String[] args) {
		String current = "11";
		System.out.println(current);
		String output = "";
		Scanner scan = new Scanner(System.in);
		do{
			output = "";
			char t = current.charAt(0);		
			int count = 1;
			for (int i = 1; i < current.length(); i++) {
				if(current.charAt(i) == t) count++;
				if(current.charAt(i) != t){
					output += count + "" + t;
					count = 1;
					t = current.charAt(i);
				}
				if(i == current.length()-1){

					t = current.charAt(i);
					output += count + "" + t;
				}
			}
			current = output;
			System.out.print(output);
		} while(!scan.nextLine().equals("q"));
	}

}
