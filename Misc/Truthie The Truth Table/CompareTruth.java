import java.util.Scanner;
import java.util.HashSet;
import java.util.Stack;

public class CompareTruth {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Expression 1 plz: ");
        String ex1 = scan.nextLine();
        while (true){
            System.out.print("Expression 2 plz: ");
            String ex2 = scan.nextLine();
            System.out.println("Comparing to " + ex1);
            if (test(ex1, ex2)){
                System.out.println("Yup they're equivalent.");
            } else {
                System.out.println("Tough luck! They ain't the same.");
            }
        }
    }

    public static boolean test(String ex1, String ex2){
        String theEx1 = toPostFix(ex1.toUpperCase()
                .replaceAll("\\[", "(").replaceAll("\\]", ")")
                .replaceAll("`", "'").replaceAll(" ", "").replaceAll("⊕", "-"));
        String theEx2 = toPostFix(ex2.toUpperCase()
                .replaceAll("\\[", "(").replaceAll("\\]", ")")
                .replaceAll("`", "'").replaceAll(" ", "").replaceAll("⊕", "-"));

        String vars1 = theEx1.replaceAll("\\W", "");
        String vars2 = theEx2.replaceAll("\\W", "");
        HashSet<Character> variableSet = new HashSet<Character>();
        for (int i = 0; i < vars1.length(); i++) {
            variableSet.add(vars1.charAt(i));
        }
        for (int i = 0; i < vars2.length(); i++) {
            variableSet.add(vars2.charAt(i));
        }

        Variabul[] theVars = new Variabul[variableSet.size()];
        int j = 0;
        for (char i : variableSet){
            theVars[j++] = new Variabul(i, 0);
        }

        int numOfCases = ~((~0) << theVars.length);
        for (int i = 0; i <= numOfCases; i++){
            for (int k = 0; k < theVars.length; k++) {
                theVars[k].value = i >> (theVars.length-1-k) & 1;
            }
            if (calc(theVars, theEx1) != calc(theVars, theEx2)){
                System.out.print("Nope at:");                
                for (int k = 0; k < theVars.length; k++) {
                    System.out.print("   " + theVars[k].name + " = " + theVars[k].value);
                }
                System.out.println();
                System.out.println("Expression 1 spits out: " + calc(theVars, theEx1));
                System.out.println("Expression 2 spits out: " + calc(theVars, theEx2));
                return false;
            }
        }
        return true;
    }

    public static String toPostFix(String ex){
        String newEx = "";
        String postFix = "";

        // Add the dots
        boolean justVar = false;
        for (int i = 0; i < ex.length(); i++) {
            char current = ex.charAt(i);
            if (current != '\''){
                if (justVar && (precedence(current) == 0 || current == '('))
                    newEx += ".";
                justVar = (precedence(current) == 0 || current == ')');
            }
            newEx += current;
        }
        //        System.out.println(newEx);

        Stack<Character> operators = new Stack<Character>();
        for (int i = 0; i < newEx.length(); i++) {
            char current = newEx.charAt(i);
            if (precedence(current) == 0){
                postFix += current;
            } else {
                if (precedence(current) < 4){
                    if (!operators.isEmpty() && precedence(operators.peek()) >= precedence(current)){
                        while (!operators.isEmpty() && operators.peek() != '(' &&
                                precedence(operators.peek()) >= precedence(current)){
                            postFix += operators.pop();
                        }
                        operators.push(current);
                    } else {
                        operators.push(current);
                    }
                } else {
                    if (current == '(') {
                        operators.push(current);
                    } else if (current == ')'){
                        while (operators.peek() != '(')
                            postFix += operators.pop();
                        operators.pop();
                    }
                }
            }
        }
        while (!operators.isEmpty()){
            if (operators.peek() != '('){
                postFix += operators.pop();
            } else {
                System.out.println("Syntax error!");
                break;
            }
        }
        return postFix;
    }

    public static int calc(Variabul[] var, String ex){

        // Calculate
        String calcEx = ex;
        Stack<Integer> operands = new Stack<Integer>();
        for (int j = 0; j < var.length; j++) {
            calcEx = calcEx.replaceAll(String.valueOf(var[j].name), 
                    String.valueOf(var[j].value));
        }
        for (int j = 0; j < calcEx.length(); j++) {
            char current = calcEx.charAt(j);
            if (precedence(current) == 0 && current != '\''){
                operands.push(current-'0');
            } else {
                int arg = operands.pop();
                switch (current){
                case '.':
                    operands.push(arg & operands.pop());
                    break;
                case '+':
                    operands.push(arg | operands.pop());
                    break;
                case '-':
                    operands.push(arg ^ operands.pop());
                    break;
                case '\'':
                    operands.push(arg ^ 1);
                    break;
                }
            }
        }
        return operands.pop();
    }

    public static int precedence(char op){
        if (op == '+') return 1;
        if (op == '.') return 2;
        if (op == '-') return 3;
        if (op == '(' || op == ')') return 4;
        return 0;
    }
}
