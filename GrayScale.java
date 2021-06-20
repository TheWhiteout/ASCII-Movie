import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class GrayScale {

	public static void main(String[] args) throws IOException {
		char[] ascii = "$@B%8&WM#*oahkbdpqwmZOLCJUYXzcvunxrjft/|(1{[?-_+~<>i!lI;:,\"^`'. ".toCharArray();
		// System.out.println(ascii.length);
		File file = new File("C:\\Users\\Whiteout\\Downloads\\kevin.jpg");
		BufferedImage image = ImageIO.read(file);
		// BufferedImage scaledImage = scale(image, 20, 20);
		// BufferedImage GSImage = new BufferedImage(image.getWidth(),
		// image.getHeight(), BufferedImage.TYPE_INT_RGB);
		File text = new File("textduck.txt");
		text.createNewFile();
		FileWriter fw = new FileWriter(text);
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				// Retrieving contents of a pixel
				int pixel = image.getRGB(x, y);
				// Creating a Color object from pixel value
				Color color = new Color(pixel, true);
				// Retrieving the R G B values
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				double gray = 0.299 * red + 0.587 * green + 0.114 * blue;
				// int graycolor = new Color(gray, gray, gray).getRGB();
				// GSImage.setRGB(x, y, graycolor);
				fw.write(ascii[(int) ((gray) / 4)]);
				System.out.print(ascii[(int) ((gray) / 4)]);
			}
			fw.write("\n");
			System.out.println();
		}
		// File gimg = new File("test.jpg");
		// ImageIO.write(GSImage, "jpg", gimg);
		// gimg.createNewFile();
	}

	public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
		BufferedImage scaledImage = null;
		if (imageToScale != null) {
			scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
			Graphics2D graphics2D = scaledImage.createGraphics();
			graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
			graphics2D.dispose();
		}
		return scaledImage;
	}
}
