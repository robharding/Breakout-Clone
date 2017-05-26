package net.robharding.brickbreaker.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
	
	public static String[] getFileNames(String directory) {
		File[] listOfFiles = new File(directory).listFiles();
		String[] s = new String[listOfFiles.length];
		for(int i = 0; i < listOfFiles.length; i++) {
			s[i] = listOfFiles[i].getName();
		}
		return s;
	}
	
	public static void appendToFile(String file, String text) {
		try {
			Files.write(Paths.get(file), (text + "\n").getBytes(), StandardOpenOption.APPEND);
		} catch(IOException e) {
			e.printStackTrace();
		}
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
