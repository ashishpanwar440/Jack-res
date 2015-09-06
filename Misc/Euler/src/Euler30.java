public class Euler30 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int copy;
        int sumEach;
        int sum = 0;
        for (int i = 2; i <= 354294; i++) {
            copy = i;
            sumEach = 0;
            while (copy > 0) {
                int d = copy % 10;
                d = d*d*d*d*d;
                sumEach += d;
                copy /= 10;
            }
            if (sumEach == i) {
                sum += i;
//                System.out.println(sumEach);
            }
        }
        long stop = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println("Time elapsed: " + (stop - start) + "ms");
    }
}
