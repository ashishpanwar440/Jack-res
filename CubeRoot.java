/* My explanation for this:
 *      If A is a cuberoot of B then B = A*A*A
 *      And A = B / (A*A)
 *      A is the number we need to find
 *      What the code does is that it uses a kind of binary search to find the correct A for the equation
 *      Say we have an arbitrary C.
 *      We plug C into the equation, we have C = B / (C*C)
 *      If the equation is not right then move C to the middle of C and B / (C*C) and check again.
 *      As a result the 2 sides will get closer and closer together and eventually equal each other.
 *      That is when A is found.
 */
public class CubeRoot {
    public static double cbrt(int number){
        double t; //termporary variable
        double cuberoot = (double)number/2; //go to the middle of the number
        int times = 0;
        do{
            t = cuberoot; // make the temp = square root
            cuberoot = (t + (number / (t*t))) / 2; // move square root to the middle of the itself and number / t^2
        }while ((t - cuberoot) != 0 && times++ < 100); // do it until there isn't a distance in the two anymore or the loop has done over 100 times
        return cuberoot;
    }

    public static void main(String[] args) {
        int n = 512;
        System.out.println("Math: " + Math.cbrt(n));
        System.out.println("Mine: " + cbrt(n));
    }
}