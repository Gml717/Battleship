package boardGUI;

public class Player {

	private String player;
	private boolean turn;
	private int score;

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public boolean turnChange() {
		if (this.turn) {
			return false;
		} else {
			return true;
		}

	}

	public String getPlayer() {
		return player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isTurn() {
		return turn;
	}

	public Player(String player, boolean turn) {
		this.player = player;
		this.turn = turn;

	}

}
