package boardGUI;

import java.util.ArrayList;

public class Ship {

	private ArrayList<Coordinate> battleShip;
	private ShipType shiptype;

	public Ship(ArrayList<Coordinate> battleShip, ShipType shiptype) {
		super();
		this.battleShip = battleShip;
		this.shiptype = shiptype;
	}

	public ArrayList<Coordinate> getBattleShip() {
		return battleShip;
	}

	public ShipType getShiptype() {
		return shiptype;
	}

	public void add(Coordinate coordinateValue) {
		this.battleShip.add(coordinateValue);
	}

}
