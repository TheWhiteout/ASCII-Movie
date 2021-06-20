
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.Timer;

public class MyTimer implements ActionListener {
	private Timer timer;
	JLabel movie = new JLabel();
	String show[];
	int count = 0;

	MyTimer(String[] array) {
		timer = new Timer(31, this);
		movie.setBounds(0, 0, 2560, 1440);
		movie.setFont(new Font("Courier New", Font.PLAIN, 10));
		movie.setForeground(Color.white);
		movie.setVerticalAlignment(JLabel.CENTER);
		movie.setHorizontalAlignment(JLabel.CENTER);
		show = array;
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void nextFrame() {
		count++;
		if (count > 3170) {
			this.stop();
			Movie.movieEnd();
		}
		movie.setText(show[count]);
	}

	public void prevFrame() {
		count--;
		if (count < 0) {
			count = 0;
		}
		movie.setText(show[count]);
	}

	public void skipForwards() {
		count += 125;
		if (count > 3170) {
			this.stop();
			Movie.movieEnd();
		}
		movie.setText(show[count]);
	}

	public void skipBackwards() {
		count -= 125;
		if (count < 0) {
			count = 0;
		}
		movie.setText(show[count]);
	}

	public void downloadCurrentFrame() throws IOException {
		File text = new File("text.txt");
		text.createNewFile();
		FileWriter fw = new FileWriter(text);
		fw.write(show[count]);
	}

	// While the timer is running this method will be executing
	public void actionPerformed(ActionEvent e) {
		movie.setText(show[count++]);
		if (count > 3170) {
			this.stop();
			Movie.movieEnd();
			Movie.end = System.currentTimeMillis();
			System.out.println(Movie.end - Movie.start);
		}
	}
}
