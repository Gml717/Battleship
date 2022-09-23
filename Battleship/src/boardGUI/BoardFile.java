package boardGUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardFile extends Board {

	private int sizeX;
	private int sizeY;
	private Coordinate[][] board;
	private String[] splittedLine;
	private String shipName;
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	private Ship ship1;
	private Ship ship2;
	private Ship ship3;
	private Ship ship4;

	public BoardFile(int x, int y) throws IOException, Exception {
		super(x, y);
		board = readFileToCreateGameBoard();
	}

	public int getSizeX() {
		return sizeX;
	}

	public Coordinate getCoordinate(int x, int y) {
		return board[x][y];
	}

	public ArrayList<Ship> getShips() {
		return ships;
	}

	public Coordinate[][] readFileToCreateGameBoard() throws Exception, IOException {
		try (BufferedReader in = new BufferedReader(new FileReader("src/boardGUI/output.txt"));) {
			String line;
			int x = 1; // used to select first line of file.
			while ((line = in.readLine()) != null) {
				splittedLine = line.split(";");
				if (x == 1) {

					int y = Integer.parseInt(splittedLine[0]);

					sizeX = y;
					sizeY = y;

					// Dimension of board must be between 7 and 9
					if (6 < y && y < 10) {
						createGameBoard(sizeX, sizeY);
						x--;
					} else {
						throw new Exception("text file has errors , Warning!");
					}

				} else if (splittedLine[0].equals("Carrier")) {

					shipOnGameBoard(board);

				} else if (splittedLine[0].equals("Battleship")) {

					shipOnGameBoard(board);

				} else if (splittedLine[0].equals("Submarine")) {

					shipOnGameBoard(board);

				} else if (splittedLine[0].equals("Destroyer")) {

					shipOnGameBoard(board);

				} else {
					throw new Exception("text file has errors , Warning!");

				}
			}
		} catch (IOException e) {
			throw new IOException("text file doesn't exist, Warning!");

		} catch (Exception e) {
			throw new Exception("text file has errors , Warning!");
		}

		ships.add(ship1);
		ships.add(ship2);
		ships.add(ship3);
		ships.add(ship4);
		return board;
	}

	@Override
	public Coordinate[][] createGameBoard(int x, int y) {
		board = new Coordinate[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				board[i][j] = new Coordinate(i, j, CoordinateValue.WATER); // Each coordinate correspond with a default
				// // value of water.

			}

		}
		return board;
	}

	@Override
	public Coordinate[][] shipOnGameBoard(Coordinate[][] board2) {
		
		shipName = splittedLine[0];

		String[] subset = Arrays.copyOfRange(splittedLine, 1, splittedLine.length);
		
		ShipType shiptype;
		if (shipName.equals("Carrier")) {
			shiptype = ShipType.CARRIER;
			ship1 = new Ship(new ArrayList<Coordinate>(), shiptype);

		} else if (shipName.equals("Battleship")) {
			shiptype = ShipType.BATTLESHIP;
			ship2 = new Ship(new ArrayList<Coordinate>(), shiptype);

		} else if (shipName.equals("Submarine")) {
			shiptype = ShipType.SUBMARINE;
			ship3 = new Ship(new ArrayList<Coordinate>(), shiptype);

		} else {
			shiptype = ShipType.DESTROYER;
			ship4 = new Ship(new ArrayList<Coordinate>(), shiptype);

		}

		try {

			for (int i = 0; i < subset.length; i++) {

				String coordinateFormatFile = subset[i];

				// A coordinate is specified by two numbers separated by a “*”.
				// With the first number representing the row and the second number representing
				// the column.
				String[] coordinate = coordinateFormatFile.split("\\*");

				int x = Integer.parseInt(coordinate[0]) - 1;// Subtract by one because index start at zero
				int y = Integer.parseInt(coordinate[1]) - 1;// Subtract by one because index start at zero

				int gameBoardRow = sizeX;
				int gameBoardColumn = sizeY;

				// Check if ships don't have the same coordinate or coordinate that are out of
				// bounds if true error message.
				// Assign tiles/coordinate to a specific ship type/number.
				// Only a maximum of 18 ship can be added from the file.

				CoordinateValue coordinatevalue = board[x][y].getCoordinateValue();

				if (coordinatevalue.equals(CoordinateValue.WATER) && x < gameBoardRow && y < gameBoardColumn) {

					if (shipName.equals("Carrier")) {
						ship1.add(new Coordinate(x, y, CoordinateValue.SHIP));

					} else if (shipName.equals("Battleship")) {
						ship2.add(new Coordinate(x, y, CoordinateValue.SHIP));

					} else if (shipName.equals("Submarine")) {
						ship3.add(new Coordinate(x, y, CoordinateValue.SHIP));

					} else {
						ship4.add(new Coordinate(x, y, CoordinateValue.SHIP));

					}

				} else {
					// this else statement can be removed,error handling is done in the
					// readfiletocreategameboard method
				}

			}

			return board;
		} catch (Exception e) {

			// this else statement can be removed,error handling is done in the
			// readfiletocreategameboard method
			return null; // return statement is needed
		}

	}

}
