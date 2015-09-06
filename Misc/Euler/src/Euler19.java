
public class Euler19 {
	public static void main(String[] args) {
		Date theDate = new Date(1,1,1900,0);
		int sundayCount = 0;
		while(theDate.year < 2000 || theDate.month<12 || theDate.date<31){
			System.out.println(theDate);
			theDate.nextDay();
			if(theDate.day == 6 && theDate.year>=1901 && theDate.date == 1) sundayCount++;
		}
		System.out.println(sundayCount);
	}
}
