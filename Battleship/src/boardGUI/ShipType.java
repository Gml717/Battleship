package boardGUI;

public enum ShipType {

	CARRIER(5, 10), BATTLESHIP(4, 25), SUBMARINE(3, 35), DESTROYER(2, 40);

	private int shipSize;
	// length of ships

	private int score;
	// points that you get for hittung a ship

	private ShipType(int shipSize, int score) {
		this.shipSize = shipSize;

		this.score = score;
	}

	public int getShipSize() {
		return shipSize;
	}

	public int getScore() {
		return score;
	}
	
	
	

}
