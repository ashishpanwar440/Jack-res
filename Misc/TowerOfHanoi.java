import java.util.Stack;
import java.util.Scanner;

public class TowerOfHanoi {
    @SuppressWarnings("unchecked")
    static Stack<Integer>[] stack = new Stack[3];
    static int STACK_HEIGHT = 12;
    static Scanner scan = new Scanner(System.in);
    static int lvl = 3;
    
    public static void main(String[] args) {
        newGame();
        String input = "";
        //Play time! weeeee!
        while (true) {
            try {
                render(-1);

                //Pick the "from" stack.
                System.out.print("Pick a stack: (Enter \"n\" to restart, \"s\" if you admit that you're not smart enough to solve it) ");
                input = scan.nextLine();
                int from = Integer.parseInt(input);
                if (from < 1 || from > 3) { //When user feels like trollin'.
                    render(-2);
                    confirmPrint("You rebel! Do it right this time.");
                    continue;//Return to the top of the loop.
                }
                if (stack[from - 1].isEmpty()) { //When user tries to pick up the floor.
                    render(-2);
                    confirmPrint("There ain't nothing in there man");
                    continue;//Return to the top...
                }
                render(from - 1); //Let user know the stack picked.
                
                //Pick the "to" stack.
                System.out.print("Stack " + from + " picked. Where do you wanna drop it? ");
                input = scan.nextLine();
                int to = Integer.parseInt(input);
                if (to < 1 || to > 3) { //When he/she didn't give up on trolling.
                    render(-2);
                    confirmPrint("You rebel! Do it right this time.");
                    continue;//You get the idea...
                }
                //Invalid move when user tries to put a bigger disc on the small one.
                if (!stack[to - 1].isEmpty() && stack[to - 1].peek() < stack[from - 1].peek()) {
                    render(-2);
                    confirmPrint("Can't do that bruh.");
                    continue;//Sigh...
                }
                
                //After all the tests are pass... (Finally...)
                int disc = stack[from - 1].pop();
                stack[to - 1].push(disc);
                System.out.println("Floop!");
                render(-1);
                
                //If all the discs are on the third stack.
                if (stack[2].size() == lvl) {
                    confirmPrint("Looks like we got a winner.");
                    confirmPrint("It's been a nice game.");
                    confirmPrint("Bye ;)");
                    break;
                }
            } catch (Exception e) {
                render(-2);
                if (input.equals("n")) {
                    confirmPrint("But but you didn't finish.... oh well D:");
                    newGame();
                    continue;
                } else if (input.equals("s")) {
                    confirmPrint("Bruh this is like the easiest game on the planet.");
                    confirmPrint("Lemme show ya.");
                    init();//Re-initialize the stacks
                    System.out.println("At first it was like this, right?");
                    render(-1);
                    scan.nextLine();
                    confirmPrint("Then...");
                    solve(1, lvl, 3);
                    confirmPrint("See? Easy right?");
                    confirmPrint("See ya next time then.");
                    break;
                }
                System.out.print("Quit tryina break the system man. Let's do it again.");
                scan.nextLine();
                render(-1);
                continue;
            }
        }
    }
    
    static void confirmPrint(String theString) {
        System.out.print(theString);
        scan.nextLine();
    }
    
    static void newGame() {
        System.out.println("How much do you hate yourself? (Enter 3-8)");
        //Level selection.
        while (true){
            try {
                lvl = Integer.parseInt(scan.nextLine());
                if (lvl < 3 || lvl >8) {
                    System.out.println("Dude! I say from 3 to 8!");
                    continue;
                }
                break;
            }
            catch (Exception e) {
                System.out.println("Bruh! That ain't no number.");
            };
        }
        
        //Initialize the stacks.
        init();

        //Announce the beginning of user's nightmare.
        System.out.print("Alright here we go!\n");
        scan.nextLine();
    }

    static void init() {
        //(Re)Instantiate each stacks.
        for (int i = 0; i < stack.length; i++) {
            stack[i] = new Stack<Integer>();
        }
        
        //Drop the discs on the first stack.
        for (int i = lvl; i >= 1; i--) {
            stack[0].push(i);
        }
    }

    static void render(int selectedStack) {
        if (selectedStack >= -1) { //Print things as normal.
            /* These for loops may look switched but they have to be.
             * In the console we print line by line so we have to print all of the elements of each stack at the
             * same "height" first then we go to the next line.
             */
            for (int j = STACK_HEIGHT - 1; j >= 0; j--) {
                for (int i = 0; i < stack.length; i++) {
                    if(j >= stack[i].size()) {//When j is above the stack.
                        if (j == STACK_HEIGHT - 1) { //If on top of the stack.
                            if (i == selectedStack) { //Show the disc that's picked on top and hovering in the air.
                                System.out.print(midAlign(getDisc(stack[selectedStack].peek())));   
                            } else{ 
                                //If the current stack is not the "from" stack then just print a space
                                System.out.print(midAlign(" ")); 
                            }
                        } else {//Else just print the "*" representing the stick
                            System.out.print(midAlign("*"));
                        }
                    } else {//When j is inside the stack
                        if (i == selectedStack && j == stack[i].size() - 1) {
                            //When printing the top of the stack that is chosen, the disc is not there.
                            System.out.print(midAlign("*"));
                        } else {//Or else just print the disc out
                            System.out.print(midAlign(getDisc(stack[i].get(j))));
                        }
                    }
                }
                System.out.println();
            }
            //Print the number of each stack for user's ease of navigation
            System.out.println("\n" + midAlign("1") + midAlign("2") + midAlign("3"));
        } else { //Print a bunch of blanks to "clear" the console
            for (int i = 0; i < STACK_HEIGHT; i++) {
                System.out.println();
            }
        }
    }
    
    //Method to get the number of "#" for each disc size
    //The number of "#" = disc size * 2 + 1
    static String getDisc(int sizeOfDisc) {
        String disc = "";
        for (int k = 1; k <= sizeOfDisc * 2 + 1; k++) {
            disc += "#";
        }
        return disc;
    }
    
    //Method to return an aligned string
    static String midAlign(String content) {
        String maxSpaces = "         ";//There will be maximum of 9 spaces each side (never hit).
        //Grab as many spaces as there needs to be.
        String spaces = maxSpaces.substring(content.length()/2);
        String result = spaces;//First the spaces on the left.
        result += content;//Then the content.
        result += spaces;//Then the spaces on the right.
        return result;
    }
    
    /* Method for when user is not smart enough.
     * The idea is simple: in order to move the bottom piece to the destination,
     * we have to move the stack above it to the other stack (neither the current one or the destination)
     * then move the bottom piece to the destination. Then move the stack back onto the bottom piece, now
     * at the destination. Recursively, this will solve the puzzle.
     */
    static void solve(int original, int size, int to) {
        if (size == 1) {
            moveOver(original - 1, to - 1);
        } else {
            int theOtherStack = original ^ to; //This one is a trick...
            //1 xor 2 is 3, 2 xor 3 is 1 and so on...
            //So for 1, 2 ,3 we can do xor to get the other number.
            solve(original, size - 1, theOtherStack);// Move stack above to the other stack.
            moveOver(original - 1, to - 1); //Move bottom over.
            solve(theOtherStack, size - 1, to); //Move the stack above back on;
        }
    }
    
    //Method to move the top piece from one stack to another.
    static void moveOver(int from, int to) {
        System.out.println("Voop!");
        render(from);//Show before.
        scan.nextLine();
        int disc = stack[from].pop();
        stack[to].push(disc);
        System.out.println("Floop!");
        render(-1);//Show after.
        scan.nextLine();
    }
}
