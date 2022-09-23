package rules;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import menu.exitButtonListener;

public class Rules {

	private JFrame frame;
	private JButton exit;
	private JPanel p1;
	private JTextArea jtextarea1;

	public Rules() {

		frame = new JFrame("Rules");
		jtextarea1 = new JTextArea();
		jtextarea1.setEditable(false); 
		
//		lock j text so that user cannot edit text

		try (BufferedReader in = new BufferedReader(new FileReader("src/rules/rules1.txt"));) {

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
