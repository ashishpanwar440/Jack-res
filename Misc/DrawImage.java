import javax.imageio.ImageIO;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.*;

public class DrawImage {
	public static void main(String[] args)  throws IOException{
		String[] kinds = {" ", ".", ";", "x", "e", "B", "#"};
		BufferedImage img = ImageIO.read(new File("sample.jpg"));
		int brightness = -20, contrast = 16, lines = 70;
		String output = "";
		int incrementX = (img.getHeight()/lines)*5/11;
		int incrementY = img.getHeight()/lines;
		if(incrementX == 0) incrementX = 1;
		if(incrementY == 0) incrementY = 1;
		for(int y = 0; y < img.getHeight();y+=incrementY){
			for(int x = 0; x < img.getWidth();x+=incrementX){
				int r = img.getRGB(x, y) & 0xff;
				int g = img.getRGB(x, y)>>8 & 0xff;
				int b = img.getRGB(x, y)>>16 & 0xff;
				int avg = (r+g+b)/3;
				avg += brightness;
				avg = (avg - 128)*contrast/10 +128;
				if(avg > 255) avg = 255;
				if(avg < 0) avg = 0;
				String toDraw = kinds[(kinds.length-1) * (255-avg) / 255];
				output += toDraw;
				System.out.print(toDraw);
			}			
			output += "\n";
			System.out.println();
		}
		StringSelection selection = new StringSelection(output);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
	}
}
