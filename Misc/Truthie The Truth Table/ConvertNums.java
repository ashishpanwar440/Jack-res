import java.util.Scanner;

public class ConvertNums {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Input base is: ");
            int inputBase = Integer.parseInt(scan.nextLine());
            System.out.print("What the input be? ");
            String input = scan.nextLine().toUpperCase().replaceAll("\\.+", ".");
            String inputDec = "";
            boolean hasDecimal = input.contains(".");
            if (hasDecimal) {
                while (input.endsWith("0")) {
                    input = input.substring(0, input.length() - 1);
                }
                inputDec = input.substring(input.indexOf('.') + 1);
                input = input.substring(0, input.indexOf('.'));
            }
            long intermediate = 0;
            double intermediateDec = 0;
            long j = 0;
            for (long i = input.length() - 1; i >= 0; i--) {
                long digit = input.charAt((int) i) - '0';
                if (digit < 0 || digit > 9){
                    digit = input.charAt((int) i) - 'A' + 10;
                }
                intermediate += Math.pow(inputBase, j++) * digit;
            }
            if (hasDecimal)
                j = -1;
                for (long i = 0; i < inputDec.length(); i++) {
                    long digit = inputDec.charAt((int) i) - '0';
                    if (digit < 0 || digit > 9){
                        digit = inputDec.charAt((int) i) - 'A' + 10;
                    }
                    intermediateDec += Math.pow(inputBase, j--) * digit;
                }

            System.out.print("Output base is: ");
            int outputBase = Integer.parseInt(scan.nextLine());

            String output = "";
            while (intermediate > 0) {
                char theChar;
                if (intermediate % outputBase < 10) {
                    theChar = (char) ('0' + intermediate % outputBase);
                } else {
                    theChar = (char) (intermediate % outputBase - 10 + 'A');
                }
                output = theChar + output;
                System.out.println(intermediate + " / " + outputBase + " = " 
                        + (intermediate / outputBase) + " + " + (intermediate % outputBase) 
                        + " / " + outputBase);
                intermediate /= outputBase;
            }
            if (hasDecimal) {
                System.out.println("Dot time!!!");
                output += ".";
                for (int i = 1; i <= 10; i++) {
                    System.out.print(trim(intermediateDec) + " * " + outputBase + " = ");
                    intermediateDec *= outputBase;
                    System.out.println((int) intermediateDec + " + " + trim(intermediateDec % 1));
                    char theChar;
                    if ((int) intermediateDec < 10) {
                        theChar = (char) ('0' + (int) intermediateDec);
                    } else {
                        theChar = (char) ((int) intermediateDec - 10 + 'A');
                    }
                    output += theChar;
                    intermediateDec %= 1;
                    if (trim(intermediateDec).equals(".0")) break;
                }
            }
            System.out.println("Bleh! " + output);
        }
    }
    
    public static String trim(double daDec) {
        String theDec = "" + daDec;
        if (theDec.length() > 12) {
            theDec = theDec.substring(0, 12);
            while (theDec.endsWith("0")) {
                theDec = theDec.substring(0, theDec.length() - 1);
            }
        }
        return theDec.substring(theDec.indexOf('.'));
    }
}
