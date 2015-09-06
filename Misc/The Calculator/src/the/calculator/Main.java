/* Name: Chinh Le (Jack)
 * ID: 001983458
 * Class: 4380 - MW, 1:00 PM
 * Assignment: Assignment 2 - Calculator
 * Date: 9/3/2015
 * Description:
 *      Create a Java Swing Application -The Calculator
 *      Add a 10-key
 *      Add 1 textbox
 *      Add 4 buttons for 4 different opperations
 *      Add an results button
 *
 * Logic flow:
 *      The program consists of a current number register, an argument register,
 *      and an accumulator register. The current register has the number being
 *      input in, the accumulator accumulates the results through each operation,
 *      the argument is fed the current number when every operation happens.
 *      When an operator button is pressed, the program will do the previous
 *      calculation then change the operation code. The result button will set
 *      the operation code to a non executable one.
 *      At first the accumulator is 0, the operation code is 0 which is a plus.
 *      When user type in the first number and press an operator button, the
 *      current number will be parsed into the argument and added into the
 *      accumulator. This effectively copy the content of the current number
 *      into the accumulator. All the operations thereafter base on the same
 *      principle.
 */
package the.calculator;
public class Main {
    public static void main(String[] args) {
        TheCalcFrame theCalc = new TheCalcFrame(); // Initialize the frame.
        theCalc.setLocationRelativeTo(null); // Move it to the middle.
        theCalc.show(); // Show it out.
    }
}
