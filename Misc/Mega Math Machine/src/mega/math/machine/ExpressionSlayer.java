package mega.math.machine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class ExpressionSlayer {
    //Fo da expresshun:
    static String[] FUNCTIONS = {"abs", "sqrt", "cbrt", "asin", "sin", "acos", "cos",
        "atan", "tan", "ln", "log", "e"};
    static String[] CONSTANTS = {"pi", "inf"};
    static String[] CONSTANTS_VALUES = {"3.141592653589793", "179769000000000006323030492138942643493033036433685336215410983289126434148906289940615299632196609445533816320312774433484859900046491141051651091672734470972759941382582304802812882753059262973637182942535982636884444611376868582636745405553206881859340916340092953230149901406738427651121855107737424232448"};
    
    private static String infixToPostfix(String exp) {
        String output = "";
        Stack<Character> operator = new Stack();
        for (int i = 0; i < exp.length(); i++) {
            char current = exp.charAt(i);
            if (precidence(current) != 3) {
                if (precidence(current) == -1) {
                    output += current;
                } else if (current == '-' && (i == 0 ||
                        i > 0 && precidence(exp.charAt(i - 1)) >= 0
                        && exp.charAt(i - 1) != ')')) {
                    output += current;
                } else {
                    output += " ";
                    while (!operator.isEmpty() &&
                            precidence(operator.peek()) >= precidence(current)
                            && operator.peek() != '(') {
                        output += operator.pop() + " ";
                    }
                    operator.push(current);
                }
            } else {
                if (current == '(') operator.push(current);
                else {
                    output += " ";
                    while (operator.peek() != '(') {
                        output += operator.pop() + " ";
                    }
                    operator.pop();
                }
            }
        }
        output += " ";
        while (!operator.isEmpty()) {
            output += operator.pop() + " ";
        }
        return output.replaceAll("\\s+", " ").trim();
    }
    
    private static int precidence (char theThang) {
        switch (theThang){
            case '+': case '-':
                return 0;
            case '*': case '/':
                return 1;
            case '^':
                return 2;
            case '(': case ')':
                return 3;
        }
        return -1;
    }
    
    private static BigDecimal evaluate (String postfix) {
        String[] token = postfix.split(" ");
        Stack<BigDecimal> operands = new Stack();
        
        for (int i = 0; i < token.length; i++) {
            String current = token[i];
            if (current.length() == 1 &&
                    precidence(current.charAt(0)) >= 0 &&
                    precidence(current.charAt(0)) <= 2) {
                BigDecimal arg1 = operands.pop();
                switch (current){
                    case "+":
                        operands.push(operands.pop().add(arg1));
                        break;
                    case "-":
                        operands.push(operands.pop().subtract(arg1));
                        break;
                    case "*":
                        operands.push(operands.pop().multiply(arg1));
                        break;
                    case "/":
                        operands.push(operands.pop().divide(arg1, 100, RoundingMode.HALF_UP));
                        break;
                    case "^":
                        operands.push(new BigDecimal(Math.pow(
                                operands.pop().doubleValue(),
                                arg1.doubleValue())));
                        break;
                }
            } else {
                operands.push(BigDecimal.valueOf(Double.parseDouble(current)));
            }
        }
        return operands.peek();
    }
    
    public static BigDecimal macro (String expression, boolean rad) {
        // Turn -() into -1*()
        expression = expression.replaceAll("-\\(", "-1*(");
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '-'){
                boolean minus = true;
                for (int j = i + 1; j < expression.length(); j++) {
                    if (expression.charAt(j) == '-') {
                        minus = !minus;
                    } else {
                        if (minus) {
                            expression = expression.substring(0, i)
                                    + "-" + expression.substring(j);
                        } else {
                            expression = expression.substring(0, i)
                                    + "+" + expression.substring(j);
                        }
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < CONSTANTS.length; i++) {
            expression = expression.replaceAll(CONSTANTS[i],
                    CONSTANTS_VALUES[i]);
        }
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '|') {
                int stop;
                int barCount = 1;
                for (stop = i + 1; stop < expression.length(); stop++) {
                    if (expression.charAt(stop) == '|') {
                        if (Character.isDigit(expression.charAt(stop - 1))
                                || expression.charAt(stop - 1) == ')') {
                            barCount--;
                        } else {
                            barCount++;
                        }
                    }
                    if (barCount == 0) break;
                }
                expression = expression.substring(0, i)
                        + "abs(" + expression.substring(i + 1, stop) + ")"
                        + expression.substring(stop + 1);
                i = 0;
            }
        }
        int pos;
        String theFun = "";
        do {
            pos = -1;
            int stop = -1;
            for (String fun : FUNCTIONS) {
                if ((pos = expression.indexOf(fun + "("))>=0) {
                    theFun = fun;
                    int parenCount = 1;
                    for (stop = pos + fun.length() + 1; stop < expression.length(); stop++) {
                        if (expression.charAt(stop) == '(') parenCount++;
                        if (expression.charAt(stop) == ')') parenCount--;
                        if (parenCount == 0) break;
                    }
                    break;
                }
            }
            if (pos >= 0) {
                double replace = macro(expression.substring(pos
                        + theFun.length() + 1, stop), rad).doubleValue();
//                String[] FUNCTIONS = {"abs", "sqrt", "cbrt", "asin", "sin", "acos", "cos",
//                    "atan", "tan", "ln", "log", "e"};
                switch (theFun) {
                    case "abs":
                        replace = Math.abs(replace);
                        break;
                    case "sqrt":
                        replace = Math.sqrt(replace);
                        break;
                    case "cbrt":
                        replace = Math.cbrt(replace);
                        break;
                    case "sin":
                        if (rad)
                            replace = Math.toRadians(replace);
                        replace = Math.sin(replace);
                        break;
                    case "asin":
                        replace = Math.asin(replace);
                        if (rad)
                            replace = Math.toDegrees(replace);
                        break;
                    case "cos":
                        if (rad)
                            replace = Math.toRadians(replace);
                        replace = Math.cos(replace);
                        break;
                    case "acos":
                        replace = Math.acos(replace);
                        if (rad)
                            replace = Math.toDegrees(replace);
                        break;
                    case "tan":
                        if (rad)
                            replace = Math.toRadians(replace);
                        replace = Math.tan(replace);
                        break;
                    case "atan":
                        replace = Math.atan(replace);
                        if (rad)
                            replace = Math.toDegrees(replace);
                        break;
                    case "ln":
                        replace = Math.log(replace);
                        break;
                    case "log":
                        replace = Math.log10(replace);
                        break;
                    case "e":
                        replace = Math.exp(replace);
                        break;
                }
                expression = expression.substring(0, pos) +
                        BigDecimal.valueOf(replace).toPlainString()
                        + expression.substring(stop + 1);
            }
        } while (pos >= 0);
        String postfix = infixToPostfix(expression);
        return evaluate(postfix).setScale(14, RoundingMode.HALF_UP);
    }
}
