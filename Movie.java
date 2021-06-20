import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

public class Movie {

	static long start = 0l;
	static long end = 0l;
	static boolean check = true;
	static JFrame frame = new JFrame("ASCII Movie");

	public static void main(String[] args) throws IOException {

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);
		/*
		 * JLabel movie = new JLabel(); movie.setBounds(0, 0, 2560, 1440);
		 * movie.setFont(new Font("Courier New", Font.PLAIN, 10));
		 * movie.setForeground(Color.white); movie.setVerticalAlignment(JLabel.CENTER);
		 * movie.setHorizontalAlignment(JLabel.CENTER); frame.add(movie);
		 */
		char[] ascii = " .,:;ox%#@".toCharArray();

		String[] show = new String[3171];
		// 196 248
		// 196 068
		StringBuilder sb = new StringBuilder("<html>");

		File file = new File("C:\\Users\\Whiteout\\Downloads\\hey video.mp4");
		Java2DFrameConverter c = new Java2DFrameConverter();

		FFmpegFrameGrabber g = new FFmpegFrameGrabber(file);
		g.start();

		for (int i = 0; i < 3171; i++) {
			BufferedImage image = c.convert(g.grabImage());

			for (int y = 0; y < 360; y += 5) {
				for (int x = 0; x < 480; x += 2) {
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
			show[i] = sb.toString();
			// movie.setText(sb.toString());
			sb = new StringBuilder("<html><div style='text-align: center;'>");
			System.out.println(i);
		}

		System.out.println("Loaded!");

		/*
		 * for (int i = 0; i < 3171; i++) { movie.setText(show[i]); }
		 */
		MyTimer timer = new MyTimer(show);
		frame.getContentPane().add(timer.movie);
		frame.getContentPane().setFocusable(true);
		frame.getContentPane().addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_A) {
					if (check) {
						timer.stop();
						check = false;
						System.out.println("Paused");
					} else {
						timer.start();
						check = true;
						System.out.println("Resumed");
					}

				}
				if (event.getKeyCode() == KeyEvent.VK_Q) {
					timer.nextFrame();
				}
				if (event.getKeyCode() == KeyEvent.VK_W) {
					timer.prevFrame();
				}
				if (event.getKeyCode() == KeyEvent.VK_E) {
					timer.skipForwards();
				}
				if (event.getKeyCode() == KeyEvent.VK_R) {
					timer.skipBackwards();
				}
				if (event.getKeyCode() == KeyEvent.VK_D) {
					try {
						timer.downloadCurrentFrame();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		});
		frame.setVisible(true);
		start = System.currentTimeMillis();
		timer.start();
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

		return 0.0299
				* (color1.getRed() + color2.getRed() + color3.getRed() + color4.getRed() + color5.getRed()
						+ color6.getRed() + color7.getRed() + color8.getRed() + color9.getRed() + color10.getRed())
				+ 0.0587 * (color1.getGreen() + color2.getGreen() + color3.getGreen() + color4.getGreen()
						+ color5.getGreen() + color6.getGreen() + color7.getGreen() + color8.getGreen()
						+ color9.getGreen() + color10.getGreen())
				+ 0.0114 * (color1.getBlue() + color2.getBlue() + color3.getBlue() + color4.getBlue() + color5.getBlue()
						+ color6.getBlue() + color7.getBlue() + color8.getBlue() + color9.getBlue()
						+ color10.getBlue());
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

	public static void movieEnd() {
		frame.dispose();
	}

}
