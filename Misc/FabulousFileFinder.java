import java.io.File;
import java.util.Scanner;
import java.util.LinkedList;

public class FabulousFileFinder {
    static Scanner scan = new Scanner(System.in);
    static int count = 0;
    static int previousLineLength = 0;
    static LinkedList<String> foundList = new LinkedList<String>();
    
    public static void main(String[] args) {
        System.out.print("What is the file called (regex is fine)? ");
        String theFileName = scan.nextLine().toLowerCase(); //File name.
        System.out.print("Where should I start at? ");
        String thePath = scan.nextLine(); //Starting directory.
        search(thePath, theFileName);//Call the recursive search in the starting directory.
        System.out.println("\nDone! " + count + " file(s) found. Gimme a cookie!");
        if (count > 0) {
            System.out.print("Here they be (Press ENTER):");
            scan.nextLine();
            for (String theName : foundList) {
                System.out.println(theName);
            }
        }
    }
    
    public static void search(String path, String name) {
        try {
            for (int i = 0; i <= previousLineLength; i++) {
                System.out.print("\b \b"); //Move back and "clear" every character in the line on the console. (Only works in command line tools)
            }
            String message = "Looking in: " + path ;
            previousLineLength = message.length();//Cache the length of the line to know how many characters to clear next time.
            System.out.print(message); //Print out the message of what directory the program is looking in.
            File filePath = new File(path);//Get the path for the folder.
            File[] demFiles = filePath.listFiles(); //Get list of files in the folder (remember directories are files too.)
            for (File disFile : demFiles) {
                if (disFile.isFile() && (disFile.getName().toLowerCase().matches(name) 
                        || disFile.getName().toLowerCase().contains(name))) { 
                    //Print the file path out if the name contains the name we're looking for or matches the regex.
                    System.out.println("\nFound: " + disFile.getPath());
                    foundList.add(disFile.getPath());
                    count++;
                } else if (disFile.isDirectory()) {
                    search(disFile.getPath(), name); //If the file is a directory then call search in that directory.
                }
            }
        } catch (Exception e) {
            System.out.println("\n...Sum error in this folder..."); //Sometimes we don't have persmission to search a folder...
        }
    }
}
