public class HelloWorld{
    static boolean[] digits;
    static int count;
    public static void main(String []args){
        long start = System.currentTimeMillis();
        int multiplicand;
        int sum = 0;
        for (int product = 1234;product <= 98765; product++) {
            for (int j = 2; j < 987; j++) {
                if (product % j == 0) {
                    multiplicand = product / j;
                    count = 0;
                    digits = new boolean[9];
                    if (sink(product) && sink(j) && sink(multiplicand) && count == 9) {
                        sum += product;
                        break;
                    } else {
                        continue;
                    }
                }
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("Sum = " + sum);
        System.out.println("Time elapsed: " + (stop - start) + " ms");
    }
    
    public static boolean sink(int num) {
        int theDigit;
        while (num > 0) {
            theDigit = num % 10;
            if (theDigit == 0) return false;
            if (!digits[theDigit-1]) {
                digits[theDigit-1] = true;
                count++;
            } else {
                return false;
            }
            num /= 10;
        }
        return true;
    }
}
