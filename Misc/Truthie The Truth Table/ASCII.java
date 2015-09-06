import java.util.Scanner;

public class ASCII {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Can i haz sum input plz.");
            String inTheFlippingPut = scan.nextLine();
            System.out.println("Would you flipping kindly tell the type of parity check? It's for the sake of your homework man.");
            int parityOri = scan.nextLine().equalsIgnoreCase("even") ? 0 : 1;
            for (int i = 0; i < inTheFlippingPut.length(); i++) {
                int parity = parityOri;
                int theActualValue = inTheFlippingPut.charAt(i);
                int copy = theActualValue;
                for (int j = 1; j <= 7; j++) {
                    parity = parity ^ (copy & 1);
                    copy >>= 1;
                }
                theActualValue |= parity << 7;
                String unpadded = Integer.toBinaryString(theActualValue);
                String padded = "00000000".substring(unpadded.length()) + unpadded;
                System.out.println(inTheFlippingPut.charAt(i) + " : "
                        + (int) inTheFlippingPut.charAt(i) + " - "
                        + padded);
            }
        }
    }
}
