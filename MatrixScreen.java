import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.lang.Runnable;
import java.lang.Thread;
import java.awt.FontMetrics;
import java.awt.Font;

public class MatrixScreen {
    static long INTERVAL = 10; 
    static String CHARACTERS = ""; 
    static int MAX_BRIGHTNESS = 300;
    static int DIMMING_SPEED = 8;
    static JFrame theMatrix = new JFrame("The Matrix"); //Main jframe.
    static Image bufferImg; //Off screen image to implement double buffering.
    static Graphics bufferGraph; //Graphics of the image.
    static int width = 0;//Width of the window.
    static int height = 0;//Height of the window.
    static char[][] codes;//Array of random characters.
    static int[][] brightness;//Array of each character's brightness.
    static Font theFont = new Font("Courier New", Font.BOLD, 8);//The font we're gonna use.
    static FontMetrics theFontMetrics;//Used to calculate the size of a character.
    static int charWidth;//Size of a character.
    static int charHeight;
    
    //There are "runners" on the screen, each will move down every tick and set the brighness to max.
    static ArrayList <Point> runnerList = new ArrayList<Point>();

    public static void main(String[] args) {
        for (int i = 0; i < 1200; i++) {
            CHARACTERS += (char) (i+33);
        }
        theMatrix.setSize(800, 500);
        theMatrix.setLocationRelativeTo(null); //Middle of the screen.
        theMatrix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit program when jframe closes
        theMatrix.show();
        while(theMatrix.getWidth() == 0); //Loop until the form is clearly visible.
        resize();//Recalculate the array of codes and brightness.

        theFontMetrics = bufferGraph.getFontMetrics(theFont);
        charWidth = theFontMetrics.charWidth('A') + 7;
        charHeight = theFontMetrics.getHeight();
        
        //Add a resize handler to the jframe to execute when the window is resized.
        theMatrix.addComponentListener(new ComponentAdapter() { 
            public void componentResized(ComponentEvent evt) {
                resize();
                randomizeCodes();
            }
        });
        randomizeCodes(); //Generate all the random character, at this point they're still all black.
        
        
        Runnable runny = new Runnable() {//A runnable for a separate thread.
            @Override
            public void run() {
                try {
                    while (true) {
                        bufferGraph.setColor(Color.black);
                        bufferGraph.fillRect(0, 0, width, height);//Clear back ground of the off screen image.
                        twitch(width / 10); //Call the twitch method to randomize a certain number of characters but not all.
                        render(bufferGraph);//Render to the off screen image.
                        theMatrix.getContentPane().getGraphics().drawImage(bufferImg, 0, 0, null);//Draw to main jframe.
                        
                        for (int i = 0; i < width / 180; i++) {//Spawn a certain number of runners at random location on top of the screen.
                            runnerList.add(new Point((int) (Math.random() * codes[0].length), 0));                            
                        }
                        for (int i = 0; i < runnerList.size(); i++) {//Iterate the list of runners...
                            Point theRunner = runnerList.get(i);
                            //Randomly kill a runner, a runner has a 2% chance of dying each move it makes.
                            if (Math.random() > .98 || theRunner.y >= brightness.length
                                    || theRunner.x >= brightness[0].length) {//Or kill it when it hits the bottom.
                                runnerList.remove(i);
                                continue;
                            }
                            brightness[theRunner.y][theRunner.x] = MAX_BRIGHTNESS;//Each step the runner take will set the brightness there to max.
                            theRunner.y++;//Move the runner down.
                        }
                        Thread.sleep(INTERVAL);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        
        Thread runThread = new Thread(runny);//Create a separate thread with the above runnable.
        runThread.start();//Start the thread.
    }
    
    public static void resize() {
        bufferImg = theMatrix.getContentPane().createImage(
                theMatrix.getWidth(), theMatrix.getHeight());
        bufferGraph = bufferImg.getGraphics();
        width = theMatrix.getContentPane().getWidth();
        height = theMatrix.getContentPane().getHeight();
    }
    
    public static void randomizeCodes() {
        codes = new char[height / charHeight + 1][width / charWidth];
        brightness = new int[height / charHeight + 1][width / charWidth];
        for (int i = 0; i < codes.length; i++) {
            for (int j = 0; j < codes[0].length; j++) {
                codes[i][j] = randomChar();
            }
        }
    }
    
    public static void render(Graphics g) {
        if (codes != null) {
            for (int i = 0; i < codes.length; i++) {
                for (int j = 0; j < codes[0].length; j++) {
                    g.setColor(getColor(brightness[i][j]));
                    g.drawString("" + codes[i][j], j * charWidth, i * charHeight);
                    if (brightness[i][j] - DIMMING_SPEED > 0) {
                        brightness[i][j] -= DIMMING_SPEED;
                    } else {
                        brightness[i][j] = 0;
                    }
                }
            }
        }
    }
    
    //Method to get color. If the brightness is below 256 then just set it on the green scale.
    //Or else the color is gonna be from green to white on the 256 to 300 scale.
    //So 256 will be regular green and 300 will be totally white. 
    public static Color getColor(int bn) {
        if (bn <= 255) {
            return new Color(0, bn, 0);
        } else {
            bn = Math.min(bn, MAX_BRIGHTNESS);
            bn -= 255;
            bn = bn * 255 / (MAX_BRIGHTNESS - 255);
            return new Color(bn, 255, bn);
        }
    }
    
    public static void twitch(int times) {
        if (codes != null && codes.length > 0) {
            for (int i = 0; i < times; i++) {
                int x = (int) (Math.random() * codes[0].length);
                int y = (int) (Math.random() * codes.length);
                codes[y][x] = randomChar();
            }
        }
    }
    
    public static char randomChar() {
        return CHARACTERS.charAt((int) (Math.random() * (CHARACTERS.length()-1)));
    }
}
