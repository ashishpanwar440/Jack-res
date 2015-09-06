import javax.xml.bind.ValidationEvent;

public class Euler31 {
    static int numOCases = 0;
    static int[] val = {200, 100, 50, 20, 10, 5, 2};
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        count(0, 200);
        long stop = System.currentTimeMillis();
        System.out.println(numOCases);
        System.out.println("Time elapsed: " + (stop - start) + "ms");
    }
    
    public static void count(int coin, int pencesLeft) {
        if (coin == 7) {
            numOCases++;
        } else {
            for (int i = 0; i <= pencesLeft; i += val[coin]) {
                count(coin + 1, pencesLeft - i);
            }
        }
    }
}
