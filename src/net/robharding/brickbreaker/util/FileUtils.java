package net.robharding.brickbreaker.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileUtils {
	
	private FileUtils() {}
	
	public static String loadAsString(String file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String buffer = "";
			while((buffer = reader.readLine()) != null) {
				result.append(buffer + '\n');
			}
			reader.close();
 		} catch(IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	public static BufferedImage loadImage(String file) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(file));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}

}
