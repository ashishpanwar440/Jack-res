import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class HitsBlunt {
    static Robot theBot;
    static HashMap<Character, Character> theUpperCaseMap = new HashMap<Character, Character>();
    static HashMap<Character, Integer> theKeyMap = new HashMap<Character, Integer>();
    public static void main(String[] args) throws AWTException, FileNotFoundException, InterruptedException {
        theUpperCaseMap.put('<', ',');
        theUpperCaseMap.put('>', '.');
        theUpperCaseMap.put('?', '/');
        theUpperCaseMap.put(':', ';');
        theUpperCaseMap.put('\"', '\'');
        theUpperCaseMap.put('{', '[');
        theUpperCaseMap.put('}', ']');
        theUpperCaseMap.put('|', '\\');
        theUpperCaseMap.put('~', '`');
        theUpperCaseMap.put('!', '1');
        theUpperCaseMap.put('@', '2');
        theUpperCaseMap.put('#', '3');
        theUpperCaseMap.put('$', '4');
        theUpperCaseMap.put('%', '5');
        theUpperCaseMap.put('^', '6');
        theUpperCaseMap.put('&', '7');
        theUpperCaseMap.put('*', '8');
        theUpperCaseMap.put('(', '9');
        theUpperCaseMap.put(')', '0');
        theUpperCaseMap.put('_', '-');
        theUpperCaseMap.put('+', '=');
        
        theKeyMap.put(',', 44);
        theKeyMap.put('.', 46);
        theKeyMap.put('/', 47);
        theKeyMap.put(';', 59);
        theKeyMap.put('\'', 222);
        theKeyMap.put('[', 91);
        theKeyMap.put(']', 93);
        theKeyMap.put('\\', KeyEvent.VK_BACK_SLASH);
        theKeyMap.put('`', 192);
        theKeyMap.put(' ', KeyEvent.VK_SPACE);
        theKeyMap.put('\n', KeyEvent.VK_ENTER);
        for (int i = 0; i <= 9; i++) {
            theKeyMap.put((char) ('0' + i), KeyEvent.VK_0 + i);
        }
        theKeyMap.put('-', 45);
        theKeyMap.put('=', 61);
        
        String name = "000";
        theBot = new Robot();
        theBot.setAutoDelay(4);
        String script = "";
        File theFile = new File("HitsBlunt" + (name.equals("00" + "0") ? "" : name) + ".java");
        Scanner theScan = new Scanner(theFile);
        while (theScan.hasNext()) {
            script += theScan.nextLine() + "\n";
        }
        theBot.keyPress(KeyEvent.VK_WINDOWS);
        sleepForABit();
        pressKey(KeyEvent.VK_R);
        theBot.keyRelease(KeyEvent.VK_WINDOWS);
        sleepForABit();
        type("cmd\n");
        sleepForABit();
        int nextVersion = Integer.parseInt(name) + 1;
        String nextString = String.format("%03d", nextVersion);
        if (name.equals("00" + "0")) {
            script = script.substring(0, script.indexOf("HitsBlunt"))
                    + "HitsBlunt000" 
                    + script.substring(script.indexOf("HitsBlunt") + "HitsBlunt".length());
        }
        script = script.replaceAll(name, nextString);
        type("touch HitsBlunt" + nextString + ".java\n");
        sleepForABit();
        type("notepad HitsBlunt" + nextString + ".java\n");
        sleepForABit();
        type(script);
        theBot.keyPress(KeyEvent.VK_CONTROL);
        pressKey(KeyEvent.VK_S);
        theBot.keyRelease(KeyEvent.VK_CONTROL);
        sleepForABit();
        theBot.keyPress(KeyEvent.VK_ALT);
        pressKey(KeyEvent.VK_F4);
        theBot.keyRelease(KeyEvent.VK_ALT);
        sleepForABit();
        type("javac HitsBlunt" + nextString + ".java\n");
        sleepForABit();
        sleepForABit();
        type("java HitsBlunt" + nextString + "\n");
    }
    
    public static void sleepForABit () throws InterruptedException {
        Thread.sleep(1500);
    }
    
    public static void type (String toType) throws InterruptedException {
        char theChar;
        for (int i = 0; i < toType.length(); i++) {
            theChar = toType.charAt(i);
            if (theChar >= 'A' && theChar <= 'Z') { // If uppercase
                theBot.keyPress(KeyEvent.VK_SHIFT);
                pressKey(theChar - 'A' + KeyEvent.VK_A);
                theBot.keyRelease(KeyEvent.VK_SHIFT);
            } else if (theChar >= 'a' && theChar <= 'z'){
                pressKey(theChar - 'a' + KeyEvent.VK_A);
            } else if (theChar >= '0' && theChar <= '9'){
                pressKey(theChar - '0' + KeyEvent.VK_0);
            } else if (theKeyMap.containsKey(theChar)) {
                pressKey(theKeyMap.get(theChar));
            } else if (theUpperCaseMap.containsKey(theChar)) {
                theBot.keyPress(KeyEvent.VK_SHIFT);
                pressKey(theKeyMap.get(theUpperCaseMap.get(theChar)));
                theBot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
    }
    
    public static void pressKey (int theChar) {
        theBot.keyPress(theChar);
        theBot.keyRelease(theChar);
    }
}
