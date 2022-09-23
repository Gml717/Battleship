package boardGUI;

public interface GameBoard {
	public Coordinate[][] createGameBoard(int x, int y);

	public Coordinate[][] shipOnGameBoard(Coordinate[][] board2);

}
