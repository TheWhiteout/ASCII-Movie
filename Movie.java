import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

public class Movie {

	// Fields
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static long start = 0l;
	static long end = 0l;
	static boolean check = true;
	static JFrame frame = new JFrame("ASCII Movie");
	static double ratio = screenSize.getHeight() / 1440;
	static MyTimer timer;
	static int coefficientPixelUnion = 1; // CPU
	static String address;
	static int videoHeight;
	static int videoWidth;
	static int frameCount;
	static int fontSize;
	static int timerDelay;

	public static void main(String[] args) throws IOException {

		// Setting up main frame
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);

		// Setting up start frame
		JFrame startFrame = new JFrame("ASCII Movie");
		startFrame.setSize(500, 300);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setResizable(false);
		startFrame.getContentPane().setLayout(null);
		startFrame.getContentPane().setBackground(Color.BLACK);

		// Here the user will enter the values of all the fields
		JTextField addressField = new JTextField();
		addressField.setBounds(113, 24, 330, 24);
		startFrame.getContentPane().add(addressField);

		JLabel lblNewLabel = new JLabel("Address:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 20, 93, 25);
		startFrame.getContentPane().add(lblNewLabel);

		JLabel lblCpu = new JLabel("CPU:");
		lblCpu.setForeground(Color.WHITE);
		lblCpu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCpu.setBounds(259, 132, 48, 25);
		startFrame.getContentPane().add(lblCpu);

		JLabel lblHeigth = new JLabel("Height:");
		lblHeigth.setForeground(Color.WHITE);
		lblHeigth.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHeigth.setBounds(10, 60, 74, 25);
		startFrame.getContentPane().add(lblHeigth);

		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setForeground(Color.WHITE);
		lblWidth.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWidth.setBounds(259, 60, 74, 25);
		startFrame.getContentPane().add(lblWidth);

		JTextField widthField = new JTextField();
		widthField.setColumns(10);
		widthField.setBounds(369, 63, 74, 24);
		startFrame.getContentPane().add(widthField);

		JLabel lblFrameCount = new JLabel("Frame Count:");
		lblFrameCount.setForeground(Color.WHITE);
		lblFrameCount.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFrameCount.setBounds(10, 96, 136, 25);
		startFrame.getContentPane().add(lblFrameCount);

		JTextField frameCountField = new JTextField();
		frameCountField.setColumns(10);
		frameCountField.setBounds(156, 95, 87, 24);
		startFrame.getContentPane().add(frameCountField);

		JLabel lblFontSize = new JLabel("Font Size:");
		lblFontSize.setForeground(Color.WHITE);
		lblFontSize.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFontSize.setBounds(259, 96, 109, 25);
		startFrame.getContentPane().add(lblFontSize);

		JTextField fontSizeField = new JTextField();
		fontSizeField.setColumns(10);
		fontSizeField.setBounds(369, 98, 74, 24);
		startFrame.getContentPane().add(fontSizeField);

		JLabel lblTimerDelay = new JLabel("Timer Delay:");
		lblTimerDelay.setForeground(Color.WHITE);
		lblTimerDelay.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTimerDelay.setBounds(10, 132, 136, 25);
		startFrame.getContentPane().add(lblTimerDelay);

		JTextField timerDelayField = new JTextField();
		timerDelayField.setColumns(10);
		timerDelayField.setBounds(156, 130, 87, 24);
		startFrame.getContentPane().add(timerDelayField);

		JTextField CPUField = new JTextField();
		CPUField.setColumns(10);
		CPUField.setBounds(369, 132, 74, 24);
		startFrame.getContentPane().add(CPUField);

		JTextField heightField = new JTextField();
		heightField.setColumns(10);
		heightField.setBounds(156, 60, 87, 24);
		startFrame.getContentPane().add(heightField);

		// Finish entering values and convert file into string array
		JButton set = new JButton("Set");
		set.setFont(new Font("Tahoma", Font.BOLD, 20));
		set.setBounds(103, 200, 100, 40);
		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					address = addressField.getText();
					coefficientPixelUnion = Integer.parseInt(CPUField.getText());
					videoHeight = Integer.parseInt(heightField.getText());
					videoWidth = Integer.parseInt(widthField.getText());
					frameCount = Integer.parseInt(frameCountField.getText());
					fontSize = Integer.parseInt(fontSizeField.getText());
					timerDelay = Integer.parseInt(timerDelayField.getText());

					// "Map" of symbols this app is using. Ordered by brightness (number of pixels
					// in each symbol)
					char[] ascii = " .,:;ox%#@".toCharArray();

					String[] show = new String[frameCount];

					// Using html formating to allow new lines in a label
					StringBuilder sb = new StringBuilder("<html>");

					// Create file object from movie and convert it into string array frame by frame
					File file = new File(address);

					Java2DFrameConverter c = new Java2DFrameConverter();

					FFmpegFrameGrabber g = new FFmpegFrameGrabber(file);
					g.start();

					for (int i = 0; i < frameCount; i++) {
						BufferedImage image = c.convert(g.grabImage());

						for (int y = 0; y < videoHeight; y += 5 * coefficientPixelUnion) {
							for (int x = 0; x < videoWidth; x += 2 * coefficientPixelUnion) {

								// Retrieve contents of each pixel in a frame. Join groups of pixels (using CPU
								// here). Retrieve RGB values of pixels and average them. Then grayscale and add
								// to builder.
								sb.append(ascii[(int) ((joinGrayPixels(image, x, y)) * 10 / 256)]);
							}
							sb.append("<br>");
						}
						sb.append("</html>");
						show[i] = sb.toString();
						sb = new StringBuilder("<html>");
						System.out.println(i);
					}

					System.out.println("Loaded!");

					// Using timer to delay label changes
					timer = new MyTimer(show);
					frame.getContentPane().add(timer.movie);
					frame.getContentPane().setFocusable(true);
					frame.getContentPane().addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent event) {

							// pause/unpause
							if (event.getKeyCode() == KeyEvent.VK_A) {
								if (check) {
									timer.stop();
									check = false;
								} else {
									timer.start();
									check = true;
								}

							}

							// next frame
							if (event.getKeyCode() == KeyEvent.VK_Q) {
								timer.nextFrame();
							}

							// prev frame
							if (event.getKeyCode() == KeyEvent.VK_W) {
								timer.prevFrame();
							}

							// ~5 sec forwards
							if (event.getKeyCode() == KeyEvent.VK_E) {
								timer.skipForwards();
							}

							// ~5 sec backwards
							if (event.getKeyCode() == KeyEvent.VK_R) {
								timer.skipBackwards();
							}

							// Export current frame to a txt file
							if (event.getKeyCode() == KeyEvent.VK_D) {
								try {
									timer.downloadCurrentFrame();
								} catch (IOException e) {
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
				} catch (Exception e1) {
					throw new IncorrectDataEnteredException("Enter correct data!", e1);
				}

			}
		});
		startFrame.getContentPane().add(set);

		// Show main frame, start timer and close start frame
		JButton play = new JButton("Play");
		play.setBounds(283, 200, 100, 40);
		play.setFont(new Font("Tahoma", Font.BOLD, 20));
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frame.setVisible(true);
					timer.start();
					start = System.currentTimeMillis();
					startFrame.dispose();
				} catch (Exception e1) {
					startFrame.dispose();
					frame.dispose();
					throw new IncorrectDataEnteredException("Enter correct data!", e1);
				}
			}
		});
		startFrame.add(play);

		// Setting the start frame visibility to true
		startFrame.setVisible(true);

	}

	// Grayscaled average of a certain group of pixels
	public static double joinGrayPixels(BufferedImage image, int x, int y) {

		int redSum = 0;
		int greenSum = 0;
		int blueSum = 0;
		for (int i = 0; i < 10 * coefficientPixelUnion * coefficientPixelUnion; i++) {
			Color color = new Color(
					image.getRGB(x + i / (coefficientPixelUnion * 5), y + i % (coefficientPixelUnion * 5)));
			redSum += color.getRed();
			greenSum += color.getGreen();
			blueSum += color.getBlue();
		}

		return (0.0299 * redSum + 0.0587 * greenSum + 0.0114 * blueSum)
				/ (coefficientPixelUnion * coefficientPixelUnion);

	}

	// When the movie is over dispose of the frame
	public static void movieEnd() {
		frame.dispose();
	}

	// Resizing for different screens
	static int screen(int original) {
		int ready = (int) (original * ratio);
		return ready;
	}

	static void setBoundsScreen(JComponent component, int x, int y, int width, int height) {
		component.setBounds(screen(x), screen(y), screen(width), screen(height));
	}

}
