import java.util.Scanner;
import java.util.HashSet;
import java.util.Stack;
public class TTTT {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.print("Expression plz: ");
            String theEx = scan.nextLine().toUpperCase()
                    .replaceAll("\\[", "(").replaceAll("\\]", ")")
                    .replaceAll("`", "'").replaceAll(" ", "").replaceAll("⊕", "-");
            System.out.println("Reformat: " + theEx);

            String vars = theEx.replaceAll("\\W", "");
            HashSet<Character> variableSet = new HashSet<Character>();
            for (int i = 0; i < vars.length(); i++) {
                variableSet.add(vars.charAt(i));
            }
            Variabul[] theVars = new Variabul[variableSet.size()];            
            int j = 0;
            for (char i : variableSet){
                theVars[j++] = new Variabul(i, 0);
            }
            System.out.println("Variables: " + variableSet.toString()
                    + " \nChange order?(leave blank if no)");
            String order = scan.nextLine().toUpperCase().replaceAll("\\W", "");
            if (order.length() > 0){
                if (order.length() == theVars.length){
                    HashSet<Character> compareSet = new HashSet<Character>();
                    for (int i = 0; i < theVars.length; i++) {
                        compareSet.add(order.charAt(i));
                    }
                    if (compareSet.equals(variableSet)){
                        for (int i = 0; i < theVars.length; i++) {
                            theVars[i].name = order.charAt(i);
                        }
                    } else {
                        System.out.print("Sum'n ain't right. You got some spare "
                                + "variables or some duplicates in there.");
                        scan.nextLine();
                        System.out.print("I'm gonna have to stick to the "
                                + "aphabetical order :'(");
                        scan.nextLine();
                    }
                } else {
                    System.out.print("Sum'n ain't right. You don't "
                            + "got enough or more than enough variables.");
                    scan.nextLine();
                    System.out.print("I'm gonna have to stick to the "
                            + "aphabetical order :'(");
                    scan.nextLine();
                }
            }
            String outputString = calc(theVars, theEx);
            if (theVars.length >= 2 && theVars.length <= 6){
                String varsForK = "";
                for (int i = 0; i < theVars.length; i++) {
                    varsForK += theVars[i].name;
                }
                System.out.println("And here's the K map for it. Thank me later.");
                int[][][] theMap = ToKMap.stringToMap(outputString, vars);
                ToKMap.printK(theMap, varsForK);
                System.out.print("And for the next trick I shall simplify....(press ENTER)");
                scan.nextLine();
                System.out.print(".");
                scan.nextLine();
                System.out.print(".");
                scan.nextLine();
                System.out.println("Poof!");
                System.out.println(Grouperino.simplify(theMap, varsForK));
            }
            System.out.print("Again?(y fo shizzle or n fo nay) ");
            if (scan.nextLine().equals("n")) break;
        }
        scan.close();
    }

    public static String calc(Variabul[] var, String ex){
        String newEx = "";
        String postFix = "";
        String outputString = "";

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
        System.out.println("Dots added: " + newEx);

        boolean carryOn = true;
        try { // Convert to postfix.
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
                    carryOn = false;
                    break;
                }
            }
        } catch (Exception exxy) {
            System.out.println("Syntax error!");
            carryOn = false;
        }
        //        System.out.println(postFix);

        // Calculate
        if (carryOn){
            try {
                for (int i = 0; i < var.length; i++) {
                    System.out.print("|" + var[i].name);
                }
                System.out.println("| F |");

                int numOfCases = ~((~0) << var.length);
                for (int i = 0; i <= numOfCases; i++){
                    String calcEx = postFix;
                    Stack<Integer> operands = new Stack<Integer>();
                    for (int j = 0; j < var.length; j++) {
                        var[j].value = i >> (var.length-1-j) & 1;
                    calcEx = calcEx.replaceAll(String.valueOf(var[j].name), 
                            String.valueOf(var[j].value));
                    System.out.print("|" + var[j].value);
                    }
                    //                System.out.println("\n" + calcEx);
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
                    int finalOutput = operands.pop();
                    outputString += finalOutput;
                    System.out.println("| " + finalOutput + " |");
                }
            } catch (Exception exxy) {
                System.out.println("Sum'in gone wrong when calculating man.");
            }
        }
        return outputString;
    }

    public static int precedence(char op){
        if (op == '+') return 1;
        if (op == '.') return 2;
        if (op == '-') return 3;
        if (op == '(' || op == ')') return 4;
        return 0;
    }
}