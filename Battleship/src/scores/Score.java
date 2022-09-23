package scores;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import menu.exitButtonListener;

public class Score {
	private JFrame frame;
	private JTextArea jtextarea1;
	private JButton exit;
	private JPanel p1;

	public Score(String y, String x) {

		ArrayList<String> scores = new ArrayList<String>();
		ArrayList<Integer> scores1 = new ArrayList<Integer>();

		try {
			Path myText_path = Paths.get("src/scores/highscores.txt");
			Charset charset = Charset.defaultCharset();
			scores = (ArrayList<String>) Files.readAllLines(myText_path, charset);
			scores.add(x);
			scores.add(y);

			for (String marks : scores) {
				scores1.add(Integer.parseInt(marks));
			}

			Collections.sort(scores1, Collections.reverseOrder());

			scores.removeAll(scores);

			int i = 0;
			for (int marks : scores1) {
				if (i < scores1.size()) {
					if (i < 10) {
						scores.add(String.valueOf(marks));
						i++;
					} else {
						break;
					}

				} else {
					break;
				}
			}

			Files.write(myText_path, scores, charset, StandardOpenOption.TRUNCATE_EXISTING);

		} catch (FileNotFoundException e) {

			JOptionPane.showMessageDialog(frame, "text file doesn't exist ", "Warning!", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "text file has errors ", "Warning!", JOptionPane.WARNING_MESSAGE);
		}

	}

	public Score() {
		frame = new JFrame("High score");
		jtextarea1 = new JTextArea();
		jtextarea1.setEditable(false);
		
//		lock j text so that user cannot edit text

		try (BufferedReader in = new BufferedReader(new FileReader("src/scores/highscores.txt"));) {

			jtextarea1.read(in, null);
			jtextarea1.requestFocus();

		} catch (FileNotFoundException e) {

			JOptionPane.showMessageDialog(frame, "text file doesn't exist ", "Warning!", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "text file has errors ", "Warning!", JOptionPane.WARNING_MESSAGE);
		}
		exit = new JButton("Exit");
		exit.addActionListener(new exitButtonListener(frame));

		p1 = new JPanel();

		p1.setLayout(new BorderLayout());
		p1.add(jtextarea1, BorderLayout.CENTER);
		p1.add(exit, BorderLayout.PAGE_END);

		frame.add(p1);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}
