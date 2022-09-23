package boardGUI;

public class Coordinate {
	private int x;
	private int y;
	private CoordinateValue coordinateValue;

	public Coordinate(int x, int y, CoordinateValue coordinateValue) {
		super();
		this.x = x;
		this.y = y;
		this.coordinateValue = coordinateValue;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setCoordinateValue(CoordinateValue coordinateValue) {
		this.coordinateValue = coordinateValue;
	}

	public CoordinateValue getCoordinateValue() {
		return coordinateValue;
	}

}
