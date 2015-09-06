public class Euler17 {
	public static void main(String[] args) {
		long start = System.nanoTime();
		int sumLength = 0;
		for(int i = 1; i<=1000;i++){
			String write = writeNumber(i).replaceAll("\\s|,|-", "");
			System.out.println(writeNumber(i));
			sumLength += write.length();
		}
		System.out.println(sumLength);
		long stop = System.nanoTime();
		long elapse = stop - start;
		System.out.println(elapse + " nanoseconds");

	}

	public static String writeNumber(int number){
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
		int numOfBlocks = ((int)Math.log10(number))/3+1;
		int[] numBlock = new int[numOfBlocks];
		for (int i = 0; i < numBlock.length; i++) {
			numBlock[i] = number%1000;
			number /= 1000;
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
				if(i>0) builder += " " + numbers[i+26] + " ";
			}
		}
		builder = builder.replaceAll("\\s+", " ");
		builder = builder.replaceAll(" ,", ",");
		return builder;
	}
}
