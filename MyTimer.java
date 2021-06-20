
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	// While the timer is running this method will be executing
	public void actionPerformed(ActionEvent e) {
		movie.setText(show[count++]);
		if (count > 3170) {
			this.stop();
			Movie.end = System.currentTimeMillis();
			System.out.println(Movie.end - Movie.start);
		}
	}
}
