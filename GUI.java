import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

public class GUI {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("ASCII Movie");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);

		JLabel movie = new JLabel();
		movie.setBounds(0, 0, 2560, 1440);
		movie.setFont(new Font("Courier New", Font.PLAIN, 8));
		movie.setForeground(Color.green);
		movie.setVerticalAlignment(JLabel.CENTER);
		movie.setHorizontalAlignment(JLabel.CENTER);
		frame.add(movie);

		// char[] ascii =
		// "$@B%8&WM#*oahkbdpqwmZOLCJUYXzcvunxrjft/|(1{[?-_+~<>i!lI;:,\"^`'.
		// ".toCharArray();
		char[] ascii = " .,:;ox%#@".toCharArray();
		// char[] ascii = "@#%xo;:,. ".toCharArray();
		// File file = new File("C:\\Users\\Whiteout\\Downloads\\kevin.jpg");
		// BufferedImage image = ImageIO.read(file);
		// BufferedImage image = scale(raw, 400, 168);

		StringBuilder sb = new StringBuilder("<html>");

		frame.setVisible(true);

		File file = new File("C:\\Users\\Whiteout\\Downloads\\PopcornTime\\The Matrix (1999) [1080p]\\matrix.mp4");
		Java2DFrameConverter c = new Java2DFrameConverter();

		FFmpegFrameGrabber g = new FFmpegFrameGrabber(file);
		g.start();

		for (int i = 0; i < 196248; i++) {
			BufferedImage image = c.convert(g.grabImage());

			for (int y = 0; y < 800; y += 10) {
				for (int x = 0; x < 1920; x += 4) {
					/*
					 * // Retrieving contents of a pixel int pixel = image.getRGB(x, y); // Creating
					 * a Color object from pixel value Color color = new Color(pixel, true); //
					 * Retrieving the R G B values int red = color.getRed(); int green =
					 * color.getGreen(); int blue = color.getBlue(); double gray = 0.299 * red +
					 * 0.587 * green + 0.114 * blue;
					 */
					sb.append(ascii[(int) ((joinGrayPixels(image, x, y)) * 10 / 256)]);
				}
				sb.append("<br>");
			}
			sb.append("</html>");

			movie.setText(sb.toString());
			sb = new StringBuilder("<html><div style='text-align: center;'>");
			System.out.println(i);
		}

	}

	public static double joinGrayPixels(BufferedImage image, int x, int y) {

		Color color1 = new Color(image.getRGB(x, y));
		Color color2 = new Color(image.getRGB(x, y + 1));
		Color color3 = new Color(image.getRGB(x, y + 2));
		Color color4 = new Color(image.getRGB(x, y + 3));
		Color color5 = new Color(image.getRGB(x, y + 4));
		Color color6 = new Color(image.getRGB(x + 1, y));
		Color color7 = new Color(image.getRGB(x + 1, y + 1));
		Color color8 = new Color(image.getRGB(x + 1, y + 2));
		Color color9 = new Color(image.getRGB(x + 1, y + 3));
		Color color10 = new Color(image.getRGB(x + 1, y + 4));

		Color color11 = new Color(image.getRGB(x, y + 5));
		Color color12 = new Color(image.getRGB(x, y + 6));
		Color color13 = new Color(image.getRGB(x, y + 7));
		Color color14 = new Color(image.getRGB(x, y + 8));
		Color color15 = new Color(image.getRGB(x, y + 9));
		Color color16 = new Color(image.getRGB(x + 1, y + 5));
		Color color17 = new Color(image.getRGB(x + 1, y + 6));
		Color color18 = new Color(image.getRGB(x + 1, y + 7));
		Color color19 = new Color(image.getRGB(x + 1, y + 8));
		Color color20 = new Color(image.getRGB(x + 1, y + 9));

		Color color21 = new Color(image.getRGB(x + 2, y));
		Color color22 = new Color(image.getRGB(x + 2, y + 1));
		Color color23 = new Color(image.getRGB(x + 2, y + 2));
		Color color24 = new Color(image.getRGB(x + 2, y + 3));
		Color color25 = new Color(image.getRGB(x + 2, y + 4));
		Color color26 = new Color(image.getRGB(x + 3, y));
		Color color27 = new Color(image.getRGB(x + 3, y + 1));
		Color color28 = new Color(image.getRGB(x + 3, y + 2));
		Color color29 = new Color(image.getRGB(x + 3, y + 3));
		Color color30 = new Color(image.getRGB(x + 3, y + 4));

		Color color31 = new Color(image.getRGB(x + 2, y + 5));
		Color color32 = new Color(image.getRGB(x + 2, y + 6));
		Color color33 = new Color(image.getRGB(x + 2, y + 7));
		Color color34 = new Color(image.getRGB(x + 2, y + 8));
		Color color35 = new Color(image.getRGB(x + 2, y + 9));
		Color color36 = new Color(image.getRGB(x + 3, y + 5));
		Color color37 = new Color(image.getRGB(x + 3, y + 6));
		Color color38 = new Color(image.getRGB(x + 3, y + 7));
		Color color39 = new Color(image.getRGB(x + 3, y + 8));
		Color color40 = new Color(image.getRGB(x + 3, y + 9));

		return 0.0299 * 0.25
				* (color1.getRed() + color2.getRed() + color3.getRed() + color4.getRed() + color5.getRed()
						+ color6.getRed() + color7.getRed() + color8.getRed() + color9.getRed() + color10.getRed()
						+ color11.getRed() + color12.getRed() + color13.getRed() + color14.getRed() + color15.getRed()
						+ color16.getRed() + color17.getRed() + color18.getRed() + color19.getRed() + color20.getRed()
						+ color21.getRed() + color22.getRed() + color23.getRed() + color24.getRed() + color25.getRed()
						+ color26.getRed() + color27.getRed() + color28.getRed() + color29.getRed() + color30.getRed()
						+ color31.getRed() + color32.getRed() + color33.getRed() + color34.getRed() + color35.getRed()
						+ color36.getRed() + color37.getRed() + color38.getRed() + color39.getRed() + color40.getRed())
				+ 0.0587 * 0.25
						* (color1.getGreen() + color2.getGreen() + color3.getGreen() + color4.getGreen()
								+ color5.getGreen() + color6.getGreen() + color7.getGreen() + color8.getGreen()
								+ color9.getGreen() + color10.getGreen() + color11.getGreen() + color12.getGreen()
								+ color13.getGreen() + color14.getGreen() + color15.getGreen() + color16.getGreen()
								+ color17.getGreen() + color18.getGreen() + color19.getGreen() + color20.getGreen()
								+ color21.getGreen() + color22.getGreen() + color23.getGreen() + color24.getGreen()
								+ color25.getGreen() + color26.getGreen() + color27.getGreen() + color28.getGreen()
								+ color29.getGreen() + color30.getGreen() + color31.getGreen() + color32.getGreen()
								+ color33.getGreen() + color34.getGreen() + color35.getGreen() + color36.getGreen()
								+ color37.getGreen() + color38.getGreen() + color39.getGreen() + color40.getGreen())
				+ 0.0114 * 0.25
						* (color1.getBlue() + color2.getBlue() + color3.getBlue() + color4.getBlue() + color5.getBlue()
								+ color6.getBlue() + color7.getBlue() + color8.getBlue() + color9.getBlue()
								+ color10.getBlue() + color11.getBlue() + color12.getBlue() + color13.getBlue()
								+ color14.getBlue() + color15.getBlue() + color16.getBlue() + color17.getBlue()
								+ color18.getBlue() + color19.getBlue() + color20.getBlue() + color21.getBlue()
								+ color22.getBlue() + color23.getBlue() + color24.getBlue() + color25.getBlue()
								+ color26.getBlue() + color27.getBlue() + color28.getBlue() + color29.getBlue()
								+ color30.getBlue() + color31.getBlue() + color32.getBlue() + color33.getBlue()
								+ color34.getBlue() + color35.getBlue() + color36.getBlue() + color37.getBlue()
								+ color38.getBlue() + color39.getBlue() + color40.getBlue());
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
