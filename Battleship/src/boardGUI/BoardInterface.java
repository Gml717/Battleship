package boardGUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import menu.exitButtonListener;
import menu.scoreButtonListener;
import scores.Score;

public class BoardInterface {
	final JButton[][] tiles;
	private int rows;
	private int columns;
	private JButton scores;
	private JButton quit;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JLabel player1label;
	private JLabel player2label;
	private JLabel turnlabel;
	private JLabel scoreplayer1;
	private JLabel scoreplayer2;
	private JLabel turnplayer;
	private JLabel blank1;
	private JLabel blank2;
	private static JFrame frame;
	private Board board;

	Player player1 = new Player("Player 1", true);// turn of player if argument is true
	Player player2 = new Player("Player 2", false);

	/*
	 * two options for scoring systems: one which assigns the same points-per-hit to
	 * each player(false), and one which provides a unique points-per-hit to each
	 * player to accommodate for going first or second to start the game(true).
	 * Fourth, the placement of the ships on the board should either be determined
	 * randomly(true) (important: each tile on the board can only be used once), or
	 * through a user-provided text file(false).
	 */
	public BoardInterface(int r, int c, boolean notEqualScore, boolean randomShips) {

		if (randomShips) {

			board = new Board(r, c);

			this.tiles = new JButton[r][c];
			this.rows = r;
			this.columns = c;
		} else {

			try {
				board = new BoardFile(r, c);

			} catch (IOException e) {

				JOptionPane.showMessageDialog(frame, "text file doesn't exist ", "Warning!",
						JOptionPane.WARNING_MESSAGE);
				System.exit(0);// terminate application
			} catch (Exception e) {

				JOptionPane.showMessageDialog(frame, "text file has errors ", "Warning!", JOptionPane.WARNING_MESSAGE);
				System.exit(0);// terminate application
			}

			int dimension = board.getSizeX();
			this.tiles = new JButton[dimension][dimension];

			this.rows = dimension;
			this.columns = dimension;
		}

		frame = new JFrame("Battleship");

		player1label = new JLabel("Player 1 Score:  ", JLabel.RIGHT);
		player2label = new JLabel("Player 2 Score:  ", JLabel.LEFT);
		turnlabel = new JLabel("Turn", JLabel.CENTER);
		scoreplayer1 = new JLabel(Integer.toString(player1.getScore()), JLabel.RIGHT);
		scoreplayer2 = new JLabel(Integer.toString(player2.getScore()), JLabel.LEFT);
		turnplayer = new JLabel(player1.getPlayer(), JLabel.CENTER);
		blank1 = new JLabel("");
		blank2 = new JLabel("");

		scores = new JButton("High scores");
		scores.addActionListener(new scoreButtonListener());
		quit = new JButton("Quit game");
		quit.addActionListener(new exitButtonListener(frame));

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();

		p1.add(scores);
		p1.add(player1label);
		p1.add(turnlabel);
		p1.add(player2label);
		p1.add(quit);
		p1.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
		p1.setLayout(new GridLayout(1, 5));

		p2.add(blank1);
		p2.add(scoreplayer1);
		p2.add(turnplayer);
		p2.add(scoreplayer2);
		p2.add(blank2);
		p2.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
		p2.setLayout(new GridLayout(1, 5));

		// layout for the board
		p3.setLayout(new GridLayout(rows, columns));

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				tiles[i][j] = new JButton();
				tiles[i][j].setBackground(Color.GRAY);
				tiles[i][j].setForeground(Color.WHITE);

				p3.add(tiles[i][j]);
				tiles[i][j].addActionListener(new ClickTilesButtonListener1(notEqualScore));// pass argument of scoring
				// systems

			}

		}

		frame.setLayout(new GridLayout(3, 1));
		frame.add(p1);
		frame.add(p2);
		frame.add(p3);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(700, 700);
		frame.setVisible(true);
	}

	public static void points(Player player1, Player player2, int points, boolean notEqualScore) {
		if (player1.isTurn()) {
			player1.setScore(player1.getScore() + points);
		} else if (notEqualScore) {
			player2.setScore(player2.getScore() + points + 5);// (unique points-per-hit )For every ships that player 2
			// hits, 5 extra points are added.
		} else {
			player2.setScore(player2.getScore() + points);
		}

	}

	private class ClickTilesButtonListener1 implements ActionListener {

		boolean notEqualScore;

		public ClickTilesButtonListener1(boolean notEqualScore) {
			this.notEqualScore = notEqualScore;
		}

		// distinguish which button you have clicked
		@Override
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (tiles[i][j] == e.getSource()) {
						isHit(i, j, notEqualScore);
					}
				}
			}

		}

	}

	public void isHit(int x, int y, boolean notEqualScore) {

		// Coordinate of tile that has been clicked.

		ShipType shiptype1;
		int pointForShip = 0;

		for (Ship ship : board.getShips()) {

			for (Coordinate coordinate1 : ship.getBattleShip()) {
				if (coordinate1.getX() == x && coordinate1.getY() == y
						&& coordinate1.getCoordinateValue().equals(CoordinateValue.SHIP)) {

					switch (coordinate1.getCoordinateValue()) {
					case SHIP:
						coordinate1.setCoordinateValue(CoordinateValue.HIT);
						board.getCoordinate(x, y).setCoordinateValue(CoordinateValue.HIT);

						shiptype1 = ship.getShiptype();
						pointForShip = shiptype1.getScore();

						ship.getBattleShip().remove(coordinate1);
						if (ship.getBattleShip().isEmpty()) {
							pointForShip *= 2;
							board.getShips().remove(ship);

						}

						switch (shiptype1) {
						case CARRIER:
							tiles[x][y].setBackground(Color.green);
							break;

						case BATTLESHIP:
							tiles[x][y].setBackground(Color.red);
							break;
						case SUBMARINE:
							tiles[x][y].setBackground(Color.yellow);
							break;
						case DESTROYER:
							tiles[x][y].setBackground(Color.black);
							break;
						default:
							throw new IllegalArgumentException("Unexpected value: ");
						}
						break;

					case HIT:
						coordinate1.setCoordinateValue(CoordinateValue.HIT);
						board.getCoordinate(x, y).setCoordinateValue(CoordinateValue.CLICKED);
						break;

					default:
						throw new IllegalArgumentException("Unexpected value: " + coordinate1.getCoordinateValue());

					}
				}

				if (pointForShip != 0) {
					break;
				}

			}

			if (pointForShip != 0) {
				break;
			}

		}

		CoordinateValue coordinatevalue = board.getCoordinate(x, y).getCoordinateValue();

		switch (coordinatevalue) {
		case HIT:
			points(player1, player2, pointForShip, notEqualScore);
			break;
		case CLICKED:
			// Nothing happens to the tiles that have already been clicked.
			return;

		case WATER:
			tiles[x][y].setBackground(Color.blue);
			board.getCoordinate(x, y).setCoordinateValue(CoordinateValue.CLICKED);
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}

		player1.setTurn(player1.turnChange());

		String scorePlayer1 = Integer.toString(player1.getScore());
		String scorePlayer2 = Integer.toString(player2.getScore());

		scoreplayer1.setText(scorePlayer1);
		scoreplayer2.setText(scorePlayer2);
		new Score(scorePlayer1, scorePlayer2);// Control if we can put these score in the top 10 best score

		if (board.getShips().isEmpty()) {
			String winner;
			if (player1.getScore() > player2.getScore()) {
				winner = player1.getPlayer();

			} else if (player1.getScore() < player2.getScore()) {
				winner = player2.getPlayer();
			} else {
				winner = "draw";
			}
			System.out.println("Winner:" + winner);
			frame.dispose();
			JOptionPane.showMessageDialog(frame, "Winner:" + winner, "game ends", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		if (player1.isTurn()) {
			turnplayer.setText(player1.getPlayer());

		} else {
			turnplayer.setText(player2.getPlayer());

		}

	}

}
