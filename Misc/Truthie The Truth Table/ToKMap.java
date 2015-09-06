import java.util.Scanner;

public class ToKMap {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println("Eh yo what do the variables look like?");
            String vars = scan.nextLine().toUpperCase();
            if (vars.length() >= 2 && vars.length() <= 6) {
                int numOfCases = ~(~0 << vars.length());
                System.out.println("Input the values eh");
                String outputString = "";
                do {
                    outputString = scan.nextLine();
                    if (outputString.length() != numOfCases + 1) {
                        System.out.println("Case number mismatch. Do it again!");
                    }
                } while (outputString.length() != numOfCases + 1);
                int[][][] theMap = stringToMap(outputString, vars);
                printK(theMap, vars);
                System.out.print("And for the next trick I shall simplify....");
                scan.nextLine();
                System.out.print(".");
                scan.nextLine();
                System.out.print(".");
                scan.nextLine();
                System.out.println("Poof!");
                System.out.println(Grouperino.simplify(theMap, vars));
            }
            System.out.print("Again?(y fo shizzle or n fo nay) ");
            if (scan.nextLine().equals("n")) break;
        }
        scan.close();
    }

    public static int[][][] stringToMap (String outputString, String vars) {
        int[] output = new int[outputString.length()];
        for (int i = 0; i < output.length; i++) {
            int current = outputString.charAt(i) - '0';
            if (current >= 0 && current <= 1){
                output[i] = current;
            } else {
                output[i] = 3;
            }
        }
        int z = outputString.length() / 16;
        if (z == 0) z = 1;
        int y = outputString.length() / z / 4;
        if (y == 0) y = 1;
        int x = outputString.length() / z / y;
        int[][][] theMap = new int[z][y][x];
        int index = 0;
        for (int i = 0; i < theMap.length; i++) {
            for (int j = 0; j < theMap[0].length; j++) {
                for (int k = 0; k < theMap[0][0].length; k++) {
                    theMap[i][j][k] = output[index];
                    index = nextIndex(index, 0);
                }
            }
        }
        return theMap;
    }

    public static void printK(int[][][] theMap, String vars){
        int index = 0;
        for (int i = 0; i < theMap.length; i++) {
            System.out.println();
            if (vars.length() == 2){
                System.out.println("You seriously need a K map for that?");
                System.out.println(vars.substring(vars.length()-2));
            } else if (vars.length() >= 3 && vars.length() <= 4){
                System.out.println("  \\ " + vars.substring(vars.length() - 2));
                System.out.println(vars.substring(0, vars.length()-2));
            } else {
                String unpadded = Integer.toBinaryString(index >> 4);
                String toPad = "00".substring(6 - vars.length());
                String padded = toPad.substring(unpadded.length()) + unpadded;
                System.out.println(vars.substring(0, vars.length() - 4) + " = " + padded + "\n");
                System.out.println("  \\ " + vars.substring(vars.length() - 2));
                System.out.println(vars.substring(vars.length() - 4, vars.length() - 2));
            }
            System.out.print("      ");
            for (int l = 0; l != -1; l = next2Bits(l)) {
                String unpadded = Integer.toBinaryString(l);
                String padded = "00".substring(unpadded.length()) + unpadded;
                System.out.print(padded + " ");
            }
            System.out.println();
            for (int j = 0; j < theMap[0].length; j++) {
                System.out.print("   ");
                String unpadded = Integer.toBinaryString(index >> 2 & 3);
                String padded = "00".substring(unpadded.length()) + unpadded;
                System.out.print(padded + "  ");
                for (int k = 0; k < theMap[0][0].length; k++) {
                    System.out.print((theMap[i][j][k] < 3 ? theMap[i][j][k] : "X") + "  ");
                    index = nextIndex(index, 0);
                }
                System.out.println();
            }
        }
    }

    public static int next2Bits (int index){
        switch (index) {
        case 0:
            return 1;
        case 1:
            return 3;
        case 3:
            return 2;
        default:
            return -1;
        }
    }

    public static int nextIndex (int index, int position) {
        int left = index >> position;
                    int processing = left & 3;
                    left = left >> 2 << 2;
                int right = ~(~0 << position) & index;
                if (processing == 2){
                    left <<= position;
                    return nextIndex(left + right, position + 2);
                }
                processing = next2Bits(processing);
                left += processing;
                left <<= position;
                position = 0;
                return left + right;
    }
}
