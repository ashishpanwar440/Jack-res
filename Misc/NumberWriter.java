public class NumberWriter {
	public static String writeNumber(String number){
		String[] numbers = {"one","two","three","four","five"
				,"six","seven","eight","nine","ten"
				,"eleven","twelve","thirteen","fourteen","fifteen"
				,"sixteen","seventeen","eighteen","nineteen"
				,"twenty","thirty","forty","fifty","sixty"
				,"seventy","eighty","ninety"
				,"thousand","million","billion","trillion"
				,"quadrillion","quintillion","sextillion"
				,"septillion","octillion","nonillion"
				,"decillion","undecillion","duodecillion"
				,"tredecillion","quattuordecillion"
				,"quindecillion","sexdecillion","septendecillion"
				,"octodecillion","novemdecillion"
				,"vigintillion","unvigintillion","duovigintillion"
				,"trevigintillion","quattuorvigintillion"
				,"quinvigintillion","sexvigintillion"
				,"septenvigintillion","octovigintillion"
				,"novemvigintillion","trigintillion"
				,"untrigintillion","duotrigintillion"
				,"tretrigintillion","quattuortrigintillion"
				,"quintrigintillion","sextrigintillion"
				,"septentrigintillion","octotrigintillion"
				,"novemtrigintillion","quadragintillion"
				,"unquadragintillion","duoquadragintillion"
				,"trequadragintillion","quattuorquadragintillion"
				,"quinquadragintillion","sexquadragintillion"
				,"septenquadragintillion","octoquadragintillion"
				,"novemquadragintillion","quinquagintillion"
				,"unquinquagintillion","duoquinquagintillion"
				,"trequinquagintillion","quattuorquinquagintillion"
				,"quinquinquagintillion","sexquinquagintillion"
				,"septenquinquagintillion","octoquinquagintillion"
				,"novemquinquagintillion","sexagintillion"
				,"unsexagintillion","duosexagintillion"
				,"tresexagintillion","quattuorsexagintillion"
				,"quinsexagintillion","sexsexagintillion"
				,"septensexagintillion","octosexagintillion"
				,"novemsexagintillion","septuagintillion"
				,"unseptuagintillion","duoseptuagintillion"
				,"treseptuagintillion","quattuorseptuagintillion"
				,"quinseptuagintillion","sexseptuagintillion"
				,"septenseptuagintillion","octoseptuagintillion"
				,"novemseptuagintillion","octogintillion"
				,"unoctogintillion","duooctogintillion"
				,"treoctogintillion","quattuoroctogintillion"
				,"quinoctogintillion","sexoctogintillion"
				,"septoctogintillion","octooctogintillion"
				,"novemoctogintillion","nonagintillion"
				,"unnonagintillion","duononagintillion"
				,"trenonagintillion","quattuornonagintillion"
				,"quinnonagintillion","sexnonagintillion"
				,"septennonagintillion","octononagintillion"
				,"novemnonagintillion","centillion"};
		int numOfBlocks = (number.toString().length()-1)/3+1;
		int[] numBlock = new int[numOfBlocks];
		for (int i = 0; i < numBlock.length; i++) {
			if(number.toString().length()>=3)
				numBlock[i] = Integer.parseInt(number.toString().substring(number.toString().length()-3));
			else
				numBlock[i] = Integer.parseInt(number.toString());
			if(number.toString().length()>3)
				number = number.substring(0, number.toString().length()-3);
		}
		String builder = "";
		for (int i = numBlock.length-1; i >= 0; i--) {
			if(numBlock[i] > 0){
				if(i != numBlock.length-1) builder += ", ";
				if(numBlock[i] < 20) builder += numbers[numBlock[i]-1];
				else{
					int d1 = numBlock[i] % 10;
					int d2 = numBlock[i] % 100 / 10;
					int d3 = numBlock[i] / 100;
					if(numBlock[i]%100!=0 ){
						if(d3>0)builder += numbers[d3-1] + " hundred and ";
						if(numBlock[i]%100<=20) builder += numbers[numBlock[i]%100-1];
						else{
							builder += numbers[d2+17];
							if(d1>0) builder += "-"+numbers[d1-1];
						}
					} else if(d3>0)builder += numbers[d3-1] + " hundred ";
				}
				if(i>0){
					int cutOff = numbers.length-27;
					builder += " " + numbers[i%cutOff+26] + " ";
					for(int j = 1; j <= i/cutOff;j++)
						builder += " " + numbers[26+cutOff] + " ";
				}
			}
		}
		builder = builder.replaceAll("\\s+", " ");
		builder = builder.replaceAll(" ,", ",");
		return builder;
	}
}
