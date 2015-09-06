import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rainbowizer {
    static Color[] bunchaColor = new Color[6];
    public static void main(String[] args) throws IOException {
        bunchaColor[0] = new Color(255,50,50); // Red
        bunchaColor[1] = new Color(255,127,100); // Orange
        bunchaColor[2] = new Color(255,255,50); // Yellow
        bunchaColor[3] = new Color(100,255,100); // Green
        bunchaColor[4] = new Color(100,127,255); // Blue
        bunchaColor[5] = new Color(177,60,177); // Violet
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Which image shall I use?");
        String fileName = scan.nextLine();
        BufferedImage theImg = ImageIO.read(new File(fileName));
        BufferedImage newImg = new BufferedImage(theImg.getWidth(), theImg.getHeight(), theImg.getType());
        
        Graphics g = newImg.getGraphics();
        for (int i = 0; i < newImg.getHeight(); i++) {
            for (int j = 0; j < newImg.getWidth(); j++) {
                Point center = new Point(theImg.getWidth() / 2, theImg.getHeight());
                double max = Math.sqrt(center.getX() * center.getX()
                        + center.getY() * center.getY());
                double current = Math.sqrt((center.getX() - j) * (center.getX() - j)
                        + (center.getY() - i) * (center.getY() - i));
                Color rainbowColor = rainbow(.20 + (current / max) * 5.79);
                int pixel = theImg.getRGB(j, i);
                int r = pixel & 0xff;
                int gr = pixel >> 8 & 0xff;
                int b = pixel >> 16 & 0xff;
                int grayScale = (r + gr +b) / 3;
                int addition = 35;
                Color newColor = new Color(Math.min(grayScale * rainbowColor.getRed() / 255 + addition, 255),
                        Math.min(grayScale * rainbowColor.getGreen() / 255 + addition, 255), 
                        Math.min(grayScale * rainbowColor.getBlue() / 255 + addition, 255));
                g.setColor(newColor);
                g.drawLine(j, i, j, i);
            }
        }
        
        String extension = fileName.substring(fileName.indexOf('.', fileName.length() - 5) + 1);
        String newFileName = fileName.substring(0, fileName.indexOf('.', fileName.length() - 5))
                + " - rainbowized." + extension;
        ImageIO.write(newImg, extension.toUpperCase(), new File(newFileName));
        System.out.println("Done!!!");
        scan.close();
    }

    public static Color rainbow(double position){
        if (position <= .5) return bunchaColor[0];
        else if (position > .5 && position < 5.5){
            double individualPos = position % 1;
            if (individualPos >= .75) {
                Color color1 = bunchaColor[(int) position];
                Color color2 = bunchaColor[(int) position + 1];
                return getMiddleColor(color1, color2, (individualPos - .75) / .5);
            } else if (individualPos <= .25) {
                Color color1 = bunchaColor[(int) position - 1];
                Color color2 = bunchaColor[(int) position];
                return getMiddleColor(color1, color2, .50 + individualPos / .5);                
            }
            return bunchaColor[(int) position];
        }
        return bunchaColor[5];
    }
    
    public static Color getMiddleColor(Color color1, Color color2, double mid) {
        int red = getMiddleNumber(color1.getRed(), color2.getRed(), mid);
        int green = getMiddleNumber(color1.getGreen(), color2.getGreen(), mid);
        int blue = getMiddleNumber(color1.getBlue(), color2.getBlue(), mid);
        return new Color(red, green, blue);
    }
    
    public static int getMiddleNumber(int num1, int num2, double mid) {
        int interval = num2 - num1;
        int result = num1 + (int)(mid * interval);
        return result;
    }
}
