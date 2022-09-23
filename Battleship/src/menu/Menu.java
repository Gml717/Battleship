package menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import boardGUI.BoardInterface;
import rules.Rules;

public class Menu {
	public static JFrame frame;
	private JButton rules;
	private JButton start;
	private JButton score;
	private JButton exit;
	private JComboBox<String> row;
	private JComboBox<String> column;
	private JLabel welcomLabel;
	private JLabel blank1;
	private JLabel blank2;
	private JLabel blank3;
	private JLabel blank4;
	private JLabel blank5;
	private JLabel blank6;
	private JLabel blank7;
	private JLabel blank8;
	private JLabel rows;
	private JLabel columns;
	private JLabel optionlabel;
	private JLabel boardLabel;
	private JRadioButton samePoints;
	private JRadioButton differntPoints;
	private JRadioButton shipsRandom;
	private JRadioButton shipsFile;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;

	public Menu() {

		frame = new JFrame("Battleship Game: selection screen");

		welcomLabel = new JLabel("Welcome to Battleship");
		optionlabel = new JLabel("Please selct your options and get started");
		boardLabel = new JLabel("Choose the board size");
		blank1 = new JLabel("");
		blank2 = new JLabel("");
		blank3 = new JLabel("");
		blank4 = new JLabel("");
		blank5 = new JLabel("");
		blank6 = new JLabel("");
		blank7 = new JLabel("");
		blank8 = new JLabel("");
		rows = new JLabel("Rows");
		columns = new JLabel("Columns");

		rules = new JButton("Rules");
		rules.addActionListener(new rulesButtonListener());

		start = new JButton("Start Game");
		start.addActionListener(new startButtonListener());

		exit = new JButton("Exit");
		exit.addActionListener(new exitButtonListener(frame));

		score = new JButton("High score");
		score.addActionListener(new scoreButtonListener());

		// Players can choose these dimensions for the board
		row = new JComboBox<String>(new String[] { "      7", "      8", "      9" });
		column = new JComboBox<String>(new String[] { "      7", "      8", "      9" });

		samePoints = new JRadioButton("same points-per-hit");
		differntPoints = new JRadioButton("different points-per-hit");
		shipsRandom = new JRadioButton("Random ship placement");
		shipsFile = new JRadioButton("Ship placement by file");

		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(samePoints);
		bg1.add(differntPoints);

		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(shipsRandom);
		bg2.add(shipsFile);

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();

		p1.add(welcomLabel);
		p1.add(optionlabel);

		p1.setLayout(new GridLayout(2, 1));

		p2.add(shipsRandom);
		p2.add(samePoints);
		p2.add(boardLabel);
		p2.add(shipsFile);
		p2.add(differntPoints);
		p2.add(rows);
		p2.add(blank3);
		p2.add(blank4);
		p2.add(row);
		p2.add(blank5);
		p2.add(blank6);
		p2.add(columns);
		p2.add(blank7);
		p2.add(blank8);
		p2.add(column);

		p2.setLayout(new GridLayout(5, 3));

		p3.add(blank1);
		p3.add(start);
		p3.add(blank2);
		p3.add(rules);
		p3.add(score);
		p3.add(exit);
		p3.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		p3.setLayout(new GridLayout(2, 3));

		frame.setLayout(new GridLayout(3, 1));
		frame.add(p1);
		frame.add(p2);
		frame.add(p3);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Menu();
			}
		});
	}

	private class rulesButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			new Rules();// Shows top 10 highscores

		}

	}

	private class startButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String rows = row.getSelectedItem().toString().trim();
			String columns = column.getSelectedItem().toString().trim();

			int rowsBoard = Integer.parseInt(rows);
			int columnsBoard = Integer.parseInt(columns);

			if (samePoints.isSelected() && shipsRandom.isSelected()) {
				new BoardInterface(rowsBoard, columnsBoard, false, true);

			} else if (samePoints.isSelected() && shipsFile.isSelected()) {
				new BoardInterface(rowsBoard, columnsBoard, false, false);

			} else if (differntPoints.isSelected() && shipsRandom.isSelected()) {
				new BoardInterface(rowsBoard, columnsBoard, true, true);

			} else if (differntPoints.isSelected() && shipsFile.isSelected()) {
				new BoardInterface(rowsBoard, columnsBoard, true, false);

			} else {
				// When one or two options are not selected.
				JOptionPane.showMessageDialog(frame, "Select options!", "Warning!", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

}
