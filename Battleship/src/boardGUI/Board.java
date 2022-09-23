package boardGUI;

import java.util.ArrayList;
import java.util.Random;

public class Board implements GameBoard {
	private int sizeX;
	private int sizeY;
	private Coordinate[][] board;
	private ArrayList<Integer> listX = new ArrayList<Integer>();
	private ArrayList<Integer> listY = new ArrayList<Integer>();

	private ArrayList<Ship> ships = new ArrayList<Ship>();

	public Board(int sizeX, int sizeY) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		board = createGameBoard(sizeX, sizeY);
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public Coordinate[][] getBoard() {
		return board;
	}

	public Coordinate getCoordinate(int x, int y) {
		return board[x][y];
	}

	public ArrayList<Ship> getShips() {
		return ships;
	}
	
	@Override
	public Coordinate[][] createGameBoard(int x, int y) {
		board = new Coordinate[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				board[i][j] = new Coordinate(i, j, CoordinateValue.WATER); // Each coordinate correspond with a default
				// value of water.

			}

		}
		return shipOnGameBoard(board);
	}

	@Override
	public Coordinate[][] shipOnGameBoard(Coordinate[][] board2) {

		int shipnumber = 0;

		ShipType shiptype = ShipType.CARRIER;

		while (shipnumber <= 3) {
			generateRandomShipCoordinate(sizeX, sizeY, shiptype);

			shipnumber++;

			switch (shipnumber) {
			case 1:
				shiptype = ShipType.BATTLESHIP;
				break;
			case 2:
				shiptype = ShipType.SUBMARINE;
				break;
			case 3:
				shiptype = ShipType.DESTROYER;
				break;
			default:

			}

			// Check that we don't assign ships with the same coordinates.
			// Assign tiles/coordinate to a specific ship type/number.
			// number of ship types that will be added is predefined (4).

		}

		return board2;
	}

	private void generateRandomShipCoordinate(int sizeX2, int sizeY2, ShipType shiptype) {

		boolean coordinateOK;

		do {

			int x = 0;
			int y = 0;

			boolean creatingCoordinates = true;

			while (creatingCoordinates) {

				for (int i = 0; i < 1; i++) {

					x = new Random().nextInt(sizeX2); // generate a random x-coordinate, min(0)--max(row - 1)

					y = new Random().nextInt(sizeY2); // generate a random y-coordinate, min(0)--max(column - 1)
				}

				if (listX.contains(x) || y >= 4) {
					continue;

				}

				creatingCoordinates = false;

			}
			listX.add(x);
			listY.add(y);

			//
			ships.add(buildShip(new Coordinate(x, y, CoordinateValue.SHIP),
					new Ship(new ArrayList<Coordinate>(), shiptype)));

			coordinateOK = false;

		} while (coordinateOK);

	}

	public Ship buildShip(Coordinate coordinate, Ship battleShip) {

		int x;
		int y;
		switch (battleShip.getShiptype().getShipSize()) {
		case 2:

			battleShip.add(coordinate);
			x = coordinate.getX();
			y = coordinate.getY();
			battleShip.add(new Coordinate(x, y + 1, CoordinateValue.SHIP));
			listY.add(y + 1);

			break;
		case 3:

			battleShip.add(coordinate);
			x = coordinate.getX();
			y = coordinate.getY();
			battleShip.add(new Coordinate(x, y + 1, CoordinateValue.SHIP));
			listY.add(y + 1);
			battleShip.add(new Coordinate(x, y + 2, CoordinateValue.SHIP));
			listY.add(y + 2);
			break;
		case 4:

			battleShip.add(coordinate);
			x = coordinate.getX();
			y = coordinate.getY();
			battleShip.add(new Coordinate(x, y + 1, CoordinateValue.SHIP));
			listY.add(y + 1);
			battleShip.add(new Coordinate(x, y + 2, CoordinateValue.SHIP));
			listY.add(y + 2);
			battleShip.add(new Coordinate(x, y + 3, CoordinateValue.SHIP));
			listY.add(y + 3);

			break;

		case 5:

			battleShip.add(coordinate);
			x = coordinate.getX();
			y = coordinate.getY();
			battleShip.add(new Coordinate(x, y + 1, CoordinateValue.SHIP));
			listY.add(y + 1);
			battleShip.add(new Coordinate(x, y + 2, CoordinateValue.SHIP));
			listY.add(y + 2);
			battleShip.add(new Coordinate(x, y + 3, CoordinateValue.SHIP));
			listY.add(y + 3);
			battleShip.add(new Coordinate(x, y + 4, CoordinateValue.SHIP));
			listY.add(y + 4);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + battleShip.getShiptype());
		}
		return battleShip;
	}

}