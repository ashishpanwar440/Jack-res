public class Date{
	int date, month, year, day;
	public Date(int da, int mo, int ye, int d){
		date = da;
		month = mo;
		year = ye;
		day = d;
	}

	public void nextDay(){
		day = (day + 1) % 7;
		if(++date > daysOfMonth(month,year)){
			date = 1;
			month++;
		}
		if(month > 12){
			month = 1;
			year++;
		}
	}

	private int daysOfMonth(int mo, int ye){
		switch(mo){
		case 9: case 4: case 6: case 11:
			return 30;
		case 2:
			if(ye % 4 == 0 && ye % 100 != 0
			|| ye % 400 ==0) return 29;
			else return 28;
		default:
			return 31;
		}
	}
	
	public String toString(){
		return month + "/" + date + "/" + year + " " + day; 
	}
}